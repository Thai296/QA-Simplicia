package com.simplicia.qaautomation.browserfactory;

public class BrowserManagerFactory {
	
	public static DriverManager getBrowser(String browserName) throws Exception {
	DriverManager driverManager;

		switch (browserName) {
		case "chrome":
			driverManager = new ChromeDriverManager();
			break;
		case "firefox":
			driverManager = new FirefoxDriverManager();
			break;
		case "edge":
			driverManager = new EdgeDriverManager();
			break;
		default:
			throw new Exception("			No such browser is present to be initialize. Browser name: " + browserName);
		}
		return driverManager;
	}
}
