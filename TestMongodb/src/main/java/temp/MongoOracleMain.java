package temp;

import com.aug3.storage.mongoclient.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
@author junmeng.xu
@date  2016年6月30日上午10:52:28
 */
public class MongoOracleMain {

	public static void main(String[] args) {
		
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement");
		BasicDBObject field = new BasicDBObject();
		
		field.put("sid", 1);
		DBCursor cur = coll.find(null, field);
		DBObject dto = null;
		while (cur.hasNext()) {
			dto = cur.next();
			String sid = MongoUtils.getStringByFieldNameChain(dto, "sid");
		}
		
	}
	
}
