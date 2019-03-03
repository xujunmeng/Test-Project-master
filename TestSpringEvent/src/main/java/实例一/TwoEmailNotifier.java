package 实例一;

import org.springframework.context.ApplicationListener;
import org.springframework.util.ErrorHandler;

/**
 * @author james
 * @date 2018/11/7
 */
public class TwoEmailNotifier implements ApplicationListener<EmailEvent> {

    @Override
    public void onApplicationEvent(EmailEvent event) {

        System.out.println("TwoEmailNotifier 当前线程名称 : " + Thread.currentThread().getName());

        System.out.println(Thread.currentThread().getName() + " 邮件地址 : " + event.getAddress());

        System.out.println(Thread.currentThread().getName() + " 邮件内容 : " + event.getText());
    }
}
