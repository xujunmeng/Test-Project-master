package 基本操作;

import com.aug3.storage.mongoclient.MongoUtils;
import com.google.common.collect.Lists;
import com.mongodb.*;
import org.apache.commons.lang.ObjectUtils;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
@author junmeng.xu
@date  2016年5月27日下午2:06:49
 */
public class Main {

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
	 * $setOnInsert
	 * 当查询不到这条记录的时候，插入相对应的字段
	 * 当该key不存在的时候执行插入操作，当存在的时候则不管，可以使用setOnInsert
	 */
	@Test
	public void test1(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("test");
		DBCollection users = db.getCollection("category");
		BasicDBObject query = new BasicDBObject();
		query.put("name", "jkl2");
		
//		users.update(query, new BasicDBObject("$set", new BasicDBObject("upt", new Date())));
		//自己安装的环境不支持这种插入
//		users.update(query, new BasicDBObject("f05", "jkl3"), true, false);
		users.update(query, new BasicDBObject("$setOnInsert", new BasicDBObject("crt", new Date())), true, false);
		
	}
	
	/**
	 * $unset
	 * 删除query 后  的  记录中的  name:jkl1
	 */
	@Test
	public void test2(){
		Mongo mg = new Mongo("192.168.100.40", 27017);
		DB db = mg.getDB("test");
		DBCollection users = db.getCollection("category");
		BasicDBObject query = new BasicDBObject();
		query.put("name", "jkl1");
		
		users.update(query, new BasicDBObject("$unset", new BasicDBObject("name", "jkl1")));
		//批量更新 
//		coll.update(new BasicDBObject("f63", new BasicDBObject("$exists", true)), new BasicDBObject("$unset", new BasicDBObject("f63", "")),false,true);
	}
	
	//  $nin
	@SuppressWarnings("deprecation")
	@Test
	public void test3(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_strategy_combination");
		List<String> list = new ArrayList<String>();
		list.add("100101");
		BasicDBObject query = new BasicDBObject("metrics", new BasicDBObject("$in", list)).append("cd", new BasicDBObject("$nin", Lists.newArrayList(new ObjectId("5715ed11a4c3a97e172fe24a"))));
		DBCursor find = coll.find(query);
		while(find.hasNext()) {
			DBObject next = find.next();
			System.out.println(next);
		}
		
		
	}

	/**
	 * 主要在查询的时候，不到万不得已，不要用skip,limit来处理历史数据，效率非常的低！！！！
	 */
	@Test
	public void test3334(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement");
		BasicDBObject query = new BasicDBObject();
//		query.put("pub", null);
//		query.put("sid", new BasicDBObject("$nin", new String[]{""}));
		BasicDBObject field = new BasicDBObject();
		field.put("sid", 1);
		long count = coll.count(query);
		System.out.println(count);
		DBCursor cur = coll.find(query, field);
		DBObject dto = null;
		List<String> list = Lists.newArrayList();
		while (cur.hasNext()) {
			dto = cur.next();
			String sid = MongoUtils.getStringByFieldNameChain(dto, "sid");
			list.add(sid);
		}
		for (String sid : list) {
			coll.update(new BasicDBObject("sid", sid), new BasicDBObject("$set", new BasicDBObject("pub", "2011-01-01")), false, true);
		}
		
	}
	
	//查询索引
	@Test
	public void testindex(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		List<DBObject> list = coll.getIndexInfo();
		for (DBObject dbObject : list) {
			System.out.println(dbObject);
		}
	}
	
	//创建索引
	@Test
	public void testBuild(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject keys = new BasicDBObject();
		keys.put("file.url", 1);
		keys.put("file.fn", 1);
		coll.createIndex(keys);
	}
	
	
	//寻找相同的记录
	@SuppressWarnings("deprecation")
	@Test
	public void testsame(){
		ArrayList<String> list = Lists.newArrayList();
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement");
		DBCursor find = coll.find();
		while(find.hasNext()) {
			DBObject next = find.next();
			String sid = MongoUtils.getStringByFieldNameChain(next, "sid");
			BasicDBObject query = new BasicDBObject();
			query.put("sid", sid);
			long count = coll.count(query);
			if(count > 1){
				if(!list.contains(sid)){
					System.out.println("sid : " + sid);
					list.add(sid);
				}
			}
		}
	}

	@Test
	public void insertList(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("test");
		DBCollection users = db.getCollection("testtest");
		BasicDBObject query = new BasicDBObject();
		query.put("name", "aaa");
		List<Atgf> list = Lists.newArrayList();
		list.add(new Atgf("xxx", 23));
		list.add(new Atgf("yyy", 25));
		list.add(new Atgf("zzz", 26));
		users.update(query, new BasicDBObject("$setOnInsert", new BasicDBObject("obj", list)), true, false);
	}

	
	
}
class Atgf extends BaseObject{
	private String name;
	private Integer age;

	public Atgf() {
	}

	public Atgf(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Atgf atgf = (Atgf) o;

		if (name != null ? !name.equals(atgf.name) : atgf.name != null) return false;
		return !(age != null ? !age.equals(atgf.age) : atgf.age != null);

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (age != null ? age.hashCode() : 0);
		return result;
	}

	@Override
	public String
	toString() {
		return "Atgf{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}