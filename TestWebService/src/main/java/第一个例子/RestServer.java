package 第一个例子;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

/**
@author junmeng.xu
@date  2016年5月31日下午1:34:13
 */
public class RestServer {

	public static void main(String[] args) {
		
		JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
		jaxrsServerFactoryBean.setResourceClasses(StudentServiceImpl.class);
		jaxrsServerFactoryBean.setAddress("http://localhost:335/");
		jaxrsServerFactoryBean.create();
		
	}
	
}
