package googleguava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
@author junmeng.xu
@date  2016年2月19日下午2:07:31
 */
public class Main {

	public static void main(String[] args) {
		
		try {
			System.out.println("第一次调用dao方法，正确状态，应该调用Dao里的方法");
			List<Book> books = BookCache.cache.get("points", () -> {
                BookDao dao = new BookDao();
                @SuppressWarnings("unchecked")
                List<Book> list = (List<Book>) dao.executeSQL();
                if(null == list || list.size() <= 0){
                    list = new ArrayList<Book>();
                }
                return list;
            });
			for (Book book : books) {
				System.out.println(book);
			}
			
			
			
			
			System.out.println("第二次调用dao方法，正确状态：不调用Dao里的方法");
			List<Book> books2 = BookCache.cache.get("points", new Callable<List<Book>>() {

				public List<Book> call() throws Exception {
					BookDao dao = new BookDao();
					List<Book> list= (List<Book>) dao.executeSQL();
					if(null == list || list.size() <= 0){
						list = new ArrayList<Book>();
					}
					return list;
				}
			});
			for (Book book : books2) {
				System.out.println(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
