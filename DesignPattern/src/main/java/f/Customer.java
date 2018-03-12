package f;

import java.util.Enumeration;
import java.util.Vector;



public class Customer {
	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();
	
	public Customer(String name) {
		this.name = name;
	}
	
	public void addRental(Rental rental) {
		rentals.add(rental);
	}
	
	

	public void statement(){
		getTotalChange();
		getTotalFrequentRenterPoints();
	}
	
	public double getTotalChange(){
		Enumeration<Rental> elements = rentals.elements();
		double result = 0;
		while (elements.hasMoreElements()) {
			Rental rental = (Rental) elements.nextElement();
			result += rental.getCharge();
			
		}
		return result;
	}
	public double getTotalFrequentRenterPoints(){
		Enumeration<Rental> elements = rentals.elements();
		int result = 0;
		while (elements.hasMoreElements()) {
			Rental rental = (Rental) elements.nextElement();
			result += rental.getFrequentRenterPoints();
			
		}
		return result;
	}


	
	
}
