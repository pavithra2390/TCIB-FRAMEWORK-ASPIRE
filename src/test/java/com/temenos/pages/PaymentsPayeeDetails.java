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

public class PaymentsPayeeDetails extends
		LoadableComponent<PaymentsPayeeDetails> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(xpath = "//h2[contains(string(), \"Payee Details\")]")
	private WebElement lbePayeeDetails;

	@FindBy(css = "#C4__C4__HEAD_BBA538E230C96EFD549320")
	private WebElement lbePaymentDetails;

	@FindBy(css = "input[name*='C5__C4__PAYEEDETAILS[1].IBAN[1].BENEFICIARYIBAN']")
	private WebElement txtPayeeIBAN;

	@FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYEENAME[1].PAYEENAME']")
	private WebElement txtPayeeName;

	@FindBy(css = "input[name*='C5__C4__PAYEEDETAILS[1].MULTIPLENCC[1].BENBANKCLEARINGCODE']")
	private WebElement txtPayeeSortCode;

	@FindBy(css = "input[name*='C5__C4__PAYEEDETAILS[1].SWIFT[1].BENEFICIARYBIC']")
	private WebElement txtPayeeBIC;

	@FindBy(css = "input[name*='C5__C4__PAYEEDETAILS[1].SWIFT[1].BENEFICIARYACCOUNTNUMBER']")
	private WebElement txtPayeeAccNo;

	@FindBy(css = "input[name*='C5__C4__PAYEEDETAILS[1].MULTIPLENCC[1].BENEFICIARYACCOUNTNUMBER']")
	private WebElement txtNccAccNo;

	@FindBy(css = "#C5__QUE_8031F2210246B1EB207166")
	private WebElement txtPayeeBICOUtGB;

	@FindBy(css = "#C5__QUE_8031F2210246B1EB207170")
	private WebElement txtPayeeAccNoOutGB;

	@FindBy(css = "select[name*='C5__C4__PAYEEDETAILS[1].MULTIPLENCC[1].CLEARINGCHANNEL']")
	private WebElement drpdwnClearingChannel;

	@FindBy(css = "select[name*='BENBANKCLEARINGCODE']")
	private WebElement drpdwnClearingCodel;

	@FindBy(css = "select[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].PAYMENTCURRENCY']")
	private WebElement drpdownCurrency;

	@FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].PAYMENTAMOUNT']")
	private WebElement txtPaymentAmount;

	@FindBy(css = "#C4__C4__HEAD_BBA538E230C96EFD1174124")
	private WebElement lbeRecordPaymentReference;

	@FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].NARRATIVE[1].NARRATIVE']")
	private WebElement txtPaymentReference;

	@FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].ADDITIONALINFO[1].ADDITIONALINFO']")
	private WebElement txtCustomerReference;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1309391']/div[1]/label")
	private WebElement optIwillPayMyBanksChargesAndThePayeeWillPayTheirBanksCharges;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1309391']/div[2]/label")
	private WebElement optIWillPayAllCharges;

	@FindBy(xpath = ".//*[@id='C4__C4__FS_QUE_BBA538E230C96EFD1309391']/div[3]/label")
	private WebElement optThePayeeWillPayAllCharges;

	@FindBy(css = "#C4__QUE_FA21C39AC9A32F5B37509")
	private WebElement lbeWhenShouldTheMoneyLeaveYourAccount;

	@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD1174093_0']")
	private WebElement optImmediatelyOrASAP;

	@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD1174093_1']")
	private WebElement optOnAFutureDate;

	@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD1174093_2']")
	private WebElement optAsARecurringPaymentOrStanding;

	@FindBy(xpath = ".//*[@id='C4__C4__p1_QUE_68769533CF2E49EA269655']/div/label")
	private WebElement lbeSavePayee;

	@FindBy(css = "label[for='C5__C4__QUE_68769533CF2E49EA269655_0']")
	private WebElement optYes;

	@FindBy(css = "label[for='C5__C4__QUE_68769533CF2E49EA269655_1']")
	private WebElement optNo;

	@FindBy(css = "#C5__C4__p4_BUT_68769533CF2E49EA297791 span")
	private WebElement btnContinue;

	@FindBy(css = "#C5__C4__p4_BUT_68769533CF2E49EA297779")
	private WebElement btnBack;

	@FindBy(css = "#C5__C4__p4_BUT_68769533CF2E49EA297785")
	private WebElement btnCancel;

	@FindBy(xpath = "//table[@id='C5__TBL_8031F2210246B1EB122986']//td[2]")
	private WebElement lblCompanyName;

	@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD195611_0']")
	private WebElement lblSwiftAccNo;

	@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD195664_0']")
	private WebElement lblMutipleNcc;

	public PaymentsPayeeDetails(WebDriver driver) {
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

	public PaymentsPayeeDetails enterPayeeDetails(String iban, String name)
			throws Exception {
		BrowserActions.typeOnTextField(txtPayeeIBAN, iban, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter IBAN number in the textbox");
		return new PaymentsPayeeDetails(driver).get();
	}

	public void enterPayeeDetailsBICOption(String bic,
			String accNo, String name) throws Exception {
		BrowserActions.typeOnTextField(txtPayeeAccNo, accNo, driver,
				"Enter Account number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeBIC, bic, driver,
				"Enter Bic number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter Payee name in the textbox");
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void enterPayeeDetailsSEPA(String iban, String name)
			throws Exception {
		BrowserActions.typeOnTextField(txtPayeeIBAN, iban, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter IBAN number in the textbox");
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void enterPayeeDetailsNCC(String bic, String accNo,
			String name) throws Exception {
		BrowserActions.typeOnTextField(txtPayeeSortCode, bic, driver,
				"Enter Sort code number in the textbox");
		BrowserActions.typeOnTextField(txtNccAccNo, accNo, driver,
				"Enter account number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter Name number in the textbox");
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void enterPayeeDetailsBICOptionOutGB(String bic,
			String accNo, String name) throws Exception {
		BrowserActions.typeOnTextField(txtPayeeBICOUtGB, bic, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeAccNoOutGB, accNo, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPayeeName, name, driver,
				"Enter IBAN number in the textbox");
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void enterPaymentDetails(String amount)
			throws Exception {
		BrowserActions.typeOnTextField(txtPaymentAmount, amount, driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtPaymentReference, "12345", driver,
				"Enter IBAN number in the textbox");
		BrowserActions.typeOnTextField(txtCustomerReference, "4566", driver,
				"Enter IBAN number in the textbox");

		//return new PaymentsPayeeDetails(driver).get();
	}

	public void selectCurrency(String currency)
			throws Exception {
		BrowserActions.selectFromDropDown(drpdownCurrency, currency, driver,
				"Country is Selected successfully");
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void seletcingImmediatePayment() throws Exception {
		// BrowserActions.clickOnButton(optImmediatelyOrASAP, driver,
		// "Click on Immediate payment ");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", optImmediatelyOrASAP);
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void selectingSavePayee(String options)
			throws Exception {
		if (options.equalsIgnoreCase("Yes")) {
			BrowserActions.clickOnButton(optYes, driver,
					"Save Payee option as Yes ");
		} else {
			BrowserActions.clickOnButton(optNo, driver,
					"Save Payee option as No ");
		}
		//return new PaymentsPayeeDetails(driver).get();
	}

	public String getCompanyName() throws Exception {
		return BrowserActions
				.getText(driver, lblCompanyName, "Name of company");
	}

	public CheckPayeeDetails clickOverviewDetails() throws Exception {
		// BrowserActions.clickOnButton(btnContinue, driver,
		// "Overview details");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click()", btnContinue);
		WaitUtil.waitForSpinnerToComplete(driver);
		return new CheckPayeeDetails(driver).get();
	}

	public void selectClearingChannel(String channel)
			throws Exception {
		BrowserActions.selectFromDropDown(drpdwnClearingChannel, channel,
				driver, "Clearing Channel is Selected successfully");
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void selectingPaymentMethod() throws Exception {
		BrowserActions.clickOnButton(lblSwiftAccNo, driver,
				"Selecting Swift Account number");
		//return new PaymentsPayeeDetails(driver).get();
	}

	public void selectingMultipleNcc() throws Exception {
		BrowserActions.clickOnButton(lblSwiftAccNo, driver,
				"Selecting Swift Account number");
		//return new PaymentsPayeeDetails(driver).get();
	}
}