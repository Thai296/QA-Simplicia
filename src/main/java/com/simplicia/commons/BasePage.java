package com.simplicia.commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WebElement element;
	protected List<WebElement> elements;
	protected Select select;
	protected JavascriptExecutor jsExcutor;
	protected Actions actions;

	// The functions are interact with the browser.
	public BasePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void back() {
		driver.navigate().back();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void sendKeysAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getTextAlert() {
		return driver.switchTo().alert().getText();
	}

	public void waitAlertPresence() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchTiWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}

	public boolean areAllWindowsCloseWithoutParent(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	// The functions are interaction with the elements
	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(String locator) {
		return driver.findElement(byXpath(locator));
	}

	public WebElement findElementByXpath(String locator, String... value) {
		return driver.findElement(byXpath(castToObject(locator, value)));
	}

	public List<WebElement> findElementsByXpath(String locator) {
		return driver.findElements(byXpath(locator));
	}

	public void clickToElement(String locator) {
		findElementByXpath(locator).click();
	}

	public void sendkeyToElement(String locator, String value) {
		element = findElementByXpath(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void sendkeyToElement(String locator, String value, String... expectedText) {
		element = findElementByXpath(castToObject(locator, expectedText));
		element.clear();
		element.sendKeys(value);
		element.sendKeys(Keys.TAB);
	}

	public String getElementText(String locator) {
		return findElementByXpath(locator).getText().trim();
	}

	public String getElementText(String locator, String... strings) {
		return findElementByXpath(castToObject(locator, strings)).getText().trim();
	}

	public String getAttributeText(String locator, String attributeName) {
		return findElementByXpath(locator).getAttribute(attributeName);
	}

	public void selectValueInDropdown(String locator, String value) {
		select = new Select(findElementByXpath(locator));
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInDropdown(String locator) {
		select = new Select(findElementByXpath(locator));
		return select.getFirstSelectedOption().getText();
	}

	public void sleepInSeconds(long secondNumber) {
		try {
			Thread.sleep(secondNumber * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectItemCustomDropDown(String parentXpath, String allItemXath, String expectedValueItem) {

		jsExcutor = (JavascriptExecutor) driver;
		element = findElementByXpath(parentXpath);
		jsExcutor.executeScript("arguments[0].click();", element);
		sleepInSeconds(1);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemXath)));
		elements = findElementsByXpath(allItemXath);
		for (WebElement childElemnt : elements) {
			if (childElemnt.getText().equals(expectedValueItem)) {
				if (childElemnt.isDisplayed()) {
					childElemnt.click();
				} else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", childElemnt);
					jsExcutor.executeScript("arguments[0].click();", childElemnt);
				}
				sleepInSeconds(1);
				break;
			}

		}
	}

	public int countElementNumber(String locator) {
		elements = findElementsByXpath(locator);
		return elements.size();
	}

	public void checkToCheckBox(String locator) {
		element = findElementByXpath(locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckBox(String locator) {
		element = findElementByXpath(locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(String locator) {
		return findElementByXpath(locator).isDisplayed();
	}

	public boolean isElementDisplayed(String locator, String... expectedText) {
		return findElementByXpath(castToObject(locator, expectedText)).isDisplayed();
	}

	public boolean isElementSelected(String locator) {
		return findElementByXpath(locator).isSelected();
	}

	public boolean isElementSelected(String locator, String... expectedText) {
		return findElementByXpath(castToObject(locator, expectedText)).isSelected();
	}

	public boolean isElementEnabled(String locator) {
		return findElementByXpath(locator).isEnabled();
	}

	public boolean isElementEnabled(String locator, String... expectedText) {
		return findElementByXpath(castToObject(locator, expectedText)).isEnabled();
	}

	public void switchToIFrameOrFrame(String locator) {
		driver.switchTo().frame(locator);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToelement(String locator) {
		actions = new Actions(driver);
		actions.moveToElement(findElementByXpath(locator)).perform();
	}

	public void doubleClickToElement(String locator) {
		actions = new Actions(driver);
		actions.doubleClick(findElementByXpath(locator)).perform();
	}

	public void rightClickToElement(String locator) {
		actions = new Actions(driver);
		actions.contextClick(findElementByXpath(locator)).perform();
	}

	public void sendKeyboardToElement(String locator, String key) {
		actions = new Actions(driver);
		actions.sendKeys(findElementByXpath(locator), key).perform();
	}

	public void clickToElementByJS(String locator) {
		jsExcutor = (JavascriptExecutor) driver;
		element = findElementByXpath(locator);
		jsExcutor.executeScript("arguments[0].click();", element);
	}

	public void sendKeyToElementByJS(String locator, String value) {
		jsExcutor = (JavascriptExecutor) driver;
		element = findElementByXpath(locator);
		jsExcutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public boolean verifyTextInInnerTextByJS(String expectedText) {
		jsExcutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExcutor
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "'[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPageByJS() {
		jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public void highlightElementByJS(String locator) {
		jsExcutor = (JavascriptExecutor) driver;
		element = findElementByXpath(locator);
		String originalStyle = element.getAttribute("style");
		jsExcutor.executeScript("arguments[0].setAttribute(arguments[1], argument[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		sleepInSeconds(1);
		jsExcutor.executeScript("arguments[0].setAttribute(arguments[1], argument[2])", element, "style",
				originalStyle);
	}

	public boolean isImageLoaded(String locator) {
		jsExcutor = (JavascriptExecutor) driver;
		element = findElementByXpath(locator);
		boolean status = (boolean) jsExcutor.executeScript("return arguments[0].complete && typeof arguments[0]"
				+ ".naturalWidth != 'undefined' && arguments[0]" + ".naturalWidth > 0", element);
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(String locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementVisible(String locator, String... value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, value))));
	}

	public void waitForElementInvisible(String locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementInvisible(String locator, String... value) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, value))));
	}

	public void waitForElemenClickable(String locator) {
		wait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public void waitForElemenClickable(String locator, String... value) {
		wait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, value))));
	}

	public void waitForElemenPresence(String locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
	}

	public void waitForElemenPresence(String locator, String... value) {
		wait.until(ExpectedConditions.presenceOfElementLocated(byXpath(castToObject(locator, value))));
	}

	// commons open page

	public String castToObject(String locator, String... value) {
		return locator = String.format(locator, (Object[]) value);
	}

	public void clickToElement(String locator, String... value) {
		findElementByXpath(castToObject(locator, value)).click();
	}

	public void removeAttributeInDom(String locator, String attributeRemove) {
		jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				findElementByXpath(locator));
	}

	public void removeAttributeInDom(String locator, String attributeRemove, String... nameAttributeValue) {
		jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				findElementByXpath(castToObject(locator, nameAttributeValue)));
	}

	public void scrollToElement(String locator) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(locator));
		Thread.sleep(2000);
	}

	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
}
