package 测试quant;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

/**
@author junmeng.xu
@date  2016年9月29日下午4:38:29
 */
public class CsfIgnite {

	private static Ignite ignite;
	
	static {
		ignite = Ignition.start("example-ignite.xml");
	}
	
	public static Ignite getIgnite(){
		return ignite;
	}
	
}
