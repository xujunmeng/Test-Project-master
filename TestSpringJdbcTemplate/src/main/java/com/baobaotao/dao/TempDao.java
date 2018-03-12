package com.baobaotao.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
@author junmeng.xu
@date  2016年6月13日下午5:21:00
 */
@Repository
public class TempDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
}
