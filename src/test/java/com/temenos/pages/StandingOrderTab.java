package com.temenos.pages;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

//import com.salesforce.pages.NoSuchElementException;
//import com.salesforce.support.Utils;
//import com.salesforce.support.Utils;
import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.Utils;
import com.temenos.support.WaitUtil;

public class StandingOrderTab extends LoadableComponent<StandingOrderTab> {

	private WebDriver driver;

	private Headers headers;

	private Footers footers;

	private boolean isFailedFirstTime;

	@FindBy(id = "C5__TAB_4")
	private WebElement standingOrdTitle;

	@FindBy(css = "#C5__C5__p4_BUT_0D7184C5D7933FCE364042 a")
	private WebElement lnkselectOption;

	@FindBy(css = "#C5__C5__p4_BUT_1451951D03038CF4324080 a")
	private WebElement lnkPrintOption;

	@FindBy(css = "#C5__C5__p4_BUT_1451951D03038CF4324082 a")
	private WebElement lnkPDFDownloadOption;

	@FindBy(css = "#C5__C5__p4_BUT_1451951D03038CF4324084 a")
	private WebElement lnkCSVDownloadOption;

	@FindBy(css = "#C5__C5__p1_QUE_176BE187C511D0CC11976")
	private WebElement payeeNameColumn;

	@FindBy(css = "#C5__C5__p1_QUE_176BE187C511D0CC11981")
	private WebElement payeeAccColumn;

	@FindBy(css = "#C5__C5__p1_QUE_176BE187C511D0CC15128")
	private WebElement currencyColumn;

	@FindBy(css = "#C5__C5__p1_QUE_176BE187C511D0CC11986")
	private WebElement amountColumn;

	@FindBy(css = "#C5__C5__p1_QUE_176BE187C511D0CC11991")
	private WebElement frequencyColumn;

	@FindBy(css = "#C5__C5__p1_QUE_176BE187C511D0CC11996")
	private WebElement nextPaymentColumn;

	@FindBy(css = "#C5__C5__p1_QUE_176BE187C511D0CC12001")
	private WebElement endDateColumn;

	@FindBy(css = "#C5__C5__p4_BUT_F4DF6A915ED71ED1127317_R1 a")
	private WebElement viewDetailsStandingOrdArrow;

	@FindBy(css = "#C5__C5__p4_BUT_19B850AE210A75E5142619 a")
	private WebElement btnStandOrdDetailsDelete;

	@FindBy(css = "a#C5__C5__BUT_19B850AE210A75E5142624")
	private WebElement btnStandOrdDetailsEdit;

	@FindBy(css = "a#C5__C5__BUT_19B850AE210A75E5160097")
	private WebElement btnStandOrdPopUpDelete;

	@FindBy(css = "a#C5__C5__BUT_19B850AE210A75E5160092")
	private WebElement btnStandOrdPopUpCancel;

	@FindBy(css = "#C5__C5__TBL_176BE187C511D0CC10622_info")
	private WebElement lblPageCountTxt;

	@FindBy(css = "#C5__C5__HEAD_19B850AE210A75E5130278")
	private WebElement lblStandingOrderHeading;

	public Headers getHeaders() {
		return headers;
	}

	public Footers getFooters() {
		return footers;
	}

	public StandingOrderTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime	&& !WaitUtil.waitForElement(driver, standingOrdTitle)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to StandingOrder tab!", driver);
		} else {
			Log.message("Navigated to Standing Order tab!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public void clickOnShowOptions() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnButton(lnkselectOption, driver, "Show Options");
	}

	public class StandingOrdersDetails extends LoadableComponent<StandingOrdersDetails> {

		private WebDriver driver;
		
		@Override
		protected void isLoaded() throws Error {
			if (!isFailedFirstTime	&& !WaitUtil.waitForElement(driver, standingOrdTitle)) {
				isFailedFirstTime = true;
				throw new Error();
			} else if (isFailedFirstTime) {
				Log.fail("Not navigated to StandingOrder Details!", driver);
			} else {
				Log.message("Navigated to Standing Order Details!", driver);
			}
		}

		@Override
		protected void load() {
			WaitUtil.waitForSpinnerToComplete(driver);
		}
		
		@FindBy(css = "#C5__C5__HEAD_19B850AE210A75E5149507")
		private WebElement lblEditStandingOrderTitle;

		@FindBy(css = "span#C5__C5__QUE_176BE187C511D0CC11996_R1")
		private WebElement btnPaymentStartDateFirstRow;

		@FindBy(css = "span#C5__C5__QUE_176BE187C511D0CC12001_R1")
		private WebElement btnPaymentEndDateFirstRow;

		@FindBy(css = "a#C5__C5__BUT_19B850AE210A75E5142614")
		private WebElement btnStandingOrdDetailBack;

		@FindBy(css = "input#C5__C5__QUE_F4DF6A915ED71ED1267798")
		private WebElement txtPayeeName;

		@FindBy(css = "input#C5__C5__QUE_F4DF6A915ED71ED1269288")
		private WebElement txtPayeeAcc;

		@FindBy(css = "input#C5__C5__QUE_19B850AE210A75E5136229")
		private WebElement txtReference;

		@FindBy(css = "input#C5__C5__QUE_F4DF6A915ED71ED1270778")
		private WebElement txtAmount;

		@FindBy(css = "input#C5__C5__QUE_F4DF6A915ED71ED1270782")
		private WebElement txtCurrency;

		@FindBy(css = "input#C5__C5__QUE_19B850AE210A75E5136244")
		private WebElement txtStartDate;

		@FindBy(css = "input#C5__C5__QUE_19B850AE210A75E5136249")
		private WebElement txtEndDate;

		@FindBy(css = "input#C5__C5__QUE_19B850AE210A75E5136254")
		private WebElement txtFrequency;

		
		public StandingOrdersDetails(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public boolean checkFrequencyTextBoxEditable() throws Exception {
			if (txtFrequency.isEnabled()) {
				return true;
			}
			return false;
		}
		
		public HashMap<String, String> getDetailsOfStandingOrder()
				throws Exception {
			HashMap<String, String> map = new HashMap<>();

			map.put("Frequency", BrowserActions.getTextFromAttribute(driver,
					txtFrequency, "value", "Frequency"));
			map.put("EndDate", BrowserActions.getTextFromAttribute(driver,
					txtEndDate, "value", "EndDate"));
			map.put("StartDate", BrowserActions.getTextFromAttribute(driver,
					txtStartDate, "value", "StartDate"));
			map.put("Currency", BrowserActions.getTextFromAttribute(driver,
					txtCurrency, "value", "Currency"));
			map.put("Amount", BrowserActions.getTextFromAttribute(driver,
					txtAmount, "value", "Amount"));
			map.put("Reference", BrowserActions.getTextFromAttribute(driver,
					txtReference, "value", "Reference"));
			map.put("PayeeAcc", BrowserActions.getTextFromAttribute(driver,
					txtPayeeAcc, "value", "PayeeAcc"));
			map.put("PayeeName", BrowserActions.getTextFromAttribute(driver,
					txtPayeeName, "value", "PayeeName"));
			return map;
		}
		

		public boolean checkEndDateTextBoxEditable() throws Exception {
			if (txtEndDate.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkStartDateTextBoxEditable() throws Exception {
			if (txtStartDate.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkCurrencyTextBoxEditable() throws Exception {
			if (txtCurrency.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkAmountTextBoxEditable() throws Exception {
			if (txtAmount.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkReferenceTextBoxEditable() throws Exception {
			if (txtReference.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkPayeeAccTextBoxEditable() throws Exception {
			if (txtPayeeAcc.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkPayeeNameTextBoxEditable() throws Exception {
			if (txtPayeeName.isEnabled()) {
				return true;
			}
			return false;
		}

		public StandingOrderTab clickOnStandingOrdDetailBack() throws Exception {
			Utils.waitForPageLoad(driver);
			BrowserActions.clickOnButton(btnStandingOrdDetailBack, driver,
					"Standing Order Back");
			Utils.waitForPageLoad(driver);
			WaitUtil.sleep(5);
			return new StandingOrderTab(driver).get();
		}

		public void clickOnStandOrdPopUpDelete() throws Exception {
			Utils.waitForPageLoad(driver);
			BrowserActions.clickOnButton(btnStandOrdPopUpDelete, driver,
					"Standing Order Popup Delete");
			Utils.waitForPageLoad(driver);
			WaitUtil.sleep(5);
		}

		public String getStartDate() throws Exception {
			Utils.waitForPageLoad(driver);
			return BrowserActions.getTextFromAttribute(driver, txtStartDate,
					"value", "StartDate");
		}

		public void clickOnStandOrdDetailsDelete() throws Exception {
			Utils.waitForPageLoad(driver);
			BrowserActions.clickOnButton(btnStandOrdDetailsDelete, driver,
					"Standing Order Delete");
			Utils.waitForPageLoad(driver);
			WaitUtil.sleep(5);
		}

		public void clickOnStandOrdPopUpCancel() throws Exception {
			Utils.waitForPageLoad(driver);
			BrowserActions.clickOnButton(btnStandOrdPopUpCancel, driver,
					"Standing Order Popup Cancel");
			Utils.waitForPageLoad(driver);
			WaitUtil.sleep(5);
		}

		public String getNextPaymentDateFromFirstRow() throws Exception {
			Utils.waitForPageLoad(driver);
			return BrowserActions.getText(driver, btnPaymentStartDateFirstRow,
					"Start date");
		}

		public String getEndDateFromFirstRow() throws Exception {
			Utils.waitForPageLoad(driver);
			return BrowserActions.getText(driver, btnPaymentEndDateFirstRow,
					"End date");
		}

		public boolean verifyPageElementsAreDisplayed(
				List<String> expectedTabs, Object obj, WebDriver driver)
				throws Exception {
			Log.message(
					"Verifying whether the expected page elements are displayed..",
					driver);

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

		public boolean VerifyPageElementDisplayed(
				List<String> expectedElements, Object obj) throws Exception {
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
			return PaymentsTab
					.compareTwoList(expectedElements, actual_elements);
		}
	}

	public class EditStadingOrders extends LoadableComponent<EditStadingOrders>{

		private WebDriver driver;
		
		@Override
		protected void isLoaded() throws Error {
			if (!isFailedFirstTime	&& !WaitUtil.waitForElement(driver, standingOrdTitle)) {
				isFailedFirstTime = true;
				throw new Error();
			} else if (isFailedFirstTime) {
				Log.fail("Not navigated to Standing Order Edit!", driver);
			} else {
				Log.message("Navigated to Standing Order Edit!", driver);
			}
		}

		@Override
		protected void load() {
			WaitUtil.waitForSpinnerToComplete(driver);
		}

		@FindBy(css = "#C5__C5__HEAD_19B850AE210A75E5149507")
		private WebElement lblEditStandingOrderTitle;

		@FindBy(css = "#C5__C5__QUE_19B850AE210A75E5147985")
		private WebElement txtAmountTextBox;

		@FindBy(css = "a#C5__C5__BUT_19B850AE210A75E5153291")
		private WebElement btnEditSaveChangesPopUp;

		@FindBy(css = "a#C5__C5__BUT_19B850AE210A75E5148015")
		private WebElement btnEditSaveChanges;

		@FindBy(css = "#ui-datepicker-div a.ui-state-default:not(.ui-state-active)")
		private List<WebElement> btnUnselectedDayFromCalender;

		@FindBy(css = "#C5__C5__QUE_19B850AE210A75E5147975")
		private WebElement txtPayeeName;

		@FindBy(css = "#C5__C5__QUE_19B850AE210A75E5147980")
		private WebElement txtPayeeAcc;

		@FindBy(css = "#C5__C5__QUE_19B850AE210A75E5147990")
		private WebElement txtPayeeCurrency;

		@FindBy(css = "input#C5__C5__QUE_19B850AE210A75E5147995")
		private WebElement txtPaymentStartDate;

		@FindBy(css = "input#C5__C5__QUE_19B850AE210A75E5148005")
		private WebElement txtEndDate;

		@FindBy(css = "select#C5__C5__QUE_19B850AE210A75E5148000")
		private WebElement txtPaymentFrequency;

		@FindBy(css = "#C5__C5__row_QUE_19B850AE210A75E5147995 img.ui-datepicker-trigger")
		private WebElement lnkPaymentStartDateCalender;

		@FindBy(css = "#C5__C5__row_QUE_19B850AE210A75E5148005 img.ui-datepicker-trigger")
		private WebElement lnkPaymentFinalDateCalender;

//		@FindBy(css = ".tc-form-control label[for='C5__C5__QUE_4EA815C4CCBD4ED4718769_0']")
//		private WebElement chkFurtherNotice;
		
		@FindBy(css = "#C5__C5__QUE_4EA815C4CCBD4ED4718769_0")
		private WebElement chkFurtherNotice;

		@FindBy(css = "a#C5__C5__BUT_19B850AE210A75E5148010")
		private WebElement btnCancelEdit;

		@FindBy(css = ".ui-button-icon-primary")
		private WebElement btnSaveChangesPopUpClose;

		@FindBy(css = "span#C5__C5__QUE_19B850AE210A75E5147985_ERRORMESSAGE")
		private WebElement lblErrorMessage_Amt;

		public EditStadingOrders(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public boolean checkPaymentStartDateTextBoxEditable() throws Exception {
			if (txtPaymentStartDate.isEnabled()) {
				return true;
			}
			return false;
		}

		public void selectAnyUnselectedPaymentStartDateFromCalender()
				throws Exception {

			BrowserActions.clickOnButton(lnkPaymentStartDateCalender, driver,
					"Calender start date");
			BrowserActions.clickOnButton(btnUnselectedDayFromCalender.get(1),
					driver, "Day");
			WaitUtil.sleep(5);
		}

		public void selectCancelEdit() throws Exception {
			if (Utils.waitForElement(driver, btnCancelEdit)) {
				BrowserActions.clickOnButton(btnCancelEdit, driver,
						"Cancel edit");
			}
			WaitUtil.sleep(5);
		}

		public void selectSaveChangesPopUpClose() throws Exception {
			if (Utils.waitForElement(driver, btnSaveChangesPopUpClose)) {
				BrowserActions.clickOnButton(btnSaveChangesPopUpClose, driver,
						"Cancel edit");
			}
			WaitUtil.sleep(5);
		}

		public void selectAnyUnselectedFinalPaymentDateFromCalender()
				throws Exception {
			BrowserActions.clickOnButton(lnkPaymentFinalDateCalender, driver,
					"Calender final date");
			BrowserActions.clickOnButton(btnUnselectedDayFromCalender.get(1),
					driver, "Day");
			WaitUtil.sleep(5);
		}

		public String getPaymentStartDate() throws Exception {
			return BrowserActions.getTextFromAttribute(driver,
					txtPaymentStartDate, "value", "Payment Start date");
		}

		public void enterPaymentEndDate(String txtToType) throws Exception {
			BrowserActions.typeOnTextField(txtEndDate, txtToType, driver,
					"End date");
		}

		public String getPaymentFrequency() throws Exception {
			WaitUtil.sleep(3);
			return BrowserActions.getTextFromAttribute(driver,
					txtPaymentFrequency, "value", "Payment Frequency");
		}

		public boolean getFurtherNoticeCheckBoxStatus() throws Exception {
			if (chkFurtherNotice.isSelected()) {
				return true;
			}
			return false;
		}

		public void selectFurtherNoticeCheckBox(boolean isNeedToCheck)
				throws Exception {
			if (isNeedToCheck) {
				if (!getFurtherNoticeCheckBoxStatus()) {
					BrowserActions.clickOnButton(chkFurtherNotice, driver,"Further notice");
				}
			} else {
				if (getFurtherNoticeCheckBoxStatus()) {
					BrowserActions.clickOnButton(chkFurtherNotice, driver,"Further notice");
				}
			}
		}

		public void selectDifferentFrequency() throws Exception {
			WaitUtil.sleep(3);
			String freq = getPaymentFrequency();
			if (freq.trim().toLowerCase().equals("monthly")) {
				BrowserActions.selectFromDropDown(txtPaymentFrequency,
						"Weekly", driver, "Frequency");
			} else {
				BrowserActions.selectFromDropDown(txtPaymentFrequency,
						"Monthly", driver, "Frequency");
			}
		}

		public void selectFrequency(String freq) throws Exception {
			WaitUtil.sleep(3);
			BrowserActions.selectFromDropDown(txtPaymentFrequency, freq,
					driver, "Frequency");
		}

		public String getPaymentEndDate() throws Exception {
			return BrowserActions.getTextFromAttribute(driver, txtEndDate,
					"value", "Payment Start date");
		}

		public void enterPaymentStartDate(String txtToType) throws Exception {
			BrowserActions.typeOnTextField(txtPaymentStartDate, txtToType,
					driver, "Payment Start date");
		}

		public boolean checkStartDateNotAcceptAlphabet() throws Exception {
			String text = BrowserActions.getTextFromAttribute(driver,
					txtPaymentStartDate, "onchange", "Payment Start date");
			if (text.toLowerCase().contains("return validDate".toLowerCase())) {
				return true;
			}
			return false;
		}

		public boolean checkFinalDateNotAcceptAlphabet() throws Exception {
			String text = BrowserActions.getTextFromAttribute(driver,
					txtEndDate, "onchange", "Payment End date");
			if (text.toLowerCase().contains("return validDate".toLowerCase())) {
				return true;
			}
			return false;
		}

		public boolean checkPayeeNameEditable() throws Exception {
			if (txtPayeeName.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkPayeeCurrencyEditable() throws Exception {
			if (txtPayeeCurrency.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkPaymentFrequencyTextBoxEditable() throws Exception {
			if (txtPaymentFrequency.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkPaymentEndDateTextBoxEditable() throws Exception {
			if (txtEndDate.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkPayeeAccEditable() throws Exception {
			if (txtPayeeAcc.isEnabled()) {
				return true;
			}
			return false;
		}

		public boolean checkAmountTextBoxEditable() throws Exception {
			if (txtAmountTextBox.isEnabled()) {
				return true;
			}
			return false;
		}

		public void enterTextInAmountTextBox(String txtToType) throws Exception {
			BrowserActions.typeOnTextField(txtAmountTextBox, txtToType, driver,
					"Amount text box");
		}

		public String getAmountFromTextBox() throws Exception {
			return BrowserActions.getTextFromAttribute(driver,
					txtAmountTextBox, "value", "Amount");
		}

		public void clickOnEditSaveChanges() throws Exception {
			Utils.waitForPageLoad(driver);
			BrowserActions.clickOnButton(btnEditSaveChanges, driver,
					"Edit Save Changes");
			Utils.waitForPageLoad(driver);
			WaitUtil.sleep(5);
		}

		public StandingOrdersDetails clickOnEditSaveChangesPopUp()
				throws Exception {
			Utils.waitForPageLoad(driver);
			BrowserActions.clickOnButton(btnEditSaveChangesPopUp, driver,
					"Edit Save Changes");
			Utils.waitForPageLoad(driver);
			WaitUtil.sleep(5);
			return new StandingOrdersDetails(driver);
		}

		public boolean verifyPageElementsAreDisplayed(
				List<String> expectedTabs, Object obj, WebDriver driver)
				throws Exception {
			Log.message(
					"Verifying whether the expected page elements are displayed..",
					driver);

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

		public boolean VerifyPageElementDisplayed(
				List<String> expectedElements, Object obj) throws Exception {
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
			return PaymentsTab
					.compareTwoList(expectedElements, actual_elements);
		}
	}

	public void clickOnPDFDownloadOption() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnButton(lnkPDFDownloadOption, driver,
				"PDF Download Options");
		Utils.waitForPageLoad(driver);
		WaitUtil.sleep(5);
	}

	public void clickOnCSVDownloadOption() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnButton(lnkCSVDownloadOption, driver,
				"CSV Download Options");
		Utils.waitForPageLoad(driver);
		WaitUtil.sleep(5);
	}

	public StandingOrdersDetails clickOnViewDetailsStandingOrdArrow()
			throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnButton(viewDetailsStandingOrdArrow, driver, "Standing Order details view");
		Utils.waitForPageLoad(driver);
		WaitUtil.sleep(5);
		return new StandingOrdersDetails(driver).get();
	}

	public EditStadingOrders clickOnStandOrdDetailsEdit() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnButton(btnStandOrdDetailsEdit, driver,
				"Standing Order Edit");
		Utils.waitForPageLoad(driver);
		WaitUtil.sleep(5);
		return new EditStadingOrders(driver).get();
	}

	public int getNumberOfStandingOrder() throws Exception {
		String text = BrowserActions.getText(driver, lblPageCountTxt,
				"Count label");
		int len = text.length();
		int beginIndex = len - 2;
		int endIndex = len;
		String numb = text.substring(beginIndex, endIndex).trim();
		int noOfStandingOrd = Integer.parseInt(numb);
		return noOfStandingOrd;
	}

	public boolean isFileDownloaded(String downloadPath, String fileName)
			throws Exception {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().contains(fileName)) {
				dirContents[i].delete();
				return true;
			}
		}
		return false;
	}

	public boolean verifyPageElementsAreDisplayed(List<String> expectedTabs,
			Object obj, WebDriver driver) throws Exception {
		Log.message(
				"Verifying whether the expected page elements are displayed..",
				driver);
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
