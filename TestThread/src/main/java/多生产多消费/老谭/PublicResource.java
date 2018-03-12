package 多生产多消费.老谭;
/**
@author junmeng.xu
@date  2016年8月17日下午4:21:22
 */

//公共资源类
public class PublicResource {

	private int number = 0;
	
	//增加公共资源
	public synchronized void increace(){
		while(number != 0){
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		number++;
		System.out.println(number);
		notify();
	}
	//减少公共资源
	public synchronized void decreace(){
		while(number == 0){
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		number--;
		System.out.println(number);
		notify();
	}
}
