package 对mongo表中的ext进行修改;

import com.aug3.storage.mongoclient.MongoUtils;
import com.google.common.collect.Lists;
import com.mongodb.*;

import java.util.Date;
import java.util.List;

/**
 * Created by junmeng.xu on 2016/12/25.
 */
public class Main {

    private static Mongo mg = new Mongo("122.144.134.95", 27017);

    //private static Mongo mg = new Mongo("192.168.250.208", 27017);

    public static void main(String[] args) {

        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        BasicDBObject query = new BasicDBObject();
        query.put("file.ext", "docx");
        BasicDBObject field = new BasicDBObject();
        field.put("sid", 1);

        coll.find(query, field);

        List<String> result = Lists.newArrayList();
        DBCursor cur = coll.find(query, field);
        DBObject dto = null;
        while(cur.hasNext()){
            dto = cur.next();
            String sid = MongoUtils.getStringByFieldNameChain(dto, "sid");
            result.add(sid);
        }

        for (String s : result) {
            coll.update(new BasicDBObject("sid", s), new BasicDBObject("$set", new BasicDBObject("file.ext", "pdf").append("stat", 2).append("upt", new Date())));
        }

    }

}
