package 多生产多消费.test2;


/**
@author junmeng.xu
@date  2016年8月17日下午4:35:03
 */
public class Producter implements Runnable {
	private Resource resource;
	public Producter(Resource resource) {
		this.resource = resource;
	}
	
	public void run() {
		while(true){
			resource.set("土豆");
		}
	}
}
