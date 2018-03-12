package 第一个测试用例;

import com.aihuishou.service.client.AbstractRequest;
import com.aihuishou.service.client.IClient;
import com.aihuishou.service.client.impl.JerseyClient;
import com.aihuishou.service.client.spring.JerseyClientFactory;
import org.junit.Test;

/**
 * Created by james on 2017/6/1.
 */
public class Main {



    @Test
    public void test2() throws Exception {

        JerseyClientFactory jerseyClientFactory = new JerseyClientFactory();

        String uri = "http://localhost:8080/services/hello";

        jerseyClientFactory.setServiceUrl(uri);

        JerseyClient jerseyClient = jerseyClientFactory.getObject();

        String s = jerseyClient.invoke(new AbstractRequest(), String.class);

        System.out.println(s);
    }


}
