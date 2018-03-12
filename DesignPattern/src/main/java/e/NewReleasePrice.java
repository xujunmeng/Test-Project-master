package e;

public class NewReleasePrice extends Price  {
	double getCharge(int daysRented){
		return daysRented * 3;
	}

	@Override
	int getFrequentRenterPoints(int daysRented) {
		return (daysRented > 1) ? 2 : 1;
	}
	
	
	
}
