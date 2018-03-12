package 计算的例子;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteCallable;

/**
 * @author junmeng.xu
 * @date 2016年6月7日下午6:30:35
 */
public class Main3 {
	public static void main(String[] args) throws Exception {

		try (Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml")) {
			IgniteCache<Integer, Person> cache = ignite.cache("junmeng");
			
			Collection<IgniteCallable<List<Person>>> calls = new ArrayList<>();
			
			
			List<String> list = addList();
			
			int size = list.size();
			
			int times = size / 300 + (size % 300 == 0 ? 0 : 1);
			
			int head = 0;
			
			List<IgniteCallable<List<Person>>> igniteCallableList = new ArrayList<IgniteCallable<List<Person>>>();
			
			for (int i = 0; i < times; i++) {
				
				int end = head + 300;
				
				if(end > size) end = size;
				
				List<String> subList = list.subList(head, end);
				
				//=====================
				IgniteCallable<List<Person>> igniteCallable = new IgniteCallable<List<Person>>() {
					private static final long serialVersionUID = -3426842249701350408L;
					@Override
					public List<Person> call() throws Exception {
						return dealData(subList);
					}
				};
				calls.add(igniteCallable);
				
				igniteCallableList.add(igniteCallable);
				
				//=========================
				head += 300;
			}
			
			for (IgniteCallable<List<Person>> igniteCallable : igniteCallableList) {
				List<Person> call = igniteCallable.call();
				for (Person person : call) {
					cache.put(1, person);
					System.out.println(person);
				}
			}
			
		}

	}
	private static List<String> addList() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000; i++) {
			list.add("11" + i);
		}
		return list;
	}
	private static List<Person> dealData(List<String> subList) {
		List<Person> persons = new ArrayList<Person>();
		for (String str : subList) {
			Person p = new Person();
			p.setId(Integer.parseInt(str) + 3);
			p.setName(str + "的名字");
			persons.add(p);
		}
		return persons;
	}
}
class Person{
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
		return "Person [id=" + id + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
