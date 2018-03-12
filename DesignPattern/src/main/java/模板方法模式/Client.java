package 模板方法模式;
/**
@author junmeng.xu
@date  2016年8月11日下午2:20:03
 */
public class Client {

	public static void main(String[] args) {
		
		Account account = new MoneyMarketAccount();
		
		System.out.println("货币市场账号的利息数额为 : " + account.calculateInterest());
		
		account = new CDAccount();
		
		System.out.println("定期账号的利息数额为 : " + account.calculateInterest());
		
	}
	
}
