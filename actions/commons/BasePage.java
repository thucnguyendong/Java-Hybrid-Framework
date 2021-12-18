
package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.AddressPageObject;
import pageObjects.BackInStockSubscriptionPageObject;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.DownloadableProductPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.OrderPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointPageObject;
import pageUI.BasePageUI;

public class BasePage {
	
	protected static BasePage getBasePage() {
		return new BasePage();
	}
	
	/** 
	 * Open browser
	 * @param driver
	 * @param Url of page
	 */	
	public void openBrowser(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	/** 
	 * Get Title of the current page
	 * @param driver
	 * @return the title of the current page
	 */	
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	/** 
	 * Get URL of the current page
	 * @param driver
	 * @return the url of the current page
	 */	
	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	/** 
	 * Get html page source code of the current page
	 * @param driver
	 * @return the html source text of the current page
	 */	
	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	/** 
	 * Back to previous page of the web
	 * @param driver
	 */	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
		
	/** 
	 * Forward to next page of the web
	 * @param driver
	 */	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	/** 
	 * Refresh the current page of the web
	 * @param driver
	 */		
	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	/** 
	 * Wait for the browser alert to appear
	 * @param driver
	 */		
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	/** 
	 * Confirm the browser alert
	 * @param driver
	 */		
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	/** 
	 * Close the browser alert
	 * @param driver
	 */		
	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	/** 
	 * Get the text of the browser alert
	 * @param driver
	 */		
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	/** 
	 * Switch to another tab (only 2 tabs)
	 * @param driver
	 * @param ID of the first/home/default tab
	 */	
	protected void switchToWindowNotHomeByID(WebDriver driver,String homeID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(homeID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	/** 
	 * Switch to another tab using window/tab title
	 * @param driver
	 * @param title of the another tab
	 */	
	protected void switchToWindowByTitle(WebDriver driver,String windowTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(windowTitle)) {
				break;
			}
		}
	}
	
	/** 
	 * Close all tabs except the first/home/default tab
	 * @param driver
	 * @param ID of the first/home/default tab
	 */	
	protected void closeAllWithoutParent(WebDriver driver,String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
		
	/** 
	 * Input text to the browser alert
	 * @param driver
	 */		
	protected void inputToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	/** 
	 * Get xpath xpathLocator of the element
	 * @param xpath of the element
	 * @return xpathLocator of the element
	 */	
	private By getByXpath(String xpath) {
		return By.xpath(xpath);
	}
	
	/** 
	 * Find and get the Web Element
	 * @param driver
	 * @param xpathLocator xpathLocator of the element
	 * @return web element found
	 */	
	private WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	/** 
	 * Find and get the list of Web Element
	 * @param driver
	 * @param xpathLocator xpathLocator of elements
	 * @return list of web element found
	 */	
	private List<WebElement> getListElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	private String getDynamicLocator(String locator, String...params) {
		return String.format(locator, (Object[])params);
	}
	/** 
	 * Click element
	 * @param driver
	 * @param xpathLocator of the element
	 */	
	public void clickElement(WebDriver driver, String xpathLocator) {
		getElement(driver,xpathLocator).click();
	}
	
	protected void clickElement(WebDriver driver, String locator, String...params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}
	
	/**
	 * Input string value into element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param input value inputed into the element
	 */	
	protected void inputIntoElement(WebDriver driver, String xpathLocator, String input) {
		WebElement element = getElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(input);
	}
	
	protected void inputIntoElement(WebDriver driver, String xpathLocator, String input, String...params) {
		WebElement element = getElement(driver, getDynamicLocator(xpathLocator, params));
		element.clear();
		element.sendKeys(input);
	}
	
	/**
	 * Select Element in the dropdownList
	 * @param driver
	 * @param xpathLocator of the element
	 * @param input visible text you want to select from the list
	 */	
	protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String input) {
		waitForAllElementsToBePresenced(driver, xpathLocator+"/option");
		Select selectDropdownList = new Select(getElement(driver, xpathLocator));
		selectDropdownList.selectByVisibleText(input);
	}
	
	/**
	 * Get text of the selected item in the dropdownList
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected String getItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select selectDropdownList = new Select(driver.findElement(getByXpath(xpathLocator)));
		return selectDropdownList.getFirstSelectedOption().getText();
	}
	
	/**
	 * Get text of the selected item in the dropdownList
	 * @param driver
	 * @param xpathLocator of the dropdown element
	 * @param xpathLocator of the items in dropdown element
	 * @param value you want to select in the dropdown
	 */
	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String itemValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		getElement(driver, parentLocator).click();		
		List<WebElement> allItems= explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
		
		for (WebElement item: allItems) {
			if (item.getText().equals(itemValue)) {
				if(!item.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					sleepInSecond(1);
				}
				item.click();
				break;
			}
		}
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @return element text
	 */	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).getText();
	}
	
	protected String getElementText(WebDriver driver, String xpathLocator, String...params) {
		return getElement(driver, getDynamicLocator(xpathLocator, params)).getText();
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param attribute tag name of the element
	 * @return attribute value
	 */	
	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	/**
	 * Get text of the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param css tag name of the element
	 * @return css value of
	 */	
	protected String getCssValue(WebDriver driver, String xpathLocator, String cssValue) {
		return getElement(driver, xpathLocator).getCssValue(cssValue);
	}
	
	/**
	 * get color of the rgba
	 * @param driver
	 * @return css value as string
	 */	
	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	/**
	 * Get size of multiple the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @return size of element list-
	 */
	protected int getElementSize(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator)).size();
	}
	
	/**
	 * Check the checkbox to be selected before unchecking it
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	/**
	 * Check the checkbox to be unselected before checking it
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	/**
	 * Check the element to be enabled
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}
	
	/**
	 * Check the element to be displayed
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isDisplayed();
	}
	
	protected boolean isElementDisplayed(WebDriver driver, String xpathLocator, String...params) {
		return getElement(driver, getDynamicLocator(xpathLocator, params)).isDisplayed();
	}
	
	/**
	 * Check the element to be selected
	 * @param driver
	 * @param xpathLocator of the element
	 * @return true/false
	 */
	protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isSelected();
	}
	
	/**
	 * Switch back to default/main frame
	 * @param driver
	 */
	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * Switch to Frame/ iFrame
	 * @param driver
	 * @param xpathLocator of the frame
	 */
	protected void switchToFrame(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getElement(driver, xpathLocator));
	}
	
	/**
	 * Hover to element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected void moveToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, xpathLocator)).perform();
	}
	
	/**
	 * Interact with keyboard
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected void sendKeyboardToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(driver, xpathLocator),key).perform();
	}
	
	/**
	 * Inject a java script to web driver
	 * @param driver
	 * @param javaScript script user want to input
	 * @return result after script running (can be void, text, number...)
	 */
	protected Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}
	
	/**
	 * Get the innerText property of element
	 * @param driver
	 * @return innerText of Element
	 */
	protected String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	/**
	 * Compare the innerText property of element with expected text
	 * @param driver
	 * @return true/false
	 */
	protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}
	
	/**
	 * Scroll to the bottom of the page
	 * @param driver
	 */
	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * Navigate to another URL
	 * @param driver
	 * @param url of the landed page
	 */
	protected void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	/**
	 * highlight the border of the element red
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	/**
	 * click the element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, xpathLocator));
	}
	
	/**
	 * scroll to the element
	 * @param driver
	 * @param xpathLocator of the element
	 */
	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpathLocator));
	}
	
	/**
	 * input value to the element
	 * @param driver
	 * @param xpathLocator of the element
	 * @param value inputed into element
	 */
	protected void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, xpathLocator));
	}

	/**
	 * remove attribute of the element in DOM
	 * @param driver
	 * @param xpathLocator of the element
	 * @param attributeRemove attribute tag name of the element that user want to remove
	 */
	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xpathLocator));
	}

	/**
	 * check that all jquery and javascript in the page are loaded fully
	 * @param driver
	 * @return true/false
	 */
	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	/**
	 * get the validation message of the element (not in DOM)
	 * @param driver
	 * @param xpathLocator of the element
	 * @return validation message text
	 */
	protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, xpathLocator));
	}

	/**
	 * check that the image is loaded fully
	 * @param driver
	 * @param locator of the image
	 * @return true/false
	 */
	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * wait for the element to display
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	protected void waitForElementVisible(WebDriver driver, String xpathLocator, String...params) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}
	
	/**
	 * wait for all elements to be display
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
		
	/**
	 * wait for the element to be clickable
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}
	
	protected void waitForElementClickable(WebDriver driver, String xpathLocator, String...params) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, params))));
	}
		
	/**
	 * wait for the element to be invisible/no long display
	 * @param driver
	 * @param xpathLocator of the element to wait
	 */
	protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	protected void waitForElementInvisible(WebDriver driver, String xpathLocator, String...params) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}
	
	/**
	 * wait for all elements to be invisible/no long display
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	protected void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, xpathLocator)));
	}
	
	/**
	 * wait for all elements to be presence
	 * @param driver
	 * @param xpathLocator of elements to wait
	 */
	protected void waitForAllElementsToBePresenced(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	
	protected void waitForStaleness(WebDriver driver, String xpathLocator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.stalenessOf(getElement(driver, xpathLocator)));
	}
	
	/**
	 * Create a random number
	 */	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	/**
	 * Hard wait the test
	 * @param timeoutInSecond waiting time in second
	 */	
	public void sleepInSecond(int timeoutInSecond){
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickElement(driver, BasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
	}
	
	public CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}
	
	public AddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getAddressPage(driver);
	}
	
	public OrderPageObject openOrderPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ORDER_LINK);
		clickElement(driver, BasePageUI.ORDER_LINK);
		return PageGeneratorManager.getOrderPage(driver);
	}
	
	public DownloadableProductPageObject openDownloadableProductPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
		clickElement(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
		return PageGeneratorManager.getDownloadableProductPage(driver);
	}
	
	public BackInStockSubscriptionPageObject openBackInStockSubscriptionPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.BACK_IN_STOCK_SUBSCRIPTION_LINK);
		clickElement(driver, BasePageUI.BACK_IN_STOCK_SUBSCRIPTION_LINK);
		return PageGeneratorManager.getBackInStockSubscriptionPage(driver);
	}
	
	public RewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getRewardPointPage(driver);
	}
	
	public ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CHANGE_PASSWORD_LINK);
		clickElement(driver, BasePageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getChangePasswordPage(driver);
	}
	
	public MyProductReviewPageObject openMyReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getMyProductReviewPage(driver);
	}
	
	public CustomerInfoPageObject clickMyAccountLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
		clickElement(driver, BasePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}
	
	public HomePageObject clickLogOutLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
		clickElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}
}
