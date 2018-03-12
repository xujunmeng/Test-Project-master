package 第一个实例ApplicationContextAware;

import org.springframework.context.ApplicationEvent;

/**
 * Created by james on 2017/7/7.
 */
public class PropertyGettedEvent extends ApplicationEvent {

    public PropertyGettedEvent(Object source) {
        super(source);
    }

}
