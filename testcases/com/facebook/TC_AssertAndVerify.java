package com.facebook;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

public class TC_AssertAndVerify extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName,url);	
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}
	
	@Test
	public void TC_01_Assert() {	
		registerPage.clickRegisterLink();
		verifyTrue(registerPage.isEmailTextboxDisplayed());
		verifyTrue(registerPage.isConfirmEmailTextboxDisplayed());
		verifyFalse(registerPage.isLoginButtonNotDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
