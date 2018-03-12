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
@date  2016年5月26日下午6:04:02
 */
public class Main7 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo smg = new Mongo("54.251.56.179", 27017);
		DBCollection scoll = smg.getDB("ada").getCollection("fin_rpt_catlog");
		
		
		
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("ada");
		DBCollection coll = db.getCollection("fin_rpt_catlog");
		DBCursor cursor = coll.find();
		while(cursor.hasNext()){
			DBObject dto = cursor.next();
			
			String tick = MongoUtils.getStringByFieldNameChain(dto, "tick");
			
			BasicDBObject query = new BasicDBObject();
			
			query.put("tick", tick);
			int count = scoll.find(query).count();
			if(0 == count){
				scoll.insert(dto);
			}
			
		}
		
	}
	
}
