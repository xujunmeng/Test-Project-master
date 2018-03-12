package 基本操作;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by junmeng.xu on 2016/11/15.
 */
public class TestMongodbUtils {

    public static DBObject unpackaging(BaseObject entity) {
        DBObject result = new BasicDBObject();
        try{
            MongoPkgTools.unpackaging(entity, result);
            return result;
        }catch(Exception e) {
            return result;
        }
    }

}
