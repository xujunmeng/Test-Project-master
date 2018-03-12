package com.csf.cloud.service.help;

import com.csf.cloud.entity.mongo.announce.AnnouncementStock;
import com.csf.cloud.entity.mongo.base.BaseStock;

import java.util.Map;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public interface IPdfFileNameCreator {

    String createFileName(AnnouncementStock announce, String secu, Map<String, BaseStock> stockMap, String defaultName);

    boolean isSepcialName(AnnouncementStock announce, String secu, Map<String, BaseStock> stockMap);

    boolean isPeriodicReport(String sortcode);

}
