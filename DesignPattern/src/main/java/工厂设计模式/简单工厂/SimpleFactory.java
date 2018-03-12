package 工厂设计模式.简单工厂;

/**
 * Created by Administrator on 2017/5/9.
 */
public class SimpleFactory {

    public static Product factory(String productName) {
        if (productName.equals("Washer")) {
            return new Washer();
        } else if (productName.equals("Icebox")) {
            return new Icebox();
        } else if (productName.equals("AirCondition")) {
            return new AirCondition();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Product washer = SimpleFactory.factory("Washer");
        System.out.println(washer);
    }

}
