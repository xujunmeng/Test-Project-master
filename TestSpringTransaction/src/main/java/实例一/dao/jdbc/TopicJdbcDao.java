package 实例一.dao.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import 实例一.Topic;
import 实例一.dao.TopicDao;


public class TopicJdbcDao extends JdbcDaoSupport implements TopicDao {

	public void addTopic(final Topic topic) {
		final String sql = "INSERT INTO t_topic(topic_title) VALUES(?)";
        		
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		getJdbcTemplate().update(new PreparedStatementCreator() {
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ps.setString(1, topic.getTopicTitle());
//				return ps;
//			}
//		}, keyHolder);
//		topic.setTopicId(keyHolder.getKey().intValue());
		
		Object[] params = new Object[]{topic.getTopicTitle()};
		getJdbcTemplate().update(sql, params);
	}

	@Override
	public int getTopicNum() {
		final String sql = "SELECT COUNT(1) FROM t_topic";
		return getJdbcTemplate().queryForInt(sql);
	}
}
