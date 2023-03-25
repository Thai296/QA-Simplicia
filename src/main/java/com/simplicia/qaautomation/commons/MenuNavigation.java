package com.simplicia.qaautomation.commons;

import org.openqa.selenium.WebDriver;

public class MenuNavigation {
	private WebDriver driver;
	private ConfigurationLoader configLoader;

	public MenuNavigation(WebDriver driver, ConfigurationLoader configLoader) {
		this.driver = driver;
		this.configLoader = configLoader;
	}

	public void openLoginPage() {
		driver.get(configLoader.loadConfiguration().getProperty(GlobalConstants.URL) + "/login");
	}

	public void navigateToHomePage() {
		navigateToUrl(configLoader.loadConfiguration().getProperty(GlobalConstants.URL) + "/home");
	}

	private void navigateToUrl(String url) {
		driver.navigate().to(url);
	}
}
