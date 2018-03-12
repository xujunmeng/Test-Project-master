package CXF入门示例;

import javax.xml.ws.Endpoint;

/**
@author junmeng.xu
@date  2016年5月31日下午2:18:27
 */
public class DeployHelloWorldService {

	public static void deployService(){
		System.out.println("Server start ......");
		HelloWorldService service = new HelloWorldService();
		String address = "http://localhost:9000/helloworld";
		Endpoint.publish(address, service);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		//发布WebService
		deployService();
		System.out.println("server ready ......");
		Thread.sleep(1000 * 60);
		System.out.println("server exiting");
		
		System.out.println(0);
		
	}
	
}
