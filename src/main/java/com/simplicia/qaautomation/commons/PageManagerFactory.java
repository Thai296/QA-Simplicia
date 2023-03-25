package com.simplicia.qaautomation.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplicia.qaautomation.pageobject.HomePageObject;
import com.simplicia.qaautomation.pageobject.LoginPageObject;

public class PageManagerFactory {
	
	public static LoginPageObject getLoginPageObject(WebDriver driver, WebDriverWait wait) {
		return new LoginPageObject(driver, wait);
	}
	
	public static HomePageObject getHomePageObject(WebDriver driver, WebDriverWait wait) {
		return new HomePageObject(driver, wait);
	}
}
