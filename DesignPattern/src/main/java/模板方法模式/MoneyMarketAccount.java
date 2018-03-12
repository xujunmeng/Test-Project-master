package 模板方法模式;
/**
@author junmeng.xu
@date  2016年8月11日下午2:16:27
 */
public class MoneyMarketAccount extends Account {

	@Override
	protected String doCalculateAccountType() {
		return "Money Market";
	}

	@Override
	protected double doCalculateInterestRate() {
		return 0.045;
	}

}
