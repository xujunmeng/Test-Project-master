package junmeng.服务定位器模式;

/**
 * 为JNDI查询创建InitialContext
 * Created by james on 2017/9/13.
 */
public class InitialContext {

    public Object lookup(String jndiName) {
        if ("SERVICE1".equalsIgnoreCase(jndiName)) {
            System.out.println("Looking up and creating a new Service1 object");
            return new Service1();
        } else if ("SERVICE2".equalsIgnoreCase(jndiName)) {
            System.out.println("Looking up and creating a new Service2 object");
            return new Service2();
        }
        return null;
    }

}
