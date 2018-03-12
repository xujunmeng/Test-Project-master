package 工厂设计模式.抽象工厂;

/**
 * Created by Administrator on 2017/5/9.
 */
//创建型号为B的产品工厂
public class FactoryB implements Factory{
    //创建洗衣机-B
    public Washer createWasher(){
        return new WasherB();
    }

    //创建冰箱-B
    public Icebox createIcebox(){
        return new IceboxB();
    }
}
