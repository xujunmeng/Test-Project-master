package com.junmeng.service;

import com.aug3.sys.rs.response.RespObj;
import com.aug3.sys.rs.response.RespType;
import com.aug3.sys.util.JSONUtil;

/**
@author junmeng.xu
@date  2016年6月1日下午2:34:45
 */
public class BaseService {

	protected String build(Object obj){
		if(obj instanceof RespType) {
			RespType type = (RespType) obj;
			return build(type, obj);
		} else {
			return build(RespType.SUCCESS, obj);
		}
	}
	
	protected String build(RespType type, Object object){
		RespObj obj = new RespObj();
		obj.setCode(type.getCode());
		obj.setType(type.name());
		obj.setMessage(object);
		return JSONUtil.toJson(obj);
	}
	
}
