package 适配器模式.缺省适配模式.实例一;

/**
 * Created by james on 2017/7/12.
 */
public class Test {

    public static void main(String[] args) {

        鲁智深 obj = new 鲁智深();

        String name = obj.getName();

        System.out.println(name);

        obj.习武();

    }

}
