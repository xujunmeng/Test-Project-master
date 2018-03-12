package volatile变量;
/**
@author junmeng.xu
@date  2016年8月12日下午5:11:53
 */
public class SafeThread {

	volatile static int number;
	
	public static int getNumber(){
		return number;
	}
	
	public static void setNumber(int number){
		SafeThread.number = number;
	}
	
}
