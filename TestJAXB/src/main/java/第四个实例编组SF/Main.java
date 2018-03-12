package 第四个实例编组SF;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {

        try {
            List<Route> routes = new ArrayList<Route>();
            Route route = new Route();
            route.setRemark("易碎物品，轻拿轻放!");
            route.setAccept_address("上海市");
            route.setAccept_time("2016-6-21 8:32:54");
            route.setOpcode("0010");
            routes.add(route);

            List<RouteResponse> routeResponses = new ArrayList<RouteResponse>();
            RouteResponse routeResponse = new RouteResponse();
            routeResponse.setMailno("123456");
            routeResponse.setRoute(routes);
            routeResponses.add(routeResponse);

            Response response = new Response();
            response.setBody(routeResponses);
            response.setHead("BSPdevelop");
            response.setService("routeService");

            //初始化 jaxb marshaler
            JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            /* 设置为格式化输出 */
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            /* 将java对象 编组 为xml (输出到文件或标准输出) */
            jaxbMarshaller.marshal( response, new File( "routes.xml" ) );
            jaxbMarshaller.marshal( response, System.out );

    } catch (Exception e) {
        e.printStackTrace();
    }

    }

}
