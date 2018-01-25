package com.temenos.pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

public class PaymentsTab extends LoadableComponent<PaymentsTab> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(id = "C4__TAB_3")
	private WebElement paymentsTitle;

	@FindBy(id = "C4__C4__TAB_2")
	private WebElement tabNewPayee;

	@FindBy(id = "C4__C4__TAB_1")
	private WebElement tabSavePayee;

	@FindBy(css = "#C5__C4__QUE_1D2ACFBF048B8D51416261_R101")
	WebElement savedPayeeList;

	@FindBy(css = "label[for='C5__C4__QUE_1D2ACFBF048B8D51585248_0']")
	WebElement paymentIBAN;

	@FindBy(id = "C5__C4__QUE_1D2ACFBF048B8D51585258_0")
	WebElement paymentSwiftBIC;

	@FindBy(id = "C5__C4__QUE_1D2ACFBF048B8D51585278_0")
	WebElement paymentMultipleNCC;

	@FindBy(id = "C5__C4__COL_BBA538E230C96EFD102161")
	WebElement newPayeePresonWithinGB;

	@FindBy(id = "C5__C4__COL_BBA538E230C96EFD102169")
	WebElement newPayeeCompanyWithinGB;

	@FindBy(id = "C5__C4__COL_BBA538E230C96EFD102165")
	WebElement newPayeePresonOutSideGB;

	public PaymentsTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, paymentsTitle)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Payments tab!", driver);
		} else {
			Log.message("Navigated to Payments tab!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public SavePayee clickOnSavePayeeTab() throws Exception {
		BrowserActions.clickOnButton(tabSavePayee, driver,
				"Click on Save Payee Tab");
		WaitUtil.waitForSpinnerToComplete(driver);
		return new SavePayee(driver);
	}

	public NewPayeePayments clickOnNewPayeeTab() throws Exception {
		BrowserActions.clickOnButton(tabNewPayee, driver,
				"Click on New Payee Tab");
		WaitUtil.waitForSpinnerToComplete(driver);
		Utils.waitForPageLoad(driver);
		return new NewPayeePayments(driver);
	}

	public class SavePayee {

		private WebDriver driver;

		@FindBy(css = "#C4__C4__HEAD_917906F10752499E56899")
		private WebElement titleChoiceInfo;

		@FindBy(css = "#C4__C4__HEAD_3E590269BD319CB11817949")
		private WebElement recordInfo;

		public SavePayee(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public String getNoRecordMessageInfo() throws Exception {
			return BrowserActions.getText(driver, recordInfo,
					"No Records Message");
		}

		public SavePayee clickOnPayeeDetails() throws Exception {
			BrowserActions.clickOnButton(savedPayeeList, driver,
					"Click on Save Payee Tab");
			WaitUtil.waitForSpinnerToComplete(driver);
			return new SavePayee(driver);
		}

		public void clickOnIbanPayment() throws Exception {
			BrowserActions.clickOnButton(paymentIBAN, driver,
					"Click on Save Payee Tab");

		}
	}

	public static boolean compareTwoList(List<String> expectedElements,
			List<String> actualElements) throws Exception {
		boolean statusToBeReturned = false;
		List<String> uniqueList = new ArrayList<String>();
		List<String> missedList = new ArrayList<String>();
		for (String item : expectedElements) {
			if (actualElements.contains(item)) {
				uniqueList.add(item);
			} else {
				missedList.add(item);
			}
		}
		/*
		 * Collections.sort(expectedElements); Collections.sort(actualElements);
		 */
		if (expectedElements.equals(actualElements)) {
			statusToBeReturned = true;
		} else {
			statusToBeReturned = false;
		}
		return statusToBeReturned;
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

	public boolean verifyAttributeForElement(String element,
			String attributeName, String actualValue, Object obj)
			throws Exception {
		boolean result = false;
		Field f = obj.getClass().getDeclaredField(element);
		f.setAccessible(true);
		WebElement webElement = ((WebElement) f.get(obj));
		String actualAttibuteValue = webElement.getAttribute(attributeName);
		//Log.event("--->>>" + attributeName + " :: " + actualAttibuteValue);
		//Log.event("--->>>actual Value ::" + actualValue);

		// Log.message("actualClassProperty=="+actualAttibuteValue);
		// Log.message("actualValue==="+actualValue);
		if (actualAttibuteValue.contains(actualValue)) {
			result = true;
		}
		return result;
	}

	public PaymentsTab clickOnPayeeDetails() throws Exception {
		BrowserActions.clickOnButton(savedPayeeList, driver,
				"Click on Save Payee Tab");
		WaitUtil.waitForSpinnerToComplete(driver);
		Utils.waitForPageLoad(driver);
		return new PaymentsTab(driver);
	}

	public void clickOnIbanPayment() throws Exception {
		BrowserActions.clickOnButton(paymentIBAN, driver,
				"Click on Save Payee Tab");

	}
}
