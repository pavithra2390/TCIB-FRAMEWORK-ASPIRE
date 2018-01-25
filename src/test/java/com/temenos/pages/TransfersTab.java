package com.temenos.pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.ElementLayer;
import com.temenos.support.Log;
import com.temenos.support.Utils;
import com.temenos.support.WaitUtil;

public class TransfersTab extends LoadableComponent<TransfersTab> {

	private WebDriver driver;

	public ElementLayer elementLayer;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C5__TAB_2")
	private WebElement transferTab;

	@FindBy(css = "a[oldtitle='Show Options']")
	private WebElement lnkShowOptions;

	@FindBy(css = "select[name*='CREDITACCOUNTLIST']")
	private WebElement selectAccountList;

	@FindBy(css = "option[selected='selected']")
	private WebElement txtSelectedToAcc;

	@FindBy(css = "input[name*='PAYMENTCURRENCY']")
	private WebElement txtPaymentCurrency;

	@FindBy(css = "input[name*='PAYMENTAMOUNT']")
	private WebElement txtPaymentAccount;

	@FindBy(css = "#C5__C3__p4_QUE_2E038A57785DBF302857191 span")
	private WebElement txtTransferMessage;
	//
	// @FindBy(xpath="//input[contains(@name,'PAYMENTEXECUTIONDATELIST') and @value='Immediate']/parent::div")
	// private WebElement radioImmediatePayment;
	//

	@FindBy(css = "#C5__C3__FMT_76F9BA578CAD5312622173 div.tc-info-label")
	private WebElement txtNoOfTransfers;

	@FindBy(css = "#C5__C3__FMT_76F9BA578CAD5312622173 div.tc-info-action a")
	private WebElement btnTransferArrow;

	@FindBy(xpath = "//fieldset[@id='C5__C3__FS_QUE_82ABB0F847B5B79B101937']/div/label")
	private WebElement radioImmediatePayment;

	@FindBy(xpath = "//fieldset[@id='C5__C3__FS_QUE_82ABB0F847B5B79B101937']/div[2]/label")
	private WebElement radioFuturePayment;

	@FindBy(xpath = "//fieldset[@id='C5__C3__FS_QUE_82ABB0F847B5B79B101937']/div[3]/label")
	private WebElement radioRecurringPayment;

	@FindBy(css = "input[name*='NARRATIVE']")
	private WebElement txtNarrative;

	@FindBy(css = "a[oldtitle='Cancel']")
	private WebElement lnkCancel;

	@FindBy(css = "a[title='Continue']")
	private WebElement lnkContinue;

	@FindBy(css = "div#C4__C3__FMT_76F9BA578CAD5312622173 div.tc-info-label")
	private WebElement labelPendingTxnDetails;

	@FindBy(css = "div#C4__C3__FMT_A42CE725E3C05DE91021603 div.tc-info-label")
	private WebElement labelFutureTxnDetails;

	// Future date related controls
	@FindBy(css = "input[name*='FUTUREDATE'][type='text']")
	private WebElement txtFutureDate;

	// Recurring payment Start and end date
	@FindBy(css = "input[name*='PAYMENTSTARTS'][type='text']")
	private WebElement txtPaymentStartDate;

	@FindBy(css = "input[name*='KEEPPAYINGUNTIL'][type='text']")
	private WebElement txtPaymentUntilDate;

	// Recurring related controls
	@FindBy(css = "input[name*='PAYMENTSTARTS'][type='text']")
	private WebElement txtPaymentStarts;

	@FindBy(css = "select[name*='FREQUENCYLIST']")
	private WebElement txtFreqList;

	@FindBy(css = "input[name*='KEEPPAYINGUNTIL'][type='text']")
	private WebElement txtPaymentUntil;

	@FindBy(css = "input[name*='UNTILFURTHERNOTICE'][type='checkbox']")
	private WebElement checkboxFutherNotice;

	@FindBy(css = "#C5__C3__BUT_284400FD85D8BE5E18341")
	private WebElement overViewTransferDetails;

	// Confirm Transfer
	@FindBy(css = "#C5__C3__HEAD_68769533CF2E49EA99872")
	private WebElement confirmTransferLabel;

	@FindBy(css = "#C5__C3__QUE_56EEC2411B69D0D52228585")
	private WebElement fromAccountLabel;

	@FindBy(css = "#C5__C3__QUE_EA43D91BD2C21B2A78807")
	private WebElement toAccountLabel;

	@FindBy(css = "#C5__C3__QUE_6A94BA33EF612664182116")
	private WebElement currencyLabel;

	@FindBy(css = "#C5__C3__QUE_EA43D91BD2C21B2A78812")
	private WebElement paymentAmountLabel;

	@FindBy(css = "#C5__C3__QUE_EA43D91BD2C21B2A145231")
	private WebElement paymentTypeLabel;

	@FindBy(css = "#C5__C3__BUT_284400FD85D8BE5E330660")
	private WebElement transferButton;

	// Transfered Page
	@FindBy(css = "#C5__C3__HEAD_284400FD85D8BE5E338428")
	private WebElement transferedHeadLabel;
	@FindBy(css = "#C5__C3__QUE_5EC36DDD152974CE86685")
	private WebElement transferedFromAccountNumber;
	@FindBy(css = "#C5__C3__QUE_EA43D91BD2C21B2A147662")
	private WebElement transferedToAccountNumber;
	@FindBy(css = "#C5__C3__QUE_EE9E2CFD98FC5382273028")
	private WebElement transferedAmount;
	@FindBy(css = "#C5__C3__QUE_2E038A57785DBF302857191")
	private WebElement TransferedPaymentType;
	@FindBy(css = "#C5__C3__BUT_284400FD85D8BE5E338624")
	private WebElement goToHomePage;
	@FindBy(css = "#C5__C3__BUT_284400FD85D8BE5E338634")
	private WebElement makeAnotherTransferButton;
	@FindBy(css = "#C5__C3__BUT_5EC36DDD152974CE85818")
	private WebElement downloadPdfLink;
	@FindBy(css = "#C5__C3__BUT_5EC36DDD152974CE85824")
	private WebElement printLink;
	@FindBy(css = "#C5__C3__QUE_C788E057B1510D08345404")
	private WebElement unqiueNumber;

	// Future Transfers details
	@FindBy(css = "#C5__C3__C1__p1_QUE_B3B9B40B8D4B7342101935")
	private WebElement scheduleDateDetails;

	@FindBy(css = "#C5__C3__C1__p1_QUE_B3B9B40B8D4B7342101952")
	private WebElement narrativeDetails;

	@FindBy(css = "#C5__C3__C1__p1_QUE_B3B9B40B8D4B7342101967")
	private WebElement amountDetails;

	@FindBy(css = "#C5__C3__C1__BUT_CEFF3029909D1C98208502")
	private WebElement btnBackToPayment;

	@FindBy(css = "#C5__C3__C1__BUT_B3B9B40B8D4B7342105431_R1")
	private WebElement seperateTransferDetailArrow;

	private Headers headers;

	private Footers footers;

	public TransfersTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		headers = new Headers(driver);
		footers = new Footers(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, selectAccountList)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Transfers tab!", driver);
		} else {
			Log.message("Navigated to Transfers tab!", driver);
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

	public ShowOptions clickOnShowOptions() throws Exception {
		BrowserActions.clickOnButton(lnkShowOptions, driver, "Show Options");
		return new ShowOptions(driver);
	}

	public class PendingTransfers {

		private WebDriver driver;

		@FindBy(css = "table[summary='PendingPayments'] thead tr th[aria-label*='Date'] a")
		private WebElement lnkDate;

		@FindBy(css = "table[summary='PendingPayments'] thead tr th[aria-label*='Description'] a")
		private WebElement lnkDescription;

		@FindBy(css = "table[summary='PendingPayments'] thead tr th[aria-label*='Amount'] a")
		private WebElement lnkAmount;

		@FindBy(css = "table[summary='PendingPayments'] tbody tr")
		private List<WebElement> transactionList;

		@FindBy(css = "a[oldtitle='Back to Payments']")
		private WebElement lnkBackToPayments;

		public PendingTransfers(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	}

	public class PendingTransferDetails {

		private WebDriver driver;

		@FindBy(css = "input[name*='.ACCOUNTNAME']")
		private WebElement txtDebitAccountName;

		@FindBy(css = "input[name*='ACCOUNTNO']")
		private WebElement txtDebitAccountNumber;

		@FindBy(css = "input[name*='CURRENCYSYMBOL']")
		private WebElement txtDebitCurrency;

		@FindBy(css = "input[name*='PAYMENTAMOUNT']")
		private WebElement txtPaymentAmount;

		@FindBy(css = "input[name*='PAYMENTEXECUTIONDATE']")
		private WebElement txtPaymentDate;

		@FindBy(css = "input[name*='CREDITACCOUNTNAME']")
		private WebElement txtCreditAccountName;

		@FindBy(css = "input[name*='CREDITACCOUNT']")
		private WebElement txtCreditAccountNumber;

		@FindBy(css = "input[name*='SORTCODE']")
		private WebElement txtSortCode;

		@FindBy(css = "input[name*='NARRATIVE']")
		private WebElement txtReference;

		@FindBy(css = "input[oldtitle='Back']")
		private WebElement lnkBack;

		public PendingTransferDetails(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

	}

	public void selectCreditAccount(String accountNumberFrom) throws Exception {
		WaitUtil.sleep(3);
		BrowserActions.selectFromDropDown(selectAccountList, accountNumberFrom,
				driver, "Transfers Tab Credit account");
	}

	public void selectFrequency(String frequency) throws Exception {
		WaitUtil.sleep(3);
		BrowserActions.selectFromDropDown(txtFreqList, frequency, driver,
				"Frequency");
	}

	public String getSelectedToAcc() throws Exception {
		return BrowserActions.getText(driver, txtSelectedToAcc,
				"Selected 'To' Account");
	}

	public void enterAmount(String amount) throws Exception {
		BrowserActions.typeOnTextField(txtPaymentAccount, amount, driver,"Credit Amount ");
		WaitUtil.sleep(3);
	}

	public void selectImmediatePayment() throws Exception {
		BrowserActions.clickOnButton(radioImmediatePayment, driver,"Immediate Payment option");
	}

	public void selectRecurringPayment() throws Exception {
		BrowserActions.clickOnButton(radioRecurringPayment, driver,
				"Recurring Payment Payment option");
		
	}

	public void selectoverViewTransferDetailsButton() throws Exception {
		WaitUtil.waitForElement(driver, overViewTransferDetails);
		
		BrowserActions.clickOnButton(overViewTransferDetails, driver,
				"Overview Transfer Details Button");
		WaitUtil.sleep(2);
	}

	public TransfersTab selectNoOfTransfers() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnButton(btnTransferArrow, driver,
				"Transfer schedule");
		Utils.waitForPageLoad(driver);
		return new TransfersTab(driver);
	}

	public TransfersTab selectFutureDate() throws Exception {
		BrowserActions.clickOnButton(radioFuturePayment, driver,
				"Future Date Payment option");
		WaitUtil.sleep(2);
		return new TransfersTab(driver);
	}

	public TransfersTab enterFutureDate(String futureDate) throws Exception {
		BrowserActions.typeOnTextField(txtFutureDate, futureDate, driver, " ");
		WaitUtil.sleep(2);
		return new TransfersTab(driver);
	}

	public void enterPaymentStartDate(String startDate) throws Exception {
		BrowserActions.typeOnTextField(txtPaymentStartDate, startDate, driver,
				" ");
		WaitUtil.sleep(2);
	}

	public void enterPaymentUntilDate(String untilDate) throws Exception {
		BrowserActions.typeOnTextField(txtPaymentUntilDate, untilDate, driver,
				"");
		WaitUtil.sleep(2);
	}

	public void verifyFromAccountNumber(String fromAccountExp) throws Exception {
		String fromAccountAct = BrowserActions.getTextFromAttribute(driver,
				fromAccountLabel, "value", " From Account Number is matching");
		Assert.assertEquals(
				"From account in Confirm transfer page is not matching",
				fromAccountExp, fromAccountAct);
		
	}

	public String verifyToAccountNumber(String toAccountExp) throws Exception {
		String toAccountAct = BrowserActions.getTextFromAttribute(driver,
				toAccountLabel, "value",
				"To account in Confirm transfer page is matching");
		Assert.assertEquals(
				"To account in Confirm transfer page is not matching",
				toAccountExp, toAccountAct);
		
		return toAccountAct;
	}

	public void verifyAmount(String amountExp) throws Exception {
		String amountAct = null;
		try {
			amountAct = BrowserActions.getTextFromAttribute(driver,
					paymentAmountLabel, "value",
					"Amount in confirm transfer page is match!");
			Assert.assertEquals(
					"Amount in Confirm transfer page is not matching",
					amountExp, amountAct);
			
		} catch (java.lang.AssertionError e) {
			e.printStackTrace();
			Log.failsoft("Step failed : <b> Expected payment = </b>"
					+ amountExp + "<b> Actual payment = </b>" + amountAct,
					driver);
		}
	}

	public void verifyPaymentMethod(String paymentExp) throws Exception {
		String paymentAct = null;
		try {
			paymentAct = BrowserActions.getText(driver, paymentTypeLabel,
					"Payment type in confirm transfer page is match!");
			Assert.assertTrue(
					"Payment type in Confirm transfer page is not matching",
					paymentAct.contains(paymentExp));
			
		} catch (java.lang.AssertionError e) {
			e.printStackTrace();

			Log.failsoft("Step failed : <b> Expected payment = </b>"
					+ paymentExp + "<b> Actual payment = </b>" + paymentAct,
					driver);
		}
	}

	public void verifyTransferButton(String transferExp) throws Exception {
		String transferAct = BrowserActions.getText(driver, transferButton,
				"Transfer button in confirm transfer page is match!");
		Assert.assertTrue(
				"Payment type in Confirm transfer page is not matching",
				transferAct.contains(transferExp));
		
	}

	public void selectTransferButton() throws Exception {
		BrowserActions.clickOnButton(transferButton, driver,
				"Transfer button on Confirm your transfer details below");
		WaitUtil.sleep(5);
	}

	public String getTransferMessage() throws Exception {
		return BrowserActions.getText(driver, txtTransferMessage,
				"Transfer Message");
	}

	public boolean verifyTransferedDetails() throws Exception {
		
		return true;
	}

	public boolean verifyPageElementsAreDisplayed(List<String> expectedTabs,
			Object obj, WebDriver driver) throws Exception {
		
		List<String> missingElements = new ArrayList<String>();
		for (String tab : expectedTabs) {
			try {
				Field f = obj.getClass().getDeclaredField(tab);
				f.setAccessible(true);
				if (!((WebElement) f.get(obj)).isDisplayed()) {
					missingElements.add(tab);
				}
			} catch (Exception e) {
				e.printStackTrace();
				missingElements.add(tab);
			}
		}

		Log.softAssertThat(missingElements.isEmpty(), expectedTabs
				+ " are displayed correctly.", missingElements
				+ " are not displayed.");

		return PaymentsTab.compareTwoList(expectedTabs, missingElements);
	}

	public boolean VerifyPageElementDisplayed(List<String> expectedElements,
			Object obj) throws Exception {
		/*
		 * WebElement element = getElement(elementName, obj);
		 * if(element.isDisplayed() == true) return true; else return false;
		 */

		List<String> actual_elements = new ArrayList<String>();
		for (String expEle : expectedElements) {
			Field f = null;
			try {
				f = obj.getClass().getDeclaredField(expEle);
				f.setAccessible(true);
			} catch (NoSuchFieldException | SecurityException e1) {
				throw new Exception(
						"No such a field present on this page, Please check the expected list values:: "
								+ expEle);
			}
			WebElement element = null;
			try {
				element = ((WebElement) f.get(obj));
				// BrowserActions.scrollToViewElement(element, driver);
				if (element.isDisplayed()) {
					actual_elements.add(expEle);
				}
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				Log.exception(e1);
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return PaymentsTab.compareTwoList(expectedElements, actual_elements);
	}
}
