package 循环依赖.构造器循环依赖;

/**
 * Created by james on 2018/4/16.
 */
public class TestC {

    private TestA testA;

    public TestC(TestA testA) {
        this.testA = testA;
    }

    public TestA getTestA() {
        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }
}
