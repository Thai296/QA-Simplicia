package com.simplicia.commons;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigurationLoader {
	private static InputStream input;
	private static final String filepath_automation = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\config\\automation.properties";
	private final Logger log = Logger.getLogger(ConfigurationLoader.class);

	public Properties loadConfiguration() {
		try {
			Properties properties = new Properties();
			loadResourceFile(properties, filepath_automation);
			return properties;
		} catch (Exception e) {
			log.info("File not found exception thrown for config.properties file.");
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void loadResourceFile(Properties properties, String filename) {
		try (InputStream input = new FileInputStream(filename)) {
			properties.load(input);
			log.info("Loaded properties from resource file, filename = " + filename);
		} catch (Exception e) {
			log.info("Unable to load properties from resource file, filename = " + filename, e);
		}
	}
}
