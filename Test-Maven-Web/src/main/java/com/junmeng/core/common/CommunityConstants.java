package com.junmeng.core.common;
/**
@author junmeng.xu
@date  2016年6月1日下午5:41:53
 */
public class CommunityConstants {

	
	public interface regist_result_code {
		
		//已注册
		String USER_REGISTEDED = "1";
		
		//验证码验证失败
		String FAILED_REGIST = "3";
	}
	
	public interface login_result_code {
		
		//用户不存在
		String USER_INEXISTENCE = "1";
		
		//密码验证失败
		String FAILED_VERIFY_PWD = "3";
	}
	
}
