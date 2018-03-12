package 工厂设计模式.工厂方法;

/**
 * Created by Administrator on 2017/5/9.
 */
public class CreateIcebox implements Factory{
    public Product create(){
        return new Icebox();
    }
}
