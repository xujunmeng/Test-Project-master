package CXF对Interceptor拦截器的支持;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import CXF入门示例2.IHelloWorldService;

/**
@author junmeng.xu
@date  2016年5月31日下午2:33:42
 */
public class ServiceMessageIntercepterClient {

	public static void main(String[] args) {
		
		//调用webservice
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setServiceClass(IHelloWorldService.class);
		factoryBean.setAddress("http://localhost:1800/helloWorld");
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		
		IHelloWorldService service = (IHelloWorldService)factoryBean.create();
		System.out.println("result : " + service.sayHello("下面"));
		
	}
	
}
