package com.simplicia.qaautomation.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.simplicia.qaautomation.commons.BaseTest;
import com.simplicia.qaautomation.commons.GlobalConstants;
import com.simplicia.qaautomation.commons.PageManagerFactory;
import com.simplicia.qaautomation.pageobject.HomePageObject;
import com.simplicia.qaautomation.pageobject.LoginPageObject;

public class LoginPageTests extends BaseTest {
	private LoginPageObject loginPageObject;
	private HomePageObject homePageObject;
	private boolean logOut = false; 

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		loginPageObject = PageManagerFactory.getLoginPageObject(driver, wait);
		homePageObject = PageManagerFactory.getHomePageObject(driver, wait);
	}

	@Test(description = "Verify simplicia logo is displayed")
	public void verifySimpliciaLogoIsDisplayed() {
		log.info("*** Starting verifySimpliciaLogoIsDisplayed test case ***");

		log.info("Step 1: Expected - Verify that Login page is display successful");
		loginPageObject.verifySimpliciaLogoIsDisplayed();
	}

	@Test(description = "Verify that 'Se connecter à votre compte' and 'Bienvenue' texts are displayed correctly")
	public void verifyTextsAreDisplayed() {
		log.info("*** Starting verifyTextsAreDisplayed test case ***");

		log.info("Step 1: Expected - Verify that 'Se connecter à votre compte' and 'Bienvenue' texts are displayed correctly");
		loginPageObject.verifyTextsAreDisplayed();
	}

	@Test(description = "Verify that email and password fields are displayed")
	public void verifyEmailAndPasswordFieldsAreDisplayed() {
		log.info("*** Starting verifyEmailAndPasswordFieldsAreDisplayed test case ***");

		log.info("Step 1: Expected - Verify that email and password fields are displayed");
		loginPageObject.verifyEmailAndPasswordFieldsAreDisplayed();
	}

	@Test(description = "Verify that 'Se Connecter' button is disabled if no input data")
	public void verifySeConnecterButtonIsDisableIfNoInputData() {
		log.info("*** Starting verifySeConnecterButtonIsDisableIfNoInputData test case ***");

		log.info("Step 1: Expected - Verify that 'Se Connecter' button is disabled if no input data");
		loginPageObject.verifySeConnecterButtonIsDisableIfNoInputData();
	}

	@Test(description = "Verify the Help Scout Beacon button is displayed at the bottom of page")
	public void verifyHelpScoutBeaconButtonIsDisplayed() {
		log.info("*** Starting verifyHelpScoutBeaconButtonIsDisplayed test case ***");

		log.info("Step 1: Expected - Verify the Help Scout Beacon button is displayed at the bottom of page");
		loginPageObject.verifyHelpScoutBeaconButtonIsDisplayed();
	}
	
	@Test(description = "Verify the presence of 'email' and 'vpnkey' icons along with email and password fields respectively")
	public void verifyEmailAndVpnkeyIconsAlongWithEmailAndPasswordFields() {
		log.info("*** Starting verifyEmailAndVpnkeyIconsAlongWithEmailAndPasswordFields test case ***");

		log.info("Step 1: Expected - Verify the presence of 'email' and 'vpnkey' icons along with email and password fields respectivel");
		loginPageObject.verifyEmailAndVpnkeyIconsAlongWithEmailAndPasswordFields();
	}

	@Test(description = "Verify that user is able to fill data into email and password text fields")
	public void verifyEmailAndPasswordTextCanFillDatas() {
		log.info("*** Starting verifyEmailAndPasswordTextCanFillDatas test case ***");

		log.info("Step 2: Action - Input email and password datas");
		String emailInput = properties.getProperty(GlobalConstants.EMAIL);
		loginPageObject.inputEmail(emailInput);
		
		String passwordInput = properties.getProperty(GlobalConstants.PASSWORD);
		loginPageObject.inputPassword(passwordInput);

		log.info("Step 2: Expected - Verify that user is able to fill data into email and password text fields");
		loginPageObject.verifyEmailAndPasswordTextCanFillDatas(emailInput, passwordInput);
	}

	@Test(description = "Verify the Se Connecter button is enabled after input email and password")
	public void verifySeConnecterButtonEnabledAfterInputEmailAndPassword() {
		log.info("*** Starting verifySeConnecterButtonEnabledAfterInputEmailAndPassword test case ***");

		log.info("Step 2: Action - Input email and password datas");
		loginPageObject.inputEmail(properties.getProperty(GlobalConstants.EMAIL));
		loginPageObject.inputPassword(properties.getProperty(GlobalConstants.PASSWORD));

		log.info("Step 2: Expected - Verify the Se Connecter button is enabled after input email and password");
		loginPageObject.verifySeConnecterButtonEnabledAfterInputEmailAndPassword();
	}

	@Test(description = "Verify the user is login the application successful")
	public void verifyLoginSuccessful() {
		log.info("*** Starting verifyLoginSuccessful test case ***");

		log.info("Step 1: Expected - Verify that Login page is display successful");
		loginPageObject.verifySimpliciaLogoIsDisplayed();
		loginPageObject.verifyTextsAreDisplayed();
		loginPageObject.verifyEmailAndPasswordFieldsAreDisplayed();
		loginPageObject.verifyEmailAndVpnkeyIconsAlongWithEmailAndPasswordFields();
		loginPageObject.verifySeConnecterButtonIsDisableIfNoInputData();
		loginPageObject.verifyHelpScoutBeaconButtonIsDisplayed();

		log.info("Step 2: Action - Input email, password datas and click [Se Connecter] button");
		loginPageObject.loginToLoginPage(properties.getProperty(GlobalConstants.EMAIL), properties.getProperty(GlobalConstants.PASSWORD));

		log.info("Step 2: Expected - Verify the user is login the application successful");
		homePageObject.verifyAcceuilHeadingIsDisplayed();
		logOut = true;
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		try {
			
			if (logOut) {
				log.info("*** Starting log out the account ***");
				homePageObject.logOut();
				log.info("*** Logout the account successful ***");
			}
			
		} catch (Exception e) {
			log.debug("*** Failing when log out the account ***", e);
		}
	}
}
