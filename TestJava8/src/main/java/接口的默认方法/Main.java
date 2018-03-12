package 接口的默认方法;


/**
@author junmeng.xu
@date  2016年4月22日上午10:01:50
 */
public class Main {

	public static void main(String[] args) {
		
		Formula formula = new Formula() {
			
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};
		
		System.out.println(formula.calculate(100));;
		System.out.println(formula.sqrt(16));
		
	}
	
}
interface Formula{
	double calculate(int a);
	
	default double sqrt(int a){
		return Math.sqrt(a);
	}
}
