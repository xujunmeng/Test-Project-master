package 第一个例子;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
@author junmeng.xu
@date  2016年5月31日下午1:16:59
 */

@Path(value="/student/{id}")
@Produces("application/xml")
public interface IStudentService {

	@GET
	@Path(value="/info")
	Student getStudent(@PathParam("id") long id, @QueryParam("name") String name);
	
	@GET
	@Path(value="/info2")
	Student getStudent(@QueryParam("name") String name);
	
}
