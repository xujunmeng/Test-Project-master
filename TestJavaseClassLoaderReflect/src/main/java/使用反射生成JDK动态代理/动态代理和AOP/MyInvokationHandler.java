package 使用反射生成JDK动态代理.动态代理和AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
@author junmeng.xu
@date  2016年5月25日下午1:23:31
 */
public class MyInvokationHandler implements InvocationHandler {

	
	//需要被代理的对象
	private Object object;
	public void setObject(Object object) {
		this.object = object;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		DogUtil dogUtil = new DogUtil();
		dogUtil.method1();
		//以object作为主调来执行method方法
		Object result = method.invoke(object, args);
		dogUtil.method2();
		return result;
	}
	
}
