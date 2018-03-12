package 多生产多消费.老谭;
/**
@author junmeng.xu
@date  2016年8月17日下午4:30:43
 */
public class ConsumerThread implements Runnable {

	private PublicResource resource;

	public ConsumerThread(PublicResource resource) {
		this.resource = resource;
	}
	
	public void run() {
		for(int i = 0 ; i < 10 ; i++){
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resource.decreace();
		}
		
		
	}
	
	
	
}
