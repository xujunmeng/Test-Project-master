package e;

import java.util.Vector;

public class Test {
	public static void main(String[] args) {
		Rental rental = new Rental();
		Movie _movie = new Movie();
		_movie.set_title("水浒传");
		_movie.set_title("西游记");
		rental.set_movie(_movie);
		rental.setDays(3);
		Vector<Rental> vector = new Vector<Rental>();
		vector.add(rental);
		Customer customer = new Customer("张三",vector);
		customer.statement();
	}
}
