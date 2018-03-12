package CXF入门示例2;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
@author junmeng.xu
@date  2016年5月31日下午2:25:35
 */
@WebService
public interface IHelloWorldService {

	public String sayHello(@WebParam(name="name") String name);
	
}
