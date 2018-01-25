package com.temenos.pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class NewPayee extends LoadableComponent<NewPayee> {

	public String name_of_company;
	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C5__HEAD_22B9C56E3E5035D883994")
	private WebElement titleChoiceInfo;

	@FindBy(css = "#C5__BUT_22B9C56E3E5035D880355")
	private WebElement lnkDomesticOrPersonWithinGB;

	@FindBy(css = "#C5__BUT_22B9C56E3E5035D880360")
	private WebElement lnkCompanyOrCompanyWithinGB;

	@FindBy(css = "#C5__BUT_22B9C56E3E5035D880365")
	private WebElement lnkInternationalOrPersonOutsideGB;

	@FindBy(css = "a[oldTitle='Go Back']")
	private WebElement btnGoBack;

	@FindBy(id = "C5__C4__COL_BBA538E230C96EFD102161")
	WebElement newPayeePresonWithinGB;

	@FindBy(id = "C5__C4__COL_BBA538E230C96EFD102169")
	WebElement newPayeeCompanyWithinGB;

	@FindBy(id = "C5__C4__COL_BBA538E230C96EFD102165")
	WebElement newPayeePresonOutSideGB;
	
	@FindBy(id = "C5__QUE_8031F2210246B1EB205386")
	WebElement newPayeeSelectCountry;

	public NewPayee(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, titleChoiceInfo)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to NewPayee page!", driver);
		} else {
			Log.message("Navigated to NewPayee page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public DomesticPayee gotoPersonWithInGBTabFromAddPayee() throws Exception {
		BrowserActions.clickOnButton(lnkDomesticOrPersonWithinGB, driver,
				"Domestic Payee");
		return new DomesticPayee(driver);
	}

	public CompanyPayee gotoCompanyWithInGBTab() throws Exception {
		BrowserActions.clickOnButton(lnkCompanyOrCompanyWithinGB, driver,
				"Company Payee");
		return new CompanyPayee(driver);
	}

	public InternationalPayee gotoPersonOutSideGBTab() throws Exception {
		BrowserActions.clickOnButton(lnkInternationalOrPersonOutsideGB, driver,
				"International Payee");
		return new InternationalPayee(driver);
	}
	
	public InternationalPayee as() throws Exception {
		BrowserActions.clickOnButton(lnkInternationalOrPersonOutsideGB, driver,
				"International Payee");
		return new InternationalPayee(driver);
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

	public class DomesticPayee {
		private WebDriver driver;

		@FindBy(css = "#C4__C4__Anchor_Title")
		private WebElement paymentsTitle;

		@FindBy(css = "#C4__C4__HEAD_BBA538E230C96EFD194008")
		private WebElement lbepaymentMethod;

		@FindBy(css = "label[for='C5__QUE_BC65870C27FF470893219_0']")
		private WebElement optIBAN;

		@FindBy(css = "label[for='C5__QUE_BC65870C27FF470893229_0']")
		private WebElement optSWIFTBIC;

		@FindBy(css = "label[for='C5__QUE_BC65870C27FF470893249_0'")
		private WebElement optMultipleNCC;

		@FindBy(css = "label[for='C5__QUE_BC65870C27FF470893259_0']")
		private WebElement optDSEPA;

		public DomesticPayee(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public PayeeDetails gotoPayeeDetailsWithIBANPaymentMode()
				throws Exception {
			BrowserActions.clickOnButton(optIBAN, driver, "IBAN Payment Mode");
			return new PayeeDetails(driver).get();
		}

		public PayeeDetails gotoPayeeDetailsWithSWIFTBICPaymentMode()
				throws Exception {
			BrowserActions.clickOnButton(optSWIFTBIC, driver,
					"SWIFTBIC Payment Mode");
			return new PayeeDetails(driver).get();
		}

		public PayeeDetails gotoPayeeDetailsWithMultipleNCCPaymentMode()
				throws Exception {
			BrowserActions.clickOnButton(optMultipleNCC, driver,
					"MultipleNCC Payment Mode");
			return new PayeeDetails(driver).get();
		}

		public PayeeDetails gotoPayeeDetailsWithDSEPAPaymentMode()
				throws Exception {
			BrowserActions
					.clickOnButton(optDSEPA, driver, "DSEPA Payment Mode");
			return new PayeeDetails(driver).get();
		}
	}

	public class CompanyPayee {
		private WebDriver driver;

		@FindBy(css = "#C4__C4__Anchor_Title")
		private WebElement lbePayments;

		@FindBy(css = "label[for='C5__QUE_8031F2210246B1EB261701_0']")
		private WebElement lbeFindACompany;

		@FindBy(css = "label[for='C5__QUE_8031F2210246B1EB261701_1']")
		private WebElement lblFindAccountDetails;

		@FindBy(css = "input[name*='ACCOUNTNO']")
		private WebElement txtAccNo;

		@FindBy(css = "input[name*='SORTCODE']")
		private WebElement txtSortCode;

		@FindBy(css = "#C5__QUE_5B7BF686F521E7E8263413")
		private WebElement txtFindbyCompanyName;

		@FindBy(css = "#C5__BUT_5B7BF686F521E7E8263418")
		private WebElement btnSearch;

		@FindBy(css = "#C5__BUT_8031F2210246B1EB97883")
		private WebElement btnSearchAcc;

		@FindBy(xpath = ".//*[@id='C4__C4__HEAD_3E590269BD319CB11832447']")
		private WebElement lbeNoRecordMessage;

		@FindBy(css = "#C5__BUT_8031F2210246B1EB133948_R1_1 span")
		private WebElement lnkSelect;

		@FindBy(css = "#C5__BUT_8031F2210246B1EB133652_R1_1 span")
		private WebElement lnkSelectAcc;

		@FindBy(css = "input[name*='C5__BENEFICIARY[1].CUSTOMERREF']")
		private WebElement txtReference;
		
		@FindBy(id = "C5__QUE_5B7BF686F521E7E8258477")
		private WebElement drpdownCurrency;

		public CompanyPayee(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public PayeeDetails gotoPayeeDetails(String companyName,
				String reference) throws Exception {
			BrowserActions.clickOnButton(lbeFindACompany, driver,
					"Find by company name");
			WaitUtil.sleep(2);
			BrowserActions.typeWithoutClearTextField(txtFindbyCompanyName,
					companyName, driver, "Company Name Search Box");
			BrowserActions.clickOnButton(btnSearch, driver, "Search Button");
			// BrowserActions.clickOnButton(lnkSelect, driver, "select");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", lnkSelect);
			BrowserActions.typeOnTextField(txtReference, reference, driver,
					"Reference of customer");
			BrowserActions.selectFromDropDown(drpdownCurrency, "USD", driver,
					"Country is Selected successfully");
			return new PayeeDetails(driver).get();
		}

		public PayeeDetails gotoPayeeDetailsByAccount(String accountNo,
				String sortCode, String reference) throws Exception {
			BrowserActions.clickOnButton(lblFindAccountDetails, driver,
					"Find by Account Number");
			WaitUtil.sleep(2);
			BrowserActions.typeWithoutClearTextField(txtAccNo, accountNo,
					driver, "Account Number");
			BrowserActions.typeWithoutClearTextField(txtSortCode, sortCode,
					driver, "Sort Code");
			BrowserActions.clickOnButton(btnSearchAcc, driver, "Search Button");
			WaitUtil.sleep(2);
			BrowserActions.clickOnButton(lnkSelectAcc, driver, "select");
			BrowserActions.typeOnTextField(txtReference, reference, driver,
					"Reference of customer");
			BrowserActions.selectFromDropDown(drpdownCurrency, "USD", driver,
					"Country is Selected successfully");
			return new PayeeDetails(driver).get();
		}

	}

	public class InternationalPayee {
		private WebDriver driver;

		@FindBy(xpath = ".//*[@id='C4__C4__HEAD_9B56560E9B78FFC6215081']")
		private WebElement lbepayments;

		@FindBy(xpath = ".//*[@id='C4__C4__HEAD_73F5E3F70567C90B157806']")
		private WebElement lbeWhereWouldYouLikeToSendYourMoney;

		@FindBy(xpath = ".//*[@id='C4__C4__HEAD_73F5E3F70567C90B159297']")
		private WebElement lbePleaseContactUsIfYourCountryIsNotDisplayed;

		@FindBy(css = "select[name*='COUNTRY']")
		private WebElement drpdwnCountry;

		@FindBy(css = "a[oldtitle='Cancel']")
		private WebElement btnCancel;

		@FindBy(css = "#C5__BUT_8031F2210246B1EB205543")
		private WebElement btnEnterPaymentDetail;

		@FindBy(css = "label[for='C5__QUE_BC65870C27FF4708306678_0']")
		private WebElement lblSwiftAccNo;

		@FindBy(css = "label[for='C5__QUE_BC65870C27FF4708306698_0']")
		private WebElement lblNCCAccNo;
		
		@FindBy(css = "#C5__BUT_8031F2210246B1EB205543")
		private WebElement btnEnterPayeeDetail;

		public InternationalPayee(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public PayeeDetails gotoPayeeDetails(String country, String bic)
				throws Exception {
			BrowserActions.selectFromComboBox(drpdwnCountry, "IN", driver,
					"Country dropdown");
			WaitUtil.sleep(4);
			BrowserActions.clickOnButton(btnEnterPaymentDetail, driver,
					"Enter payment details");
			BrowserActions.clickOnButton(lblSwiftAccNo, driver,
					"Swift/BIC With Account Number");
			return new PayeeDetails(driver).get();
		}

		public PayeeDetails gotoPayeeDetailsWithMultipleNCCPaymentMode()
				throws Exception {
			BrowserActions.clickOnButton(lblNCCAccNo, driver,
					"MultipleNCC Payment Mode");
			return new PayeeDetails(driver).get();
		}
		
		
		public void selectPayeeCountry(String country)
				throws Exception {
			BrowserActions.selectFromDropDown(newPayeeSelectCountry, country, driver,
					"Country is Selected successfully");
		}
		
		public InternationalPayee clickEnterPaymentDetail() throws Exception {
			BrowserActions.clickOnButton(btnEnterPayeeDetail, driver,"Enter Payee Detail");
			return new InternationalPayee(driver);
		}

	}

}