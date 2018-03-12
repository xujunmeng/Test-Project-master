package com.junmeng.domain;

import org.apache.commons.lang.StringUtils;

import com.junmeng.core.common.BaseUtils;
import com.junmeng.core.common.CommunityConstants;
import com.junmeng.entity.User;
import com.junmeng.repository.UserRepository;

/**
@author junmeng.xu
@date  2016年6月1日下午3:31:29
 */
public class UserDomain {

	private UserRepository userRepository;
	
	/**
	 * 用户注册
	 */
	public String regist(String info, String pwd, int type, String nickname) {
		
		String pkey = BaseUtils.generateCommunityAccessKey();
		
		User user = userRepository.findUser(info);
		if(null != user){
			return CommunityConstants.regist_result_code.USER_REGISTEDED;
		}
		user = new User();
		if(type == 1){  //手机号注册
			user.setPhone(info);
			user.setPh_state(1);
		} else if (type == 2){  //邮箱注册
			user.setMail(info);
			user.setMl_state(1);
		}
		user.setPasswd(pwd);
		user.setRegist_type(type);
		user.setPkey(pkey);
		user.setNick_name(nickname);
		int result = userRepository.regist(user);
		if(result == 1) {
			return pkey;
		}
		return CommunityConstants.regist_result_code.FAILED_REGIST;
	}
	
	public String login(String info, String pwd, int type) {
		
		User user = userRepository.findUser(info);
		if (null == user) {
			return CommunityConstants.login_result_code.USER_INEXISTENCE;
		}
		
		if (StringUtils.equals(user.getPasswd(), pwd)) {
			userRepository.updateLastLoginTime(user.getPkey());
			return user.getPkey();
		}
		return CommunityConstants.login_result_code.FAILED_VERIFY_PWD;
	}
	
	
	
	
	
	
	
	
	

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


}
