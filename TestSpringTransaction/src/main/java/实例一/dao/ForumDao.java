package 实例一.dao;

import 实例一.Forum;


public interface ForumDao {

   void addForum(Forum forum);

   void updateForum(Forum forum);

   Forum getForum(int forumId);

   int getForumNum();

}
