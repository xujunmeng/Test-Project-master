package temp;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.sys.util.DateUtil;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
@author junmeng.xu
@date  2016年4月28日下午6:07:39
 */
public class Main4 {

	DBCollection quant;
	
	@Before
	public void before(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("quant");
		quant = db.getCollection("quant_user_combination");
	}
	@Test
	public void asdf(){
		String cd = "571432a6b022e25263a6e8d8";
		sdccx("quant.quant_analyze_stock_combination_trace", new BasicDBObject("cd", cd), false);
	}
	public Map<String, Double[]> sdccx(String table, DBObject query, boolean firstValue){
		DBObject keys = new BasicDBObject();
		keys.put("cd", 1);
		keys.put("dt", 1);
		keys.put("stock", 1);

		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		quant = db.getCollection(table);

		List<DBObject> dboList = quant.find(query, keys).sort(new BasicDBObject("dt", firstValue?1:-1)).limit(1).toArray();
		if (dboList.isEmpty()) return Maps.newHashMap();
		DBObject dbo = dboList.get(0);
		Map<String, BasicDBList> stockMap = Optional
				.ofNullable((Map<String, BasicDBList>) MongoUtils.getObjectByFieldNameChain(dbo, "stock"))
				.orElse(Maps.newHashMap());
		Map<String, Double[]> result = new LinkedHashMap<>();
		stockMap.forEach((k,v)-> result.put(k, v.stream().map(v1 -> (Double)v1).toArray(Double[]::new)));
		return result;
	}

	@Test
	public void test(){
		DBCursor find = quant.find(new BasicDBObject("$or", Lists.newArrayList(new BasicDBObject("stat", 2), new BasicDBObject("src", new BasicDBObject("$ne", "")))),
                new BasicDBObject("_id", 1).append("crt", 1));
		Map<String, String> userCombination = new HashMap<String, String>();
		while(find.hasNext()){
			DBObject dto = find.next();
			System.out.println(dto.toString());
			String _id = MongoUtils.getStringByFieldNameChain(dto, "_id");
			Date crt = (Date)MongoUtils.getObjectByFieldNameChain(dto, "crt");
			userCombination.put(_id, DateUtil.formatDate(crt));
		}
		
		Map<String, String> noEarn = Maps.filterValues(userCombination, v -> v.compareTo("2016-04-07") == 0);
		noEarn.forEach((k, v) -> System.out.println(k + " : " + v));
		System.out.println("=============");
			
		Map<String, String> transformValues = Maps.transformValues(noEarn, new Function<String, String>() {
	
			@Override
			public String apply(String input) {
				return input;
			}
		});
		transformValues.forEach((k, v) -> System.out.println(k + " : " + v));
			
		
		  
		
	}
	@Test
	public void testccc(){
		String y = "2016-04-07".substring(0, "2016-04-07".indexOf("-"));
		System.out.println(y);
		Map<String, String> map = new HashMap<String, String>();
		for(Map.Entry<String, String> entry : map.entrySet()){
			
		}
	}
	

	
	
}
