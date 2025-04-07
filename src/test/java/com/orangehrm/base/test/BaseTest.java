package com.orangehrm.base.test;


import static com.orangehrm.util.Constants.PASSWORD_TEXT;
import static com.orangehrm.util.Constants.USERNAME_TEXT;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.orangehrm.admin.page.UserManagementPage;
import com.orangehrm.login.page.ForgotYourPasswordPage;
import com.orangehrm.login.page.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


public class BaseTest {
	
	public  WebDriver driver = null;
	public LoginPage loginPage;
	public ForgotYourPasswordPage forgotYourPasswordPage;
	public UserManagementPage userManagementPage;

	private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

	protected static Properties testDataProp = null;
	protected static Properties expectedAssertionsProp = null;

	@BeforeSuite
	public void initTestdata() {
		FileReader testDataReader = null;
		FileReader assertionReader = null;
		try {
			testDataReader = new FileReader("src/main/resources/testdata.properties");
			assertionReader = new FileReader("src/main/resources/expectedassertions.properties");

			testDataProp = new Properties();
			testDataProp.load(testDataReader);

			expectedAssertionsProp = new Properties();
			expectedAssertionsProp.load(assertionReader);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getBrowserAndWebsite(String browser,String siteURL) throws InterruptedException {
		logger.info("Starting getBrowserAndWebsite method");

		if (browser.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}

		driver.manage().window().maximize();
	
		driver.get(siteURL);
		driver.navigate().to(siteURL);
		
		logger.info("Ending getBrowserAndWebsite method");

	}

	public void clickOutside() {
		logger.info("Starting of clickOutside method");

		Actions action = new Actions(driver);
		action.moveByOffset(0, 0).click().build().perform();

		logger.info("Ending of clickOutside method");
	}
	
	public void ValidLogin() {
		logger.info("Starting verifyLoginByLeavingAllFieldsBlank Method");

		loginPage=new LoginPage(driver);
		loginPage.setUsername(testDataProp.getProperty(USERNAME_TEXT));
		loginPage.setPassword(testDataProp.getProperty(PASSWORD_TEXT));
		loginPage.clickOnLoginButton();
		
	    Assert.assertTrue(loginPage.isDisplayedOrangeHRMLogo());

		logger.info("Ending verifyLoginByLeavingAllFieldsBlank Method");

	}

	

}
