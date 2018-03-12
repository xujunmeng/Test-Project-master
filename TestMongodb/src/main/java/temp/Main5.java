package temp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.sys.util.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

/**
@author junmeng.xu
@date  2016年4月29日上午9:36:32
 */
public class Main5 {
	
	DBCollection quant2;
	
	@Before
	public void before(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("quant");
		quant2 = db.getCollection("quant_analyze_stock_combination_his");
	}
	
	@Test
	public void test1(){
		
		
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("quant");
		DBCollection quant = db.getCollection("quant_user_combination");
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
		
		Map<String, String> combinationIds = Maps.filterValues(userCombination, v -> v.compareTo("2016-04-07") < 0);
		for(Map.Entry<String, String> entry : combinationIds.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		
        DBObject query = new BasicDBObject("$match", new BasicDBObject("cd", new BasicDBObject("$in", combinationIds.keySet())));
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("cd", -1).append("dt", -1));
        DBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$cd")
                .append("dt", new BasicDBObject("$first", "$dt"))
                .append("stock", new BasicDBObject("$first", "$stock")));
        Iterable<DBObject> it = quant2.aggregate(Lists.newArrayList(query, sort, group)).results();
        for (DBObject dbObject : it) {
			System.out.println(dbObject);
		}
        System.out.println("=====================");
        Map<String, Map<String, Double>> result = new HashMap<>();
        for (DBObject dbo : it) {

            String cd = MongoUtils.getStringByFieldNameChain(dbo, "_id");
            Map<String, BasicDBList> stockMap = Optional
                    .ofNullable((Map<String, BasicDBList>) MongoUtils.getObjectByFieldNameChain(dbo, "stock"))
                    .orElse(Maps.newHashMap());
            Map<String, Double> map = Maps.transformValues(stockMap, objects -> (Double) objects.get(1));
            result.put(cd, map);
        }
        Set<Entry<String, Map<String, Double>>> entrySet = result.entrySet();
        Iterator<Entry<String, Map<String, Double>>> iterator = entrySet.iterator();
        for (Entry<String, Map<String, Double>> entry : entrySet) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	
	
	
	@Test
	public void testmm(){
		Map<String, Double> map = new HashMap<String, Double>();
		Map<String, Double> map2 = new HashMap<String, Double>();
		map.put("aaa", 2d);
		map.put("bbb", 3d);
		Map<String, Double> transformEntries = Maps.transformEntries(map, (k,v) -> map2.getOrDefault(k, 0d) * v);
		for(Map.Entry<String, Double> entry : transformEntries.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	
	
	
	@Test
	public void testplus(){
    	Set<String> set = new HashSet<String>();
    	set.add("56f8c92da4c3a901dbe144fe");
    	set.add("56f8cde9a4c3a92fa815d707");
    	set.add("56f8ce60a4c3a92fa815d709");
    	set.add("56fa14f2a4c3a9321713051c");
    	set.add("56fba287a4c3a93f6c9c02f0");
    	set.add("56fcd0f3a4c3a93f6c9c02f2");
    	set.add("56fcfc14a4c3a94ba525ab57");
    	set.add("56fe3f49a4c3a95b3fe28729");
    	set.add("56fe4dbca4c3a960945e568e");
    	set.add("56fe5410a4c3a960945e5692");
    	set.add("5705c7eba4c3a93639f54683");
    	set.add("5705c907a4c3a93639f54684");
    	set.add("5706198da4c3a929b9a98631");
    	set.add("57061dc7a4c3a933a48392af");
    	set.add("57072744a4c3a93a37140e08");
    	combinationEarnPlus(set);
	}
    public void combinationEarnPlus(Collection<String> combinationIds) {
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection quant_user_combination_earn = db.getCollection("quant_user_combination_earn");
        DBCursor cur = quant_user_combination_earn.find(new BasicDBObject("cd", new BasicDBObject("$in", combinationIds)))
                .sort(new BasicDBObject("cd", 1).append("y", 1));
        Map<String, List<Double>> combinationEarnRate = new LinkedHashMap<>();

        while (cur.hasNext()) {

            DBObject dbo = cur.next();
            String cd = MongoUtils.getStringByFieldNameChain(dbo, "cd");
            Map<String, Double> items = Optional
                    .ofNullable((Map<String, Double>) MongoUtils.getObjectByFieldNameChain(dbo, "items"))
                    .orElse(Maps.newHashMap());
            if (!combinationEarnRate.containsKey(cd)) combinationEarnRate.put(cd, new ArrayList<>());
            combinationEarnRate.get(cd).addAll(items.values());
        }
        
        for(Entry<String, List<Double>> entry : combinationEarnRate.entrySet()){
        	System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        
        
        // 最大回撤
        Map<String, Double> maxDrawbackMap = new HashMap<>();
        combinationEarnRate.forEach((s, doubles) -> {
        	
        	List<Double> ratePlus = ratePlus(doubles);
        	
        	Double maxDrawback = maxDrawback(ratePlus);
        	
            maxDrawbackMap.put(s, maxDrawback);
        });
        // 总收益
        Map<String, Double> earnPlus = new HashMap<>();
        combinationEarnRate.forEach((s, doubles) -> {
            double plus = doubles.stream().filter(b -> b != null && !b.isNaN())
                    .mapToDouble(c -> c + 1).reduce((p, b) -> p * b).orElse(1d) - 1;
            earnPlus.put(s, plus);
        });

        this.updateCombinationEarnPlus(earnPlus, maxDrawbackMap);
    }
    private void updateCombinationEarnPlus(Map<String, Double> data, Map<String, Double> maxDrawbackMap) {
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection quant_user_combination = db.getCollection("quant_user_combination");
        data.forEach((k, v) -> quant_user_combination.update(new BasicDBObject("_id", new ObjectId(k)),
                        new BasicDBObject("$set", new BasicDBObject("earn", v).append("maxDrawback", maxDrawbackMap.get(k))),
                        true,
                        false,
                        WriteConcern.SAFE));
    }
	
	
	
	
	
    /**
     * 最大回测
     */
    public static Double maxDrawback(List<Double> data) {
        List<Double> plus = data.stream().map(v -> v +1).collect(Collectors.toList());
        double maxDrawBack = 0d;
        for (int i = 0; i < plus.size(); i++) {
            Double curr = Optional.ofNullable(plus.get(i)).orElse(1d);
            List<Double> sub = plus.subList(i, plus.size() - 1);
            if (sub.isEmpty()) continue;
            Double min = Optional.ofNullable(min(sub)).orElse(0d);
            double drawback = Math.abs((curr - min)/ curr);
            if (maxDrawBack < drawback) maxDrawBack = drawback;
        }
        return maxDrawBack;
    }
    public static <T extends Comparable<? super T>> T min (List<T> data){
        if (data == null || data.isEmpty()) return null;
        T min = data.get(0);
        int len = data.size();
        for (int i = 1; i < len; i++){
            T th = data.get(i);
            if (th != null && min != null && th.compareTo(min) == -1) min = th;
        }
        return min;
    }
	
	
	
    /**
     * 累计值
     */
    public static List<Double> ratePlus(Collection<Double> data) {
        List<Double> plus = new ArrayList<>();
        Double pre = 1d;
        for (Double aListEarn : data) {
            if (aListEarn != null && !aListEarn.isNaN()) {
                pre = pre * (1 + aListEarn);
                plus.add(pre - 1);
            }else {
                plus.add(pre);
            }
        }
        return plus;
    }
	
	
	@Test
	public void testaaaa(){
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("quant");
		DBCollection quant = db.getCollection("quant_user_combination");
		DBCursor find = quant.find(new BasicDBObject("$or", Lists.newArrayList(new BasicDBObject("stat", 2), new BasicDBObject("src", new BasicDBObject("$ne", "")))),
                new BasicDBObject("_id", 1).append("crt", 1).append("factors", 1));
		while(find.hasNext()){
			DBObject dto = find.next();
			Map<String, Integer> map = (Map<String, Integer>) MongoUtils.getObjectByFieldNameChain(dto, "factors");
			List<String> list = Lists.newArrayList(Optional.ofNullable((Map<String, Integer>)MongoUtils.getObjectByFieldNameChain(dto, "factors")).orElse(Maps.newHashMap()).keySet());
			for (String string : list) {
				System.out.println(string);
			}
		}
	}
	
	
	
}
