package 策略设计模式.test1;
/**
@author junmeng.xu
@date  2016年5月27日下午3:00:07
 */
public class Context {

	private Strategy strategy;
	
	public Context(Strategy strategy){
		this.strategy = strategy;
	}
	
	public void contextM(){
		strategy.work();
	}
	
}
