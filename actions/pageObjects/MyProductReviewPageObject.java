package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.MyProductReviewPageUI;

public class MyProductReviewPageObject extends BasePage {
	private WebDriver driver;	
	public MyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getReviewTitle() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEW_TITLE);
		return getElementText(driver, MyProductReviewPageUI.REVIEW_TITLE);
	}
	
	public String getReviewText() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEW_TEXT);
		return getElementText(driver, MyProductReviewPageUI.REVIEW_TEXT);
	}
}
