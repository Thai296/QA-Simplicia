package com.simplicia.qaautomation.commons;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestClass;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.simplicia.qaautomation.browserfactory.BrowserManagerFactory;
import com.simplicia.qaautomation.browserfactory.DriverManager;

public class BaseTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected ConfigurationLoader configLoaded;
	protected DriverManager driverManager;
	protected MenuNavigation menuNavigate;
	protected Properties properties;

	protected static final int timeOut = 30;
	protected Logger log = Logger.getLogger(getClass());

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		configLoaded = new ConfigurationLoader();
		properties = configLoaded.loadConfiguration();
		driverManager = BrowserManagerFactory
				.getBrowser(properties.getProperty(GlobalConstants.BROWSER));
		driver = driverManager.getDriver();

		wait = new WebDriverWait(driver, timeOut);
		menuNavigate = new MenuNavigation(driver, configLoaded);
		
		log.info("Step 1: Action - Open the Login page");
		menuNavigate.openLoginPage();

		driverManager.maximizeBrowser();
		driverManager.setImplicitWaitTimeOut(timeOut);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws Exception {
		try {
			switch (result.getStatus()) {
			case ITestResult.SUCCESS:
				log.info("***** Passed Test Case " + result.getMethod().getMethodName() + " *****");
				break;
			case ITestResult.FAILURE:
				log.error("***** Failed Test Case " + result.getMethod().getMethodName() + ". Capturing Screenshot "
						+ takeScreenshot(driver, result.getMethod()) + " *****");
				break;
			case ITestResult.SKIP:
				log.info("***** Skipped Test Case " + result.getMethod().getMethodName() + " *****");
				break;
			default:
				log.warn("***** Unknown Test Result Status (" + result.getStatus() + ") for Test Case "
						+ result.getMethod().getMethodName() + " *****");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			quitWebDriver();
		}
	}

	private String takeScreenshot(WebDriver driver, ITestNGMethod testMethod) throws IOException {
		if (driver == null) {
			log.warn("Unable to take screenshot as driver is not initialized, methodName="
					+ ((testMethod != null) ? testMethod.getMethodName() : null));
			return StringUtils.EMPTY;
		}
		byte[] content = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		File file = getScreenshotFile(testMethod.getMethodName(), testMethod.getTestClass());
		FileUtils.writeByteArrayToFile(file, content);
		return file.getAbsolutePath();
	}

	private static File getScreenshotFile(String testName, ITestClass testclass) throws IOException {
		return new File(System.getProperty("user.dir") + File.separator + "screen-shots" + File.separator
				+ getScreenshotName(testName, testclass));
	}

	private static String getScreenshotName(String testName, ITestClass testclass) {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		return testName + "_" + testclass.getName() + "_" + dateFormat.format(new Date()) + ".png";
	}

	protected void quitWebDriver() {
		if (driver != null) {
			try {
				if (driver.getCurrentUrl().startsWith("http")) {
					log.info("Attempting reset, url=" + driver.getCurrentUrl());
					new Actions(driver).keyDown(Keys.ALT).sendKeys("R").keyUp(Keys.ALT).perform();
					Thread.sleep(TimeUnit.SECONDS.toMillis(5));
					new Actions(driver).sendKeys(Keys.ENTER).perform();
				}
			} catch (Exception e) {
				// No big deal
			}
			try {
				driver.close();
			} catch (Exception e) {
				// Maybe no big deal either
			}
			try {
				driver.quit();
			} catch (Exception e) {
				log.error("Error quitting WebDriver, driver=" + driver, e);
			}
			driver = null;
		}
	}
}
