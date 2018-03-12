package CXF入门示例2;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
@author junmeng.xu
@date  2016年5月31日下午2:26:41
 */
public class HelloWorldServiceClient {

	public static void main(String[] args) {
		
		//调用webservice
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(IHelloWorldService.class);
		factory.setAddress("http://localhost:9000/helloWorld");
		
		IHelloWorldService service = (IHelloWorldService)factory.create();
		System.out.println("result : " + service.sayHello("徐军猛"));
		
	}
	
}
