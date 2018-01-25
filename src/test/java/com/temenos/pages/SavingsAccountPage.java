package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class SavingsAccountPage extends LoadableComponent<SavingsAccountPage> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C4__C2__HEAD_26B2531682588B1B1795189")
	private WebElement pageTitle;

	@FindBy(css = "#C4__C2__TXT_26B2531682588B1B1806453_R1 h2.tc-fs-p2")
	private WebElement savingsAccountText;

	@FindBy(css = "#C4__C2__TXT_26B2531682588B1B1806453_R2")
	private WebElement childAccountText;

	@FindBy(css = "#C4__C2__TXT_26B2531682588B1B1806453_R3")
	private WebElement savingsAccountFCYText;

	@FindBy(css = "#C4__C2__TXT_26B2531682588B1B1806453_R4")
	private WebElement noticeAccountText;

	@FindBy(css = "#C4__C2__TXT_26B2531682588B1B1806453_R5")
	private WebElement staffAccountSavingsText;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B1792403_R1")
	private WebElement savingsAccountButton;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B1792403_R2")
	private WebElement childAccountButton;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B1792403_R3")
	private WebElement savingsAccountFCYButton;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B1792403_R4")
	private WebElement noticeAccountButton;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B1792403_R5")
	private WebElement staffAccountSavingsButton;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B1825964")
	private WebElement backButton;

	public SavingsAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Savings Account page!", driver);
		} else {
			Log.message("Navigated to Savings Account page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

}