package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.SearchPageObject;

public class TC_Search {
	WebDriver driver;
	SearchPageObject searchPageObject;
	String searchValue;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void beforeClass() {
		searchPageObject = new SearchPageObject(driver);
	}
	
	@Test
	public void TC_01_Search_Empty_Data() {
		searchPageObject.openBrowser(driver, "https://demo.nopcommerce.com/");
		searchPageObject.clickSearchButton();
		assertEquals(searchPageObject.getAlertText(driver), "Please enter some search keyword");
		searchPageObject.acceptAlert(driver);
	}
	
	@Test
	public void TC_02_Search_Less_Than_3_Characters() {
		searchValue="ab";
		searchPageObject.openBrowser(driver, "https://demo.nopcommerce.com/");
		searchPageObject.inputSearch(searchValue);
		searchPageObject.clickSearchButton();
		
		assertEquals(searchPageObject.getSearchError(), "Search term minimum length is 3 characters");
	}
	
	@Test
	public void TC_03_Relative_Search_With_Product_Name() {
		searchValue="Shoes";
		searchPageObject.openBrowser(driver, "https://demo.nopcommerce.com/");
		searchPageObject.inputSearch(searchValue);
		searchPageObject.clickSearchButton();
		assertTrue(searchPageObject.getNumberOfSearchResult(searchValue)>1);
	}
	
	@Test
	public void TC_04_Absolute_Search_With_Product_Name() {
		searchValue="Build your own computer";
		searchPageObject.openBrowser(driver, "https://demo.nopcommerce.com/");
		searchPageObject.inputSearch(searchValue);
		searchPageObject.clickSearchButton();
		assertTrue(searchPageObject.getNumberOfSearchResult(searchValue)==1);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
