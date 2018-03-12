package 测试删除txt;

import org.junit.Test;

/**
 * Created by junmeng.xu on 2016/12/24.
 */
public class Main {

    @Test
    public void test(){
        String str = "/announce/fund/20010101/asdfasdf.pdf";
        int length = str.length();
        String bb = str.substring(0, length - 4);
        String cc = bb + ".txt";
        System.out.println(cc);
    }

}
