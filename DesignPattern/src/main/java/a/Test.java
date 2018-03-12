package a;

public class Test {
	public static void main(String[] args) {
		
		Customer customer1 = new Customer("张三");
		
		
		
		Movie movie = new Movie("水浒", 1);
		
		
		Rental rental = new Rental(movie, 6);
		
		customer1.addRental(rental);
		
		customer1.statement();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
