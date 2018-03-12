package redis.dao.实例三;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.BaseTestCase;
import redis.dao.实例二.CommonDAO;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by james on 2017/6/13.
 */
public class Main extends BaseTestCase {

    @Autowired
    private CommonDAO commonDAO;

    private static int number=0;

    private static String serialNumber;

    private static final Format DF = new SimpleDateFormat("yyyyMMdd");

    public boolean test222(String prefix, Date date) {

        //作为缓存的key
        String key = prefix + DF.format(date);

        //生成流水单号
        ++number;
        serialNumber = key + String.format("%04d", number);

        //去缓存比对是否存在
        boolean exists = commonDAO.exists(key, serialNumber);

       //不存在时
        if (!exists) {
            //把该单号放入缓存
            commonDAO.setSerialNumber(key, serialNumber);
            //设置该key的缓存时间 1天的有效期
            commonDAO.liveTime(key, 86400);
            return false;
        } else {
            return true;
        }
    }

    @Test
    public void test() {

        while(test222("YC", new Date())) {
        }
        System.out.println(serialNumber);

    }

}

