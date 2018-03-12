package 实例一;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import 实例一.service.BbtForum;

/**
 * Created by junmeng.xu on 2016/12/13.
 */
public class TestBbtForumtx {

    public static void main(String[] args) throws Exception {

        String configPath = "classpath:applicationContext-tx.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        BbtForum bbtForum = ctx.getBean("bbtForum",BbtForum.class);


        Topic topic = new Topic();
        topic.setTopicTitle("Title -pfb");

        Post post = new Post();
        post.setPostText("post content -pfb");

        topic.setPost(post);

        bbtForum.addTopic(topic);

    }

}
