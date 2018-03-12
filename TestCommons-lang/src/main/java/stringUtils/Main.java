package stringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
@author junmeng.xu
@date  2016年4月7日上午10:25:35
 */
public class Main {

	public static void main(String[] args) {
		
		String str = "阿克苏劳动纠纷指数阿克苏京东方概念指数";
		String replace = StringUtils.replace(str, "概念指数", "");
		System.out.println(replace);
		
	}
	
	@Test
	public void test() throws IllegalAccessException, InvocationTargetException{
		
		Person1 p1 = new Person1();
		Person2 p2 = new Person2();
		p2.setAge(2);
		p2.setName("aaa");
		
		BeanUtils.copyProperties(p1, p2);
		System.out.println(p1);
	}
	
	@Test
	public void test2(){
		List<String> uids = new ArrayList<String>();
		uids.add("aaa");
		uids.add("bbb");
		String join = StringUtils.join(uids, ",");
		System.out.println(join);
	}
	
	public class Person1{
		private String name;
		private Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person1 [name=" + name + ", age=" + age + "]";
		}
	}
	public class Person2{
		private String name;
		private Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person2 [name=" + name + ", age=" + age + "]";
		}
	}
}
