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
	
	public ProductPageObject selectProduct(String searchValue) {
		String xpath = SearchPageUI.PRODUCT_TITLE +"/a[contains(text(),'"+ searchValue + "')]";
		waitForElementClickable(driver, xpath);
		clickElement(driver, xpath);
		return PageGeneratorManager.getProductPage(driver);
	}

	public String getSearchErrorText() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_ERROR);
		return getElementText(driver, SearchPageUI.SEARCH_ERROR);
	}
	
	public String getSearchNoValueText() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_NO_RESULT);
		return getElementText(driver, SearchPageUI.SEARCH_NO_RESULT);
	}


	public int getNumberOfSearchResult(String searchValue) {
		waitForAllElementsVisible(driver, SearchPageUI.PRODUCT_TITLE);
		return getElementSize(driver, SearchPageUI.PRODUCT_TITLE);
	}
}
