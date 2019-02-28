package 实例一;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by james on 2017/7/7.
 */
public class OneEmailNotifier implements ApplicationListener<EmailEvent> {

    //@Async
    @Override
    public void onApplicationEvent(EmailEvent event) {

        System.out.println("OneEmailNotifier 当前线程名称 : " + Thread.currentThread().getName());

        System.out.println(Thread.currentThread().getName() + " 邮件地址 : " + event.getAddress());

        System.out.println(Thread.currentThread().getName() + " 邮件内容 : " + event.getText());
    }
}
