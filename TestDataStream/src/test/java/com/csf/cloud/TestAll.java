package com.csf.cloud;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csf.cloud.dao.juchao.announce.TbText0042Dao;
import com.csf.cloud.entity.juchao.announce.TbText0042;
import com.csf.cloud.service.announce.AnnounceExecute;
import com.csf.cloud.service.announce.AnnounceTriggerExecute;
import com.google.common.collect.Lists;

/**
@author junmeng.xu
@date  2016年9月8日下午4:12:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/applicationContext.xml"})
public class TestAll {

	@Autowired
	AnnounceTriggerExecute announceTriggerExecute;
	
	@Autowired
	AnnounceExecute announceExecute;
	
	@Autowired
	TbText0042Dao tbText0042Dao;
	
	@Test
	public void testall() throws Exception{
		announceTriggerExecute.service(null);
	}
	
	@Test
	public void testaa() throws Exception{
		List<String> data = Lists.newArrayList("1202679976", "1202679975", "1202679974", "1202679973");
		announceExecute.service(data, null);
	}
	
	@Test
	public void testdao(){
		Collection<String> sids = Lists.newArrayList("1202679976", "1202679975", "1202679974", "1202679973");
		List<TbText0042> list = tbText0042Dao.getFileBySids(sids);
		for (TbText0042 tbText0042 : list) {
			System.out.println(tbText0042);
		}
	}
	
}
