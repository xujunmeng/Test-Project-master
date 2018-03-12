package 第二个实例logback的入门;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by james on 2017/5/27.
 */
public class BlogAction {

    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(BlogAction.class);

    public static void main(String[] args) {
        logger.info("logback 成功了");
        logger.error("logback 成功了");
    }



}
