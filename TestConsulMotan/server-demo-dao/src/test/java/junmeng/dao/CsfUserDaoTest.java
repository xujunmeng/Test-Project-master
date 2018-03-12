package junmeng.dao;

import com.junmeng.dao.ICsfUserDao;
import com.junmeng.entity.CsfUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by junmeng.xu on 2016/11/11.
 */
public class CsfUserDaoTest extends BaseTest {

    @Autowired
    ICsfUserDao iCsfUserDao;

    @Test
    public void test(){
        CsfUser csfUser = new CsfUser();
        csfUser.setName("小赵");
        csfUser.setAge(22);
        csfUser.setRepresent("码农");
        csfUser.setCrt(new Date());
        csfUser.setUid(4);
        Integer s = iCsfUserDao.insertCsfUser(csfUser);
        System.out.println(s);
    }

    @Test
    public void test2(){

        Integer uuid = 1;
        Integer integer = iCsfUserDao.delCsfUserByUid(uuid);
        System.out.println(integer);

    }

    @Test
    public void test3(){
        CsfUser csfUser = new CsfUser();
        csfUser.setName("小赵");
        csfUser.setAge(22);
        csfUser.setRepresent("码农");
        csfUser.setCrt(new Date());
        csfUser.setUid(3);
        Integer integer = iCsfUserDao.updateCsfUser(csfUser);
        System.out.println(integer);
    }

    @Test
    public void test4(){
        List<CsfUser> csfUser = iCsfUserDao.findCsfUser();
        System.out.println(csfUser);
    }

}
