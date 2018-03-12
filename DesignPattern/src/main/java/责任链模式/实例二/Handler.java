package 责任链模式.实例二;

/**
 * Created by james on 2017/9/18.
 */
public abstract class Handler {

    private Handler nextHandler;

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void doHandler();

}
