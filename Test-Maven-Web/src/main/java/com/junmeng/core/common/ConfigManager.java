package com.junmeng.core.common;

import org.apache.commons.lang.StringUtils;

import com.aug3.sys.props.ConfigProperties;
import com.aug3.sys.props.PropertyLoader;

/**
 * @author junmeng.xu
 * @date 2016年6月2日下午3:15:48
 */
public class ConfigManager {

	private static final PropertyLoader PROPERTY_LOADER = new PropertyLoader(
			"/application.properties");

	private static ConfigProperties configProperties = null;

	public static ConfigProperties getConfigProperties() {
		if (configProperties == null) {
			String list = PROPERTY_LOADER.getProperty("app.properties.list");
			
			if(StringUtils.isNotBlank(list)) {
				String[] propertiesFile = list.split(",");
				configProperties = new ConfigProperties(propertiesFile);
			}
		}
		return configProperties;
	}

	public static PropertyLoader getAppProperties(){
		return PROPERTY_LOADER;
	}
	
	public static String getProperty(String key){
		return getConfigProperties().getProperty(key);
	}
	
}
