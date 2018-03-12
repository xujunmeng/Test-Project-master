package e;


public class Rental {
	private int days;
	
	private Movie _movie;
	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Movie get_movie() {
		return _movie;
	}

	public void set_movie(Movie _movie) {
		this._movie = _movie;
	}

	
	
	
	double getCharge(){
		return _movie.getCharge(days);
	}
	
	int getFrequentRenterPoints(){
		return _movie.getFrequentRenterPoints(days);
	}
	
}
