package 使用反射生成JDK动态代理.动态代理和AOP;
/**
@author junmeng.xu
@date  2016年5月25日下午1:18:59
 */
public class GunDog implements Dog {

	public void info() {
		System.out.println("我是一个猎狗");
	}
	public void run() {
		System.out.println("我奔跑迅速");
	}
	
}
