package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class CustomerProfilePage extends LoadableComponent<CustomerProfilePage> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C5__TAB_48D258774FD2468F211970")
	private WebElement tabCustomerDetails;

	@FindBy(css = "#C5__TAB_48D258774FD2468F212014")
	private WebElement tabPreferences;

	private Headers headers;

	private Footers footers;

	public CustomerProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		headers = new Headers(driver);
		footers = new Footers(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, tabCustomerDetails)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Customer Profile Page!", driver);
		} else {
			Log.message("Navigated to Customer Profile Page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public Headers getHeaders() {
		return headers;
	}

	public Footers getFooters() {
		return footers;
	}

	public boolean verifyOpenCustomerPage() throws Exception{
		WaitUtil.waitForElement(driver, tabCustomerDetails, 15);
		if (tabCustomerDetails.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public class ContactDetails {

		private WebDriver driver;

		@FindBy(css = "#C5__C1__BUT_701CAE441BD094F7207364>span")
		private WebElement lnkEditPhoneNumber;

		@FindBy(css = "#C5__C1__BUT_701CAE441BD094F7207369>span")
		private WebElement lnkEditEmail;

		@FindBy(css = "#C5__C1__QUE_701CAE441BD094F772809")
		private WebElement txtPhoneNumber;

		@FindBy(css = "input[name*='EMAIL']")
		private WebElement txtEmail;

		@FindBy(css = "a[oldtitle='Save']")
		private WebElement lnkSave;

		@FindBy(css = "a[oldtitle='CANCEL'")
		private WebElement lnkCancel;

		public ContactDetails(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public void clickOnEditPhoneNumber() throws Exception{
			BrowserActions.clickOnButton(lnkEditPhoneNumber, driver,
					"Edit phone Number is clicked");
		}

		public void enterPhoneNumber(String phone) throws Exception{
			BrowserActions.typeOnTextField(txtPhoneNumber, phone, driver,
					"Phone Number");
		}

		public void clickOnSave() throws Exception{
			BrowserActions.clickOnButton(lnkSave, driver,
					"Clicked on save button");
		}
	}

	public class Preferences {

		private WebDriver driver;

		@FindBy(css = "a[title='Edit']")
		private WebElement lnkEditLanguage;

		@FindBy(css = "a[oldtitle='Save'")
		private WebElement lnkSave;

		@FindBy(css = "a[oldtitle='CANCEL'")
		private WebElement lnkCancel;

		public Preferences(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

	}
}
