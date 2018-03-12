package 第一个实例;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by james on 2017/5/27.
 */
@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello Jersey !";
    }

    @POST
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserById(Order inputOrder) {
        System.out.println("Received order from :"+inputOrder.getCustmer());
        System.out.println("Order worth: "+inputOrder.getAmount());
        System.out.println("Customer address: "+inputOrder.getAddress());

        return Response.status(200).entity("Your order is in-progress").build();
    }

}
