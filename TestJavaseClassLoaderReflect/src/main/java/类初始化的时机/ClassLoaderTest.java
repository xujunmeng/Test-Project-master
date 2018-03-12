package 类初始化的时机;
/**
@author junmeng.xu
@date  2016年5月25日上午10:06:25
 */

class Tester{
	static{
		System.out.println("Tester类的静态初始化块...");
	}
}
public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		//加载Tester类
		classLoader.loadClass("类初始化的时机.Tester");
		System.out.println("系统加载Tester类");
		//初始化Tester类
		Class.forName("类初始化的时机.Tester");
		
		
	}
	
}
