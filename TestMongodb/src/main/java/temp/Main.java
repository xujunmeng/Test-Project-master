package temp;

import com.aug3.storage.mongoclient.MongoUtils;
import com.google.common.collect.Lists;
import com.mongodb.*;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
@author junmeng.xu
@date  2016年4月19日下午6:26:03
 */
public class Main {

	public static void main(String[] args) throws ParseException {
		
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection report = db.getCollection("fin_rpt_catlog");
		BasicDBObject query = new BasicDBObject();
		Calendar calendar = Calendar.getInstance();  
	      // 对 calendar 设置时间的方法  
	      // 设置传入的时间格式  
	      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	      // 指定一个日期  
	      Date date = dateFormat.parse("2016-04-13 08:00:00");  
	      // 对 calendar 设置为 date 所定的日期  
	      calendar.setTime(date);  
	      
	      
		query.put("rdt", date);
		query.put("src.cd", "");
		query.put("src.szh", "");
		query.put("typ.cd", "");
		query.put("typ.szh", "");
		int count = report.find(query).count();
		System.out.println(count);
		DBCursor cur = report.find(query);
		DBObject dto = null;
		while (cur.hasNext()) {
			dto = cur.next();
			
			String url = MongoUtils.getStringByFieldNameChain(dto, "url");
			BasicDBObject q = new BasicDBObject("url", url);
			
			report.update(q, new BasicDBObject("$set", new BasicDBObject("stat", 0)));
			
		}
	}
	//MongoAdaptor.getDBCollection(MongoCollections.COLL_ANNOUNCEMENT).update(fields, new BasicDBObject("$set", keys));

	// 修补 secu.org 为空
	@Test
	public void testse() throws Exception{
		Mongo mg = new Mongo("122.144.134.95", 27017);
		DB db = mg.getDB("news");
		DBCollection coll = db.getCollection("announcement_fund");
		BasicDBObject query = new BasicDBObject();
		query.put("secu.org", "");
		DBCursor cur = coll.find(query);
		DBObject dto = null;
		List<String> secucds = Lists.newArrayList();

		DB db2 = mg.getDB("fund");
		DBCollection coll2 = db2.getCollection("base_fund");
		DBCursor cur2 = coll2.find(new BasicDBObject("code", new BasicDBObject("$in", secucds)));
		DBObject dto2 = null;
		List<String> ids = Lists.newArrayList();
		while(cur2.hasNext()){
			dto2 = cur2.next();
			String _id = MongoUtils.getStringByFieldNameChain(dto2, "_id");
			String orgid = MongoUtils.getStringByFieldNameChain(dto2, "org.id");
			if (StringUtils.isBlank(orgid)) {
				ids.add(_id);
			}
		}
		System.out.println("base_fund 缺少org.id的记录总数：" + ids.size());
		System.out.println(ids);

	}

}
