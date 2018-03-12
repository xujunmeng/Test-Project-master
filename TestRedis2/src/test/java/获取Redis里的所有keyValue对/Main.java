package 获取Redis里的所有keyValue对;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by james on 2017/9/25.
 */
public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        //连接redis服务器，localhost:6379
        Jedis redis = new Jedis("192.168.3.114", 6379);
        // 获取所有key
        Set<byte[]> keySet = redis.keys("*".getBytes());
        byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
        // 获取所有value
        byte[][] values = redis.mget(keys).toArray(new byte[keySet.size()][]);

        // 打印key-value对
        for (int i = 0; i < keySet.size(); ++i) {
            System.out.println(byte2hex(keys[i]) + " --- " + byte2hex(values[i]));
        }

        long end = System.currentTimeMillis();
        // 计算耗时
        System.out.println("Query " + values.length + " pairs takes " + (end - start) + " millis");
        redis.close();
    }

    private static String byte2hex(byte[] buffer) {
        String h = "0x";

        for (byte aBuffer : buffer) {
            String temp = Integer.toHexString(aBuffer & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h = h + " " + temp;
        }

        return h;

    }

    @Test
    public void tesT() {
        Jedis redis = new Jedis("192.168.3.114", 6379);
        redis.select(3);
        Set<String> set = redis.zrangeByScore("USER_COUPON_REMIND", 0D, 2515772799000D);
        for (String s : set) {
            System.out.println(s.toString());
        }
    }

}
