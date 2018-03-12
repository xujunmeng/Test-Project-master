package 第一个实例;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import 第一个实例.dao.UserDao;
import 第一个实例.model.User;
import 第一个实例.公司Interceptor.PagingCriteria;

import java.util.List;

/**
 * Created by james on 2017/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/ApplicationContext.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
        PagingCriteria criteria = new PagingCriteria();
        criteria.setPageIndex(0);
        criteria.setPageSize(2);
        List<User> users = userDao.getUsers(criteria);

        PagingCriteria criteria2 = new PagingCriteria();
        criteria2.setPageIndex(1);
        criteria2.setPageSize(2);
        List<User> users2 = userDao.getUsers(criteria2);
        System.out.println(users);
    }

}
