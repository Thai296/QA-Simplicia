package com.simplicia.pages.web.home;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.simplicia.commons.BasePage;
import com.simplicia.ui.web.home.HomePageUI;

public class HomePageObject extends BasePage {
	public HomePageObject(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void verifyAcceuilHeadingIsDisplayed() {
		assertTrue(isElementDisplayed(HomePageUI.ACCEUIL_HEADING), "The Accueil should display");
	}

	public void logOut() {
		clickToElement(HomePageUI.ACCEUIL_ROOT_MENU_ICON);
		clickToElement(HomePageUI.DE_CONNECTER_BUTTON);
	}
	
	public void verifyFooterIsDisplayed() {
		assertTrue(isElementDisplayed(HomePageUI.WEB_FOOTER), "The footer should display");
	}
}
