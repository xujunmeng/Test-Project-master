package simple;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




/**
@author junmeng.xu
@date  2016年4月28日上午11:03:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:data-source.xml")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public abstract class BaseTestCase {

}
