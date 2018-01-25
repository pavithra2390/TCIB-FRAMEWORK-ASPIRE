package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class PayOverdueTab extends LoadableComponent<PayOverdueTab> {

	private WebDriver driver;
	boolean isFailedFirstTime;

	@FindBy(css = "#C5__C4__HEAD_2F8E223FAE9F70151716663")
	private WebElement lblPayOverdue;

	@FindBy(css = "#C5__C4__row_QUE_B887ADA47B30BBDA34474")
	private WebElement lblTotalDue;

	@FindBy(css = "input[name*='AMOUNT']")
	private WebElement txtAmount;

	@FindBy(css = "#C5__C4__QUE_BCD8C5C21EC823B434108")
	private WebElement selectFromAccount;

	@FindBy(css = "#C5__C4__BUT_7F37BCD24078011F342179")
	private WebElement btnContinue;

	@FindBy(css = "#C5__C4__C2__row_HEAD_456BD73E4C87041D112534")
	private WebElement lbltxnRef;

	@FindBy(css = "#C5__C4__BUT_6A6A3A62F2D44155278957")
	private WebElement btnBack;

	@Override
	protected void isLoaded() throws Error {
		WaitUtil.waitForSpinnerToComplete(driver);
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, lblPayOverdue)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, lblPayOverdue)) {
			Log.fail("Not navigated to Pay Overdue page!", driver);
		} else {
			Log.message("Navigated to Pay Overdue Page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public PayOverdueTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTotalOverDue() throws Exception {
		return BrowserActions.getText(driver, lblTotalDue, "Total Due").split(
				"\n")[1];
	}

	public String getAmountToPay() throws Exception {
		return BrowserActions.getTextFromAttribute(driver, txtAmount, "value",
				"Total due amount");
	}

	public PayOverdueTab payAmount() throws Exception {
		BrowserActions.selectFromComboBox(selectFromAccount, "2", driver,
				"From Account selection box");
		BrowserActions.clickOnButton(btnContinue, driver, "Continue button");
		return new PayOverdueTab(driver).get();
	}

	public String getTransactionReference() throws Exception {
		return BrowserActions.getText(driver, lbltxnRef,
				"Transaction Reference");
	}

	public PayOverdueTab back() throws Exception {
		BrowserActions.clickOnButton(btnBack, driver, "Back button");
		return new PayOverdueTab(driver).get();
	}
}
