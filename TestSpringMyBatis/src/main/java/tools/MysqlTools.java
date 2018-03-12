package tools;

import dao.UserDao;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junmeng.xu on 2016/12/13.
 */
public class MysqlTools {


    private UserDao userDao;

    //注意事务中  切点的地方，之前犯的错就是切点位置搞错了！！
    public void testTempBatch(){
        List<User> list = getObjs();
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++){
            userDao.insert2(list.get(i));
        }
        System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
    }

    public static List<User> getObjs(){
        List<User> result = new ArrayList<User>();
        for (int i = 0; i < 10000; i++) {
            User entity = new User();
            entity.setId(i);
            entity.setUsername("上海" + i);
            entity.setPassword("你猜" + i);
            entity.setPassword1("你猜" + i);
            entity.setPassword2("你猜" + i);
            entity.setPassword3("你猜" + i);
            entity.setPassword4("你猜" + i);
            entity.setPassword5("你猜" + i);
            entity.setPassword6("你猜" + i);
            entity.setPassword7("你猜" + i);
            entity.setPassword8("你猜" + i);
            result.add(entity);
        }
        return result;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
