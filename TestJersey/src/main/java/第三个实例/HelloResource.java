package 第三个实例;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by james on 2017/5/27.
 */
@Path("/hello2")
public class HelloResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {

        LOGGER.info("sayHello info log ...");

        LOGGER.error("sayHello error log ...");

        return "Hello Jersey !";

    }

}
