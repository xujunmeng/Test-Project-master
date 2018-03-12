package 练习zookeper客户端的使用二;

import java.io.Serializable;

/**
@author junmeng.xu
@date  2016年9月21日下午1:45:04
 */
public class User implements Serializable {

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
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
