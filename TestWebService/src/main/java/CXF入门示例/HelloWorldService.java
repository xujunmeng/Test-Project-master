package CXF入门示例;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
@author junmeng.xu
@date  2016年5月31日下午2:15:09
 */
@WebService
public class HelloWorldService {

	public String sayHello(@WebParam(name="name") String name){
		return name + " say : Hello World ";
	}
	
	
}
