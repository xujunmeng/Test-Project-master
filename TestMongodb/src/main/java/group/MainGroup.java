package group;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.aug3.storage.mongoclient.MongoUtils;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
@author junmeng.xu
@date  2016年4月19日下午6:25:29
 */
public class MainGroup {

	public static void main(String[] args) {
		
		Mongo mg = new Mongo("192.168.0.123", 27017);
		DB db = mg.getDB("test");
		DBCollection person = db.getCollection("person");
//		DBCursor cur = person.find(new BasicDBObject("_id", new ObjectId("56309e654b3a75b69aa81815")));
		DBCursor cur = person.find(new BasicDBObject("_id", new ObjectId("56309e654b3a75b69aa81815")));
		DBObject dto = null;
		while (cur.hasNext()) {
			dto = cur.next();
			System.out.println("==============" + dto);
		}
		
		BasicDBObject key = new BasicDBObject();   
		key.put("name", "true");  
		BasicDBObject initial = new BasicDBObject();  
		initial.put("count", 0); 
		BasicDBObject condition = new BasicDBObject();  
//		condition.put("name", "xujunmeng"); 
		String reduceString = "function(obj,prev) { prev.count++; }";   
		List<DBObject> dbo = (List<DBObject>)person.group(key, condition , initial, reduceString);
		List<String> names = new ArrayList<String>();
		for (DBObject dbObject : dbo) {
			String count = MongoUtils.getStringByFieldNameChain(dbObject, "count");
			String name = MongoUtils.getStringByFieldNameChain(dbObject, "name");
			if(Double.parseDouble(count) >= 2){
				names.add(name);
			}
			System.out.println(dbObject);
		}
	}
	DBCollection users;
	@Before
	public void Before(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("fund");
		users = db.getCollection("fund_net_value_chg_rank_csf");
	}
	//$project
	@SuppressWarnings("deprecation")
	@Test
	public void test(){
		DBObject project = new BasicDBObject();
		project.put("$project", new BasicDBObject("type", 1).append("_id", 0));
		AggregationOutput aggregate = users.aggregate(project);
		Iterable<DBObject> results = aggregate.results();
		for (DBObject dbObject : results) {
			System.out.println(dbObject);
		}
	}
	/**
	 * $group
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void test2(){
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$type").append("count", new BasicDBObject("$sum", 1)));
		BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("count", -1));
		BasicDBObject limit = new BasicDBObject("$limit", 5);
		Iterable<DBObject> results = users.aggregate(group,sort,limit).results();
		for (DBObject dbObject : results) {
			System.out.println(dbObject);
		}
	}
	//$avg   $total   ： total不能是一个string类型的值 , 可以是 int double..
	@SuppressWarnings("deprecation")
	@Test
	public void test22(){
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$type").append("avg", new BasicDBObject("$avg", "$total")));
		Iterable<DBObject> results = users.aggregate(group).results();
		for (DBObject dbObject : results) {
			System.out.println(dbObject);
		}
	}
	//$max  $min
	@SuppressWarnings("deprecation")
	@Test
	public void test222(){
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$type").append("lowestTotal", new BasicDBObject("$min", "$total")).append("highestTotal", new BasicDBObject("$max", "$total")));
		Iterable<DBObject> results = users.aggregate(group).results();
		for (DBObject dbObject : results) {
			System.out.println(dbObject);
		}
	}
	//$first $last
	@SuppressWarnings("deprecation")
	@Test
	public void testeee(){
		BasicDBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$type").append("firstRank", new BasicDBObject("$first", "$rank")));
		Iterable<DBObject> results = users.aggregate(group).results();
		for (DBObject dbObject : results) {
			System.out.println(dbObject);
		}
	}
	
	/**
	 * $match
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void test3(){
		BasicDBObject match = new BasicDBObject("$match", new BasicDBObject("type", "指数型"));
		Iterable<DBObject> results = users.aggregate(match).results();
		for (DBObject dbObject : results) {
			System.out.println(dbObject);
		}
	}
	/**
	 * distinct
	 */
	@Test
	public void test4(){
		List distinct2 = users.distinct("type");
		for (Object object : distinct2) {
			System.out.println(distinct2);
		}
	}
}
