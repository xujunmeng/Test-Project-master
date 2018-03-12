package com.csf.cloud.dao.mongo.base;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.aug3.storage.mongoclient.MongoUtils;
import com.csf.app.base.CollectionConstants;
import com.csf.cloud.dao.mongo.BaseMongoDao;
import com.csf.cloud.entity.mongo.base.BaseStock;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junmeng.xu on 2016/8/2.
 */
public class BaseStockDao extends BaseMongoDao {

    private static final Logger log = LoggerFactory.getLogger(BaseStockDao.class);

    public DBCollection getCollection(){
        return MongoAdaptor.getCollection(CollectionConstants.COLL_BASE_STOCK);
    }

    public Map<String, BaseStock> getBaseStockMap() {
        Map<String, BaseStock> result = new HashMap<>();
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
            BaseStock baseStock = new BaseStock();
            dbo = cursor.next();
            String tick = (String)dbo.get("tick");
            String code = (String)dbo.get("code");
            BaseStock.Abbr abbr = baseStock.new Abbr();
            abbr.setEn(MongoUtils.getStringByFieldNameChain(dbo, "abbr.en"));
            abbr.setPy(MongoUtils.getStringByFieldNameChain(dbo, "abbr.py"));
            abbr.setSzh(MongoUtils.getStringByFieldNameChain(dbo, "abbr.szh"));
            BaseStock.Mkt mkt = baseStock.new Mkt();
            mkt.setAbbr(MongoUtils.getStringByFieldNameChain(dbo, "mkt.abbr"));
            mkt.setCode(MongoUtils.getStringByFieldNameChain(dbo, "mkt.code"));
            mkt.setEn(MongoUtils.getStringByFieldNameChain(dbo, "mkt.en"));
            mkt.setSzh(MongoUtils.getStringByFieldNameChain(dbo, "mkt.szh"));
            BaseStock.Org org = baseStock.new Org();
            String orgId = MongoUtils.getStringByFieldNameChain(dbo, "org.id");
            org.setId(orgId);
            baseStock.setTick(tick);
            baseStock.setCode(code);
            baseStock.setAbbr(abbr);
            baseStock.setMkt(mkt);
            baseStock.setOrg(org);
            result.put(baseStock.getTick(), baseStock);

        }
        return result;
    }

}


