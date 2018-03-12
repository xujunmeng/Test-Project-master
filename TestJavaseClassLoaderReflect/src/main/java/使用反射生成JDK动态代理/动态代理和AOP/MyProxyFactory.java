package 使用反射生成JDK动态代理.动态代理和AOP;

import java.lang.reflect.Proxy;

/**
@author junmeng.xu
@date  2016年5月25日下午1:29:48
 */
public class MyProxyFactory {

	//为指定target生成动态代理对象
	public static Object getProxy(Object target){
		
		//创建一个MyInvokationHandler对象
		MyInvokationHandler myInvokationHandler = new MyInvokationHandler();
		
		//为MyInvokationHandler设置target对象
		myInvokationHandler.setObject(target);
		
		//创建，并返回一个动态代理
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), myInvokationHandler);
		
	}
	
}
