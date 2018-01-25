package com.temenos.pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class NewPayeePayments extends LoadableComponent<NewPayeePayments> {

	public String name_of_company;
	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C5__HEAD_22B9C56E3E5035D883994")
	private WebElement titleChoiceInfo;

	@FindBy(css = "a[oldTitle='Go Back']")
	private WebElement btnGoBack;

	@FindBy(css = "#C4__C4__BUT_BBA538E230C96EFD34861")
	WebElement newPayeePresonWithinGB;

	@FindBy(css = "#C4__C4__BUT_BBA538E230C96EFD34866")
	WebElement newPayeeCompanyWithinGB;

	@FindBy(css = "#C4__C4__BUT_BBA538E230C96EFD34871")
	WebElement newPayeePresonOutSideGB;

	@FindBy(css = "table[id^='C5__C4__TBL_'] tr[id^='C5__C4__p1_HDR_TBL_'] th[id^='C5__C4__p1_QUE_']")
	private List<WebElement> thPaymentHeaders;

	@FindBy(css = "table[id^='C5__C4__TBL_'] tr[id^='C5__C4__p0_TBL_'] td[id^='C5__C4__p4_QUE_']")
	private List<WebElement> thPayeeDetails;

	@FindBy(css = "div.fg-toolbar div.dataTables_info")
	WebElement totalPagesInfo;

	@FindBy(css = "a[id*='_next']")
	WebElement nextPageButton;

	public NewPayeePayments(WebDriver driver) {
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
			Log.fail("Not navigated to NewPayee payment page!", driver);
		} else {
			Log.message("Navigated to NewPayee Payment page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public DomesticPayee gotoPersonWithInGBTabFromAddPayee() throws Exception {
		BrowserActions.clickOnButton(newPayeePresonWithinGB, driver,
				"Domestic Payee");
		WaitUtil.waitForSpinnerToComplete(driver);
		return new DomesticPayee(driver);
	}

	public CompanyPayee gotoCompanyWithInGBTab() throws Exception {
		BrowserActions.clickOnButton(newPayeeCompanyWithinGB, driver,
				"Company Payee");
		WaitUtil.waitForSpinnerToComplete(driver);
		return new CompanyPayee(driver);
	}

	public InternationalPayee gotoPersonOutSideGBTab() throws Exception {
		BrowserActions.clickOnButton(newPayeePresonOutSideGB, driver,
				"International Payee");
		WaitUtil.sleep(3);
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

	public boolean verifySort(String sortingColumn) throws Exception {
		int totalPages = getTotalPagesCommon();
		List<String> sortingElements = new ArrayList<String>();
		List<String> contentElements = new ArrayList<String>();
		System.out.println("Total Pages :" + totalPages);
		int coulmnNo = 0;
		if (sortingColumn.equalsIgnoreCase("Reference")) {
			coulmnNo = 2;
			BrowserActions.clickOnButton(thPaymentHeaders.get(coulmnNo),
					driver,
					"Sorting on the header - "
							+ thPaymentHeaders.get(coulmnNo).getText());
			WaitUtil.waitForSpinnerToComplete(driver);
		} else if (sortingColumn.equalsIgnoreCase("Account No")) {
			coulmnNo = 1;
			BrowserActions.clickOnButton(thPaymentHeaders.get(coulmnNo),
					driver,
					"Sorting on the header - "
							+ thPaymentHeaders.get(coulmnNo).getText());
			WaitUtil.waitForSpinnerToComplete(driver);
		} else
			coulmnNo = 0;
		sortingElements = getTablesContent(totalPages, coulmnNo);
		System.out.println("Total Name List: " + sortingElements.size());
		Collections.sort(sortingElements);
		System.out.println("Asending Order : " + sortingElements);
		BrowserActions.clickOnButton(thPaymentHeaders.get(coulmnNo), driver,
				"Sorting on the header - "
						+ thPaymentHeaders.get(coulmnNo).getText());
		WaitUtil.waitForSpinnerToComplete(driver);
		Collections.sort(sortingElements, Collections.reverseOrder());
		System.out.println("Desendig Order was verified : " + sortingElements);
		contentElements = getTablesContent(totalPages, coulmnNo);
		if (compareTwoList(sortingElements, contentElements))
			Log.pass("Desendig Order was verified : " + sortingElements);
		else
			Log.fail("Error while verifying Desendig Order : "
					+ sortingElements);
		BrowserActions.clickOnButton(thPaymentHeaders.get(coulmnNo), driver,
				"Sorting on the header - "
						+ thPaymentHeaders.get(coulmnNo).getText());
		WaitUtil.waitForSpinnerToComplete(driver);
		Collections.sort(sortingElements);
		System.out.println("Desendig Order was verified : " + sortingElements);
		contentElements = getTablesContent(totalPages, coulmnNo);
		if (compareTwoList(sortingElements, contentElements))
			Log.pass("Asending Order was verified : " + sortingElements);
		else
			Log.fail("Error while verifying Asending Order : "
					+ sortingElements);
		return true;
	}

	public int getTotalPagesCommon() throws Exception {
		System.out.println(totalPagesInfo.getText());
		int totalRec = Integer.parseInt(totalPagesInfo.getText().split("of")[1]
				.trim());
		int pages = totalRec / 10;
		if (totalRec % 10 != 0)
			pages++;
		return pages;
	}

	public List<String> getTablesContent(int totalPages, int column)
			throws Exception {
		List<String> sortingElements = new ArrayList<String>();
		for (int i = 0; i < totalPages; i++) {
			if (i != 0) {
				BrowserActions.clickOnButton(nextPageButton, driver,
						"Clicked on the next page button");
				WaitUtil.waitForSpinnerToComplete(driver);
				thPayeeDetails = driver
						.findElements(By
								.cssSelector("table[id^='C5__C4__TBL_'] tr[id^='C5__C4__p0_TBL_'] td[id^='C5__C4__p4_QUE_']"));
			}
			thPayeeDetails = driver
					.findElements(By
							.cssSelector("table[id^='C5__C4__TBL_'] tr[id^='C5__C4__p0_TBL_'] td[id^='C5__C4__p4_QUE_']"));
			System.out.println("Size :" + thPayeeDetails.size());
			for (int index = column; index < thPayeeDetails.size(); index = index + 3) {
				System.out.println(thPayeeDetails.get(index).getText());
				sortingElements.add(thPayeeDetails.get(index).getText());
			}
		}
		return sortingElements;
	}

	public boolean compareTwoList(List<String> Alist, List<String> Blist)
			throws Exception {
		Boolean status = true;
		System.out.println("Blist :" + Blist);
		System.out.println("Alist :" + Alist);
		if (Alist.size() == Blist.size())
			Log.pass("Both list have the same size");
		else
			Log.fail("2 list are not having the same size of data");

		for (int i = 0; i < Alist.size(); i++) {
			System.out.println((i + 1) + ": " + Alist.get(i).trim() + " - "
					+ Blist.get(i).trim());
			if (Blist.get(i).trim().equals(Alist.get(i).trim()))
				System.out.println("Equals..: " + Blist.get(i));
			else {
				status = false;
				break;
			}
		}
		return status;
	}

	public class DomesticPayee {
		private WebDriver driver;

		@FindBy(css = "#C4__C4__Anchor_Title")
		private WebElement paymentsTitle;

		@FindBy(css = "#C4__C4__HEAD_BBA538E230C96EFD194008")
		private WebElement lbepaymentMethod;

		@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD195585_0']")
		private WebElement optIBAN;

		@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD195611_0']")
		private WebElement optSWIFTBIC;

		@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD195664_0']")
		private WebElement optMultipleNCC;

		@FindBy(css = "label[for='C5__QUE_BC65870C27FF470893259_0']")
		private WebElement optDSEPA;

		public DomesticPayee(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetailsWithIBANPaymentMode()
				throws Exception {
			BrowserActions.clickOnButton(optIBAN, driver, "IBAN Payment Mode");
			WaitUtil.waitForSpinnerToComplete(driver);
			return new PaymentsPayeeDetails(driver).get();
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetailsWithSWIFTBICPaymentMode()
				throws Exception {
			BrowserActions.clickOnButton(optSWIFTBIC, driver,
					"SWIFTBIC Payment Mode");
			return new PaymentsPayeeDetails(driver).get();
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetailsWithMultipleNCCPaymentMode()
				throws Exception {
			BrowserActions.clickOnButton(optMultipleNCC, driver,
					"MultipleNCC Payment Mode");
			return new PaymentsPayeeDetails(driver).get();
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetailsWithDSEPAPaymentMode()
				throws Exception {
			BrowserActions
					.clickOnButton(optDSEPA, driver, "DSEPA Payment Mode");
			return new PaymentsPayeeDetails(driver).get();
		}
	}

	public class CompanyPayee {
		private WebDriver driver;

		@FindBy(css = "#C4__C4__Anchor_Title")
		private WebElement lbePayments;

		@FindBy(css = "label[for='C5__C4__QUE_48C7AFBAC8EA882068341_0']")
		private WebElement lbeFindACompany;

		@FindBy(css = "label[for='C5__C4__QUE_48C7AFBAC8EA882068341_1']")
		private WebElement lblFindAccountDetails;

		@FindBy(css = "input[name*='C5__C4__UTILITYLIST[1].INPUTPARAMETERS[1].ACCOUNTNO']")
		private WebElement txtAccNo;

		@FindBy(css = "input[name*='C5__C4__UTILITYLIST[1].INPUTPARAMETERS[1].SORTCODE']")
		private WebElement txtSortCode;

		@FindBy(css = "#C5__C4__QUE_48C7AFBAC8EA882068476")
		private WebElement txtFindbyCompanyName;

		@FindBy(css = "#C5__C4__p4_BUT_48C7AFBAC8EA882068478")
		private WebElement btnSearch;

		@FindBy(css = "#C5__C4__p4_BUT_48C7AFBAC8EA882068628")
		private WebElement btnSearchAcc;

		@FindBy(xpath = ".//*[@id='C4__C4__HEAD_3E590269BD319CB11832447']")
		private WebElement lbeNoRecordMessage;

		@FindBy(css = "#C5__C4__p4_BUT_48C7AFBAC8EA882068488_R1 span")
		private WebElement lnkSelect;

		@FindBy(css = "#C5__C4__p4_BUT_48C7AFBAC8EA882068638_R1 span")
		private WebElement lnkSelectAcc;

		@FindBy(css = "#C5__QUE_5B7BF686F521E7E8258462")
		private WebElement txtReference;

		public CompanyPayee(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetails(String companyName)
				throws Exception {
			BrowserActions.clickOnButton(lbeFindACompany, driver,
					"Find by company name");
			WaitUtil.sleep(2);
			BrowserActions.typeWithoutClearTextField(txtFindbyCompanyName,
					companyName, driver, "Company Name Search Box");
			BrowserActions.clickOnButton(btnSearch, driver, "Search Button");
			BrowserActions.clickOnButton(lnkSelect, driver, "select");
			// BrowserActions.typeOnTextField(txtReference, reference, driver,
			// "Reference of customer");
			return new PaymentsPayeeDetails(driver).get();
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetailsByAccount(
				String accountNo, String sortCode) throws Exception {
			BrowserActions.clickOnButton(lblFindAccountDetails, driver,
					"Find by Account Number");
			WaitUtil.sleep(2);
			BrowserActions.typeWithoutClearTextField(txtAccNo, accountNo,
					driver, "Account Number");
			BrowserActions.typeWithoutClearTextField(txtSortCode, sortCode,
					driver, "Sort Code");
			BrowserActions.clickOnButton(btnSearchAcc, driver, "Search Button");
			BrowserActions.clickOnButton(lnkSelectAcc, driver, "select");
			// BrowserActions.typeOnTextField(txtReference, reference, driver,
			// "Reference of customer");
			return new PaymentsPayeeDetails(driver).get();
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

		@FindBy(css = "select[name*='C5__C4__INPUTPARAMETERS[1].COUNTRY']")
		private WebElement drpdwnCountry;

		@FindBy(css = "a[oldtitle='Cancel']")
		private WebElement btnCancel;

		@FindBy(css = "#C5__C4__p4_BUT_C39CDB4A4CEFB87523645 span")
		private WebElement btnEnterPaymentDetail;

		@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD195611_0']")
		private WebElement lblSwiftAccNo;

		@FindBy(css = "label[for='C5__C4__QUE_BBA538E230C96EFD195664_0']")
		private WebElement lblMutipleNcc;

		public InternationalPayee(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetails(String country)
				throws Exception {
			// BrowserActions.selectFromComboBox(drpdwnCountry, country, driver,
			// "Country dropdown");
			BrowserActions.selectFromDropDown(drpdwnCountry, country, driver,
					"Country dropdown");
			BrowserActions.clickOnButton(btnEnterPaymentDetail, driver,
					"Enter payment details");
			BrowserActions.clickOnButton(lblSwiftAccNo, driver,
					"Selecting Swift Account number");
			return new PaymentsPayeeDetails(driver).get();
		}

		public PaymentsPayeeDetails gotoPaymentsPayeeDetails1(String country)
				throws Exception {
			// BrowserActions.selectFromComboBox(drpdwnCountry, country, driver,
			// "Country dropdown");
			BrowserActions.selectFromDropDown(drpdwnCountry, country, driver,
					"Country dropdown");
			BrowserActions.clickOnButton(btnEnterPaymentDetail, driver,
					"Enter payment details");
			BrowserActions.clickOnButton(lblMutipleNcc, driver,
					"Selecting Swift Account number");
			return new PaymentsPayeeDetails(driver).get();
		}

	}

}