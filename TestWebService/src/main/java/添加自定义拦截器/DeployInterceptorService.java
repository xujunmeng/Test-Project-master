package 添加自定义拦截器;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.phase.Phase;

import CXF入门示例.HelloWorldService;

/**
@author junmeng.xu
@date  2016年5月31日下午2:40:56
 */
public class DeployInterceptorService {

	public static void main(String[] args) throws InterruptedException {
		
	        //发布WebService
	        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
	        //设置Service Class
	        factory.setServiceClass(HelloWorldService.class);
	        factory.setAddress("http://localhost:9000/helloWorld");
	        //设置ServiceBean对象
	         factory.setServiceBean(new HelloWorldService());
	        
	        //添加请求和响应的拦截器，Phase.RECEIVE只对In有效，Phase.SEND只对Out有效
	         factory.getInInterceptors().add(new MessageInterceptor(Phase.RECEIVE));
	        factory.getOutInterceptors().add(new MessageInterceptor(Phase.SEND));
	        
	        factory.create();
	        
	        System.out.println("Server start ......");
	        Thread.sleep(1000 * 60);
	        System.exit(0);
	        System.out.println("Server exit ");
		
	}
	
}
