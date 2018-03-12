package com.csf.ada.datashift.dao.news;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.aug3.storage.dbclient.Context;
import com.aug3.storage.dbclient.DataSourceFactory;
import com.aug3.sys.util.DateUtil;
import com.csf.ada.datashift.common.constants.Constants;
import com.csf.platform.dataentry.common.Parameter;
import com.csf.platform.dataentry.entity.news.NewsCat;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jolbox.bonecp.BoneCPDataSource;

public class AnnouncementTransportDao extends NewsAnnouncementTransportBaseDao {
    private static final String ANNOUNCEMENT_CAT_CODE = "0047";  // 公告分类编码表
    private static final String NEWS_CAT_CODE = "0031";  // 新闻分类编码表
    private static final Cache<String, Map<String, NewsCat>> newsCatMapCache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.HOURS).build();
    private static final Cache<String, Map<String, Stock>>   stockMapCache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.HOURS).build();
    private static final Cache<String, Map<String, Bond>>    bondMapCache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.HOURS).build();
    private static final Cache<String, Map<String, Fund>>    fundMapCache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.HOURS).build();
	
	protected void initMaps(){
        this.getNewsCatMap();
        this.getAnnounceCatMap();
        this.getStockMap();
        this.getBondMap();
        this.getFundMap();
	}
    public long countAll(Parameter p){
        Map<String, Object> params = new HashMap<>();
        params.put("from", p.getFrom());
        params.put("to", p.getTo());
        NamedParameterJdbcTemplate template = this.getOracleTemplate(p.getTo());
        if(Constants.TRANSPORT_COMPANY_HISTORY == p.getOpTyp()){
            params.put("org_sids", p.get(ParamConstant.ORG_SID_KEY));
            log.info("Import history announcements for orgSids: " + p.get(ParamConstant.ORG_SID_KEY));
            return template.queryForObject(adaptorSql(SQL_ANNOUNCE_COUNT_HISTORY, p.getTo()), params, Long.class);
        }else if (Constants.TRANSPORT_ANNOUNCEMENT_PERIOD == p.getOpTyp()){
            return template.queryForObject(adaptorSql(SQL_ANNOUNCE_COUNT_PERIOD, p.getTo()), params, Long.class);
        }
        return template.queryForObject(adaptorSql(SQL_ANNOUNCE_COUNT_DELTA, p.getTo()), params, Long.class);
    }
    public Map<String, NewsCat> getNewsCatMap() {
        try {
            return newsCatMapCache.get(NEWS_CAT_CODE, new Callable<Map<String, NewsCat>>() {
                @Override
                public Map<String, NewsCat> call(){
                    Map<String, NewsCat> catMap = null;
                    int retryCount = 5;
                    while (retryCount-->0){
                        try {
                            catMap = getNewsCatMap(NEWS_CAT_CODE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (catMap != null) break;
                        log.error("wait 1 min, retry...");
                        try {
                            TimeUnit.MINUTES.sleep(1);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    return catMap==null?new HashMap<String, NewsCat>():catMap;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
	@Override
	public void initNewOp(Date from , Date to , Parameter param){
		initAllOp();
	}
	@Override
	public void initAllOp(){
		this.initMaps();
		initDictMKt();
	}
	private static final String SQL_ANNOUNCE_ID_DELTA = "SELECT t4.ob_textid_0042 FROM (SELECT rownum AS rn, ob_textid_0042 FROM JUCHAO.tb_text_0042  WHERE ob_rectime_0042 >= :from and ob_rectime_0042 < :to) t4 WHERE rn between :rn_start and :rn_end";
    /**
     * 2013 年前的数据在juchao_ann
     */
    public String adaptorSql(String sql, Date to){
        String tempSql = sql.toLowerCase();
        if (to != null && Integer.valueOf(DateUtil.getYearOfDate(to)) < 2013){
            tempSql = tempSql.replace("juchao.tb_text_0021", "juchao_ann.tb_text_0021");
            tempSql = tempSql.replace("juchao.tb_text_0025", "juchao_ann.tb_text_0025");
            tempSql = tempSql.replace("juchao.tb_text_0042", "juchao_ann.tb_text_0042");
            tempSql = tempSql.replace("juchao.tb_text_0046", "juchao_ann.tb_text_0046");
            tempSql = tempSql.replace("juchao.tb_text_0048", "juchao_ann.tb_text_0048");
        }
        return tempSql;
    }

	public List<String> willImportAnnounce(Parameter p , int start , int end){
		Map<String , Object> params = new HashMap<String , Object>();
		params.put("from", p.getFrom());
		params.put("to", p.getTo());
		params.put("rn_start", start);
		params.put("rn_end", end);
		NamedParameterJdbcTemplate template = this.getOracleTemplate(p.getTo());
		return template.queryForList(adaptorSql(SQL_ANNOUNCE_ID_DELTA, p.getTo()) , params , String.class);
	}
    protected NamedParameterJdbcTemplate namedJdbcTemplate;
    public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        return namedJdbcTemplate;
    }

    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }
	//根据导入公告的时间返回不同的oracle
	public NamedParameterJdbcTemplate getOracleTemplate(Date to){
		if(to != null && Integer.valueOf(DateUtil.getYearOfDate(to)) < 2013){
			return getOldOracleJdbcTemplate();
		}
		return getNamedJdbcTemplate();
	}
    protected NamedParameterJdbcTemplate oldOracleJdbcTemplate;  // for announcement before 2013
	public NamedParameterJdbcTemplate getOldOracleJdbcTemplate(){
		if(this.oldOracleJdbcTemplate == null){
			Context context = new Context();
			context.setDbKey("juchao_ann");
			context.setDbType("oracle");
			DataSourceFactory factory = new DataSourceFactory();
			BoneCPDataSource ds = factory.getDataSource(context);
			this.oldOracleJdbcTemplate = new NamedParameterJdbcTemplate(ds);
		}
		return oldOracleJdbcTemplate;
	}
	
	
	
	
	
	
	
	
}
