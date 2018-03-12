package forEach;

import java.util.ArrayList;
import java.util.List;

/**
@author junmeng.xu
@date  2016年2月1日下午1:25:16
 */
public class TestStream {
	
	public static void main(String[] args) {
		
		List<People> peoples = new ArrayList<People>();
		
		People p1 = new People();
		p1.setName("a1");
		p1.setAge(1);
		peoples.add(p1);
		
		People p2 = new People();
		p2.setName("a2");
		p2.setAge(2);
		peoples.add(p2);
		
		People p3 = new People();
		p3.setName("a3");
		p3.setAge(3);
		peoples.add(p3);
		
		People p4 = new People();
		p4.setName("a4");
		p4.setAge(4);
		peoples.add(p4);
		
		People p5 = new People();
		p5.setName("a5");
		p5.setAge(5);
		peoples.add(p5);
		
//		for (People people : peoples) {
//			System.out.println(people);
//		}
		List<People2> a = new ArrayList<People2>();
//		peoples.forEach(new Consumer<People>() {
//			@Override
//			public void accept(People t) {
//				People2 people2 = new People2();
//				people2.setName(t.getName());
//				people2.setAge(t.getAge());
//				a.add(people2);
//			}
//		});
		peoples.forEach(t -> {
			People2 people2 = new People2();
			people2.setName(t.getName());
			people2.setAge(t.getAge());
			a.add(people2);
		});
		for (People2 people2 : a) {
			System.out.println(people2);
		}
		
//		
//		List<People2> transform = Lists.transform(peoples, new Function<People, People2>() {
//
//			public People2 apply(People input) {
//				People2 people2 = new People2();
//				people2.setName(input.getName());
//				people2.setAge(input.getAge());
//				return people2;
//			}
//		});
//		for (People2 people2 : transform) {
//			System.out.println(people2);
//		}
//		List<Object> collect = transform.stream().collect(Collectors.toList());
//		for (Object object : collect) {
//			System.out.println(object);
//		}
//		
//		List<People2> transform2 = Lists.transform(peoples, input -> {
//			People2 people2 = new People2();
//			people2.setName(input.getName());
//			people2.setAge(input.getAge());
//			return people2;
//		});
//		for (People2 people2 : transform2) {
//			System.out.println(people2);
//		}
//		
//		
		
	}
	
}
