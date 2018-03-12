package 第一个实例;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by james on 2017/5/26.
 */
@Service
public class LogonService implements BeanNameAware {

    @Autowired(required = false)
    private LogDao logDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public void setBeanName(String beanName) {
        System.out.println("beanName : " + beanName);
    }
}
