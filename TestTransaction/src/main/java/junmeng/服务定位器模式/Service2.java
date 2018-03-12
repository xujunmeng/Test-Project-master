package junmeng.服务定位器模式;

/**
 * 实体服务
 * Created by james on 2017/9/13.
 */
public class Service2 implements Service {

    @Override
    public String getName() {
        return "Service2";
    }

    @Override
    public void execute() {
        System.out.println("Executing Service2");
    }
}
