package 使用反射生成JDK动态代理.动态代理和AOP.test1;
/**
@author junmeng.xu
@date  2016年5月25日下午2:09:12
 */
public class Test {

	public static void main(String[] args) {
		
		Dog  d = new GunDog();
		
		MyProxyFactory myProxyFactory = new MyProxyFactory();
		Dog proxy = (Dog)myProxyFactory.getProxy(d);
		proxy.info();
		proxy.run();
		
	}
	
}
