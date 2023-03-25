package com.simplicia.qaautomation.pageobject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplicia.qaautomation.commons.BasePage;
import com.simplicia.qaautomation.commons.ConfigurationLoader;
import com.simplicia.qaautomation.commons.GlobalConstants;
import com.simplicia.qaautomation.pageui.LoginPageUI;

public class LoginPageObject extends BasePage {
	private ConfigurationLoader config;

	public LoginPageObject(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		config = new ConfigurationLoader();
	}

	public String inputEmail() {
		String email = config.loadConfiguration().getProperty(GlobalConstants.EMAIL);
		sendkeyToElement(LoginPageUI.EMAIL_TEXT, email);
		return email;
	}
	
	public String inputPassword() {
		String password = config.loadConfiguration().getProperty(GlobalConstants.PASSWORD);
		sendkeyToElement(LoginPageUI.PASSWORD_TEXT, password);
		return password;
	}

	public void verifySeConnecterButtonEnabledAfterInputEmailAndPassword() {
		assertTrue(isElementEnabled(LoginPageUI.SE_CONNECTER_BUTTON), "The Se Connecter button should enabled");
	}
	
	public String getValueEmail() {
		return getAttributeText(LoginPageUI.EMAIL_TEXT, "value");
	}
	
	public String getValuePassword() {
		return getAttributeText(LoginPageUI.PASSWORD_TEXT, "value");
	}
	
	public void verifyEmailAndPasswordTextCanFillDatas(String emailInput, String passwordInput) {
		assertEquals(getValueEmail(), emailInput, "The email value should match");
		assertEquals(getValuePassword(), passwordInput, "The password value should match");
	}
	
	public void clickSeConnecterButton() {
		clickToElement(LoginPageUI.SE_CONNECTER_BUTTON);
	}

	public void verifySimpliciaLogoIsDisplayed() {
		assertTrue(isElementDisplayed(LoginPageUI.SIMPLICIA_LOGO), "The Simplicia Logo text should display");
	}
	
	public void verifyTextsAreDisplayed() {
		assertTrue(isElementDisplayed(LoginPageUI.BIENVENUE_TEXT), "The Bienvenue text should display");
		assertTrue(isElementDisplayed(LoginPageUI.SE_CONNECTER_TEXT), "The connecter text should display");

	}

	public void verifyEmailAndPasswordFieldsAreDisplayed() {
		assertTrue(isElementDisplayed(LoginPageUI.EMAIL_TEXT), "The email text should display");
		assertTrue(isElementDisplayed(LoginPageUI.PASSWORD_TEXT), "The password text should display");
	}
	
	public void verifySeConnecterButtonIsDisableIfNoInputData() {
		assertFalse(isElementEnabled(LoginPageUI.SE_CONNECTER_BUTTON), "The Se Connecter button should disable");
	}
	
	public void verifyEmailAndVpnkeyIconsAlongWithEmailAndPasswordFields() {
		assertTrue(isElementDisplayed(LoginPageUI.EMAIL_ICON), "The email icon should display");
		assertTrue(isElementDisplayed(LoginPageUI.VPN_KEY_ICON), "The vpn key icon should display");
	}
	
	public void verifyHSBeaconContainerFrameIsDisPlay() {
		switchToFrame(findElementByXpath(LoginPageUI.HELP_SCOUT_BEACON_FRAME));
		assertTrue(isElementDisplayed(LoginPageUI.HELP_SCOUT_BEACON_BUTTON), "The HSBeaconContainerFrame button should display");
		switchToDefaultFrame();
	}
}
