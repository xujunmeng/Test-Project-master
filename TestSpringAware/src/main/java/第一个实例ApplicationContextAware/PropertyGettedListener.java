package 第一个实例ApplicationContextAware;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by james on 2017/7/7.
 */
public class PropertyGettedListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        String s = source.toString();
        System.out.println("listener : " + s);
    }
}
