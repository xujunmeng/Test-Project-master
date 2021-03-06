package 第三个实例;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by james on 2017/5/27.
 */
public class APIApplication extends ResourceConfig {

    public APIApplication() {

        //加载Resource
        register(HelloResource.class);

        //注册数据转换器
        register(JacksonJsonProvider.class);

        //Logging
        register(AhsLoggingFilter.class);
    }

}
