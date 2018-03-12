package com.junmeng.core.common;

import org.apache.commons.lang.StringUtils;

import com.aug3.sys.util.MD5;

/**
@author junmeng.xu
@date  2016年6月1日下午4:10:14
 */
public class BaseUtils {
	
	public static String generateCommunityAccessKey(){
		long m = System.currentTimeMillis() / 1000;
		return MD5.md5(String.valueOf(m));
	}
	
	//验证白名单
	public static boolean checkWhiteList(String mobileCode){
		String sw = ConfigManager.getProperty("white.list.switch");
		String list = ConfigManager.getProperty("white.list");
		if (StringUtils.isNotBlank(list) && "true".equals(sw) && list.contains(mobileCode) ) {
			return true;
		}
		return false;
	}
	
}
