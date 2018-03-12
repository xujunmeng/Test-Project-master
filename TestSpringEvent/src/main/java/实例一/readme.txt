ApplicationContext事件机制是观察者设计模式的实现，通过ApplicationEvent类和ApplicationListener接口，可以实现ApplicationContext事件处理。如果容器中有一个ApplicationListener Bean，每当ApplicationContext发布ApplicationEvent时，ApplicationListener Bean将自动被触发。

    Spring的事件框架有如下两个重要的成员：

ApplicationEvent：容器事件，必须由ApplicationContext发布

ApplicationListener：监听器，可由容器中的任何监听器Bean担任

    实际上，Spring的事件机制与所有时间机制都基本相似，它们都需要事件源、事件和事件监听器组成。只是此处的事件源是ApplicationContext，且事件必须由Java程序显式触发。下面的程序将演示Spring容器的事件机制。程序先定义了一个ApplicationEvent类，其对象就是一个Spring容器事件。代码如下：

public class EmailEvent extends ApplicationEvent{
　　 private String address;
　　 private String text;
　　 public EmailEvent(Object source, String address, String text){
　　 super(source);
　　　　　 this.address = address;
　　　　　 this.text = text;
　　 }
　　 public EmailEvent(Object source) {
　　　　　super(source);
　　 }
　　 //......address和text的setter、getter
}
　　上面的EmailEvent类继承了ApplicationEvent类，除此之外，它就是一个普通的Java类。

　　容器事件的监听器类必须实现ApplicationListener接口，实现该接口就必须实现如下方法：

onApplicationEvent(ApplicationEvent event)：每当容器内发生任何事件时，此方法都会被触发

　　本例所使用的容器监听器类代码如下：

public class EmailNotifier implements ApplicationListener{
　　 public void onApplicationEvent(ApplicationEvent event) {
　　　　　if (event instanceof EmailEvent) {
　　　　　　　 EmailEvent emailEvent = (EmailEvent)event;
　　　　　　　 System.out.println("邮件地址：" + emailEvent.getAddress());
　　　　　　　 System.our.println("邮件内容：" + emailEvent.getText());
　　　　　} else {
　　　　　　　 System.our.println("容器本身事件：" + event);
　　　　　}
　　 }
}
　　将监听器配置在Spring的容器中，代码如下：

<bean class="com.abc.EmailNotifier" />
　　为Spring容器注册监听器，不需要像AWT编程那样采用代码进行注册，只需要在Spring的配置文件中进行简单配置即可。当我们住唉Spring中配置了一个实现了ApplicationListener的Bean，Spring容器就会把这个Bean当成容器事件的监听器。

　　当系统创建Spring容器、加载Spring容器时会自动触发容器事件，容器事件监听器可以监听到这些事件。除此之外，程序也可以调用ApplicationContext的publishEvent()方法来主动触发一个容器事件，如下是一个例子：

public class SpringTest {
　　 public static void main(String args[]){
　　　　　ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
　　　　　//创建一个ApplicationEvent对象
　　　　　EmailEvent event = new EmailEvent("hello","abc@163.com","This is a test");
　　　　　//主动触发该事件
　　　　　context.publishEvent(event);
　　 }
}
　　注意：如果Bean想发布事件，则Bean必须获得其容器的引用。如果程序中没有直接获取容器的引用，则应该让Bean实现ApplicationContextAware或者BeanFactoryAware接口，从而可以获得容器的引用。

　　Spring提供如下几个内置事件：

ContextRefreshedEvent：ApplicationContext容器初始化或刷新时触发该事件。此处的初始化是指：所有的Bean被成功装载，后处理Bean被检测并激活，所有Singleton Bean 被预实例化，ApplicationContext容器已就绪可用

ContextStartedEvent：当使用ConfigurableApplicationContext(ApplicationContext的子接口）接口的start()方法启动ApplicationContext容器时触发该事件。容器管理声明周期的Bean实例将获得一个指定的启动信号，这在经常需要停止后重新启动的场合比较常见

ContextClosedEvent：当使用ConfigurableApplicationContext接口的close()方法关闭ApplicationContext时触发该事件

ContextStoppedEvent：当使用ConfigurableApplicationContext接口的stop()方法使ApplicationContext容器停止时触发该事件。此处的停止，意味着容器管理生命周期的Bean实例将获得一个指定的停止信号，被停止的Spring容器可再次调用start()方法重新启动

RequestHandledEvent：Web相关事件，只能应用于使用DispatcherServlet的Web应用。在使用Spring作为前端的MVC控制器时，当Spring处理用户请求结束后，系统会自动触发该事件。