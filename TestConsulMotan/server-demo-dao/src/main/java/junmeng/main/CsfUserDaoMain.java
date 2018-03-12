package junmeng.main;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by junmeng.xu on 2016/11/11.
 */
public class CsfUserDaoMain {

    final static Logger logger = LoggerFactory.getLogger(CsfUserDaoMain.class);

    public static void main(String[] args) {

        PropertyConfigurator.configure(CsfUserDaoMain.class.getResource("/log4j.properties"));

        new ClassPathXmlApplicationContext(new String[] {"spring_root.xml"});

        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);

        //print
        System.out.println("server start ... ");

    }

}
