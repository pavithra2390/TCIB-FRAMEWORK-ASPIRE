package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class HomePage extends LoadableComponent<HomePage> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C4__TAB_1")
	private WebElement tabAllAccounts;

	@FindBy(css = "#C5__TAB_92C8180858EF87BE55117")
	private WebElement tabFavorites;

	@FindBy(css = "#C5__TAB_92C8180858EF87BE55122")
	private WebElement tabQuickTransfers;

	@FindBy(css = "#C2__QUE_A117FA02FB501A4792871")
	private WebElement txtTotalAmount;

	@FindBy(xpath = "//*[@id='C2__QUE_A117FA02FB501A4792871']/preceding-sibling::span")
	private WebElement txtBaseCurrency;

	@FindBy(css = "#BUT_E46AC959C67B3EEE1869269")
	private WebElement lnkDiscover;

	@FindBy(css = "a[oldtitle='Card help']")
	private WebElement lnkCardHelp;

	private Headers headers;

	private Footers footers;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		headers = new Headers(driver);
		footers = new Footers(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		WaitUtil.waitForSpinnerToComplete(driver);
		if (isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, tabAllAccounts)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, tabAllAccounts)) {
			Log.fail("Not navigated to Homepage!", driver);
		} else {
			Log.message("Navigated to Homepage!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public boolean verifyOpenHomePage() throws Exception {
		WaitUtil.waitForElement(driver, tabAllAccounts, 15);
		if (tabAllAccounts.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public String getAllAccountsTab() throws Exception {
		return BrowserActions.getText(driver, tabAllAccounts,
				"All accounts Tab");
	}

	public String getFavoritesTab() throws Exception {
		return BrowserActions.getText(driver, tabFavorites, "Favorites Tab");
	}

	public String getQuickTransferTab() throws Exception {
		return BrowserActions.getText(driver, tabQuickTransfers,
				"Quick Transfers Tab");
	}

	public AllAccountsTab goToAllAccountsTab() throws Exception {
		BrowserActions.clickOnButton(tabAllAccounts, driver, "All Accounts Tab");
		return new AllAccountsTab(driver).get();
	}

	public QuickTransferTab goToQuickTransferTab() throws Exception {
		BrowserActions.clickOnButton(tabQuickTransfers, driver,
				"Quick Transfer Tab");
		return new QuickTransferTab(driver).get();
	}

	public DiscoverPage gotoDiscoverPage() throws Exception {
		BrowserActions.clickOnButton(lnkDiscover, driver, "Discover Page Link");
		return new DiscoverPage(driver).get();
	}

	public Headers getHeaders() {
		return headers;
	}

	public Footers getFooters() {
		return footers;
	}
}
