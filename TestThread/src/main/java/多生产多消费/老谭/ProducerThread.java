package 多生产多消费.老谭;
/**
@author junmeng.xu
@date  2016年8月17日下午4:28:22
 */
//生产者线程，负责生产公共资源
public class ProducerThread implements Runnable {

	private PublicResource resource;

	public ProducerThread(PublicResource resource) {
		this.resource = resource;
	}
	
	public void run() {
		for(int i = 0 ; i < 10 ; i++){
			try {
				Thread.sleep(1000);
				//Thread.sleep((long) (Math.random() * 1000));  
			} catch (Exception e) {
				e.printStackTrace();
			}
			resource.increace();
			
		}
		
	}
	
	
	
}
