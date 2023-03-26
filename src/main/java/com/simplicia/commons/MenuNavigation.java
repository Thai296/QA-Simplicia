package com.simplicia.commons;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class MenuNavigation {
	private WebDriver driver;
	private Properties properties;

	public MenuNavigation(WebDriver driver, Properties properties) {
		this.driver = driver;
		this.properties = properties;
	}

	public void openLoginPage() {
		driver.get(properties.getProperty(GlobalConstants.URL) + "/login");
	}

	public void navigateToHomePage() {
		navigateToUrl(properties.getProperty(GlobalConstants.URL) + "/home");
	}

	public void navigateToDATsPage() {
		navigateToUrl(properties.getProperty(GlobalConstants.URL) + "/dat/all");
	}
	
	private void navigateToUrl(String url) {
		driver.navigate().to(url);
	}
}
