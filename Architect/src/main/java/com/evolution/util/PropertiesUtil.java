package com.evolution.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesUtil {
	public static String anyUrl;
	
	static {
		Properties properties = getProperties("url.properties");
		Field[] fields = PropertiesUtil.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				field.set(null, properties.getProperty(field.getName()));
			} catch (Exception e) {
				System.out.println("Failed to set the value for " + field.getName() + ".");
			}
		}
	}
	
	public static Properties getProperties(String filename) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
			if (inputStream == null) {
				System.out.println("The file " + filename + " is not found.");
				return null;
			}
			properties.load(inputStream);
			return properties;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static String getProperty(String filename, String key) {
		Properties properties = getProperties(filename);
		return properties.getProperty(key);
	}
}
