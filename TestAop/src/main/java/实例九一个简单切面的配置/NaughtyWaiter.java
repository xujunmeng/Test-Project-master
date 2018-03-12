package 实例九一个简单切面的配置;


public class NaughtyWaiter implements Waiter {

	public void greetTo(String clientName) {
		System.out.println("NaughtyWaiter:greet to "+clientName+"...");
	}

	public void serveTo(String clientName){
		System.out.println("NaughtyWaiter:serving "+clientName+"...");
	}

	public void joke(String clientName,int times){
        	System.out.println("NaughtyWaiter:play "+times+" jokes to "+clientName+"...");
	}
}
