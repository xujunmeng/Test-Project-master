import org.apache.solr.client.solrj.beans.Field;

/**
@author junmeng.xu
@date  2016年3月14日下午2:50:56
 */
public class Person {

	@Field
	private String id;
	@Field
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
