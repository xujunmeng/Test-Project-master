package 实例一;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Created by james on 2017/7/8.
 *
 * 唯一需要注意的就是定时任务类中的@Lazy(vlaue=false)，
 * 这个注解，这个是懒加载的注解，可以不写，但是如果把value改为true，定时任务是不会执行的。
 * 因为加上之后，spring容器初始化的时候就不会触发这个定时任务了。
 */
@Component
@Lazy(value = false)
public class ScheduledManager {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //任务一
    @Scheduled(cron = "0/5 * * * * ?")
    public void autoCardCalculate() {
        System.out.println(simpleDateFormat.format(new Date()) + " : 执行中任务1...");
    }

    //任务二
    @Scheduled(cron = "0/5 * * * * ?")
    public void autoCardCalculate2() {
        System.out.println(simpleDateFormat.format(new Date()) + " : 执行中任务2...");
    }

}
