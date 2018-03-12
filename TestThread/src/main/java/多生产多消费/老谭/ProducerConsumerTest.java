package 多生产多消费.老谭;
/**
@author junmeng.xu
@date  2016年8月17日下午4:32:08
 */
public class ProducerConsumerTest {

	public static void main(String[] args) {
		
		PublicResource resource = new PublicResource();
		
		new Thread(new ProducerThread(resource)).start();  
		new Thread(new ProducerThread(resource)).start();  
		new Thread(new ProducerThread(resource)).start();  
		new Thread(new ProducerThread(resource)).start();  
        new Thread(new ConsumerThread(resource)).start();  
        new Thread(new ConsumerThread(resource)).start();  
        new Thread(new ConsumerThread(resource)).start();  
        new Thread(new ConsumerThread(resource)).start();  
		
	}
	
}
