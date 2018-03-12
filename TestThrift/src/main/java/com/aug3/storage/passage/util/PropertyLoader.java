package com.aug3.storage.passage.util;

import java.io.InputStream;
import java.util.Properties;

/**
@author junmeng.xu
@date  2015年12月23日下午1:55:55
 */
@SuppressWarnings("serial")
public class PropertyLoader extends Properties {

	private String resourceName;
	
	public PropertyLoader(String configResource){
		resourceName = configResource;
		load();
	}
	
	private void load(){
		
		try {
			
			InputStream in = PropertyLoader.class.getResourceAsStream(resourceName);
			
			if(in == null){
				return;
			}
			
			try {
				this.load(in);
			} finally {
				in.close();
			}
			
		} catch (Exception e) {

			throw new RuntimeException("failed loading configuration data from " + resourceName , e);
		}
	}
}
