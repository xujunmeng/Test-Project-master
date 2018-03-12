package 第一个例子;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
@author junmeng.xu
@date  2016年5月31日下午1:19:24
 */

@XmlRootElement(name="Student")
public class Student {

	private long id;
	private String name;
	private Date birthday;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
