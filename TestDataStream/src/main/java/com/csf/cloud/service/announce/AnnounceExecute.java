package com.csf.cloud.service.announce;

import com.aug3.sys.util.CollectionsUtil;
import com.csf.cloud.entity.juchao.announce.TbText0042;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by junmeng.xu on 2016/8/5.
 */
public class AnnounceExecute {

    private static final Logger log = LoggerFactory.getLogger(AnnounceExecute.class);

    AnnounceStock announceStock;

    public Collection<String> service(Collection<String> data, ConcurrentHashMap<String, Object> content) throws Exception {
    	
        //初始化
        announceStock.init();
    	
        //获取对应的记录
        if(!CollectionUtils.isEmpty(data)){
        	
        	List<TbText0042> datas = announceStock.getEntitysBySids(data);
        	log.info("datas.size : " + datas.size());
        	
        	//处理相应的记录
        	announceStock.saveDataToCsf(datas);
        }

        return data;
    }

    public void setAnnounceStock(com.csf.cloud.service.announce.AnnounceStock announceStock) {
        this.announceStock = announceStock;
    }
}
