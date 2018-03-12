package 模板方法模式;
/**
@author junmeng.xu
@date  2016年8月11日下午2:19:13
 */
public class CDAccount extends Account {

	@Override
	protected String doCalculateAccountType() {
		return "Certificate of Deposite";
	}

	@Override
	protected double doCalculateInterestRate() {
		return 0.06;
	}

}
