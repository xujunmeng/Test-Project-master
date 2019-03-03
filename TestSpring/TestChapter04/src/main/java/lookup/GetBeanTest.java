package lookup;

/**
 * Created by james on 2018/4/11.
 */
public abstract class GetBeanTest {

    public abstract User getBean();

    public void showMe() {
        getBean().showMe();
    }

}
