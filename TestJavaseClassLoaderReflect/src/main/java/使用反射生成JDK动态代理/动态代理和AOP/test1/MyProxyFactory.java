package 使用反射生成JDK动态代理.动态代理和AOP.test1;

import java.lang.reflect.Proxy;

/**
@author junmeng.xu
@date  2016年5月25日下午2:06:09
 */
public class MyProxyFactory {


	
	public Object getProxy(Object target){
		
		
		MyInvokationHandler myInvokationHandler = new MyInvokationHandler();
		myInvokationHandler.setObject(target);
		
		return (Dog)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), myInvokationHandler);
		
	}
	
}
