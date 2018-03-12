package com.csf.cloud.service.announce;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csf.cloud.service.JobParameter;

/**
 * Created by junmeng.xu on 2016/8/5.
 */
public class AnnounceTriggerExecute{

    private static final Logger log = LoggerFactory.getLogger(AnnounceTriggerExecute.class);

    AnnounceStock announceStock;

    public void service(ConcurrentHashMap<String, Object> content) throws Exception {
        //初始化
        announceStock.init();

        //设置每次执行的时间间隔
        JobParameter jobParameter = announceStock.getPeriodDate(content);

        Date fromDate = jobParameter.getFrom();

        Date toDate = jobParameter.getTo();

        log.info("fromDate : " + fromDate + " toDate : " + toDate);

        //获取所有的sid
        Set<String> allSids = announceStock.getSidsFromJuchao(fromDate, toDate);

        //去除已经存在的sid
        Set<String> sids = announceStock.removeExistsData(fromDate, toDate, allSids);

        log.info("sids.size : " + sids.size());

        log.info("sids : " + sids);
    }

    public void setAnnounceStock(AnnounceStock announceStock) {
        this.announceStock = announceStock;
    }
}
