package temp;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
@author junmeng.xu
@date  2016年4月19日下午6:57:15
 */
public class Main2 {

	public static void main(String[] args) {
		
		Mongo mg = new Mongo("192.168.1.43", 27017);
		DB db = mg.getDB("temp");
		DBCollection report = db.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		query.put("item", "jkl");
		int count = report.find(query).count();
		System.out.println(count);
		DBObject one2 = report.findOne(query);
		Integer a = (Integer)one2.get("zan");
		if(ObjectUtils.equals(a, null)){
			report.update(query, new BasicDBObject("$set", new BasicDBObject("zan", 1)));
		}else{
			if(false)
				report.update(query, new BasicDBObject("$set", new BasicDBObject("zan", a + 1)));
		}
		
	}
	
	
	/**
	 * 当查询不到这条记录的时候，插入相对应的字段
	 */
	@Test
	public void test1(){
		Mongo mg = new Mongo("192.168.100.40", 27017);
		DB db = mg.getDB("test");
		DBCollection users = db.getCollection("category");
		BasicDBObject query = new BasicDBObject();
		query.put("name", "jkl1");
		
//		users.update(query, new BasicDBObject("$set", new BasicDBObject("upt", new Date())));
		//自己安装的环境不支持这种插入
		users.update(query, new BasicDBObject("$setOnInsert", new BasicDBObject("name", "jkl1").append("crt", new Date())), true, false);
		
		
		
	}
	
	
}
