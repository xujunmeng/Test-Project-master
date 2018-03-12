package com.csf.ada.datashift.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.hbase.client.Put;
import org.apache.log4j.Logger;

import com.csf.ada.datashift.dao.fund.AbstractFundDao;
import com.csf.app.base.MongoCollections;
import com.csf.platform.dataentry.common.Parameter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mongodb.DBObject;

/**
 * 
 * @author junmeng.xu
 *导入基金数据，目前所有导入到mysql and mongodb
 */
public class FundService {
	private static final Logger log = Logger.getLogger(FundService.class);
	private static final int PAGE_COUNT = 500;
	private AbstractFundDao<DBObject> fundMongoDao;
	private AbstractFundDao<Object[]> fundMysqlDao;
	private AbstractFundDao<Put> fundHbaseDao;
	private Map<String , Date> lastTime = new HashMap<String , Date>();
	
	
	// save to hbase
	protected static final Set<String> SAVE_TO_HBASE_COLL = Sets.newHashSet();
	
	// save to mysql
	protected static final Set<String> SAVE_TO_MYSQL = Sets.newHashSet(
			MongoCollections.COLL_FUND_NET_VALUE,
			MongoCollections.COLL_FUND_EARNING_DAILY,
			MongoCollections.COLL_BASE_ETF_COMPONENT,
			MongoCollections.COLL_FUND_STOCK_DETAIL
			
		);
			
	
	public int deltaShift(String table , Date from , Date to , String transportType){
		Parameter p = new Parameter();
		p.setFrom(from);
		p.setTo(to);
		long count = this.getDao(table).countAll(table, from, to);
		log.info("find "+table+" count: " + count +", " + "from " + from + " to " + to);
		long times = count%PAGE_COUNT == 0 ? count/PAGE_COUNT : count/PAGE_COUNT+1;
		int size = 0;
		for(int i = 0 ; i < times ; i++){
			p.setStartRowNum(PAGE_COUNT * i);
			p.setMaxRows(i+1 < times ? PAGE_COUNT*(i+1): (int)count);
			size += this.getDao(table).importDataFromJuchao(table , p , transportType);
		}
		return size;
	}
	
	
	public AbstractFundDao getDao(String table){
		if(SAVE_TO_HBASE_COLL.contains(table))
			return this.fundHbaseDao;
		if(SAVE_TO_MYSQL.contains(table))
			return this.fundMysqlDao;
		return this.fundMongoDao;
	}
	
	
	
	public Long updateByModifyTime(String table){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH , -2);
		Date from = calendar.getTime();
		Date to = this.getDao(table).latestModTime(table);
		lastTime.put(table, to);
		if(from == null || to == null)
			return 0L;
		Set<Long> ids = new HashSet<Long>();
		this.getDao(table).ids(table , from , to , ids);
		long count = ids.size();
		log.info("find "+table+" count: " + count +", " + "from time: " + from);
		long times = count%PAGE_COUNT == 0 ? count/PAGE_COUNT : count/PAGE_COUNT + 1;
		long size = 0;
		List<Long> litIds = Lists.newArrayList(ids);
        for (int i = 0; i < times; i++){
            int startRow = PAGE_COUNT * i;
            int endRow  = i+1 < times ? PAGE_COUNT*(i+1): (int) count;
            size += this.getDao(table).importDataFromJuchao(table, litIds.subList(startRow, endRow));
        }
        return size;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
