package d;

public class Client {
	public static void main(String[] args) {
		
		MemberStrategy strategy = new AdvancedMemberStrategy();
		
		Price price = new Price(strategy);
		double quote = price.quote(300);
		System.out.println("图书的最终价格为 : " + quote);
	}
}
