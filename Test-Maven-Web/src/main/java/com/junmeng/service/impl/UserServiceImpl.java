package com.junmeng.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.aug3.sys.rs.response.RespType;
import com.junmeng.domain.UserDomain;
import com.junmeng.service.BaseService;
import com.junmeng.service.UserService;

/**
@author junmeng.xu
@date  2016年6月1日下午3:22:21
 */
public class UserServiceImpl extends BaseService implements UserService {

	private UserDomain userDomain; 
	
	@Override
	public String regist(HttpServletRequest request, String info, String pwd,
			int type, String nickname) {
		if (StringUtils.isBlank(info)) {
			return build(RespType.NULL_PARAMETERS);
		}
		return build(userDomain.regist(info, pwd, type, nickname));
	}

	@Override
	public String login(HttpServletRequest request, String info, String pwd,
			int type) {
		if (StringUtils.isBlank(info)) {
			return build(RespType.NULL_PARAMETERS);
		}
		return build(userDomain.login(info, pwd, type));
	}
	
	public void setUserDomain(UserDomain userDomain) {
		this.userDomain = userDomain;
	}

}
