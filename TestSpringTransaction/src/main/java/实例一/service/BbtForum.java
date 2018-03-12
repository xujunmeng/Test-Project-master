package 实例一.service;

import 实例一.Forum;
import 实例一.Topic;

public interface BbtForum {

	void addTopic(Topic topic) throws Exception;

	void updateForum(Forum forum);

	Forum getForum(int forumId);

	int getForumNum();

	int getTopicNum();

}
