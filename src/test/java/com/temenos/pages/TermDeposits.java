package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class TermDeposits extends LoadableComponent<TermDeposits> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C4__C2__HEAD_26B2531682588B1B1795189")
	private WebElement pageTitle;

	@FindBy(css = "#C4__C2__TXT_26B2531682588B1B1806453_R1")
	private WebElement shortTermFixedDeposits;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B1792403_R1")
	private WebElement apply;

	public TermDeposits(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Term Deposits page!", driver);
		} else {
			Log.message("Navigated to Term Deposits page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

}