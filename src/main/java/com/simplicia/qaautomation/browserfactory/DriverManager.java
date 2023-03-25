package com.simplicia.qaautomation.browserfactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	protected WebDriver driver;

	protected abstract void initDriver();

	public WebDriver getDriver() {
		if (null == driver) {
			initDriver();
		}
		return driver;
	}

	public void quitDriver() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
	}

	public void closeDriver() {
		driver.close();
	}

	public void navigateToDriver(String url) throws Exception {
		if (!(url.startsWith("http://") || url.startsWith("https://"))) {
			throw new Exception("			URL is invalid format and cannot open page");
		}
		driver.get(url);
	}

	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	public void setImplicitWaitTimeOut(int timeInSecond) {
		driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
	}
}
