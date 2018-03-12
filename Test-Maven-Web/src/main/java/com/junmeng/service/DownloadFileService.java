package com.junmeng.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by junmeng.xu on 2016/12/2.
 */
@Path("/download")
@Produces("application/json;charset=UTF-8")
public interface DownloadFileService {

    @GET
    @Path("/excel/file")
    String excelFile(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception;

}
