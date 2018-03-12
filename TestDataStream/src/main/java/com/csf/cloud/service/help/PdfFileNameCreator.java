package com.csf.cloud.service.help;

import com.aug3.sys.util.MD5;
import com.csf.cloud.common.Constants;
import com.csf.cloud.entity.mongo.announce.AnnouncementStock;
import com.csf.cloud.entity.mongo.base.BaseDictMarket;
import com.csf.cloud.entity.mongo.base.BaseStock;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class PdfFileNameCreator implements IPdfFileNameCreator  {

    private final Calendar cal = Calendar.getInstance();

    private Map<String, BaseDictMarket> baseDictMarketMap;

    public PdfFileNameCreator(Map<String, BaseDictMarket> dictMktMap) {
        this.baseDictMarketMap = dictMktMap;
    }

    @Override
    public String createFileName(AnnouncementStock announce, String secu, Map<String, BaseStock> stockMap, String defaultName) {
        BaseStock stock = stockMap.get(secu);
        if (isSepcialName(announce, secu, stockMap)) {
            if (announce.getPdt() != null) {
                return createSpecialFileName(announce, stock);
            }
        }
        return createNormalFileName(announce, stock, defaultName);
    }

    private String createSpecialFileName(AnnouncementStock announce, BaseStock stock) {
        cal.setTime(announce.getPdt());
        BaseDictMarket mkt = baseDictMarketMap.get(stock.getMkt().getCode());
        if (mkt == null) {
            return "";
        }
        return getPeriodicRptName(stock.getTick(), Integer.valueOf(cal.get(Calendar.YEAR)), mkt.getAbbr(),
                announce.getSortcode());
    }

    private String createNormalFileName(AnnouncementStock announce, BaseStock stock, String defaultName) {
        return StringUtils.isBlank(defaultName) ? MD5.md5(announce.getTitle()) : defaultName;
    }

    private String getPeriodicRptName(String tick, int year, String mkt, String sortcode) {
        String fileName = null;

        if ("SH".equalsIgnoreCase(mkt)) {
            mkt = "SSE";
        } else if ("SZ".equalsIgnoreCase(mkt)) {
            mkt = "SZSE";
        }

        if (sortcode.contains("01030101")) {
            fileName = (year - 1) + "Q4_" + mkt + "_" + tick + "_FULL";
        } else if (sortcode.contains("01030301")) {
            fileName = year + "Q2_" + mkt + "_" + tick + "_FULL";
        } else if (sortcode.contains("01030501")) {
            fileName = year + "Q1_" + mkt + "_" + tick + "_FULL";
        } else if (sortcode.contains("01030701")) {
            fileName = year + "Q3_" + mkt + "_" + tick + "_FULL";
        } else if (sortcode.contains("01020301")) {
            fileName = "PROSP_" + mkt + "_" + tick + "_FULL";
        } else {
            fileName = "";
        }
        return fileName;
    }

    @Override
    public boolean isSepcialName(AnnouncementStock announce, String secu, Map<String, BaseStock> stockMap) {
        BaseStock stock = stockMap.get(secu);
        if (stock == null) {
            return false;
        }

        BaseDictMarket mkt = baseDictMarketMap.get(stock.getMkt().getCode());
        if (mkt == null) {
            return false;
        }

        String sMkt = mkt.getMkt();
        return Constants.DICT_MKT_A.equals(sMkt) && isPeriodicReport(announce.getSortcode());
    }

    public boolean isPeriodicReport(String sortcode) {
        return !StringUtils.isEmpty(sortcode) && Constants.ANNOUNCE_TYPE_SET.contains(sortcode);
    }
}
