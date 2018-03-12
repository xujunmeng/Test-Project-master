package e;

import java.util.Enumeration;
import java.util.Vector;


public class Customer {

	private String name;
	
	private Vector<Rental> _rentals = new Vector<Rental>();


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer(String name, Vector _rentals) {
		super();
		this.name = name;
		this._rentals = _rentals;
	}
	
	public String statement(){
		String result = "Rental Record for " + getName() + "\n";
		getTotalCharge();
		getTotalFrequentRenterPoints();
		return null;
	}
	
	private double getTotalCharge(){
		double result = 0;
		Enumeration rentals = _rentals.elements();
		while(rentals.hasMoreElements()){
			Rental each = (Rental) rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}
	private int getTotalFrequentRenterPoints(){
		int result = 0;
		Enumeration rentals = _rentals.elements();
		while(rentals.hasMoreElements()){
			Rental each = (Rental) rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}
	
}
