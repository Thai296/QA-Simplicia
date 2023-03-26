package com.simplicia.qaautomation.browserfactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager extends DriverManager {
	
	@Override
	protected void initDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		
		options.addArguments("--enable-javascript"); 
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
//		options.addArguments("--headless");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-geolocation");
		options.addArguments("--disable-notifications");

		Map<String, Object> prefs = new HashMap<>(); 
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(options);
	}
}
