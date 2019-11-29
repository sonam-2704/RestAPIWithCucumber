package com.qa.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	private static PropertyManager instance;
	private static String propertyFilePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\config\\config.properties";
	private static String baseUrl;
	

	private static String serviceUrl;

	// Create a Singleton instance. We need only one instance of PropertyManager.
	public static PropertyManager getInstance() {
		if (instance == null) {
			synchronized (PropertyManager.class) {

				if (instance == null) {
					instance = new PropertyManager();
					instance.loadData();
				}
			}
		}
		return instance;
	}

	// Get all configuration data and assign to related fields.
	private void loadData() {
		// Declare a properties object
		Properties prop = new Properties();

		// Read configuration.properties file
		try {
			
			prop.load(new FileInputStream(propertyFilePath));
		} catch (IOException e) {
			System.out.println("Configuration properties file cannot be found");
		}

		// Get properties from configuration.properties
		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("serviceUrl");
	}

	public static String getBaseUrl() {
		return baseUrl;
	}

	public static String getServiceUrl() {
		return serviceUrl;
	}

}
