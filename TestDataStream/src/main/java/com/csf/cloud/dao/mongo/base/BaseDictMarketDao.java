package com.csf.cloud.dao.mongo.base;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.csf.app.base.CollectionConstants;
import com.csf.cloud.dao.mongo.BaseMongoDao;
import com.csf.cloud.entity.mongo.base.BaseDictMarket;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junmeng.xu on 2016/8/1.
 */
public class BaseDictMarketDao extends BaseMongoDao {

    public DBCollection getCollection(){
        return MongoAdaptor.getCollection(CollectionConstants.COLL_DICT_MARKET);
    }

    public Map<String, BaseDictMarket> getDictMap(){
        Map<String, BaseDictMarket> map = new HashMap<>();
        BasicDBObject query = new BasicDBObject();
        BasicDBObject fields = new BasicDBObject();
        fields.put("code", 1);
        fields.put("mkt", 1);
        DBObject dbo;
        DBCursor cursor = getCollection().find(query, fields);
        while(cursor.hasNext()) {
            BaseDictMarket baseDictMarket = new BaseDictMarket();
            dbo = cursor.next();
            String code = (String)dbo.get("code");
            String mkt = (String)dbo.get("mkt");
            baseDictMarket.setCode(code);
            baseDictMarket.setMkt(mkt);
            map.put(code, baseDictMarket);
        }
        return map;
    }


}
