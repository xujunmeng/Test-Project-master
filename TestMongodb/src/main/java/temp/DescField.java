package temp;


/**
@author junmeng.xu
@date  2016年8月26日下午2:00:04
 */
public class DescField{
	
	public DescField(String en, String szh) {
		super();
		this.en = en;
		this.szh = szh;
	}
	private String en;
	private String szh;
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	public String getSzh() {
		return szh;
	}
	public void setSzh(String szh) {
		this.szh = szh;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((en == null) ? 0 : en.hashCode());
		result = prime * result + ((szh == null) ? 0 : szh.hashCode());
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
		DescField other = (DescField) obj;
		if (en == null) {
			if (other.en != null)
				return false;
		} else if (!en.equals(other.en))
			return false;
		if (szh == null) {
			if (other.szh != null)
				return false;
		} else if (!szh.equals(other.szh))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DescField [en=" + en + ", szh=" + szh + "]";
	}
	
}
