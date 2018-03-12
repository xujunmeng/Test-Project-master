package TreeSet类;

import java.util.TreeSet;

/**
@author junmeng.xu
@date  2016年5月19日下午5:40:06
 */
class Err implements Comparable{

	private double count;
	
	public int compareTo(Object o) {
		Err e = (Err)o;
		return 0;
	}
	
}
public class TestTreeSetError {

	public static void main(String[] args) {
		
		TreeSet treeSet = new TreeSet();
		//向TreeSet集合中添加两个Err对象
		treeSet.add(new Err());
		treeSet.add(new Err());
		
		
	}
	
	
}
