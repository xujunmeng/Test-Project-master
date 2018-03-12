package com.junmeng.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
@author junmeng.xu
@date  2016年6月1日下午4:09:19
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {

	private static Map<String, String> ctxPropertiesMap = new HashMap<String, String>();
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props){
		super.processProperties(beanFactoryToProcess, props);
		for(Object key : props.keySet()){
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}
	
	public static String getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}
	
	
}
