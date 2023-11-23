package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	public Properties initializeProperties() {
		
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "/config/browser.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public String getConfigFilePath() {
		return System.getProperty("user.dir") + "/config/browser.properties";
	}
	
	public String getBrowserName() {
		Properties prop = initializeProperties();
		return prop.getProperty("browsername");
	}
}
