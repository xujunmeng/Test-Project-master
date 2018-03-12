package com.csf.cloud.dao.juchao.announce;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csf.cloud.entity.juchao.announce.TbText0047;

/**
@author junmeng.xu
@date  2016年9月8日下午2:21:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestAll {

	@Autowired
	TbText0047Dao tbText0047Dao;
	
	@Test
	public void test(){
		List<TbText0047> fetchJuchaoDatas = tbText0047Dao.fetchJuchaoDatas();
		for (TbText0047 tbText0047 : fetchJuchaoDatas) {
			System.out.println(tbText0047);
		}
	}
	
}
