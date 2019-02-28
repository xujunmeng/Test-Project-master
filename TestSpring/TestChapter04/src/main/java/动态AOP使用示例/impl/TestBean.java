package 动态AOP使用示例.impl;

import org.springframework.stereotype.Component;
import 动态AOP使用示例.ITestBean;

/**
 * Created by james on 2018/5/10.
 */
@Component
public class TestBean implements ITestBean {

    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    @Override
    public void test() {
        int i = 1/0;
        System.out.println("testssssxxxxxx");
    }
}
