package 责任链模式.实例三;

/**
 * Created by james on 2017/9/18.
 */
public abstract class ConsumeHandler {

    private ConsumeHandler nextHandler;

    public ConsumeHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(ConsumeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * user申请人 free报销费用
     */
    public abstract void doHandler(String user, double free);

}
