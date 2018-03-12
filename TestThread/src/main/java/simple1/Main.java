package simple1;
/**
@author junmeng.xu
@date  2016年5月17日下午4:16:02
 */
public class Main {

	public static void main(String[] args) {
		
		new First2Thread().start();
		new First2Thread().start();
		
	}
	
	
}
class First2Thread extends Thread{
	
	@Override
	public void run() {
		for(int i = 0 ; i < 100 ; i++){
			System.out.println(getName() + "  " + i);
		}
	}
}
