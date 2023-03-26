package com.simplicia.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.simplicia.commons.BaseTest;
import com.simplicia.commons.GlobalConstants;
import com.simplicia.commons.PageManagerFactory;
import com.simplicia.pages.web.home.HomePageObject;
import com.simplicia.pages.web.login.LoginPageObject;

public class HomePageTests extends BaseTest{
	private HomePageObject homePageObject;
	private LoginPageObject loginPageObject;
	
	@BeforeMethod
	public void beforeMethod() {
		homePageObject = PageManagerFactory.getHomePageObject(driver, wait);
		loginPageObject = PageManagerFactory.getLoginPageObject(driver, wait);
		
		log.info("Step 1: Expected - Verify that Login page is display successful");
		loginPageObject.verifyEmailAndPasswordFieldsAreDisplayed();
		
		log.info("Step 2: Action - Input email, password datas and click [Se Connecter] button");
		loginPageObject.loginToLoginPage(properties.getProperty(GlobalConstants.EMAIL), properties.getProperty(GlobalConstants.PASSWORD));
		
		log.info("Step 2: Expected - Verify the user is login the application successful");
		homePageObject.verifyAcceuilHeadingIsDisplayed();
	}
	
	@Test(description = "Verify that footer is disaplyed correctly")
	public void verifyFooterIsDisplayed() {
		log.info("*** Starting verifyFooterIsDisplayed test case ***");
		
		log.info("Step 3: Expected - Verify that footer is disaplyed correctly");
		homePageObject.verifyFooterIsDisplayed();
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		try {

			log.info("*** Starting log out the account ***");
			homePageObject.logOut();
			log.info("*** Logout the account successful ***");

		} catch (Exception e) {
			log.debug("*** Failing when log out the account ***", e);
		}
	}
}