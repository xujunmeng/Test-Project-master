package com.aug3.storage.passage.util;
/**
@author junmeng.xu
@date  2015年12月23日下午2:34:31
 */
public class Test {

	private static String server = ConfigManager.getProperty("passage.server");
	private static int port = ConfigManager.getIntProperty("passage.port", 8888);
	
	public static void main(String[] args) {
		
		System.out.println(server);
		System.out.println(port);
		
	}
}
