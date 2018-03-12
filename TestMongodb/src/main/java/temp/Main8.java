package temp;


import com.aug3.storage.mongoclient.MongoUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
@author junmeng.xu
@date  2016年5月30日下午5:55:42
 */
public class Main8 {


	//删除重复的记录
	@Test
	public void testDulti() throws Exception{
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		query.put("file", new BasicDBObject("$exists", false));
		long count = coll.count(query);
		System.out.println(count);
		coll.remove(query);
	}

	@Test
	public void serser(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");

		BasicDBObject fields = new BasicDBObject();
		fields.put("sid", "61988537");
		BasicDBObject dd = new BasicDBObject("$set", new BasicDBObject("stat", 2).append("upt", new Date()));
		coll.update(fields, dd);
	}

	@Test
	public void setset(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		ArrayList<Object> objects = Lists.newArrayList("1202117199", "1202117200", "1202841315");
		query.put("sid", new BasicDBObject("$in", objects));
		DBCursor cur = coll.find(query);
		DBObject dto = null;
		while (cur.hasNext()) {
			dto = cur.next();
			BasicDBList cat = (BasicDBList) MongoUtils.getObjectByFieldNameChain(dto, "secu");
		}
	}



	@Test
	public void testasdDulti() throws Exception{
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund_bak");
		BasicDBObject query = new BasicDBObject();
		query.put("file", new BasicDBObject("$exists", false));
		long count = coll.count(query);
		System.out.println(count);
		coll.remove(query);
	}


	@Test
	public void sdfasdf(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		
		query.put("file", new BasicDBObject("$exists",false));
		BasicDBObject dd = new BasicDBObject("$set", new BasicDBObject("stat", 0).append("upt", new Date()));
		coll.update(query, dd);
	}

	@Test
	public void asdf(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		coll.remove(new BasicDBObject("file", new BasicDBObject("$exists", false)));
	}
	@Test
	public void asdfsd(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		coll.remove(new BasicDBObject("sid", "1202932746"));
	}


	public static void main(String[] args) throws ParseException {
		
		@SuppressWarnings("deprecation")
		Mongo mg = new Mongo("192.168.250.200", 27017);
		@SuppressWarnings("deprecation")
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_events");
		
		Tech tech = new Tech();
		tech.setParent("T99");
		tech.setCode("T99001");
		tech.setLevel(2);
		tech.setName(new NameField("New High", ""));
		tech.setDesc(new DescField("New High", "当前K线是否创造历史新高。"));
		tech.setCrt(getNow(8));
		tech.setUpt(getNow(8));
		tech.setStat(1);
		tech.setTyp(5);
		
		Gson gson = new Gson();
		//转换成json字符串，再转换成DBObject对象
		DBObject dbObject = (DBObject)JSON.parse(gson.toJson(tech));
		
		coll.insert(dbObject);
		
	}

	/**
	 * {
	 * "_id" : ObjectId("581b10f0e15eae229846b7f7"),
	 * "username" : "whthomas",
	 * "age" : "22",
	 * "location" : {
	 * "city" : "hangzhou",
	 * "x" : 100,
	 * "y" : "200"
	 * }
	 * }
	 */
	@Test
	public void test2334(){
		@SuppressWarnings("deprecation")
		Mongo mg = new Mongo("192.168.250.200", 27017);
		@SuppressWarnings("deprecation")
		DB db = mg.getDB("test");
		DBCollection coll = db.getCollection("test");
		DBObject doc = new BasicDBObject("username","whthomas").append("age", "22").append("location", new BasicDBObject("city", "hangzhou").append("x", 100).append("y","200"));
		coll.insert(doc);
	}

	/**
	 * {
	 * "_id" : ObjectId("581b166ce15eae0d9cffe869"),
	 * "secu" : [
	 * {
	 * "name1" : "desc1"
	 * },
	 * {
	 * "name2" : "desc2"
	 * }
	 * ]
	 * }
	 */
	@Test
	public void test233asdf4(){
		@SuppressWarnings("deprecation")
		Mongo mg = new Mongo("192.168.250.200", 27017);
		@SuppressWarnings("deprecation")
		DB db = mg.getDB("test");
		DBCollection coll = db.getCollection("test");
		BasicDBList list = new BasicDBList();
		list.add(new BasicDBObject("name", "name1").append("desc", "desc1"));
		list.add(new BasicDBObject("name2", "desc2").append("desc2", "desc2"));

		DBObject dbObject = new BasicDBObject();
		dbObject.put("secu", list);

		coll.insert(dbObject);

	}

	@Test
	public void afdadsf(){
		@SuppressWarnings("deprecation")
		Mongo mg = new Mongo("122.144.134.95", 27017);
		@SuppressWarnings("deprecation")
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement");
		BasicDBList list = new BasicDBList();
		list.add(new BasicDBObject("cd", "002153_SZ_EQ").append("mkt", "1003").append("org", "10008622"));
		list.add(new BasicDBObject("cd", "002151_SZ_EQ").append("mkt", "1003").append("org", "10009400"));
		list.add(new BasicDBObject("cd", "002152_SZ_EQ").append("mkt", "1003").append("org", "10004526"));

		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("secu", list);
		BasicDBObject fields = new BasicDBObject();
		fields.put("sid", "30686907");
		BasicDBObject dd = new BasicDBObject("$set", dbObject.append("stat", 2).append("upt", new Date()));
		coll.update(fields, dd);
	}
	
	// BasicDBList -->  String
	@Test
	public void test22(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement");
		BasicDBObject query = new BasicDBObject();
		
		query.put("sid", "30686907");
		DBCursor cur = coll.find(query);
		DBObject dto = null;
		while (cur.hasNext()) {
			dto = cur.next();
			BasicDBList cat = (BasicDBList)MongoUtils.getObjectByFieldNameChain(dto, "cat");
			List<String> list = Lists.newArrayList();
			for (Object object : cat) {
				list.add((String)object);
			}
			String join = StringUtils.join(list, ";");
			System.out.println(join);
		}
	}
	
	@Test
	public void test33(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		query.put("file", new BasicDBObject("$exists", false));
		DBCursor cursor = coll.find(query);

		BasicDBObject dd = new BasicDBObject("$set", new BasicDBObject("stat", 0).append("upt", new Date()));
		coll.update(query, dd);
	}
	@Test
	public void test23454(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		
		query.put("title", "H股ETF联接(人民币)：2015年年度报告");
		query.put("sid", "1202080463");
		coll.update(query, new BasicDBObject("$set", new BasicDBObject("stat", 0)));
	}
	
	@Test
	public void Testsd(){
		System.out.println(new Date());
	}
	
	@Test
	public void test88(){

		
	}
	
	
	public static Date getNow(int timeZoneOffset) {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {  
	        timeZoneOffset = 0;  
	    }
		TimeZone defaultZone = TimeZone.getDefault();
		long diff = 0 - defaultZone.getRawOffset();
		long rawOffset = getRawOffset(timeZoneOffset);
		long currentTimeMillis = System.currentTimeMillis();
		return new Date(currentTimeMillis + diff + rawOffset);
	}
	public static long getRawOffset(int timeZoneOffset) {
		return timeZoneOffset * 60 * 60 * 1000;
	}
}


class AAAA implements Serializable{
	private String name;
	private String desc;

	public AAAA(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}