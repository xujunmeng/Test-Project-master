package temp;

import com.aug3.storage.mongoclient.MongoUtils;
import com.google.common.collect.Maps;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
@author junmeng.xu
@date  2016年5月3日上午10:47:23
 */
public class Main6 {

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
        Map<String, Map<String, Double>> combinationEarnRate = new LinkedHashMap<>();

        while (cur.hasNext()) {

            DBObject dbo = cur.next();
            String cd = MongoUtils.getStringByFieldNameChain(dbo, "cd");
            Map<String, Double> items = Optional
                    .ofNullable((Map<String, Double>) MongoUtils.getObjectByFieldNameChain(dbo, "items"))
                    .orElse(Maps.newHashMap());
            if (!combinationEarnRate.containsKey(cd)) combinationEarnRate.put(cd, new TreeMap<>());
            combinationEarnRate.get(cd).putAll(items);
        }
        
        for(Entry<String, Map<String, Double>> entry : combinationEarnRate.entrySet()){
        	System.out.println(entry.getKey() + " : " + entry.getValue());
        }
//        
//        
//        // 最大回撤
//        Map<String, Double> maxDrawbackMap = new HashMap<>();
//        combinationEarnRate.forEach((s, doubles) -> {
//        	
//        	List<Double> ratePlus = ratePlus(doubles);
//        	
//        	Double maxDrawback = maxDrawback(ratePlus);
//        	
//            maxDrawbackMap.put(s, maxDrawback);
//        });
//        // 总收益
//        Map<String, Double> earnPlus = new HashMap<>();
//        combinationEarnRate.forEach((s, doubles) -> {
//            double plus = doubles.stream().filter(b -> b != null && !b.isNaN())
//                    .mapToDouble(c -> c + 1).reduce((p, b) -> p * b).orElse(1d) - 1;
//            earnPlus.put(s, plus);
//        });
//
//        this.updateCombinationEarnPlus(earnPlus, maxDrawbackMap);
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


    /**
     * 没有记录时，插入该条记录
     * 有该条记录时，更新该条记录
     */
    @Test
    public void tests(){
        Mongo mg = new Mongo("192.168.250.200", 27017);
        DB db = mg.getDB("metrics");
        DBCollection coll = db.getCollection("comm_idx_event_cur");
        coll.update(new BasicDBObject(), new BasicDBObject("$set", new BasicDBObject("dt", "2007-10-15").append("upt", new Date())), true, true);


    }
    @Test
    public void set2(){
        Mongo mg = new Mongo("192.168.250.200", 27017);
        DB db = mg.getDB("metrics");
        DBCollection coll = db.getCollection("comm_idx_event_cur");
        DBObject one = coll.findOne(new BasicDBObject());
        String dt = (String)one.get("dt");
        System.out.println(dt);
    }

    @Test
    public void tes(){
        Mongo mg = new Mongo("192.168.250.200", 27017);
        DB db = mg.getDB("metrics");
        DBCollection coll = db.getCollection("comm_idx_event_his_a");
        DBCursor dbCursor = coll.find(new BasicDBObject()).skip(5).limit(100);
        while(dbCursor.hasNext()){
            DBObject next = dbCursor.next();
            String tick = (String)next.get("tick");
            System.out.println(tick);
        }

    }
	
}
