package 循环依赖.构造器循环依赖;

/**
 * Created by james on 2018/4/16.
 */
public class TestA {

    private TestB testB;

    public TestA(TestB testB) {
        this.testB = testB;
    }

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}
