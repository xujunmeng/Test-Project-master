package com.csf.cloud.service.announce;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aug3.storage.passage.client.PassageClient;
import com.aug3.storage.passage.client.action.ListKeysAction;
import com.aug3.storage.passage.client.action.PutObjectAction;
import com.aug3.storage.passage.thrift.SObject;
import com.aug3.storage.passage.thrift.Storage;
import com.aug3.storage.passage.thrift.Strategy;
import com.aug3.storage.passage.util.LogUtils;
import com.aug3.sys.util.CommonHttpUtils;
import com.aug3.sys.util.DateUtil;
import com.aug3.sys.util.MD5;
import com.csf.app.base.BaseConstants;
import com.csf.app.config.ConfigManager;
import com.csf.app.dal.dto.dict.DictAnnounceRule;
import com.csf.cloud.common.Constants;
import com.csf.cloud.common.DateUtils;
import com.csf.cloud.dao.juchao.announce.TbText0042Dao;
import com.csf.cloud.dao.juchao.announce.TbText0043Dao;
import com.csf.cloud.dao.juchao.announce.TbText0047Dao;
import com.csf.cloud.dao.mongo.announce.AnnouncementStockDao;
import com.csf.cloud.dao.mongo.announce.DictAnnounceCatalogDao;
import com.csf.cloud.dao.mongo.announce.DictAnnounceRuleDao;
import com.csf.cloud.dao.mongo.base.BaseBondDao;
import com.csf.cloud.dao.mongo.base.BaseDictMarketDao;
import com.csf.cloud.dao.mongo.base.BaseStockDao;
import com.csf.cloud.entity.juchao.announce.TbText0042;
import com.csf.cloud.entity.juchao.announce.TbText0047;
import com.csf.cloud.entity.mongo.announce.AnnouncementStock;
import com.csf.cloud.entity.mongo.announce.AnnouncementTemp;
import com.csf.cloud.entity.mongo.base.BaseBond;
import com.csf.cloud.entity.mongo.base.BaseDictMarket;
import com.csf.cloud.entity.mongo.base.BaseNewsCat;
import com.csf.cloud.entity.mongo.base.BaseStock;
import com.csf.cloud.service.JobParameter;
import com.csf.cloud.service.help.IPdfFileNameCreator;
import com.csf.cloud.service.help.PdfFileNameCreator;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;
import com.itextpdf.text.pdf.PdfReader;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class AnnounceStock {

    private static final Logger log = LoggerFactory.getLogger(AnnounceStock.class);

    private static final Cache<String, Set<String>> SID_CACHE = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.MINUTES).build();

    private static final Cache<String, Map<Integer, List<DictAnnounceRule>>>  dictAnnounceRuleCache = CacheBuilder.newBuilder().expireAfterWrite(6, TimeUnit.HOURS).build();

    private static final Cache<String, Map<String, BaseDictMarket>>  baseDictMarketCache = CacheBuilder.newBuilder().expireAfterWrite(6, TimeUnit.HOURS).build();

    private static final Cache<String, Map<String, BaseNewsCat>>  baseNewsCatCache = CacheBuilder.newBuilder().expireAfterWrite(6, TimeUnit.HOURS).build();

    Map<String, BaseNewsCat> announceCatMap = new HashMap<>();

    Map<String, BaseBond> baseBondMap = new HashMap<>();

    private Map<String, BaseDictMarket> baseDictMktMap;
    
    private final Calendar cal = Calendar.getInstance();
    
    private PassageClient passageClient = new PassageClient(Constants.PASSAGE_SERVER_HOST_S3_CN, Constants.PASSAGE_SERVER_port_S3_CN);

    private Set<String> coveredCatSet;

    private IPdfFileNameCreator nameCreator;

    Map<String, BaseStock> baseStockMap = new HashMap<>();

    Map<Integer, List<DictAnnounceRule>> ruleMap = new HashMap<>();

    private final Random random = new Random();

    protected TbText0042Dao tbText0042Dao;

    protected AnnouncementStockDao announcementStockDao;

    protected TbText0043Dao tbText0043Dao;

    protected DictAnnounceRuleDao dictAnnounceRuleDao;

    protected BaseDictMarketDao baseDictMarketDao;

    protected TbText0047Dao tbText0047Dao;

    protected BaseStockDao baseStockDao;

    protected BaseBondDao baseBondDao;

    protected DictAnnounceCatalogDao dictAnnounceCatalogDao;

    private String url = ConfigManager.getConfigProperties().getProperty("announcement.index.url");

    protected Set<String> getSidsFromJuchao(Date fromDate, Date toDate) {
        return tbText0042Dao.getSidsFromJuchao(fromDate, toDate);
    }

    protected Set<String> removeExistsData(Date fromDate, Date toDate,
                                           Set<String> sids) {
        return removeExistsSid(fromDate, toDate, sids);
    }

    protected List<TbText0042> getEntitysBySids(Collection<String> sids) {
        return tbText0042Dao.getEntityBySids(sids);
    }

    protected int saveDataToCsf(List<TbText0042> juchaoEntities) {
        return dealJuchaoEntitySaveToCsf(juchaoEntities);
    }

    public Set<String> removeExistsSid(final Date from, final Date to, Set<String> ids) {
        Set<String> sidCache = new HashSet<>();
        try {
            sidCache = SID_CACHE.get(from.toString() + to.toString(),
                    () -> {
                        return announcementStockDao.getSidsByDate(from, to);
                    });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Sets.newHashSet(Sets.difference(ids, sidCache));
    }

    private int dealJuchaoEntitySaveToCsf(List<TbText0042> juchaoEntities){
        Map<String, AnnouncementTemp> announcementTemps = new HashMap<>();
        String pid = Long.toString(System.currentTimeMillis()) + "." + random.nextInt(100);
        List<AnnouncementStock> anns = new ArrayList<>();
        for (TbText0042 entity : juchaoEntities) {
            String sid = entity.getObTextid0042();
            AnnouncementStock ann = new AnnouncementStock();
            // 不存在该公司,跳过
            String ticks = entity.getObSeccode0045();
            if (Strings.isNullOrEmpty(ticks)) continue;
            //处理公告分类
            String sortcode = handlerAnnounceCat(sid, ann);
            //判断该公告是否是CSF覆盖，不导入不cover的公告
            if(!CollectionUtils.containsAny(coveredCatSet, ann.getCat())) continue;
            boolean isPeriodicReport = nameCreator.isPeriodicReport(sortcode); //是否是定期报告，如果是需要规范化文件名称
            String tickAForPeriodic = null; //只为A股公司规范化定期报告文件，同一份定期报告只可能有一家A股公司
            boolean isSpecialName = false;
            for(String tick : ticks.split(BaseConstants.COMMA)){
                BaseStock stock = baseStockMap.get(tick);
                if (stock != null) {
                    AnnouncementStock.AnnounceSecu annSecu = ann.new AnnounceSecu();
                    annSecu.setCd(stock.getCode());
                    annSecu.setMkt(stock.getMkt().getCode());
                    annSecu.setOrg(stock.getOrg().getId());
                    ann.addSecu(annSecu);
                    tickAForPeriodic = tick;
                    if(isPeriodicReport && nameCreator.isSepcialName(ann, tick, baseStockMap)){
                        isSpecialName = true;
                        break;
                    }
                }
            }
            if (tickAForPeriodic == null) continue;
            String title = entity.getF004v0042();
            if (null == title) continue;
            ann.setTitle(title);
            ann.setSid(sid);
            ann.setTyp(updateAnnounceType(title, ruleMap));
            ann.setStat(Constants.STATUS_UNPUBLISHED_INT);
            ann.setPid(pid);
            Timestamp pdt = handlerPdt(entity, ann);
            Timestamp pub;
            pub = entity.getF003d0042();
            if (pub != null) ann.setPub(DateUtil.formatDate(new Date(pub.getTime())));
            Date crt = DateUtils.getCNNow();
            ann.setValid(entity.getObIsvalid0042());
            ann.setUpt(crt);
            ann.setCrt(crt);
            ann.setSrc(Constants.SRC_JUCHAO);
            ann.setCru(Constants.SYSTEM_USER);
            ann.setUpu(Constants.SYSTEM_USER);
            anns.add(ann);
            AnnouncementTemp announcementTemp = handlerAnnouncementTemp(
                    entity, pdt, sortcode, tickAForPeriodic,
                    isSpecialName);
            announcementTemps.put(sid, announcementTemp);
        }
        int saveAnnounceCount = 0;
        int saveAnnounceFile = 0;
        if(null != anns && !anns.isEmpty()){
            saveAnnounceCount = announcementStockDao.saveAnnouncementStock(anns);
            List<AnnouncementStock> announcementStocks;
            announcementStocks = uploadFile(announcementTemps, nameCreator, baseStockMap);
            if(null != announcementStocks && !announcementStocks.isEmpty()){
                saveAnnounceFile = announcementStockDao.saveAnnouncementStockFile(announcementStocks);
                if(!Constants.ENV_MGT.contains(Constants.ENV)){
                    String res = CommonHttpUtils.executeGetMothedRequest(url + "indexer=announce_ss&pid=" + pid);
                    String res2 = CommonHttpUtils.executeGetMothedRequest(url + "indexer=announce_otc&pid=" + pid);
                    log.info("executeUrl[indexer for announce_ss],url=" + url + "pid="+pid+":"+res);
                    log.info("executeUrl[indexer for announce_otc],url=" + url + "pid="+pid+":"+res2);
                }
            }
            log.info( " saveAnnounceCount : " + saveAnnounceCount + "," + " saveAnnounceFile : " + saveAnnounceFile);
        }
        return saveAnnounceCount;
    }

    public List<AnnouncementStock> uploadFile(Map<String, AnnouncementTemp> announcementTemps, IPdfFileNameCreator nameCreator, Map<String, BaseStock> baseStockMap){
        List<String> sids = new ArrayList<>();
        Iterator<Map.Entry<String, AnnouncementTemp>> iterator = announcementTemps.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, AnnouncementTemp> next = iterator.next();
            sids.add(next.getKey());
        }
        List<AnnouncementStock> announcementStocks = new ArrayList<>();
        
        AnnouncementStock announcementStock = null;
        
        List<TbText0042> result = tbText0042Dao.getFileBySids(sids);
        
        byte[] content = null;
        
        for (TbText0042 obj : result) {
			String title = obj.getF004v0042();
			String sid = obj.getObTextid0042();
			String downLoadUrl = obj.getF006b0042();
			String ext = obj.getF008v0042();
			Date pdt = new Date(obj.getF002d0042().getTime());
			cal.setTime(pdt);
			try {
				URL url = new URL(downLoadUrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				//设置超时间为5秒
				conn.setConnectTimeout(5*1000);
				//防止屏蔽程序抓取而返回403错误
				conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				//获取自己数组
				content = readInputStream(conn.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
            PdfReader pdfReader = null;
            int pn = 0;
            try {
                if(StringUtils.equals("pdf", ext.toLowerCase())){
                    pdfReader = new PdfReader(content);
                    pn = pdfReader.getNumberOfPages();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (pdfReader != null) pdfReader.close();
            }
            AnnouncementTemp announcementTemp = announcementTemps.get(sid);
            announcementStock = new AnnouncementStock();
            AnnouncementStock.AnnounceFile fileObj = new AnnouncementStock.AnnounceFile();
            fileObj.setPn(pn);
            fileObj.setExt(ext == null ? "" : ext.toLowerCase());
            if(null != content){
                setSectionFileObj(content,fileObj,pdt,announcementTemp,nameCreator,baseStockMap);
                writerFile(fileObj,content,sid);
                content = null;
            }else{
                LogUtils.writeLog("datashift_announcement_stock", "lostContent", sid);
            }
            announcementStock.setFile(fileObj);
            announcementStock.setSid(sid);
            announcementStock.setTitle(title);
            announcementStocks.add(announcementStock);
		}
        return announcementStocks;
    }
    
    private void writerFile(AnnouncementStock.AnnounceFile fileObj , byte[] content,String sid){
        String fileAddress = "/test" + fileObj.getUrl() + fileObj.getFn() + "." + fileObj.getExt();
        byte[] data = null;
        try {
            data = content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Strategy strategy = new Strategy();
        strategy.setBucketName("cn.com.chinascope.dfs");
        strategy.setSType(Storage.S3CN);
        SObject sObj = new SObject();
        sObj.setKey(fileAddress);
        sObj.setData(data);
        ListKeysAction listKeysAction = new ListKeysAction();
        listKeysAction.setKey(fileAddress);
        listKeysAction.setStrategy(strategy);
        @SuppressWarnings("unchecked")
		List<String> list = (List<String>)passageClient.perform(listKeysAction);
        if(ObjectUtils.equals(null, list) || !list.contains(getUrl(fileAddress))){
            PutObjectAction putAction = new PutObjectAction();
            putAction.setStrategy(strategy);
            putAction.setsObj(sObj);
            passageClient.perform(putAction);
        }
        data = null;
    }
    
    private String getUrl(String url){
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        return url;
    }
    
    private void setSectionFileObj(byte[] content,AnnouncementStock.AnnounceFile fileObj,Date pdt,AnnouncementTemp announcementTemp, IPdfFileNameCreator nameCreator, Map<String, BaseStock> baseStockMap){
        try {
            String tickAForPeriodic = announcementTemp.getTickAForPeriodic();
            Boolean isSpecialName = announcementTemp.getIsSpecialName();

            AnnouncementStock ann = new AnnouncementStock();
            ann.setSid(announcementTemp.getAnnSid());
            ann.setTitle(announcementTemp.getAnnTitle());
            ann.setSortcode(announcementTemp.getAnnSortcode());
            ann.setPdt(announcementTemp.getAnnPdt());

            String md5 = MD5.getMD5Str(new ByteArrayInputStream(content));
            fileObj.setBytes((long)content.length);
            fileObj.setMd5(md5);
            fileObj.setFn(nameCreator.createFileName(ann, tickAForPeriodic, baseStockMap, md5));

            fileObj.setUrl(getPath(isSpecialName, fileObj.getFn()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getPath(boolean isSpecialName, String fn) {
        return isSpecialName
                ? "announce/cn/finrpts/file/" + fn.substring(0, fn.indexOf("_")) + "/"
                : "/announce/cn/"
                + cal.get(Calendar.YEAR) + "" + formatURLDate(cal.get(Calendar.MONTH) + 1) + ""
                + formatURLDate(cal.get(Calendar.DAY_OF_MONTH)) + "/";
    }
    
    private String formatURLDate(int i) {
        return (i > 0 && i < 10) ? "0" + i : Integer.toString(i);
    }
    
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[2048];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        inputStream.close();
        return bos.toByteArray();
    }
    
    private String handlerAnnounceCat(String sid, AnnouncementStock ann) {
        String sortcode = tbText0043Dao.getSortCodeIds(sid);
        if(StringUtils.isNotEmpty(sortcode)){
            for(String cat : sortcode.split(BaseConstants.COMMA)){
                BaseNewsCat newsCat = announceCatMap.get(removeFirstZero(cat));
                if(newsCat != null){
                    ann.addCat(newsCat.getCd());
                }
            }
        }
        return sortcode;
    }

    private String removeFirstZero(String str) {
        if (str != null && !"".equals(str)) {
            while (true) {
                if (str.charAt(0) == '0') {
                    str = str.substring(1);
                } else {
                    break;
                }
            }
        }
        return str;
    }

    /**
     * 更新announce的typ，
     * 匹配规则：
     * 具体为:先取type为1的,获得所有满足条件的code,然后选取rule字段中字符最长的code.
     * 如果全部不满足,取type为2的.按照id号正序排列.如果满足条件,则选取该code.
     * 具体匹配规则为:
     * dict_announce_rule中的rule字段.与announcement中title字段进行正则表达式匹配.
     * “_”为任意字符,
     * 注:在计算长度时,不考虑“_”的长度.
     * 最后，将announcement中的typ的值等于code值
     * @param title
     * @param ruleMap
     */
    private String updateAnnounceType(String title,
                                      Map<Integer, List<DictAnnounceRule>> ruleMap) {
        // rule.type = 1
        List<DictAnnounceRule> rules_1 = ruleMap.get(1);
        List<DictAnnounceRule> matchRules = new ArrayList<>();
        String code = null;
        for (DictAnnounceRule announceRule : rules_1) {
            String rule = announceRule.getRule();
            rule = rule.replace(BaseConstants.UNDERSCORE, ".*");
            if (rule.contains("(")) {
                rule = rule.replace("(", "\\(");
            }
            if (rule.contains(")")) {
                rule = rule.replace(")", "\\)");
            }
            Pattern pat = Pattern.compile("^.*" + rule + ".*$");
            Matcher matcher = pat.matcher(title);
            boolean find = matcher.find();
            if (find) {
                matchRules.add(announceRule);
            }
        }

        if (matchRules.size() > 0) {
            //以size排序
            Collections.sort(matchRules, new Comparator<DictAnnounceRule>(){
                @Override
                public int compare(DictAnnounceRule arg0,
                                   DictAnnounceRule arg1) {
                    return arg1.getSize() - arg0.getSize();
                }
            });
            code = matchRules.get(0).getCode();
            return code;
        }

        //rule.type = 2
        List<DictAnnounceRule> rules_2 = ruleMap.get(2);
        for (DictAnnounceRule announceRule : rules_2) {
            String rule = announceRule.getRule();
            if (rule.contains("(")) {
                rule = rule.replace("(", "\\(");
            }
            if (rule.contains(")")) {
                rule = rule.replace(")", "\\)");
            }

            rule = rule.replace(BaseConstants.UNDERSCORE, ".*");
            Pattern pat = Pattern.compile("^.*" + rule + ".*$");
            Matcher matcher = pat.matcher(title);
            boolean find = matcher.find();
            if (find) {
                code = announceRule.getCode();
                break;
            }
        }
        return code;
    }

    private Timestamp handlerPdt(TbText0042 entity, AnnouncementStock ann) {
        Timestamp pdt = null;
        pdt = entity.getF002d0042();
        if (pdt != null) ann.setPdt(DateUtil.convertTimestampLocal2UTC(DateUtil.getISOTimeFormat().format(pdt)));
        return pdt;
    }

    private AnnouncementTemp handlerAnnouncementTemp(TbText0042 entity,
                                                     Timestamp pdt, String sortcode, String tickAForPeriodic,
                                                     boolean isSpecialName) {
        AnnouncementTemp announcementTemp = new AnnouncementTemp();
        announcementTemp.setAnnTitle(entity.getF004v0042());
        announcementTemp.setAnnSortcode(sortcode);
        announcementTemp.setAnnPdt(pdt);
        announcementTemp.setAnnSid(entity.getObTextid0042());
        announcementTemp.setTickAForPeriodic(tickAForPeriodic);
        announcementTemp.setIsSpecialName(isSpecialName);
        return announcementTemp;
    }

    public Map<Integer, List<DictAnnounceRule>> getDictAnnounceRule(){
        try {
            return dictAnnounceRuleCache.get("dict_announce_rule", new Callable<Map<Integer,List<DictAnnounceRule>>>() {

                @Override
                public Map<Integer, List<DictAnnounceRule>> call()
                        throws Exception {
                    return dictAnnounceRuleDao.getAnnounceRule();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public Map<String, BaseDictMarket> getBaseDictMarketMap(){
        try {
            return baseDictMarketCache.get("base_dict_market",new Callable<Map<String,BaseDictMarket>>(){
                @Override
                public Map<String, BaseDictMarket> call() throws Exception {
                    return baseDictMarketDao.getDictMap();
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public Map<String, BaseNewsCat> getBaseNewsCat(){
        try {
            return baseNewsCatCache.get("base_news_cat", new Callable<Map<String,BaseNewsCat>>() {
                @Override
                public Map<String, BaseNewsCat> call() throws Exception {
                    List<TbText0047> tbText0047s = tbText0047Dao.fetchJuchaoDatas();
                    List<BaseNewsCat> baseNewsCats2 = new ArrayList<>();
                    for (TbText0047 tbText0047 : tbText0047s) {
                        BaseNewsCat ns = new BaseNewsCat();
                        ns.setCd(removeFirstZero(tbText0047.getObSortcode0047()));
                        ns.setPcd(removeFirstZero(tbText0047.getObParentcode0047()));
                        ns.setEn("");
                        ns.setSzh(tbText0047.getObSortname0047());
                        Timestamp std = tbText0047.getF001d0047();
                        if(std != null) ns.setSdt(DateUtil.convertTimestampLocal2UTC(DateUtil.getISOTimeFormat().format(std)));
                        Timestamp etd = tbText0047.getF002d0047();
                        if(etd != null) ns.setEdt(DateUtil.convertTimestampLocal2UTC(DateUtil.getISOTimeFormat().format(etd)));
                        baseNewsCats2.add(ns);
                    }
                    for (BaseNewsCat baseNewsCat : baseNewsCats2) {
                        announceCatMap.put(baseNewsCat.getCd(), baseNewsCat);
                    }
                    return announceCatMap;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    protected void init() {

        ruleMap = getDictAnnounceRule();

        baseDictMktMap = getBaseDictMarketMap();

        announceCatMap = getBaseNewsCat();

        baseStockMap = baseStockDao.getBaseStockMap();

        baseBondMap = baseBondDao.getBaseBondMap();

        coveredCatSet = dictAnnounceCatalogDao.getCoveredAnnounceCat();

        nameCreator = new PdfFileNameCreator(baseDictMktMap);

    }

    protected JobParameter getPeriodDate(ConcurrentHashMap<String, Object> content) {
        JobParameter jobParameter = new JobParameter();
        if(ObjectUtils.equals(null, content)){
    		getPeriodTime(jobParameter);
        }else{
        	String from = (String)content.get("from");
        	String to = (String)content.get("to");
        	if((from == null) || (to == null)){
        		getPeriodTime(jobParameter);
        	}else{
        		jobParameter.setFrom(DateUtils.parseDate(from));
        		jobParameter.setTo(DateUtils.parseDate(to));
        	}
        		
        }
        return jobParameter;
    }

	private void getPeriodTime(JobParameter jobParameter) {
		Date today = DateUtils.getToday();
		jobParameter.setFrom(DateUtils.getPreOrNextDate(today, -2));
		jobParameter.setTo(DateUtils.getPreOrNextDate(today, 2));
	}

    public void setTbText0042Dao(TbText0042Dao tbText0042Dao) {
        this.tbText0042Dao = tbText0042Dao;
    }

    public void setTbText0043Dao(TbText0043Dao tbText0043Dao) {
        this.tbText0043Dao = tbText0043Dao;
    }

    public void setAnnouncementStockDao(AnnouncementStockDao announcementStockDao) {
        this.announcementStockDao = announcementStockDao;
    }

    public void setDictAnnounceRuleDao(DictAnnounceRuleDao dictAnnounceRuleDao) {
        this.dictAnnounceRuleDao = dictAnnounceRuleDao;
    }

    public void setBaseDictMarketDao(BaseDictMarketDao baseDictMarketDao) {
        this.baseDictMarketDao = baseDictMarketDao;
    }

    public void setTbText0047Dao(TbText0047Dao tbText0047Dao) {
        this.tbText0047Dao = tbText0047Dao;
    }

    public void setBaseStockDao(BaseStockDao baseStockDao) {
        this.baseStockDao = baseStockDao;
    }

    public void setBaseBondDao(BaseBondDao baseBondDao) {
        this.baseBondDao = baseBondDao;
    }

    public void setDictAnnounceCatalogDao(DictAnnounceCatalogDao dictAnnounceCatalogDao) {
        this.dictAnnounceCatalogDao = dictAnnounceCatalogDao;
    }


}
