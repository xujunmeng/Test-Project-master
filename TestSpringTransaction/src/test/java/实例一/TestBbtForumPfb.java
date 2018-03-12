package 实例一;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import 实例一.service.BbtForum;

public class TestBbtForumPfb{

	public static void main(String[] args) throws Throwable{

		String configPath = "classpath:applicationContext-pfb.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		BbtForum bbtForum = ctx.getBean("bbtForum",BbtForum.class);


    	Topic topic = new Topic();
    	topic.setTopicTitle("Title -pfb");

    	Post post = new Post();
    	post.setPostText("post content -pfb");

    	topic.setPost(post);

    	bbtForum.addTopic(topic);

    }

	@Test
	public void test1(){
		String configPath = "classpath:applicationContext-pfb.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		BbtForum bbtForum = ctx.getBean("bbtForum",BbtForum.class);
		int num = bbtForum.getTopicNum();
		System.out.println(num);
	}
}
