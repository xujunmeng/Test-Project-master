package googleguava;

import java.util.ArrayList;
import java.util.List;

/**
@author junmeng.xu
@date  2016年2月19日下午2:02:08
 */
public class BookDao {
	
	public Object executeSQL(){
		System.out.println("此处调用了Dao方法.executeSQL");
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 3; i++) {
			Book b = new Book(i);
			books.add(b);
		}
		return books;
	}
	
}
