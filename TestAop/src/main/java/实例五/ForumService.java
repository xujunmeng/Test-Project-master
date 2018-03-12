package 实例五;

import java.sql.SQLException;

/**
 * Created by junmeng.xu on 2016/12/12.
 */
public class ForumService {

    public void removeForum(int forumId){
        throw new RuntimeException("运行异常.");
    }

    public void updateForum(Forum forum) throws Exception{
        throw new SQLException("数据更新操作异常");
    }

}
