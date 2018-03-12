package 函数式接口;

import java.util.List;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年4月22日上午10:43:58
 */
public class Main {

	public static void main(String[] args) {
		
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted);
	}

	@Test
	public void test1(){
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted);
	}
	
	@Test
	public void test2(){
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println(person);
	}
	
	
}

class Person {
    String firstName;
    String lastName;
    Person() {}
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
}
interface PersonFactory<P extends Person>{
	P create(String firstName, String lastName);
}

interface Converter<F, T>{
	T convert(F from);
}











class Student{
	private String name;
	private List<String> teacheId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTeacheId() {
		return teacheId;
	}
	public void setTeacheId(List<String> teacheId) {
		this.teacheId = teacheId;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", teacheId=" + teacheId + "]";
	}
	
}