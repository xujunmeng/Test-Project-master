package com.junmeng.entity;

import java.io.Serializable;
import java.util.Date;

/**
@author junmeng.xu
@date  2016年6月1日下午3:47:41
 */
public class User implements Serializable {
	private static final long serialVersionUID = -2280670225891479090L;
	private Long id;
	private String pkey;
	private String name;
	private String mail;
	private String nick_name;
	private String passwd;
	private String purl;
	private String phone;
	private Integer ph_state = 0;
	private Integer ml_state = 0;
	private Integer regist_type = 0;
	private Date regist_dt;
	private Date login_dt;
	
	public Date getRegist_dt() {
		return regist_dt;
	}
	public void setRegist_dt(Date regist_dt) {
		this.regist_dt = regist_dt;
	}
	public Date getLogin_dt() {
		return login_dt;
	}
	public void setLogin_dt(Date login_dt) {
		this.login_dt = login_dt;
	}
	public Integer getRegist_type() {
		return regist_type;
	}
	public void setRegist_type(Integer regist_type) {
		this.regist_type = regist_type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getPh_state() {
		return ph_state;
	}
	public void setPh_state(Integer ph_state) {
		this.ph_state = ph_state;
	}
	public Integer getMl_state() {
		return ml_state;
	}
	public void setMl_state(Integer ml_state) {
		this.ml_state = ml_state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pkey=" + pkey + ", name=" + name
				+ ", mail=" + mail + ", nick_name=" + nick_name + ", passwd="
				+ passwd + ", purl=" + purl + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nick_name == null) ? 0 : nick_name.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((pkey == null) ? 0 : pkey.hashCode());
		result = prime * result + ((purl == null) ? 0 : purl.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nick_name == null) {
			if (other.nick_name != null)
				return false;
		} else if (!nick_name.equals(other.nick_name))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (pkey == null) {
			if (other.pkey != null)
				return false;
		} else if (!pkey.equals(other.pkey))
			return false;
		if (purl == null) {
			if (other.purl != null)
				return false;
		} else if (!purl.equals(other.purl))
			return false;
		return true;
	}
	
}
