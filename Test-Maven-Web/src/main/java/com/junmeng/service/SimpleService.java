package com.junmeng.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;


@Path("/simple")
@Produces("application/json;charset=UTF-8")
public interface SimpleService {

	@GET
	@Path("/person/detail")
	String fetchPersonDetail(@Context HttpServletRequest request, @QueryParam("id") String id);
	
	
}
