package com.temenos.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class PayeeDetails extends LoadableComponent<PayeeDetails> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(xpath = "//h2[contains(string(), \"Payee Details\")]")
	private WebElement lbePayeeDetails;

	@FindBy(css = "#C4__C4__HEAD_BBA538E230C96EFD549320")
	private WebElement lbePaymentDetails;

	@FindBy(css = "input[name*='IBANBEN']")
	private WebElement txtPayeeIBAN;

	@FindBy(css = "#C5__QUE_66A7B2C9B3A9C99F112522_ERRORMESSAGE")
	private WebElement alertIBAN;
	
	@FindBy(css = ".tc-info-count")
	private WebElement paymentError;

	@FindBy(css = "#C5__QUE_8031F2210246B1EB169220_ERRORMESSAGE")
	private WebElement alertPayeeName;

	@FindBy(css = "input[name*='BENCUSTOMER']")
	private WebElement txtPayeeName;

	@FindBy(css = "input[name*='BANKSORTCODE']")
	private WebElement txtPayeeSortCode;

	@FindBy(css = "#C5__QUE_66A7B2C9B3A9C99F112693")
	private WebElement txtPayeeBIC;

	@FindBy(css = "input[name*='BENACCTNO']")
	private WebElement txtPayeeAccNo;

	@FindBy(css = "#C5__QUE_8031F2210246B1EB207166")
	private WebElement txtPayeeBICOUtGB;

	@FindBy(css = "#C5__QUE_8031F2210246B1EB207170")
	private WebElement txtPayeeAccNoOutGB;

	@FindBy(css = "select[name*='CLEARINGCHANNEL']")
	private WebElement drpdwnClearingChannel;

	@FindBy(css = "select[name*='BENBANKCLEARINGCODE']")
	private WebElement drpdwnClearingCodel;

	@FindBy(css = "input[name*='PAYMENTCURRENCY']")
	private WebElement drpdownCurrency;

	@FindBy(css = "input[name*='AMOUNT']")
	private WebElement txtPaymentAmount;

	@FindBy(css = "#C4__C4__HEAD_BBA538E230C96EFD1174124")
	private WebElement lbeRecordPaymentReference;

	@FindBy(css = "input[name*='NARRATIVE']")
	private WebElement txtPaymentReference;

	@FindBy(css = "input[name*='CUSTOMERREF']")
	private WebElement txtCustomerReference;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1309391']/div[1]/label")
	private WebElement optIwillPayMyBanksChargesAndThePayeeWillPayTheirBanksCharges;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1309391']/div[2]/label")
	private WebElement optIWillPayAllCharges;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1309391']/div[3]/label")
	private WebElement optThePayeeWillPayAllCharges;

	@FindBy(css = "#C4__QUE_FA21C39AC9A32F5B37509")
	private WebElement lbeWhenShouldTheMoneyLeaveYourAccount;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1174093']/div[1]/label")
	private WebElement optImmediatelyOrASAP;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1174093']/div[2]/label")
	private WebElement optOnAFutureDate;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1174093']/div[3]/label")
	private WebElement optAsARecurringPaymentOrStanding;

	@FindBy(xpath = ".//*[@id='C4__C4__p1_QUE_68769533CF2E49EA269655']/div/label")
	private WebElement lbeSavePayee;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_68769533CF2E49EA269655']/div[1]/label")
	private WebElement optYes;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_68769533CF2E49EA269655']/div[2]/label")
	private WebElement optNo;

	@FindBy(css = "#C5__BUT_66A7B2C9B3A9C99F192761")
	private WebElement btnContinue;

	@FindBy(css = "a[oldtitle='Back']")
	private WebElement btnBack;

	@FindBy(xpath = ".//span[contains(String(),\"Cancel\")]")
	private WebElement btnCancel;

	@FindBy(xpath = "//table[@id='C5__TBL_8031F2210246B1EB122986']//td[2]")
	private WebElement lblCompanyName;
	
	@FindBy(css = "#C5__QUE_8031F2210246B1EB207201")
	private WebElement selectPayeeCurreny;
	
	public PayeeDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, lbePayeeDetails)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to PayeeDetails page!", driver);
		} else {
			Log.message("Navigated to PayeeDetails page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public PayeeDetails enterPayeeDetails(String iban, String name)
			throws Exception {
		BrowserActions.typeOnTextField(txtPayeeIBAN, iban, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter IBAN number in the textbox");
		return new PayeeDetails(driver);
	}

	public PayeeDetails enterPayeeDetailsBICOption(String bic, String accNo,
			String name) throws Exception {
		BrowserActions.typeOnTextField(txtPayeeBIC, bic, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeAccNo, accNo, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter IBAN number in the textbox");
		return new PayeeDetails(driver);
	}

	public PayeeDetails enterPayeeDetailsSEPA(String iban, String name)
			throws Exception {
		BrowserActions.typeOnTextField(txtPayeeIBAN, iban, driver,
				"IBAN");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Payeename");
		return new PayeeDetails(driver);
	}

	public PayeeDetails enterPayeeDetailsNCC(String bic, String accNo,
			String name) throws Exception {
		BrowserActions.typeOnTextField(txtPayeeSortCode, bic, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeAccNo, accNo, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter IBAN number in the textbox");
		return new PayeeDetails(driver);
	}
	
	public void selectCurrencyInMultipleNCCPayeeDetails(String currency) throws Exception {
		BrowserActions.selectFromDropDown(selectPayeeCurreny, currency, driver,
				"Country is Selected successfully");
	}

	public PayeeDetails enterPayeeDetailsBICOptionOutGB(String bic,
			String accNo, String name) throws Exception {
		BrowserActions.typeOnTextField(txtPayeeBICOUtGB, bic, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeAccNoOutGB, accNo, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter IBAN number in the textbox");
		return new PayeeDetails(driver).get();
	}

	public PayeeDetails enterPaymentDetails() throws Exception {
		BrowserActions.typeOnTextField(txtPaymentReference, "12345", driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtCustomerReference, "4566", driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPaymentAmount, "200", driver,
				"Enter IBAN number in the textbox");
		return new PayeeDetails(driver).get();
	}

	public String getCompanyName() throws Exception {
		return BrowserActions
				.getText(driver, lblCompanyName, "Name of company");
	}

	public CheckPayeeDetails1 clickOverviewDetails1() throws Exception {
		// BrowserActions.clickOnButton(btnContinue, driver, "Continue button");
		// Log.message("Continue button is clicked");
		WaitUtil.sleep(2);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btnContinue);
		return new CheckPayeeDetails1(driver).get();
	}

	public PayeeDetails clickOverviewDetailsWithoutValue() throws Exception {
		// BrowserActions.clickOnButton(btnContinue, driver, "Continue button");
		// Log.message("Continue button is clicked");
		WaitUtil.sleep(2);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btnContinue);
		return new PayeeDetails(driver);
	}

	public String verifyAlertforIBAN() throws Exception {
		return BrowserActions.getText(driver, alertIBAN,"alert message for IBAN");
	}

	public String verifyAlertforPayee() throws Exception {
		return BrowserActions.getText(driver, alertPayeeName,"alert message for PayeeName");
	}

	public CheckPayeeDetails clickOverviewDetails() throws Exception {
		BrowserActions.clickOnButton(btnContinue, driver, "Continue button");
		// Log.message("Continue button is clicked");
		return new CheckPayeeDetails(driver).get();
	}
}