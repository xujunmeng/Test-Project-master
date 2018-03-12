package model;
/**
@author junmeng.xu
@date  2016年1月11日下午3:19:45
 */
public class User {
	
	private int id;
	private String username;
	private String password;
	private String password1;
	private String password2;
	private String password3;
	private String password4;
	private String password5;
	private String password6;
	private String password7;
	private String password8;

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPassword3() {
		return password3;
	}

	public void setPassword3(String password3) {
		this.password3 = password3;
	}

	public String getPassword4() {
		return password4;
	}

	public void setPassword4(String password4) {
		this.password4 = password4;
	}

	public String getPassword5() {
		return password5;
	}

	public void setPassword5(String password5) {
		this.password5 = password5;
	}

	public String getPassword6() {
		return password6;
	}

	public void setPassword6(String password6) {
		this.password6 = password6;
	}

	public String getPassword7() {
		return password7;
	}

	public void setPassword7(String password7) {
		this.password7 = password7;
	}

	public String getPassword8() {
		return password8;
	}

	public void setPassword8(String password8) {
		this.password8 = password8;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", password1='" + password1 + '\'' +
				", password2='" + password2 + '\'' +
				", password3='" + password3 + '\'' +
				", password4='" + password4 + '\'' +
				", password5='" + password5 + '\'' +
				", password6='" + password6 + '\'' +
				", password7='" + password7 + '\'' +
				", password8='" + password8 + '\'' +
				'}';
	}
}
