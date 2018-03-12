package tools;

import dao.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by junmeng.xu on 2016/12/13.
 */
public class MysqlToolsTest extends BaseTest {

    @Autowired
    MysqlTools mysqlTools;

    //事务 批量处理 的测试
    @Test
    public void test1(){
        mysqlTools.testTempBatch();
    }

}
