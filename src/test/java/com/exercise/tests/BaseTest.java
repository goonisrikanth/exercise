package com.exercise.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;

import com.exercise.framework.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BaseTest {
	static Logger log = Logger.getLogger(BaseTest.class.getName());
	protected WebDriver driver;
	private String browser;
	private String appUrl;
	@AfterTest
	public void tearDown(){
		log.info("Closing browser sessions if any");
		if(driver!=null)
			driver.quit();
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeParams(String browserType, String appURL) {
		this.browser=browserType;
		this.appUrl=appURL;

	}

	@BeforeMethod
	public void initializeDriver() {
		log.info("In BaseTest, initializing WebDriver based on browser type");
		try {
			driver= DriverFactory.getWebDriver(this.browser,this.appUrl);
		} catch (Exception e) {
			System.out.println("Failed to initialize browser" + e.getStackTrace());
			throw e;
		}

	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("./screenshots/" + testResult.getName() + "-"
					+ Arrays.toString(testResult.getParameters()) +  ".png"));
		}
		driver.quit();
	}
}
