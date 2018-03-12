package temp;

import java.util.List;

import com.aug3.storage.mongoclient.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
@author junmeng.xu
@date  2016年4月22日上午9:52:31
 */
public class Stream {

	public static void main(String[] args) {
		
		Mongo mg = new Mongo("192.168.0.123", 27017);
		DB db = mg.getDB("test");
		DBCollection report = db.getCollection("ann");
		BasicDBObject query = new BasicDBObject();
		
		List<DBObject> array = report.find(query).toArray();
		for (DBObject dbObject : array) {
			System.out.println(dbObject);
		}
		
		//stream forEach
		report.find(query).toArray().stream().forEach(dbo -> {
			String type     = MongoUtils.getStringByFieldNameChain(dbo, "typ");
			System.out.println(type);
		});
		
		//forEach
		report.find(query).toArray().forEach(dbo -> {
			String type     = MongoUtils.getStringByFieldNameChain(dbo, "typ");
			System.out.println(type);
		});
	}
	
}
