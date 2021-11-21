package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.SearchPageUI;

public class SearchPageObject extends BasePage {
	private WebDriver driver;
	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void inputSearch(String searchValue) {
		waitForElementVisible(driver, SearchPageUI.SEARCH_TEXTBOX);
		inputIntoElement(driver, SearchPageUI.SEARCH_TEXTBOX,searchValue);
	}
	
	public void clickSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickElement(driver, SearchPageUI.SEARCH_BUTTON);
	}
	
	public void selectProduct(String searchValue) {
		String xpath = SearchPageUI.PRODUCT_TITLE + "[contains(text(),'"+ searchValue + "')]";
		waitForElementClickable(driver, xpath);
		clickElement(driver, xpath);
	}
	
	public String getSearchError() {
		return getElementText(driver,SearchPageUI.SEARCH_ERROR_MESSAGE);
	}
	
	public int getNumberOfSearchResult(String searchValue) {
		return getElementSize(driver, SearchPageUI.PRODUCT_TITLE);
	}
}
