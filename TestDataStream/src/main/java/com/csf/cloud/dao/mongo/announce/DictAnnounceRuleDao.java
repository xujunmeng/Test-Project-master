package com.csf.cloud.dao.mongo.announce;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.csf.app.base.BaseConstants;
import com.csf.app.base.CollectionConstants;
import com.csf.app.dal.dto.dict.DictAnnounceRule;
import com.csf.cloud.dao.mongo.BaseMongoDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.*;

/**
 * Created by junmeng.xu on 2016/8/1.
 */
public class DictAnnounceRuleDao extends BaseMongoDao {

    public DBCollection getCollection(){
        return MongoAdaptor.getCollection(CollectionConstants.COLL_ANNOUNCE_RULE);
    }

    public Map<Integer, List<DictAnnounceRule>> getAnnounceRule() {
        DBObject query = new BasicDBObject();
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        query.put("type", new BasicDBObject("$in", set));
        DBCursor cursor = getCollection().find(query);
        Map<Integer, List<DictAnnounceRule>> ruleMap = new HashMap<>();
        while (cursor.hasNext()) {
            BasicDBObject dbo = (BasicDBObject) cursor.next();

            DictAnnounceRule announceRule = new DictAnnounceRule();
            Integer type = dbo.getInt("type");
            announceRule.setType(type);
            announceRule.setStat(dbo.getString("stat"));
            announceRule.setCode(dbo.getString("code"));
            String rule = dbo.getString("rule");
            announceRule.setRule(rule);
            rule.replace(BaseConstants.UNDERSCORE, "");
            announceRule.setSize(rule.length());
            List<DictAnnounceRule> typeList = ruleMap.get(type);
            if (null == typeList) {
                typeList = new ArrayList<>();
            }
            typeList.add(announceRule);
            ruleMap.put(type, typeList);
        }

        return ruleMap;
    }


}
