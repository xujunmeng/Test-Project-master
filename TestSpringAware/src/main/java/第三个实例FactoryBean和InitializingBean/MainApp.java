package 第三个实例FactoryBean和InitializingBean;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author james
 * @date 2018/7/13
 */
public class MainApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("第三个实例FactoryBean和InitializingBean/bean.xml");

        Student student1 = (Student )context.getBean("stuFactory");
        Student student2 = (Student )context.getBean("stuFactory");
        System.out.println(student1 == student2);

        Teacher teacher1 = (Teacher)context.getBean("teacherFactory");
        Teacher teacher2 = (Teacher)context.getBean("teacherFactory");
        System.out.println(teacher1 == teacher2);


    }
}
