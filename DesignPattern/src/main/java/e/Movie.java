package e;

/**
 * Movie只是一个简单的纯数据类
 * @author junmeng.xu
 *
 */
public class Movie {
	
	private String _title;
	
	private Price price;
	public String get_title() {
		return _title;
	}
	public void set_title(String _title) {
		this._title = _title;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	
	
	
	double getCharge(int days){
		return price.getCharge(days);
	}
	int getFrequentRenterPoints(int days){
		return price.getFrequentRenterPoints(days);
	}
}
