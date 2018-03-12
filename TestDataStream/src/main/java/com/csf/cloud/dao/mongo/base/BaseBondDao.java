package com.csf.cloud.dao.mongo.base;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.aug3.storage.mongoclient.MongoUtils;
import com.csf.app.base.CollectionConstants;
import com.csf.cloud.dao.mongo.BaseMongoDao;
import com.csf.cloud.entity.mongo.base.BaseBond;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junmeng.xu on 2016/8/2.
 */
public class BaseBondDao extends BaseMongoDao {

    public DBCollection getCollection(){
        return MongoAdaptor.getCollection(CollectionConstants.COLL_BASE_BOND);
    }

    public Map<String, BaseBond> getBaseBondMap() {
        Map<String, BaseBond> result = new HashMap<>();
        DBObject q = new BasicDBObject();
        DBObject k = new BasicDBObject();
        k.put("tick", 1);
        k.put("code", 1);
        k.put("abbr", 1);
        k.put("mkt", 1);
        k.put("org.id", 1);
        DBCursor cursor = getCollection().find(q, k);
        DBObject dbo;
        while(cursor.hasNext()){
            BaseBond baseBond = new BaseBond();
            dbo = cursor.next();
            String tick = (String)dbo.get("tick");
            String code = (String)dbo.get("code");
            BaseBond.Abbr abbr = baseBond.new Abbr();
            abbr.setEn(MongoUtils.getStringByFieldNameChain(dbo, "abbr.en"));
            abbr.setPy(MongoUtils.getStringByFieldNameChain(dbo, "abbr.py"));
            abbr.setSzh(MongoUtils.getStringByFieldNameChain(dbo, "abbr.szh"));
            BaseBond.Mkt mkt = baseBond.new Mkt();
            mkt.setAbbr(MongoUtils.getStringByFieldNameChain(dbo, "mkt.abbr"));
            mkt.setCode(MongoUtils.getStringByFieldNameChain(dbo, "mkt.code"));
            mkt.setEn(MongoUtils.getStringByFieldNameChain(dbo, "mkt.en"));
            mkt.setSzh(MongoUtils.getStringByFieldNameChain(dbo, "mkt.szh"));
            BaseBond.Org org = baseBond.new Org();
            String orgId = MongoUtils.getStringByFieldNameChain(dbo, "org.id");
            org.setId(orgId);
            baseBond.setTick(tick);
            baseBond.setCode(code);
            baseBond.setAbbr(abbr);
            baseBond.setMkt(mkt);
            baseBond.setOrg(org);
            result.put(baseBond.getTick(), baseBond);
        }
        return result;
    }


}
