package 流计算和CEP;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;

/**
 * @author junmeng.xu
 * @date 2016年6月12日下午2:53:54
 */
public class IgniteDataStreamerMain {

	public static void main(String[] args) {

		Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml");

		CacheConfiguration<Integer, Person2> cfg = new CacheConfiguration<>(
				"myStreamCache");

		IgniteCache<Integer, Person2> stmCache = ignite.getOrCreateCache(cfg);

		IgniteDataStreamer<Object, Person2> igniteDataStreamer = ignite
				.dataStreamer(stmCache.getName());
		for (int i = 0; i < 1000000; i++) {
			Person2 p = new Person2();
			p.setId(i+999999);
			p.setName("junmeng33" + i);
			igniteDataStreamer.addData(i, p);
		}
		String string = igniteDataStreamer.toString();

		System.out.println(string);
	}

}

class Person2 {
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

}
