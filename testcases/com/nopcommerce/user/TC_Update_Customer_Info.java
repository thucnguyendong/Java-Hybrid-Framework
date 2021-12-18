package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class TC_Update_Customer_Info extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	CustomerInfoPageObject customerInfoPage;
	String firstName;
	String lastName;
	String company;
	String day;
	String month;
	String year;
	String emailAddress;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		driver = getBrowserDriver("chrome");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.openHomePage();
	}
	
	@BeforeClass
	public void beforeClass() {
		registerPage = homePage.clickRegisterLink();
		
		emailAddress = "test"+ registerPage.getRandomNumber()+"@gmail.com";		
		firstName = "Thuc";
		lastName= "Nguyen";
		company = "Livegroup";
		String password = "123456";
		String confirmPassword= "123456";
		day = "5";
		month = "May";
		year = "1995";
		
		registerPage.selectMaleGender();
		registerPage.inputFirstName(firstName);
		registerPage.inputLastName(lastName);
		registerPage.selectDay(day);
		registerPage.selectMonth(month);
		registerPage.selectYear(year);
		registerPage.inputCompany(company);
		registerPage.inputEmail(emailAddress);
		registerPage.inputPassword(password);
		registerPage.inputConfirmPassword(confirmPassword);
		homePage =registerPage.clickRegisterButton();
	}
	
	@Test
	public void TC_01_Update_Customer_Info() {
		firstName = "Automation";
		lastName = "FC";
		day="1";
		month="January";
		year="1990";
		emailAddress = "automationfc.vn@gmail.com";
		company ="Automation FC";
		
		customerInfoPage = homePage.clickMyAccountLink(driver);
		customerInfoPage.selectFemaleGender();
		customerInfoPage.inputFirstName(firstName);
		customerInfoPage.inputLastName(lastName);
		customerInfoPage.selectDay(day);
		customerInfoPage.selectMonth(month);
		customerInfoPage.selectYear(year);
		customerInfoPage.inputEmail(emailAddress);
		customerInfoPage.inputCompany(company);
		customerInfoPage.clickSave();
		
		assertTrue(customerInfoPage.isFemaleSelected());
		assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
		assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
		assertEquals(customerInfoPage.getSelectedDayValue(),day);
		assertEquals(customerInfoPage.getSelectedMonthValue(),month);
		assertEquals(customerInfoPage.getSelectedYearValue(),year);
		assertEquals(customerInfoPage.getEmailTextboxValue(),emailAddress);
		assertEquals(customerInfoPage.getCompanyTextboxValue(),company);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
