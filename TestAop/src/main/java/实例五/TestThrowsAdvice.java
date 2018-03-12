package 实例五;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class TestThrowsAdvice {

    public static void main(String[] args) {

        String configPath = "实例五/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService)ctx.getBean("forumService");

        try{
            forumService.removeForum(10);
        } catch (Exception e) {

        }

        try{
            forumService.updateForum(new Forum());
        } catch (Exception e) {

        }

    }

}
