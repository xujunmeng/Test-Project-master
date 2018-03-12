package rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by junmeng.xu on 2016/10/21.
 * Description : REST服务测试类
 */
@Path("/rest")
public class RestService {

    @GET
    @Path("/test")
    public Response test(){
        String result = "Just do it . ";
        return Response.status(200).entity(result).build();
    }

}
