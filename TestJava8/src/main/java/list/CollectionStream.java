package list;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
@author junmeng.xu
@date  2016年1月28日下午2:40:01
 */
public class CollectionStream {

	public static void main(String[] args) {
		
		List<String> books = new ArrayList<>();
        books.add("轻量级JavaEE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂IOS讲义");
        books.add("疯狂Ajax讲义");
        books.add("疯狂Android讲义");
        
        System.out.println("疯狂的个数" + books.stream().collect(Collectors.toList()));
		Lists.transform(books, input -> {
			
			return input;
			
		});
	}

    @Test
    public void test() {
        ArrayList<Groupaa> list = Lists.newArrayList();
        Groupaa obj = new Groupaa();
        obj.setId("1");
        obj.setName("a");
        Groupaa obj2 = new Groupaa();
        obj2.setId("2");
        obj2.setName("b");
        list.add(obj);
        list.add(obj2);
        Map<String, List<Groupaa>> map = list.stream().collect(Collectors.groupingBy((a) -> a.getId()));
        System.out.println(map);
    }

}
class Groupaa {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Groupaa{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}