package redis.dao.实例二;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.BaseTestCase;

import java.util.Set;

/**
 * Created by james on 2017/6/13.
 */
public class CommonDAOTest extends BaseTestCase {

    @Autowired
    private CommonDAO commonDAO;

    @Test
    public void test() {
        Long b = commonDAO.setSerialNumber("exception_serial_number", "YC20170608002600");
        System.out.println(b);
    }

    @Test
    public void test2() {
        Set<String> number = commonDAO.getSerialNumber("exception_serial_number");
        System.out.println(number);
    }

    @Test
    public void test3() {
        boolean exists = commonDAO.exists("exception_serial_number", "YC201706080026");
        System.out.println(exists);
    }

    @Test
    public void test4() {
        boolean exception_serial_number = commonDAO.liveTime("exception_serial_number", 50);
        System.out.println(exception_serial_number);

    }

}
