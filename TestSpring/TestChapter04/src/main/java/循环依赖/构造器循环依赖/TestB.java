package 循环依赖.构造器循环依赖;

/**
 * Created by james on 2018/4/16.
 */
public class TestB {

    private TestC testC;

    public TestB(TestC testC) {
        this.testC = testC;
    }

    public TestC getTestC() {
        return testC;
    }

    public void setTestC(TestC testC) {
        this.testC = testC;
    }
}
