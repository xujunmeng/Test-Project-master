package junmeng.服务定位器模式;

/**
 *
 * Created by james on 2017/9/13.
 */
public class ServiceLocatorPatternDemo {

    public static void main(String[] args) {

        Service service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();

        service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();


    }

}
