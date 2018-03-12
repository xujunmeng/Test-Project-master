package 第二个实例BeanPostProcessor;

/**
 * Created by james on 2017/7/7.
 */
public class HelloWorld {

    private String data;

    public void setData(String data) {
        this.data = data;
    }

    public void getMessage() {
        System.out.println("Your Message : " + data);
    }

    public void init() {
        System.out.println("Bean is going through init. ");
    }

    public void destroy() {
        System.out.println("Bean will destroy now. ");
    }

}
