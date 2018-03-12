package googleguava;
/**
@author junmeng.xu
@date  2016年2月19日下午2:01:09
 */
public class Book {
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book(int id) {
		super();
		this.id = id;
	}

	public Book() {
		super();
	}

	@Override
	public String toString() {
		return "Book [id=" + id + "]";
	}
	
	
	
}
