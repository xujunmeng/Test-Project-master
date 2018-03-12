package com.csf.cloud.dao.mongo.announce;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.csf.cloud.common.Constants;
import com.csf.cloud.common.DateUtils;
import com.csf.cloud.common.MongoCollections;
import com.csf.cloud.dao.mongo.BaseMongoDao;
import com.csf.cloud.entity.mongo.announce.AnnouncementStock;
import com.google.common.collect.Sets;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class AnnouncementStockDao extends BaseMongoDao {

    public Set<String> getSidsByDate(Date from, Date to){
        DBObject query = new BasicDBObject();
        DBObject keys = new BasicDBObject();
        keys.put("sid", 1);
        query.put("pdt", new BasicDBObject("$gte", DateUtils.getPreOrNextDate(from, -2)).append("$lte", DateUtils.getPreOrNextDate(to, 2)));
        DBCursor cursor = MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT).find(query, keys);
        DBObject dbo;
        Set<String> existSids = new HashSet<>();
        while(cursor.hasNext()){
            dbo = cursor.next();
            String sid = ObjectUtils.toString(dbo.get("sid"));
            existSids.add(sid);
        }
        DBCursor cursor2 = MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT_OTC).find(query, keys);
        DBObject dbo2 = null;
        Set<String> existSids2 = new HashSet<>();
        while(cursor2.hasNext()){
            dbo2 = cursor2.next();
            String sid = ObjectUtils.toString(dbo2.get("sid"));
            existSids2.add(sid);
        }
        return Sets.newHashSet(Sets.union(
                Sets.newHashSet(existSids),
                Sets.newHashSet(existSids2)));
    }

    public int saveAnnouncementStock(List<AnnouncementStock> announcementStocks){
        for (AnnouncementStock announcementStock : announcementStocks) {
            BasicDBObject fields = new BasicDBObject();
            fields.put("sid", announcementStock.getSid());
            List<AnnouncementStock.AnnounceSecu> secus = announcementStock.getSecu();
            if (null == secus || secus.isEmpty())
                continue;
            List<String> mktCd = new ArrayList<String>();
            for (AnnouncementStock.AnnounceSecu sc : secus) {
                if(null != sc)
                    mktCd.add(sc.getMkt());
            }
            if (mktCd.contains(Constants.THREE_BOARD_MARKET.get(0))) {
//                long count = MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT_OTC).getCount(fields);
//                if(count == 0) MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT_OTC).insert(unpackaging(announcementStock));
            }else{
                long count = MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT).getCount(fields);
                if(count == 0) MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT).insert(unpackaging(announcementStock));
            }
        }
        return announcementStocks.size();
    }

    public int saveAnnouncementStockFile(List<AnnouncementStock> announcementStocks){
        for (AnnouncementStock announcementStock : announcementStocks) {
            String sid = announcementStock.getSid();
            BasicDBObject keys = new BasicDBObject();
            BasicDBObject fields = new BasicDBObject();
            fields.put("sid", sid);
            if(MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT).getCount(fields) > 0 ){
                handlerFileField(announcementStock, keys);
                MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT).update(fields, new BasicDBObject("$set", keys));
            }
//            if(MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT_OTC).getCount(fields) > 0){
//                handlerFileField(announcementStock, keys);
//                MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT_OTC).update(fields, new BasicDBObject("$set", keys));
//            }
        }
        return announcementStocks.size();
    }
    private void handlerFileField(AnnouncementStock announcementStock,
                                  BasicDBObject keys) {
        String ext = announcementStock.getFile().getExt();
        String fn = announcementStock.getFile().getFn();
        String md5 = announcementStock.getFile().getMd5();
        int pn = announcementStock.getFile().getPn();
        String url = announcementStock.getFile().getUrl();
        Long bytes = announcementStock.getFile().getBytes();
        keys.put("stat", Constants.STATUS_VERIFIED);
        keys.put(
                "file",
                new BasicDBObject("ext", ext).append("fn", fn)
                        .append("md5", md5).append("pn", pn)
                        .append("url", url).append("bytes", bytes));
    }
}
