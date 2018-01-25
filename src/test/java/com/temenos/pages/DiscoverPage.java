package com.temenos.pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class DiscoverPage extends LoadableComponent<DiscoverPage> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#p1_LVL_E46AC959C67B3EEE2174562")
	private WebElement pageTitle;

	@FindBy(css = "#C4__C2__FMT_26B2531682588B1B82859")
	private WebElement listDetails;

	@FindBy(css = "#C5__HEAD_E46AC959C67B3EEE1673515")
	private WebElement cA_SALabel;

	@FindBy(css = "#C5__HEAD_E46AC959C67B3EEE1673521")
	private WebElement termDepositsLabel;

	@FindBy(css = "#C5__BUT_E46AC959C67B3EEE1673489")
	private WebElement cA_SAButton;

	@FindBy(css = "#C5__BUT_E46AC959C67B3EEE1673509")
	private WebElement termDepositsButton;

	@FindBy(css = "#C4__C2__p4_HEAD_26B2531682588B1B915206_R3")
	private WebElement termDeposits;

	@FindBy(css = "#C4__C2__BUT_26B2531682588B1B94644_R1")
	private WebElement currentAccountFindOutMore;

	@FindBy(css = "#C4__C2__p4_HEAD_26B2531682588B1B915206_R1")
	private WebElement savingsAccountFindOutMore;

	@FindBy(css = "#C4__C2__p4_HEAD_26B2531682588B1B915206_R1")
	private WebElement termDepositsFindOutMore;

	@FindBy(css = "#C5__C1__QUE_1746DDCFA48877DA545561")
	private WebElement btnProductGroup;

	@FindBy(css = "#C5__C1__QUE_1746DDCFA48877DA545587")
	private WebElement btnProduct;

	@FindBy(css = "#C5__C1__QUE_1746DDCFA48877DA545599")
	private WebElement btnTerm;

	@FindBy(css = "#C5__C1__QUE_1746DDCFA48877DA545607")
	private WebElement btnstatus;

	@FindBy(css = "#C5__C1__QUE_1746DDCFA48877DA545603")
	private WebElement txtAmount;

	@FindBy(css = "#C5__C1__QUE_81917BE37F0C119368971")
	private WebElement btnAccount;

	@FindBy(css = "#C5__C1__BUT_1746DDCFA48877DA546751")
	private WebElement btnContinueNewDeposit;

	@FindBy(css = "#C5__C1__BUT_1746DDCFA48877DA835087")
	private WebElement btnCreateNewDeposit;

	@FindBy(css = "#C5__C1__FMT_EC24A21ACF26687E799160")
	private WebElement boxUniqueNo;

	@FindBy(css = "	#C5__C1__QUE_EC24A21ACF26687E789342")
	private WebElement lblUniqueNumber;

	@FindBy(css = "#C5__C1__QUE_67BA320F8EDC08BB35394")
	private WebElement btnProductGroupForAccCreation;

	@FindBy(css = "#C5__C1__QUE_67BA320F8EDC08BB35412")
	private WebElement btnProductForAccCreation;

	@FindBy(css = "#C5__C1__BUT_67BA320F8EDC08BB36352")
	private WebElement btnContinurForAccCreation;

	@FindBy(css = "#C5__C1__QUE_67BA320F8EDC08BB35420")
	private WebElement txtCurrency;

	@FindBy(css = "#C5__C1__BUT_67BA320F8EDC08BB36871")
	private WebElement btnCreateNewAcc;

	@FindBy(css = "#C5__C1__FMT_6C33DF9C489A9142474537")
	private WebElement boxUniqueNoForAcc;

	@FindBy(css = "#C5__C1__p4_QUE_64D71ADBA32914A2114825")
	private WebElement lblUniqueNumberForAcc;

	public DiscoverPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Discover page!", driver);
		} else {
			Log.message("Navigated to Discover page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public boolean verifyDiscoverPageDetails() throws Exception {
		if (cA_SALabel.isDisplayed() && termDepositsLabel.isDisplayed()
				&& cA_SAButton.isDisplayed()
				&& termDepositsButton.isDisplayed()) {
			return true;
		}
		return false;
	}

	public void selectCASA() throws Exception {
		BrowserActions.clickOnButton(cA_SAButton, driver,
				"Clicked Current and Savings Accounts Find out more button");
		WaitUtil.sleep(2);

	}

	public void selectTermDeposits() throws Exception {
		BrowserActions.clickOnButton(termDepositsButton, driver,
				"Clicked TermDeposits Find out more button");
	}

	public void selectProductGroup(String Value) throws Exception {
		WaitUtil.waitForElement(driver, btnProductGroup, 5);
		BrowserActions.selectFromDropDownValue(btnProductGroup, Value, driver,
				"Product is selected");
		WaitUtil.sleep(2);
	}

	public void selectProduct(String Product) throws Exception {
		WaitUtil.waitForElement(driver, btnProduct, 5);
		BrowserActions.selectFromDropDownValue(btnProduct, Product, driver,
				"Product is selected");
		WaitUtil.sleep(2);
	}

	public void selectTerm(String Term) throws Exception {
		WaitUtil.waitForElement(driver, btnTerm, 5);
		BrowserActions.selectFromDropDownValue(btnTerm, Term, driver,
				"Term is selected");
		WaitUtil.sleep(2);
	}

	public void PayImmediately(String status) throws Exception {
		WaitUtil.waitForElement(driver, btnstatus, 5);
		BrowserActions.selectFromDropDownValue(btnstatus, status, driver,
				"Pay Immediately");
		WaitUtil.sleep(2);
	}

	public void enterAmount(String amount) throws Exception {
		WaitUtil.waitForElement(driver, txtAmount, 5);
		BrowserActions.typeOnTextField(txtAmount, amount, driver, "Amount");
	}

	public void selectAccount(String Account) throws Exception {
		WaitUtil.waitForElement(driver, btnAccount, 5);
		BrowserActions.selectFromDropDownValue(btnAccount, Account, driver,
				"Term is selected");
		WaitUtil.sleep(2);
	}

	public void clickOnContinueInNewDepositCreation() throws Exception {
		WaitUtil.waitForElement(driver, btnContinueNewDeposit, 5);
		BrowserActions.clickOnButton(btnContinueNewDeposit, driver,
				"Continue button is clicked");
		WaitUtil.sleep(2);
	}

	public void clickOnCreateNewDepositCreation() throws Exception {
		WaitUtil.waitForElement(driver, btnCreateNewDeposit, 5);
		BrowserActions.clickOnButton(btnCreateNewDeposit, driver,
				"Create button is clicked");
		WaitUtil.sleep(2);
	}

	public String getAttributeValueOfUniqueNumber() throws Exception {
		WaitUtil.waitForElement(driver, boxUniqueNo, 5);
		String Colour = boxUniqueNo.getCssValue("background-color");
		WaitUtil.sleep(2);
		return Colour;
	}

	public boolean verifyUniqueId() throws Exception {
		WaitUtil.waitForElement(driver, lblUniqueNumber, 5);
		if (lblUniqueNumber.getSize() != null) {
			return true;
		}
		return false;

	}

	public void selectProductGroupForAccountCreation(String Value)
			throws Exception {
		WaitUtil.waitForElement(driver, btnProductGroupForAccCreation, 5);
		BrowserActions.selectFromDropDownValue(btnProductGroupForAccCreation,
				Value, driver, "Product is selected");
		WaitUtil.sleep(2);
	}

	public void selectProductForAccountCreation(String Product)
			throws Exception {
		WaitUtil.waitForElement(driver, btnProductForAccCreation, 5);
		BrowserActions.selectFromDropDownValue(btnProductForAccCreation,
				Product, driver, "Product is selected");
		WaitUtil.sleep(2);
	}

	public void clickOnContinueInNewAccCreation() throws Exception {
		WaitUtil.waitForElement(driver, btnContinurForAccCreation, 5);
		BrowserActions.clickOnButton(btnContinurForAccCreation, driver,
				"Continue button is clicked");
		WaitUtil.sleep(2);
	}

	public String getTextForCurrency() throws Exception {
		WaitUtil.waitForElement(driver, txtCurrency, 5);
		String Currency = BrowserActions.getTextFromAttribute(driver,
				txtCurrency, "value", "Currency");
		return Currency;
	}

	public void clickOnCreateNewAccCreation() throws Exception {
		WaitUtil.waitForElement(driver, btnCreateNewAcc, 5);
		BrowserActions.clickOnButton(btnCreateNewAcc, driver,
				"Create button is clicked");
		WaitUtil.sleep(5);
	}

	public String getAttributeValueOfUniqueNumberForAcc() throws Exception {
		WaitUtil.waitForElement(driver, boxUniqueNoForAcc, 5);
		String Colour = boxUniqueNoForAcc.getCssValue("background-color");
		WaitUtil.sleep(2);
		return Colour;
	}

	public boolean verifyUniqueIdForAcc() throws Exception {
		WaitUtil.waitForElement(driver, lblUniqueNumberForAcc, 5);
		if (lblUniqueNumberForAcc.getSize() != null) {
			return true;
		}
		return false;
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
