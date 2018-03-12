package com.junmeng.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.junmeng.service.BaseService;
import com.junmeng.service.SimpleService;






public class SimpleServiceImpl extends BaseService implements SimpleService{
	
	protected Logger log = Logger.getLogger(getClass());
	
	@Override
	public String fetchPersonDetail(HttpServletRequest request, String id) {
		String str = "哈哈!!!";
		System.out.println(str);
		return build(str);
	}


	
}
