package 实例六注解;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class ForumService {

    @NeedTest(value = true)
    public void deleteForum(int forumId){
        System.out.println("删除论坛模块 : " + forumId);
    }

    @NeedTest(value = false)
    public void deleteTopic(int postId){
        System.out.println("删除论坛主题 : " + postId);
    }

}
