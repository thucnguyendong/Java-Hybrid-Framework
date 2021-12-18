package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.BasePageUI;
import pageUI.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void openHomePage() {
		openBrowser(driver,"https://demo.nopcommerce.com");
	}
	public LoginPageObject clickLogInLink() {
		waitForElementClickable(driver, BasePageUI.LOGIN_LINK);
		clickElement(driver, BasePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	public RegisterPageObject clickRegisterLink() {
		waitForElementClickable(driver, BasePageUI.REGISTER_LINK);
		clickElement(driver, BasePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	public void clickMyAccountLink() {
		waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
		clickElement(driver, BasePageUI.MY_ACCOUNT_LINK);
	}
	
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
}
