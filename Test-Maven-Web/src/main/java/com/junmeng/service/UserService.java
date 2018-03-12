package com.junmeng.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
@author junmeng.xu
@date  2016年6月1日下午3:17:20
 */
@Path("/user")
@Produces("application/json;charset=UTF-8")
public interface UserService {

	/**
	 *  注册
	 *  @param type 注册类型(1:手机号, 2:邮箱)
	 */
	@POST
	@Path("/regist")
	String regist(@Context HttpServletRequest request, 
			@FormParam("info") String info,
			@FormParam("pwd") String pwd,
			@FormParam("type") @DefaultValue("0") int type,
			@FormParam("nickname") String nickname);
	
	/**
	 *  登录
	 *  @param type 类型(1:手机号,2:邮箱,3:用户)
	 */
	@GET
	@Path("/login")
	String login(@Context HttpServletRequest request, 
			@QueryParam("info") String info, 
			@QueryParam("pwd") String pwd,
			@QueryParam("type") @DefaultValue("0") int type);
	
	
	
	
	
	
	
	
}
