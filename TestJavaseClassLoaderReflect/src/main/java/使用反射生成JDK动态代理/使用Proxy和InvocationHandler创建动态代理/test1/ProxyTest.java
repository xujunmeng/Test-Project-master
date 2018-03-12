package 使用反射生成JDK动态代理.使用Proxy和InvocationHandler创建动态代理.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
@author junmeng.xu
@date  2016年5月25日下午1:46:36
 */
interface Person{
	void aaa();
	void bbb(String b);
}
class MyInvokationHandler implements InvocationHandler{
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println(method);
		if(null != args){
			for (Object obj : args) {
				System.out.println(obj);
			}
		}
		return null;
	}
}
public class ProxyTest {

	public static void main(String[] args) {
		
		InvocationHandler invocationHandler = new MyInvokationHandler();
		
		Person p = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, invocationHandler);
		p.aaa();
		p.bbb("asdfasdfasdfasdf");
		
	}
	
}
