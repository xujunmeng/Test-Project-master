package 使用反射生成JDK动态代理.使用Proxy和InvocationHandler创建动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
@author junmeng.xu
@date  2016年5月25日上午11:01:28
 */

interface Person{
	
	void walk();
	void sayHello(String name);
}

class MyInvoketionHandler implements InvocationHandler{
	/**
	 * 执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法
	 * 其中：
	 * proxy----代表动态代理对象
	 * method----代表正在执行的方法
	 * args----代表执行代理对象方法时传入的实参
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("动态代理对象    正在执行的方法 : " + method);
		if(args != null){
			System.out.println("动态代理对象   执行该方法时传入的实参 : ");
			for (Object val : args) {
				System.out.println("-=-=-=-=-=-=" + val);
				
			}
		} else {
			System.out.println("动态代理对象   调用该方法无须传入实参");
		}
		return null;
	}
}

public class ProxyTest {

	public static void main(String[] args) {
		
		//创建一个InvocationHandler对象
		InvocationHandler invocationHandler = new MyInvoketionHandler();
		//使用指定的InvocationHandler来生成一个动态代理对象
		Person p = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, invocationHandler);
		//动态代理对象 调用 walk()和sayHello方法
		p.walk();
		p.sayHello("yamadi");
		
	}
	
}
