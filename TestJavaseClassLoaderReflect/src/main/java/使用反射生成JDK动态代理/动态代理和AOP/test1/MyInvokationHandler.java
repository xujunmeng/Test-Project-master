package 使用反射生成JDK动态代理.动态代理和AOP.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
@author junmeng.xu
@date  2016年5月25日下午2:04:06
 */
public class MyInvokationHandler implements InvocationHandler{

	private Object object;
	
	public void setObject(Object object) {
		this.object = object;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		DogUtil dogUtil = new DogUtil();
		dogUtil.method1();
		method.invoke(object, args);
		dogUtil.method2();
		return null;
	}
	
}

