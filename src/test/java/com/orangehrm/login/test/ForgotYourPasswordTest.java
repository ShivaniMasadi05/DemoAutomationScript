package com.orangehrm.login.test;

import static com.orangehrm.util.Constants.*;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.orangehrm.base.test.BaseTest;
import com.orangehrm.login.page.ForgotYourPasswordPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class ForgotYourPasswordTest extends BaseTest {

	public static Logger logger = Logger.getLogger(ForgotYourPasswordTest.class);

	@BeforeClass
	@Parameters({ "browser", "siteURL" })
	public void initBrowserAndWebsite(String browser, String siteURL) throws InterruptedException {
		logger.info("Starting initBrowserAndWebsite Method ");

		getBrowserAndWebsite(browser, siteURL);
		forgotYourPasswordPage = new ForgotYourPasswordPage(driver);

		logger.info("Ending initBrowserAndWebsite Method ");
	}

	@Test(priority = 1, description = "Verify Forgot Your Password by Entering Invalid Username")
	@Description("Test Case #1,Verify Forgot Your Password by Entering Invalid Username")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Test Case #1, Verify Forgot Your Password by Entering Invalid Username")
	public void verifyLoginByEnteringInvalidUsername() {
		logger.info("Starting verifyLoginByEnteringInvalidUsername Method");

		forgotYourPasswordPage.clickOnForgotYourPassword();

		Assert.assertEquals(forgotYourPasswordPage.getResetPasswordText(),
				expectedAssertionsProp.get(RESET_PASSWORD_TEXT));

		forgotYourPasswordPage.setUsername(testDataProp.getProperty(INVALID_USERNAME_TEXT));
		forgotYourPasswordPage.clickOnResetPasswordButton();

		Assert.assertEquals(forgotYourPasswordPage.getResetPasswordLinkSentSuccessfullyText(),
				expectedAssertionsProp.get(RESET_PASSWORD_LINK_SENT_SUCCESSFULLY_TEXT));

		logger.info("Ending verifyLoginByEnteringInvalidUsername Method");

	}
	
	@Test(priority = 2, description = "Verify Forgot Your Password by Entering Valid Username")
	@Description("Test Case #2,Verify Forgot Your Password by Entering Valid Username")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Test Case #2, Verify Forgot Your Password by Entering Valid Username")
	public void verifyLoginByEnteringValidUsername() {
		logger.info("Starting verifyLoginByEnteringValidUsername Method");

		driver.navigate().back();
		driver.navigate().refresh();
		
		forgotYourPasswordPage.setUsername(testDataProp.getProperty(USERNAME_TEXT));
		forgotYourPasswordPage.clickOnResetPasswordButton();

		Assert.assertEquals(forgotYourPasswordPage.getResetPasswordLinkSentSuccessfullyText(),
				expectedAssertionsProp.get(RESET_PASSWORD_LINK_SENT_SUCCESSFULLY_TEXT));

		logger.info("Ending verifyLoginByEnteringValidUsername Method");

	}

}
