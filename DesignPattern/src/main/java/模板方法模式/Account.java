package 模板方法模式;
/**
@author junmeng.xu
@date  2016年8月11日下午1:59:50
 */
public abstract class Account {

	/**
	 * 模板方法,计算利息数额 。 返回利息数额
	 */
	public final double calculateInterest(){
		//调用基本方法
		
		double interestRate = doCalculateInterestRate();
		
		String accountType = doCalculateAccountType();
		
		double amount = calculateAmount(accountType);
		
		return amount * interestRate;
	}
	
	/**
	 * 基本方法的声明(由子类实现)
	 */
	protected abstract String doCalculateAccountType();
	
	/**
	 * 基本方法（空方法）
	 */
	protected abstract double doCalculateInterestRate();
	
	/**
	 * 基本方法(已经实现)
	 */
	private double calculateAmount(String accountType){
		//业务相关的代码
		//省略相关的业务逻辑
		return 7243.00;
	}
	
}
