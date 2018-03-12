package 第一个实例.dao;

import 第一个实例.model.User;
import 第一个实例.公司Interceptor.PagingCriteria;

import java.util.List;

/**
 * Created by james on 2017/7/14.
 */
public interface UserDao {

    User getUser(User user);

    List<User> getUsers(PagingCriteria criteria);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int UserId);

}
