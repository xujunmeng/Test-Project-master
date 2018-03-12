package 计算的例子;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;



public class ClusterGroupExample {

	public static void main(String[] args) {
		
		TcpDiscoverySpi spi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		// 设置预定义IP地址，注意端口或者端口范围是可选的。
		ipFinder.setAddresses(Arrays.asList("192.168.1.43:47500"));
		spi.setIpFinder(ipFinder);
		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setDiscoverySpi(spi);
		// 启动集群
		Ignite ignite = Ignition.start(cfg);
		
		Collection<IgniteCallable<Integer>> calls = new ArrayList<>();
		// Iterate through all the words in the sentence and create Callable
		// jobs.
		for (final String word : "Count characters using callable"
				.split(" "))
			calls.add(word::length);
		// Execute collection of Callables on the grid.
		Collection<Integer> res = ignite.compute().call(calls);
		// Add up all the results.
		int sum = res.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Total number of characters is '" + sum + "'.");
		
		
		
		
	}
	
}