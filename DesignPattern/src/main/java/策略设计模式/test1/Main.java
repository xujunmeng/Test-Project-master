package 策略设计模式.test1;
/**
@author junmeng.xu
@date  2016年5月27日下午2:58:46
 */
public class Main {

	public static void main(String[] args) {
		
		Strategy strategy = new ConcreteStrategyB();
		
		strategy.work();
		
		Context context = new Context(strategy);
		context.contextM();
		
	}
	
	
	
}
