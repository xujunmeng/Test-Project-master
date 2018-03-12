package 第一个实例ApplicationContextAware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Created by james on 2017/7/7.
 * Aware    意识到的;知道的;觉察到的
 */
public class HelloBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private String helloWord = "Hello!World";

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    public void setHelloWord(String helloWord) {
        this.helloWord = helloWord;
    }

    public String getHelloWord() {
        PropertyGettedEvent propertyGettedEvent = new PropertyGettedEvent(helloWord + " ~~~ is getted .");

        Object source = propertyGettedEvent.getSource();

        String s = source.toString();

        System.out.println("event : " + s);

        applicationContext.publishEvent(propertyGettedEvent);

        return helloWord;
    }
}
