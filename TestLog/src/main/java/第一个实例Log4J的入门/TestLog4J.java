package 第一个实例Log4J的入门;

import org.apache.log4j.Logger;

/**
 * Created by james on 2017/5/26.
 */
public class TestLog4J {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(TestLog4J.class);
        logger.info("info测试。。。");
        logger.error("error测试。。。");
        logger.warn("warn测试。。。");
        logger.debug("debug测试。。。");
        logger.fatal("fatal测试。。。");

    }

}
