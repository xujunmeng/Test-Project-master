package 使用反射生成JDK动态代理.动态代理和AOP;
/**
@author junmeng.xu
@date  2016年5月25日下午1:38:34
 */
public class Test {

	public static void main(String[] args) {
		
		//创建一个原始的GunDog对象，作为target
		Dog dog = new GunDog();
		
		//以指定的 dog 来创建动态代理对象
		Dog proxyDog = (Dog)MyProxyFactory.getProxy(dog);
		proxyDog.info();
		proxyDog.run();
		
	}
	
}
