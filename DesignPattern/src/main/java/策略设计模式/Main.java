package 策略设计模式;
/**
@author junmeng.xu
@date  2016年5月27日下午2:52:08
 */
public class Main {

	public static void main(String[] args) {
		
		//选择并创建需要使用的策略对象
		Strategy strategy = new ConcreteStrategyC();
		
		Context context = new Context(strategy);
		
		strategy.strategyInterface();
		
	}
	
}
