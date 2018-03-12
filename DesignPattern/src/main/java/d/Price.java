package d;

public class Price {
	
	private MemberStrategy strategy;
	
	public Price(MemberStrategy strategy){
		this.strategy = strategy;
	}
	
	//计算图书的价格
	public double quote(double booksPrice){
		return strategy.calcPrice(booksPrice);
	}
}
