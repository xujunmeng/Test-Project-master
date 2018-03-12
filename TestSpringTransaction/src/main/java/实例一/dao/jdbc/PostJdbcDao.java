package 实例一.dao.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import 实例一.Post;
import 实例一.dao.PostDao;


public class PostJdbcDao extends JdbcDaoSupport implements PostDao {

	public void addPost(final Post post) {
		String sql = " INSERT INTO t_post(topic_id,post_text)"
				+ " VALUES(?,?)";
        Object[] params = new Object[]{post.getTopicId(),post.getPostText()};
        getJdbcTemplate().update(sql, params);
	}
}
