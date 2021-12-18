package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUI.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	private WebDriver driver;
	public CustomerInfoPageObject (WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectMaleGender() {
		waitForElementClickable(driver, CustomerInfoPageUI.MALE_RADIO_BUTTON);
		clickElement(driver,CustomerInfoPageUI.MALE_RADIO_BUTTON);
	}
	
	public void selectFemaleGender() {
		waitForElementClickable(driver, CustomerInfoPageUI.FEMALE_RADIO_BUTTON);
		clickElement(driver,CustomerInfoPageUI.FEMALE_RADIO_BUTTON);
	}
	
	public void inputFirstName(String firstName) {
		waitForElementVisible(driver, CustomerInfoPageUI.FIRSTNAME_TEXTBOX);
		inputIntoElement(driver,CustomerInfoPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	
	public void inputLastName(String lastName) {
		waitForElementVisible(driver, CustomerInfoPageUI.LASTNAME_TEXTBOX);
		inputIntoElement(driver,CustomerInfoPageUI.LASTNAME_TEXTBOX, lastName);
	}
	
	public void selectDay(String day) {
		waitForElementVisible(driver, CustomerInfoPageUI.DAY_DROPDOWN);
		selectItemInDefaultDropdown(driver,CustomerInfoPageUI.DAY_DROPDOWN, day);
	}
	
	public void selectMonth(String month) {
		waitForElementVisible(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
		selectItemInDefaultDropdown(driver,CustomerInfoPageUI.MONTH_DROPDOWN, month);
	}
	
	public void selectYear(String year) {
		waitForElementVisible(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
		selectItemInDefaultDropdown(driver,CustomerInfoPageUI.YEAR_DROPDOWN, year);
	}
	
	public void inputEmail(String email) {
		waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver,CustomerInfoPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputCompany(String company) {
		waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_TEXTBOX);
		inputIntoElement(driver,CustomerInfoPageUI.COMPANY_TEXTBOX, company);
	}
	
	public void selectNewsletter() {
		waitForElementClickable(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}
	
	public void clickSave() {
		waitForElementClickable(driver, CustomerInfoPageUI.SAVE_BUTTON);
		clickElement(driver, CustomerInfoPageUI.SAVE_BUTTON);
	}
	
    public boolean isFemaleSelected()
    {
        waitForElementVisible(driver, CustomerInfoPageUI.FEMALE_RADIO_BUTTON);
        return isElementSelected(driver, CustomerInfoPageUI.FEMALE_RADIO_BUTTON);
    }
    
    public boolean isMaleSelected()
    {
        waitForElementVisible(driver, CustomerInfoPageUI.MALE_RADIO_BUTTON);
        return isElementSelected(driver, CustomerInfoPageUI.MALE_RADIO_BUTTON);
    }
	
    public String getFirstNameTextboxValue()
    {
        waitForElementVisible(driver, CustomerInfoPageUI.FIRSTNAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.FIRSTNAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxValue()
    {
    	waitForElementVisible(driver, CustomerInfoPageUI.LASTNAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.LASTNAME_TEXTBOX,"value");
    }

    public String getEmailTextboxValue()
    {
    	waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.EMAIL_TEXTBOX,"value");
    }
    
    public String getCompanyTextboxValue()
    {
    	waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.COMPANY_TEXTBOX,"value");
    }
    
    public String getSelectedDayValue()
    {
    	waitForElementVisible(driver, CustomerInfoPageUI.DAY_DROPDOWN);
        return getItemInDefaultDropdown(driver, CustomerInfoPageUI.DAY_DROPDOWN);
    }
    
    public String getSelectedMonthValue()
    {
    	waitForElementVisible(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
        return getItemInDefaultDropdown(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
    }
    
    public String getSelectedYearValue()
    {
    	waitForElementVisible(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
        return getItemInDefaultDropdown(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
    }
    
    public String getMyAccountPageTitle() {
    	waitForElementVisible(driver, CustomerInfoPageUI.MY_ACCOUNT_TITLE);
        return getElementText(driver, CustomerInfoPageUI.MY_ACCOUNT_TITLE);
    }
}
