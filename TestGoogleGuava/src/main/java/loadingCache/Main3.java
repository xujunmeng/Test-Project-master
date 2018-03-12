package loadingCache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by junmeng.xu on 2016/12/14.
 */
public class Main3 {

    private static final Logger log = Logger.getLogger(Main3.class);

    private static Cache<String, Temp2> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    @Test
    public void test1(){
        Temp2 temp2 = test24("ccc");
        System.out.println(temp2);
        Temp2 ccc = test24("ccc");
        System.out.println(ccc);
    }

    @Test
    public void test2(){
        Temp2 temp2 = test25("ccc");
        System.out.println(temp2);
        Temp2 ccc = test25("ccc");
        System.out.println(ccc);
    }


    public Temp2 test25(String key){
        Temp2 temp2 = null;
        try {
            temp2 = cache.get(key, () -> daoTest2(key));
        } catch (ExecutionException e) {
            log.error(e, e);
            return null;
        }
        return temp2;
    }

    public Temp2 test24(String key){
        Temp2 temp2 = null;
        try {
            temp2 = cache.get(key, () -> daoTest(key));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return temp2;
    }

    public Temp2 daoTest(String key){
        System.out.println("=====调用daoTest=====");
        Map<String, Temp2> map = new HashMap<>();
        map.put("aaa", new Temp2("name1", "男"));
        map.put("bbb", new Temp2("name2", "男"));
        map.put("ccc", new Temp2("name3", "男"));
        map.put("ddd", new Temp2("name4", "男"));
        map.put("eee", new Temp2("name5", "男"));
        return map.get(key);
    }

    public Temp2 daoTest2(String key){
        System.out.println("=====调用daoTest=====");
        return null;
    }










}
class Temp2 {
    private String name;
    private String sex;

    public Temp2(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Temp2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Temp2{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}