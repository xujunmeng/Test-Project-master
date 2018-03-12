package 第一次操作;
/**
@author junmeng.xu
@date  2016年7月1日下午3:42:47
 */
public class Employee {

	private String name;
	private String city;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", city=" + city + ", phone=" + phone
				+ "]";
	}
	
	
}
