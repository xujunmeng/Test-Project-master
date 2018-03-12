import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年5月3日下午7:08:05
 */
public class Collect {

	@Test
	public void test1(){
		List<User> users = new ArrayList<User>();
		User u1 = new User(1, "张三", 12,"男");
		User u2 = new User(2, "李四", 21,"女");
		User u3 = new User(3, "王五", 32,"男");
		users.add(u1);
		users.add(u2);
		users.add(u3);
		
	}
	

			
			
	class User{
		private Integer id;
		private String name;
		private Integer age;
		private String sex;
		public User(Integer id, String name, Integer age, String sex) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.sex = sex;
		}
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
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", age=" + age
					+ ", sex=" + sex + "]";
		}
	}
}

