package com.csf.cloud.dao.mongo;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.csf.cloud.common.MongoPkgTools;
import com.csf.cloud.entity.mongo.BaseObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class BaseMongoDao {

    protected Logger log = Logger.getLogger(getClass());

    /**
     * 将entity对象解封成DBObject对象
     *
     * @param entity
     * @return
     */
    protected DBObject unpackaging(BaseObject entity) {
        DBObject result = new BasicDBObject();
        try{
            MongoPkgTools.unpackaging(entity, result);
            return result;
        }catch(Exception e) {
            log.error(e, e);
            return result;
        }
    }

    /**
     * 将DBObject对象封装成entity对象
     *
     * @param obj
     * @param persistentClass
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    protected <T> T packaging(DBObject obj, Class<T> persistentClass) {
        try{
            T entity = persistentClass.newInstance();
            MongoPkgTools.packaging(entity, obj);
            return entity;
        }catch(Exception e) {
            log.error(e, e);
            return null;
        }
    }

    /**
     * 将DBObject数组封装成entity数组
     *
     * @param objects
     * @param persistentClass
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public <T> List<T> packaging(List<DBObject> objects, Class<T> persistentClass) {
        List<T> result = new ArrayList<T>();
        if(objects == null || objects.size() == 0) {
            return result;
        }
        for(DBObject obj : objects) {
            T entity = packaging(obj, persistentClass);
            if(entity == null) {
                continue;
            }
            result.add(entity);
        }
        return result;
    }

    /**
     * 获得collection对象
     *
     * @param collectionName
     * 		ie:base_stock  or ada.base_stock or MongoCollections.COLL_BASE_SHARE_VARY
     * @return
     */
    protected DBCollection getCollection(String collectionName){
        return MongoAdaptor.getCollection(collectionName);
    }

    /**
     * 构造一个可复用的查询语句，$in查询
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected BasicDBObject queryIn(Collection collection) {
        return new BasicDBObject("$in", collection);
    }

    /**
     * 构造一个可复用的查询语句，表示选择对应的key字段的范围
     * 该方法采取左闭右开的原则
     *
     * @param begin		开始,可以为null
     * @param end		结束,可以为null
     * @return
     */
    protected BasicDBObject queryRange(Object begin, Object end) {
        BasicDBObject basicDBObject = new BasicDBObject();
        if(begin == null && end == null) {
            basicDBObject.put("$exists", true);
        }
        if(begin != null) {
            basicDBObject.put("$gte", begin);
        }
        if(end != null) {
            basicDBObject.put("$lte", end);
        }
        return basicDBObject;
    }

    /**
     * 大于等于 查询
     * @param val
     * @return
     */
    protected DBObject queryGreaterEqThan(Object val) {
        DBObject result = new BasicDBObject();
        result.put("$gte", val);
        return result;
    }

    /**
     * 小于等于
     * @param val
     * @return
     */
    protected DBObject queryLessEqThan(Object val) {
        DBObject result = new BasicDBObject();
        result.put("$lte", val);
        return result;
    }

    /**
     * 大于 查询
     * @param val
     * @return
     */
    protected DBObject queryGreaterThan(Object val) {
        DBObject result = new BasicDBObject();
        result.put("$gt", val);
        return result;
    }

    /**
     * 不等于
     * @param val
     * @return
     */
    protected DBObject queryNotEq(Object val) {
        DBObject result = new BasicDBObject();
        result.put("$ne", val);
        return result;
    }

    /**
     * 小于
     * @param val
     * @return
     */
    protected DBObject queryLessThan(Object val) {
        DBObject result = new BasicDBObject();
        result.put("$lt", val);
        return result;
    }

    /**
     * 构造一个可复用的查询语句，表示对应的key字段字段存在且不为null和不为""
     * @return
     */
    protected DBObject queryNotEmpty() {
        return new BasicDBObject().append("$nin", Arrays.asList(new String[]{"", "null", null}));
    }

    protected DBObject queryEmpty() {
        return new BasicDBObject().append("$in", Arrays.asList(new String[]{"", "null", null}));
    }


    protected DBObject generateKey(String[] keys) {
        DBObject k = new BasicDBObject();
        if(keys != null) {
            for(String key : keys) {
                k.put(key, 1);
            }
        }
        return k;
    }

    protected Pattern queryRegex(String regex) {
        return Pattern.compile(regex);
    }
}
