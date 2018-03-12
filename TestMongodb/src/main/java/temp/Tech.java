package temp;

import java.util.Date;



/**
@author junmeng.xu
@date  2016年8月26日下午2:22:22
 */
public class Tech {

	private String parent;
	private String code;
	private int level;
	private NameField name;
	private DescField desc;
	private Date crt;
	private Date upt;
	private int stat;
	private int typ;
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public NameField getName() {
		return name;
	}
	public void setName(NameField name) {
		this.name = name;
	}
	public DescField getDesc() {
		return desc;
	}
	public void setDesc(DescField desc) {
		this.desc = desc;
	}
	public Date getCrt() {
		return crt;
	}
	public void setCrt(Date crt) {
		this.crt = crt;
	}
	public Date getUpt() {
		return upt;
	}
	public void setUpt(Date upt) {
		this.upt = upt;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
	@Override
	public String toString() {
		return "Tech [parent=" + parent + ", code=" + code + ", level=" + level
				+ ", name=" + name + ", desc=" + desc + ", crt=" + crt
				+ ", upt=" + upt + ", stat=" + stat + ", typ=" + typ + "]";
	}
	
}
