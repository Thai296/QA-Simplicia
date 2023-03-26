package com.simplicia.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplicia.pages.web.dat.DATPageObject;
import com.simplicia.pages.web.home.HomePageObject;
import com.simplicia.pages.web.login.LoginPageObject;

public class PageManagerFactory {
	
	public static LoginPageObject getLoginPageObject(WebDriver driver, WebDriverWait wait) {
		return new LoginPageObject(driver, wait);
	}
	
	public static HomePageObject getHomePageObject(WebDriver driver, WebDriverWait wait) {
		return new HomePageObject(driver, wait);
	}
	
	public static DATPageObject getDatPageObject(WebDriver driver, WebDriverWait wait) {
		return new DATPageObject(driver, wait);
	}
}
