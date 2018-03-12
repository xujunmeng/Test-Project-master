package com.csf.cloud.dao.mongo.announce;

import com.aug3.storage.mongoclient.MongoAdaptor;
import com.csf.app.base.BaseConstants;
import com.csf.app.base.CollectionConstants;
import com.csf.app.utils.ListUtils;
import com.csf.cloud.dao.mongo.BaseMongoDao;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by junmeng.xu on 2016/8/2.
 */
public class DictAnnounceCatalogDao extends BaseMongoDao {

    private static Cache<String, Set<String>> ANNOUNCE_COVERED_CATALOG = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS).build();

    /**
     * 获取CSF覆盖的公告分类（只需要导入这些公告数据）
     * @return
     */
    public static Set<String> getCoveredAnnounceCat(){
        Set<String> catCds = null;
        try {
            catCds = ANNOUNCE_COVERED_CATALOG.get("cache", new Callable<Set<String>>() {
                @Override
                public Set<String> call() throws Exception {
                    DBObject query = new BasicDBObject();
                    query.put("mkttyp", "A");
                    query.put("cov", "1");
                    List<String> catCds = MongoAdaptor.getCollection(CollectionConstants.COLL_DICT_ANNOUNCE_CATALOG).distinct("code", query);
                    catCds.removeAll(ListUtils.splitStringToList(BaseConstants.ANNOUNCE_UNINDEX_CAT_CODES));
                    return Sets.newHashSet(catCds);
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(catCds == null || catCds.isEmpty()){
            throw new RuntimeException("Can't find covered announce catalog, please check table: "
                    + CollectionConstants.COLL_DICT_ANNOUNCE_CATALOG);
        }
        return catCds;
    }

}
