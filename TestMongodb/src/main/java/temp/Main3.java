package temp;

import com.aug3.storage.mongoclient.MongoUtils;
import com.aug3.sys.util.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.*;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
@author junmeng.xu
@date  2016年4月26日下午1:19:01
 */
public class Main3 {
	private static  Mongo mg = new Mongo("192.168.250.200", 27017);

	@Test
	public void test3333(){
		DB db = mg.getDB("test");
		DBCollection coll = db.getCollection("test");
		List<Map<String, String>> list = Lists.newArrayList();
		Map<String, String> map = Maps.newHashMap();
		map.put("300300", "123456");
		Map<String, String> map2 = Maps.newHashMap();
		map2.put("300300", "1234567");
		list.add(map);
		list.add(map2);

		Map<String, String> result = Maps.newHashMap();
		for (Map<String, String> stringMap : list) {
			for (Map.Entry<String, String> entry : stringMap.entrySet()) {
				String cd = entry.getKey();
				String org = entry.getValue();
				result.put(cd, org);
			}

		}
		System.out.println(result);


	}

	@Test
	public void test222(){
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		DBCursor cur = coll.find(query);
		while (cur.hasNext()) {
			DBObject next = cur.next();
			List<DBObject> obj = (List<DBObject>)MongoUtils.getObjectByFieldNameChain(next, "secu");
			String sid = MongoUtils.getStringByFieldNameChain(next, "sid");
			String title = MongoUtils.getStringByFieldNameChain(next, "title");
			String bid = MongoUtils.getStringByFieldNameChain(next, "bid");
			Map<String, String> map = Maps.newHashMap();

			BasicDBList list = new BasicDBList();
			for (DBObject dbObject : obj) {
				String cd = (String)dbObject.get("cd");
				String org = (String)dbObject.get("org");
				map.put(cd, org);
			}
			for (Map.Entry<String, String> entry : map.entrySet()) {
				list.add(new BasicDBObject("cd", entry.getKey()).append("org", entry.getValue()));
			}
			DBObject dd = new BasicDBObject("secu", list);
			dd.put("stat", 2);
			dd.put("upt", new Date());
			coll.update(new BasicDBObject("sid", sid).append("title", title).append("bid", bid), new BasicDBObject("$set", dd));
		}
	}




	public static void main(String[] args) throws ParseException {
		
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection report = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		
    	String date = "2016-04-26";
    	Date parseDate = DateUtil.parseDate(date);
		
		query.put("pub", parseDate);
		int count = report.find(query).count();
		System.out.println(count);
		DBCursor cur = report.find(query);
		DBObject dto = null;
		while (cur.hasNext()) {
			dto = cur.next();
			
			Date pub = (Date)MongoUtils.getObjectByFieldNameChain(dto, "pub");

			Object id = MongoUtils.getObjectByFieldNameChain(dto, "_id");
			
			
			BasicDBObject q = new BasicDBObject("_id", id);
			
			String pub2 = DateUtil.formatDate(new Date(pub.getTime()));
			
			report.update(q, new BasicDBObject("$set", new BasicDBObject("pub", pub2).append("stat", 2)));
			
		}
		
		
	}

	@Test
	public void testinsert222(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("metrics");
		DBCollection coll = db.getCollection("comm_idx_event_his_a");
		String dt = "2016-01-01";
		Map<String, Map<String, Double>> tickIndexVal = Maps.newHashMap();
		Map<String, Double> map = Maps.newHashMap();
		map.put("T0022", new Double(0.5645));
		map.put("T0023", new Double(0.8536));
		map.put("T0024", new Double(0.2387));
		map.put("T0025", new Double(0.7854));
		tickIndexVal.put("000005", map);

		Map<String, Double > map2 = Maps.newHashMap();
		map2.put("T0032", new Double(0.0642));
		map2.put("T0033", new Double(0.0694));
		map2.put("T0034", new Double(0.0831));
		map2.put("T0035", new Double(0.3551));
		tickIndexVal.put("000006", map2);

		List<BasicDBObject> list = Lists.newArrayList();
		for (Map.Entry<String, Map<String, Double>> entry : tickIndexVal.entrySet()) {
			BasicDBObject field = new BasicDBObject();
			String tick = entry.getKey();
			for (Map.Entry<String, Double> decimalEntry : entry.getValue().entrySet()) {
				String index = decimalEntry.getKey();
				Double value = decimalEntry.getValue();
				field.append(index, value);
			}
			field.append("tick", tick).append("dt", dt);
			list.add(field);
		}
		coll.insert(list);

	}

	@Test
	public void test22(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("metrics");
		DBCollection coll = db.getCollection("comm_idx_quant_ytd_his_a");
		coll.update(new BasicDBObject("f63", new BasicDBObject("$exists", true)), new BasicDBObject("$unset", new BasicDBObject("f63", "")),false,true);
	}

	@Test
	public void test333(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");
		BasicDBObject query = new BasicDBObject();
		query.put("code", "M007005");
		BasicDBObject field = new BasicDBObject();
		field.put("desc.szh", "MACD称为指数平滑异同平均线，是一种研判股票买卖时机、跟踪股价运行趋势的技术分析工具。\n");
		field.put("detail", "1、0轴线上，快线向上穿越慢线，金叉形式之一，表明市场处在强势中，股价稳步上扬。\n" +
				"2、0轴线下，快线向上穿越慢线，金叉另一形式，表明市场处于弱势末端，股价开始止跌企稳。\n" +
				"3、0轴线上，快线向下穿越慢线，死叉形式之一，表明市场处在强势末端，有调整压力。\n" +
				"4、0轴线下，快线向下穿越慢线，死叉另一形式，表明市场处在极度弱势中，反弹之后将再次杀跌。\n" +
				"Macd底背离与顶背离：\n" +
				"底背离：一般出现在股价或指数的低价位区，经过一段时间的调整后，股价继续创新低，指标却没有，说明空头势力开始枯竭，预示股价有望探底回升，看涨信号强烈\n" +
				"顶背离：k线图上，股价走势一峰比一峰高（股价创新高），而macd指标不创新高，是一种比较可靠的反转信号，说明股价已到上涨趋势末端，调整随时到来。");
		field.put("algorithm", new BasicDBObject("content", "DIF = EMA(CLOSE,N1)-EMA(CLOSE,N2)\n" +
				"DEA = EMA(DIF,N3)\n" +
				"MACD = 2*(DIF-DEA)").append("param", "N1=12，N2=26，N3=9"));
		coll.update(query, new BasicDBObject("$set", field));
	}

	@Test
	public void test444(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");
		BasicDBObject query = new BasicDBObject();
		query.put("code", "M007007");
		BasicDBObject field = new BasicDBObject();
		field.put("name.szh", "TEMA三重指数移动平均指标");
		field.put("desc.szh", "TEMA是对单一指数移动平均、双重指数移动平均和三重指数移动平均的综合，当存在趋势时，该指标的时滞比3个组成要素中的任何一个都要短。");
		coll.update(query, new BasicDBObject("$set", field));
	}

	@Test
	public void test555(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");
		BasicDBObject query = new BasicDBObject();
		query.put("code", "M007009");
		BasicDBObject field = new BasicDBObject();
		field.put("name.szh", "TMA三角移动平均线指标");
		coll.update(query, new BasicDBObject("$set", field));
	}

	@Test
	public void test666(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");
		BasicDBObject query = new BasicDBObject();
		query.put("code", "M007008");
		BasicDBObject field = new BasicDBObject();
		field.put("name.szh", "TS趋势分数指标");
		coll.update(query, new BasicDBObject("$set", field));
	}

	@Test
	public void test777(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");
		BasicDBObject query = new BasicDBObject();
		query.put("code", "M011006");
		BasicDBObject field = new BasicDBObject();
		field.put("name.szh", "TR真实波动范围指标");
		coll.update(query, new BasicDBObject("$set", field));
	}

	@Test
	public void test888(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");
		BasicDBObject query = new BasicDBObject();
		query.put("code", "M010007");
		BasicDBObject field = new BasicDBObject();
		field.put("desc.szh", "PVT指标与OBV指标类似，显示增长交易成交量总和计算平仓价的改变。区别是计算PVT指标时，当收盘价大于前收盘价时只有部分成交量会累加到之前的PVT指标上。");
		field.put("detail", "在能量潮指标（OBV）的情况下，如果平仓价处于高水平，我们添加当前成交量到当前指标值并且前去其余的价值。在 PVT的情况下，只有部分当前成交量被添加到减去已添加到PVT值，你必须指出前一个柱当前价格和平仓价之间的差别。\n" +
				"很多投资者认为在交易成交量方面 PVT要比OBV更能够展示细节。这是因为与我们添加到OBV值相同有关，平仓价高于第二次的值。在PVT的情况下，如果相关价格改变不大我们添加当前积累价值的一小部分。如果考虑价格改变，成交量的大部分会添加到PVT值。");
		coll.update(query, new BasicDBObject("$set", field));
	}

	@Test
	public void test999(){
		Mongo mg = new Mongo("192.168.250.200", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_dict_factors");
		BasicDBObject query = new BasicDBObject();
		query.put("code", "M010008");
		BasicDBObject field = new BasicDBObject();
		field.put("name.szh", "VAMA成交量加权移动均线指标");
		coll.update(query, new BasicDBObject("$set", field));
	}

	@Test
	public void test100(){
		List<ServerAddress> list = Lists.newArrayList();
		ServerAddress serverAddress = new ServerAddress("54.223.45.156");
		ServerAddress serverAddress2 = new ServerAddress("54.223.37.5");
		list.add(serverAddress);
		list.add(serverAddress2);
		Mongo mg = new Mongo(list);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_factor_comment");
		BasicDBObject query = new BasicDBObject();
		query.put("content", "这个讲的特别详细");
		coll.remove(query);
	}

	@Test
	public void test101(){
		Mongo mg = new Mongo("54.223.37.5", 27017);
		DB db = mg.getDB("quant");
		DBCollection coll = db.getCollection("quant_factor_comment");
		BasicDBObject query = new BasicDBObject();
		query.put("content", "这个讲的特别详细");
		coll.remove(query);
	}
}
