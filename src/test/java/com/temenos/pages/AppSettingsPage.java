package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class AppSettingsPage extends LoadableComponent<AppSettingsPage> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "input[name*='ACCOUNTSFLAG']")
	private WebElement checkBoxAccounts;

	@FindBy(css = "input[name*='DEPOSITSFLAG']")
	private WebElement checkBoxDeposit;

	@FindBy(css = "input[name*='LOANSFLAG']")
	private WebElement checkBoxLoans;

	@FindBy(css = "a[oldtitle='REMOVE']")
	private WebElement lnkRemoveProfilePic;

	@FindBy(css = ".onoffswitch")
	private WebElement btnOnOffSwitch;

	@FindBy(css = "#C5__HEAD_BCCD9517C9CCE06D231111")
	private WebElement btnprofilePicture;

	@FindBy(css = "#C5__p4_QUE_120F9058C9BDDF74424784 > div > div > label > span.onoffswitch-switch")
	private WebElement btnTermDepositbal;

	@FindBy(css = "#C5__BUT_BCCD9517C9CCE06D231113>span")
	private WebElement btnRemove;

	private Headers headers;

	private Footers footers;

	public AppSettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		headers = new Headers(driver);
		footers = new Footers(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, checkBoxAccounts)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to App Settings Page!", driver);
		} else {
			Log.message("Navigated to App Settings page!", driver);
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

	public boolean verifyOpenAppSettings() throws Exception {
		WaitUtil.waitForElement(driver, checkBoxAccounts, 15);
		if (checkBoxAccounts.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyButtonAppSettings() throws Exception {
		WaitUtil.waitForElement(driver, btnprofilePicture, 15);
		if (btnOnOffSwitch.getSize() != null && btnprofilePicture.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void clickOnPushBtn() throws Exception {
		BrowserActions.clickOnButton(btnTermDepositbal, driver, "Term deposit");
	}

	public boolean clickOnRemoveProfile() throws Exception {
		if (btnRemove.isDisplayed()) {
			BrowserActions.clickOnButton(btnRemove, driver,
					"Remove Profile Picture");
			return true;
		}
		return false;
	}
}
