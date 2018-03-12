package com.aug3.storage.passage.util;
/**
@author junmeng.xu
@date  2015年12月23日下午1:33:51
 */
public class ConfigManager {
	
	private static final String[] propertyFiles = { "/passage.properties" };
	public static ConfigProperties config = new ConfigProperties(propertyFiles);
	
	public static String getProperty(String key , String defaultValue){
		return config.getProperty(key , defaultValue);
	}
	
	public static String getProperty(String key){
		return config.getProperty(key);
	}
	
	public static int getIntProperty(String key , int defaultValue){
		return config.getIntProperty(key , defaultValue);
	}
	
	public static int getIntProperty(String key){
		return config.getIntProperty(key);
	}
	
	public static boolean getBooleanProperty(String key){
		return config.getBooleanProperty(key);
	}
	
	public static boolean getBooleanPropery(String key , boolean defaultValue){
		return config.getBooleanProperty(key , defaultValue);
	}
	
	public static ConfigProperties getConfigProperties(){
		return config;
	}
}
