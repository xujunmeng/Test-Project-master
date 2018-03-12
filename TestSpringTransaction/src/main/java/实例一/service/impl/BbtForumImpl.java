package 实例一.service.impl;

import 实例一.Forum;
import 实例一.Topic;
import 实例一.dao.ForumDao;
import 实例一.dao.PostDao;
import 实例一.dao.TopicDao;
import 实例一.service.BbtForum;

public class BbtForumImpl implements BbtForum {

	private ForumDao forumDao;

	private TopicDao topicDao;

	private PostDao postDao;

	public void addTopic(Topic topic) throws Exception {
		topicDao.addTopic(topic);
//		if(true) throw new PessimisticLockingFailureException("fail");
		postDao.addPost(topic.getPost());
	}

	public Forum getForum(int forumId) {
		return forumDao.getForum(forumId);
	}

	@Override
	public int getTopicNum() {
		return topicDao.getTopicNum();
	}

	public void updateForum(Forum forum) {
		forumDao.updateForum(forum);
	}

	public int getForumNum() {
		return forumDao.getForumNum();
	}
	
	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

}
