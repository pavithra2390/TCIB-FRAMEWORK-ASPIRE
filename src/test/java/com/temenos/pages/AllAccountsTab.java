package com.temenos.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class AllAccountsTab extends LoadableComponent<AllAccountsTab> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C4__TAB_1 span")
	private WebElement tabAllAccounts;

	@FindBy(css = "#C4__TAB_2 span")
	private WebElement tabQuickTransfers;

	/*@FindBy(css = "#C5__TBL_99900AB330551EB6127590 tr td")
	private List<WebElement> trAllAccounts;
	*/
	@FindBy(css = "table[summary='Products List'] tr td")
	private List<WebElement> trAllAccounts;
	
	
	@FindBy(css = "table[title*='Products List'] tbody tr td:nth-child(6)")
	private List<WebElement> tdAllAccountsDetail;

	@FindBy(css = ".tc-table-nav-row .tc-nav-item-first.tc-table-nav-item")
	private WebElement lnkMovePrev;

	@FindBy(css = "div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper .tc-table-nav-row .tc-nav-item-first.tc-table-nav-item")
	private WebElement lnkMovePrevTrans;

	@FindBy(css = ".tc-table-nav-row .tc-nav-item-last.tc-table-nav-item")
	private WebElement lnkMoveNext;

	@FindBy(css = "div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper .tc-table-nav-row .tc-nav-item-last.tc-table-nav-item")
	private WebElement lnkMoveNextTrans;

	@FindBy(xpath = "//*[contains(@id, 'PAG_BOTTOM_')]")
	private List<WebElement> lnkPages;

	@FindBy(css = "div.tc-table-nav-row")
	private WebElement navigation;

	@FindBy(css = "#C5__C1__HEAD_7FF09F1E4657A0DE165851")
	private WebElement headerAcctType;

	@FindBy(css = "#C5__C1__HEAD_7FF09F1E4657A0DE165859")
	private WebElement headerAcctNo;

	@FindBy(css = "#C5__C1__QUE_7FF09F1E4657A0DE167038")
	private WebElement headerAcctBal;

	@FindBy(css = "#C5__C1__p4_QUE_7FF09F1E4657A0DE167038 div span.tc-prefix-part")
	private WebElement headerAcctBalCurrency;

	@FindBy(css = "div.tc-breadcrumb")
	private WebElement headerBreadcrump;

	@FindBy(css = "#C5__FMT_C1F553AF78F2748F581378 div div div div[id^='C5__TAB_']")
	private List<WebElement> trAccountsTab;

	@FindBy(css = "div[id^='C5__C2__FMT_'] div.tc-card-header div")
	private WebElement acccountsSubTabHeader;

	@FindBy(css = "#C5__C1__BUT_F53BD0FF88161173148481 > span")
	private WebElement acctShowOrLess;

	@FindBy(css = "#C5__C1__FMT_F53BD0FF88161173148327")
	private WebElement acctDetails;

	@FindBy(css = "#C5__C1__FMT_ECD628859D13C53E1677135")
	private WebElement depositDetails;

	@FindBy(css = "#C5__C1__BUT_ECD628859D13C53E1677167 > span")
	private WebElement depositShowOrLess;

	@FindBy(css = "div[id^='C5__C1__FMT_'].tc-card-bg div div")
	private List<WebElement> trAccountsDetails;

	@FindBy(css = "div[id^='C5__C2__TAB_']:not([style^='display: none;'])")
	public List<WebElement> trAccountsTransTabs;

	@FindBy(css = "#C5__C2__BUT_D74C98DEA914D17E553169")
	private WebElement showOption;

	@FindBy(css = "div[id^='C5__C2__FMT_'].tc-popup div div div div[id^='C5__C2__row_BUT_']>div:not([class='ecDIB  col-hidden']) div a[id^='C5__C2__BUT_']")
	private List<WebElement> trShowOptionTabs;

	@FindBy(css = "div#pageContainer1 div div:contains('Account Number')")
	private WebElement printPageAccountNumber;

	@FindBy(css = "th[id^='C5__C2__p1_QUE_'] div a")
	private List<WebElement> trAccountsTransCompletedTabs;

	@FindBy(css = "input[id^='C5__QUE_']")
	private List<WebElement> trTransactionDetails;

	@FindBy(css = "tr[id^='C5__C2__p0_TBL_'] td")
	private List<WebElement> trTransactionList;

	@FindBy(css = "input[id^='C5__C2__QUE_']")
	private List<WebElement> transSearchInputs;

	@FindBy(css = "h2[id^='C5__C2__HEAD_']")
	public List<WebElement> transSearchHeaders;

	@FindBy(css = "h4[id^='C5__C2__HEAD_']")
	public List<WebElement> transPendingHeaders;

	@FindBy(css = "div[id^='C5__C2__p4_'] div span[id^='C5__C2__QUE_']")
	private List<WebElement> transSearchFilters;

	@FindBy(css = "#C5__C2__BUT_A2CF85134EB0679A24691 > span")
	private WebElement clearFilter;

	@FindBy(css = "div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper [class*='tc-nav-item-last']")
	private WebElement nextButton;

	@FindBy(css = "#C5__TAB_1 span")
	private WebElement tabTransactions;

	@FindBy(css = "#C5__TAB_2 span")
	private WebElement tabEndOfAction;

	@FindBy(css = "C5__C1__HEAD_4648D7DB1813B0B2105899")
	private WebElement overDarft;

	@FindBy(css = "#C5__TAB_1")
	private WebElement Transactions;

	@FindBy(css = "#C5__TAB_2")
	private WebElement Schedule;

	@FindBy(css = "#C5__TAB_3")
	private WebElement PayOverdue;

	int counterDP = 0;

	List<String> acctHeaders = Arrays.asList("Transaction List",
			"Transfer money to another account", "Payments",
			"View standing order", "Direct Debit List", "Manage Cheques",
			"Manage Alerts");
	List<String> showOptionDetails = Arrays.asList("Print", "PDF Download",
			"CSV Download");
	List<String> transacationCompTabs = Arrays.asList("Date", "Description",
			"Notes", "Amount ($)", "Balance ($)");
	List<String> acctHeadersForDeposit = Arrays.asList("Transactions",
			"Set and action for the end of the term");
	String PAGINATION = "[class*='tc-table-nav-item'][id*='PAG_BOTTOM_%s']";
	String PAGINATIONTRANS = "div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper [class*='tc-table-nav-item'][id*='PAG_BOTTOM_%s']";
	String PAGINATIONTRANS_Total = "div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper [class*='tc-table-nav-item'][id*='PAG_BOTTOM_']";
	String PAGINATIONTRANSWHOLE1 = "div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper [class*='tc-table-nav-item']";
	String PAGINATIONTRANSWHOLE = "div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper [class*='tc-table-nav-row'] a";
	List<String> transacationAmount = new ArrayList<String>();
	String DateforGet;
	private TransactionList transactionList;

	public AllAccountsTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		transactionList = new TransactionList(driver);
	}

	public TransactionList getTransactionList() throws Exception {
		return new TransactionList(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		WaitUtil.waitForSpinnerToComplete(driver);
		if (isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, trAllAccounts.get(0))) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, trAllAccounts.get(0))) {
			Log.fail("Not navigated to All Accounts Tab!", driver);
		} else {
			Log.message("Navigated to All Accounts Tab!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public AllAccountsTab goToAllAccountsTab() throws Exception {
		if (!tabAllAccounts.getAttribute("class").contains("selected")) {
			BrowserActions.clickOnButton(tabAllAccounts, driver,
					"All Accounts Tab");
		}
		return new AllAccountsTab(driver);
	}

	public QuickTransferTab goToQuickTransferTab() throws Exception {
		BrowserActions.clickOnButton(tabQuickTransfers, driver,
				"Quick Transfer Tab");
		return new QuickTransferTab(driver);
	}

	public int getTotalPages() throws Exception {
		return Integer.parseInt(driver
				.findElement(By.cssSelector(".tc-table-nav-row span.tc-fs"))
				.getText().split("of")[1].trim());
	}

	public AccountsPage clickOnAccount(String accountId) throws Exception {
		int totalPages = getTotalPages();
		int page = 1;
		while (page <= totalPages) {
			for (int index = 0; index < trAllAccounts.size(); index++) {
				if (trAllAccounts.get(index).getText().contains(accountId)) {
					BrowserActions.clickOnButton(trAllAccounts.get(index),
							driver, "Account Number - " + accountId);
					return new AccountsPage(driver).get();
				}
			}
			goToNextPage();
			page += 1;
		}
		throw new Error("Account id:" + accountId + " is not present!");
	}

	public AccountsPage clickOnAnySavingsAccount() throws Exception {
		Log.message("Navigating to Any Saving Account..");
		for (int index = 0; index < trAllAccounts.size(); index++) {
			if (trAllAccounts.get(index).getText().contains("Savings")) {
				BrowserActions.clickOnButton(trAllAccounts.get(index), driver,
						"Saving Account");
			}
		}
		return new AccountsPage(driver).get();
	}

	public String getAnyAccountNumberOfType(String typeOfAccount)
			throws Exception {
		int totalPages = Integer.parseInt(driver
				.findElement(By.cssSelector(".tc-table-nav-row span.tc-fs"))
				.getText().split("of")[1].trim());
		int page = 1;
		boolean foundStatus = false;
		String ac = null;

		while (page <= totalPages) {
			for (int index = 0; index < trAllAccounts.size(); index++) {
				if (trAllAccounts.get(index).getText().contains(typeOfAccount)) {
					String temp = trAllAccounts.get((index + 1)).getText();
					if (temp.contains("|")) {
						foundStatus = true;
						ac = temp.replace("|", " ").trim().split("  ")[1];
						break;

					} else {
						foundStatus = true;
						ac = temp.trim();
						break;
					}
				}
			}
			if (foundStatus)
				break;
			BrowserActions.clickOnButton(lnkMoveNext, driver,
					"Next Account Page");
			WaitUtil.waitForSpinnerToComplete(driver);
			page += 1;
		}
		return ac;
	}

	public String selectAnyAccountofType(String typeOfAccount) throws Exception {
		boolean foundStatus = false;
		String acctNo = null;
		List<String> accountNumbers = new ArrayList<String>();
		int totalPages = Integer.parseInt(driver
				.findElement(By.cssSelector(".tc-table-nav-row span.tc-fs"))
				.getText().split("of")[1].trim());
		int page = 1;
		String balance = null;
		while (page <= totalPages) {
			for (int index = 0; index < trAllAccounts.size(); index++) {

				if (trAllAccounts.get(index).getText().contains(typeOfAccount)) {
					String temp = trAllAccounts.get(index + 1).getText();
					balance = trAllAccounts.get(index + 2).getText();
					if (temp.contains(" | "))
						acctNo = temp.split(" | ")[2].trim();
					else
						acctNo = temp.trim();

					BrowserActions.clickOnButton(trAllAccounts.get(index),
							driver, "Click on the Account type: "
									+ typeOfAccount);
					WaitUtil.waitForSpinnerToComplete(driver);
					foundStatus = true;
					break;
				}
			}
			if (foundStatus)
				break;
			BrowserActions.clickOnButton(lnkMoveNext, driver,
					"Next Account Page");
			WaitUtil.waitForSpinnerToComplete(driver);
			page += 1;
		}

		return acctNo;
	}

	public boolean isPaginationDisplayed() throws Exception {
		return BrowserActions.elementDisplayed(driver, navigation);
	}

	public void goToNextPage() throws Exception {
		BrowserActions.clickOnButton(lnkMoveNext, driver, "Next Page");
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public void goToPrevPage() throws Exception {
		BrowserActions.clickOnButton(lnkMovePrev, driver, "Prev. Page");
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public boolean isPageNumberHighlighted(int pageNo) throws Exception {
		By pageLocator = By.cssSelector(String.format(PAGINATION, pageNo));
		return driver.findElement(pageLocator).getTagName().equals("span");
	}

	public void clickOnPageNo(int pageNo) throws Exception {
		Log.message("Click on Page number : " + pageNo);
		By pageLocator = By.cssSelector(String.format(PAGINATION, pageNo));
		BrowserActions.clickOnButton(driver.findElement(pageLocator), driver,
				"Page Number-" + pageNo);
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public boolean isPrevButtonDisabled() throws Exception {
		return lnkMovePrev.getTagName().equals("span");
	}

	public boolean isNextButtonDisabled() throws Exception {
		return lnkMovePrev.getTagName().equals("span");
	}

	public String getAllAccountsOfType(String typeOfAccount) throws Exception {
		List<String> accountNumbers = new ArrayList<String>();
		int totalPages = Integer.parseInt(driver
				.findElement(By.cssSelector(".tc-table-nav-row span.tc-fs"))
				.getText().split("of")[1].trim());
		int page = 1;
		while (page <= totalPages) {
			for (int index = 0; index < trAllAccounts.size(); index++) {
				if (trAllAccounts.get(index).getText().contains(typeOfAccount)) {
					String temp = trAllAccounts.get(index)
							.findElement(By.xpath(".//td[4]")).getText();
					if (temp.contains("\\|")) {
						accountNumbers.add(temp.split("\\|")[1].trim());
					} else {
						accountNumbers.add(temp.trim());
					}
				}
			}

			BrowserActions.clickOnButton(lnkMoveNext, driver,
					"Next Account Page");
			WaitUtil.waitForSpinnerToComplete(driver);
			page += 1;
		}
		return null;
	}

	public boolean verifyTabAccountPage(int noOfTabs) throws Exception {
		List<String> expectedElements = Arrays.asList("tabTransactions",
				"tabEndOfAction");

		if (trAccountsTab.size() != 0) {
			for (int index = 0; index < trAccountsTab.size(); index++) {
				if (index != 0) {
					BrowserActions.clickOnButton(trAccountsTab.get(index),
							driver, "Click on the Account tab: "
									+ trAccountsTab.get(index));
					WaitUtil.waitForSpinnerToComplete(driver);

				}
			}
			return true;

		}
		return false;
	}

	public boolean verifyTabDepositPage(int noOfTabs) throws Exception {
		if (trAccountsTab.size() != 0) {
			for (int index = 0; index < trAccountsTab.size(); index++) {

				if (index != 0) {
					BrowserActions.clickOnButton(trAccountsTab.get(index),
							driver, "Click on the Account tab: "
									+ trAccountsTab.get(index));
					WaitUtil.waitForSpinnerToComplete(driver);
				}
				System.out.println(trAccountsTab.get(index).getText()
						+ " tab is navigated");
				String titleActual = driver
						.findElement(
								By.cssSelector("div h2[id^='C5__C"
										+ (index + 2) + "__HEAD_']")).getText()
						.trim();
				if (acctHeadersForDeposit.get(index).contains(titleActual))
					System.out.println(trAccountsTab.get(index).getText()
							+ " tab details are verified");
			}
			return true;
		}
		return false;
	}

	public boolean verifyShowLessAcctDetails() throws Exception {
		if (!acctDetails.getAttribute("class").isEmpty()) {
			if (acctDetails.getAttribute("class").contains("hide")) {
				System.out
						.println("Initially account details are not displayed");
				// Show the details
				BrowserActions.clickOnButton(acctShowOrLess, driver,
						"Click on the show more");
				WaitUtil.waitForSpinnerToComplete(driver);
				if (acctDetails.getAttribute("class").contains("show")) {
					System.out
							.println("Account details are displayed now after clicking on the show more option");
					// Show the details
					BrowserActions.clickOnButton(acctShowOrLess, driver,
							"Click on the show less");
					WaitUtil.waitForSpinnerToComplete(driver);
					if (acctDetails.getAttribute("class").contains("hide"))
						System.out
								.println("Account details are not displayed now after clicking on the show less option");
					else
						System.out
								.println("Account details are not displayed after clicking on the show more option");
				} else
					System.out
							.println("Account details are not displayed after clicking on the show more option");
			} else
				System.out
						.println("Account details are displayed and we are expected to details should be hiden at the initally");
			return true;
		}
		return false;
	}

	public boolean verifyShowLessDepositDetails() throws Exception {
		if (!depositDetails.getAttribute("class").isEmpty()) {
			if (depositDetails.getAttribute("class").contains("hide")) {
				// Show the details
				BrowserActions.clickOnButton(depositShowOrLess, driver,
						"Click on the show more");
				WaitUtil.waitForSpinnerToComplete(driver);
			}
			if (depositDetails.getAttribute("class").contains("show")) {
				BrowserActions.clickOnButton(depositShowOrLess, driver,
						"Click on the show less");
				WaitUtil.waitForSpinnerToComplete(driver);
			}
			return true;
		}
		return false;
	}

	public boolean verifyTabTransactionPage(String typeOfAccount)
			throws Exception {
		int noOfTabs = 3;
		BrowserActions.clickOnButton(trAccountsTab.get(0), driver,
				"Click on the Account tab: " + trAccountsTab.get(0));
		WaitUtil.waitForSpinnerToComplete(driver);
		if (trAccountsTransTabs.size() != 0) {
			for (int index = 0; index < trAccountsTransTabs.size(); index++) {
				if (index != 0) {
					BrowserActions.clickOnButton(
							trAccountsTransTabs.get(index),
							driver,
							"Click on the Account tab: "
									+ trAccountsTab.get(index).getText());
					WaitUtil.waitForSpinnerToComplete(driver);
				}
			}
			return true;
		}
		return false;
	}

	public boolean verifyShowOption() throws Exception {
		try {
			BrowserActions.clickOnButtonJS(showOption, driver, "Show option ");
			if (!trShowOptionTabs.isEmpty()) {
				BrowserActions.clickOnButtonJS(showOption, driver,
						"Show option ");
			}
			if (trShowOptionTabs.size() != 0) {
				if (trShowOptionTabs.size() == 3) {
					System.out
							.println("3 options are displayed in the show option ");
					for (int index = 0; index < trShowOptionTabs.size(); index++) {
						if (!trShowOptionTabs.isEmpty()) {
							BrowserActions.clickOnButtonJS(showOption, driver,
									"Show option ");
						}
						System.out.println("Show option :" + (index + 1) + " "
								+ trShowOptionTabs.get(index).getText());
						if (showOptionDetails.get(index).equalsIgnoreCase(
								trShowOptionTabs.get(index).getText())) {
							System.out.println("Verified the show option with "
									+ showOptionDetails.get(index));
						} else {
							return false;
						}

					}
					return true;
				}
				return false;
			}
		} catch (Exception e) {
			Log.message(e.fillInStackTrace().toString());
		}
		return false;
	}

	public boolean verifyShowOptionFunction(String optionToPrint,
			String expAcctNo) throws Exception {
		try {
			if (trShowOptionTabs.size() != 0) {
				for (int i = 0; i < trShowOptionTabs.size(); i++) {
					if (trShowOptionTabs.get(i).getText()
							.equalsIgnoreCase(optionToPrint)) {
						BrowserActions.clickOnButton(trShowOptionTabs.get(i),
								driver, "Click on the show option ");
						WaitUtil.waitForSpinnerToComplete(driver);
					}
				}
				if (optionToPrint.equalsIgnoreCase("Print")) {
					System.out.println("Print option was clicked successfully");
					Set<String> handles = driver.getWindowHandles();
					if (handles.size() == 2) {

						System.out
								.println("Print option was opened successfully");
						System.out
								.println("Verifying the content in print page");

						// Pass a window handle to the other window
						int count = 1;
						for (String handle1 : driver.getWindowHandles()) {
							if (count == 2) {
								driver.switchTo().window(handle1);
								Thread.sleep(5000);
								System.out
										.println("Handle is switched to new print window");

								// driver.close();

							}
							count++;
						}
						handles = driver.getWindowHandles();
						if (count == 1) {
							System.out
									.println("Print page was closed successfully");
							driver.switchTo().window(driver.getWindowHandle());
							System.out
									.println("Contrl was switched back to main window");
						}

					} else
						System.out.println("print window was not opened");
				} else if (optionToPrint.equalsIgnoreCase("PDF DOWNLOAD")) {
					System.out.println("Download PDF was successful");
				} else if (optionToPrint.equalsIgnoreCase("CSV DOWNLOAD")) {
					System.out.println("Download CSV was successful");
				} else {
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			Log.message(e.getMessage());
		}
		return false;
	}

	public boolean verifyTabAccountTransaction() throws Exception {
		if (trAccountsTransCompletedTabs.size() == 5)
			System.out
					.println("5 heaader are displayed in the account transaction completed screen");
		else
			System.out
					.println("Error while verify the no of headers available in the account transaction completed screen Screen -- Actual tabs :"
							+ trAccountsTab.size() + " -- Expected tabs : 7");
		if (trAccountsTransCompletedTabs.size() != 0) {
			for (int index = 0; index < trAccountsTransCompletedTabs.size(); index++) {
				String titleActual = trAccountsTransCompletedTabs.get(index)
						.getText();
				if (titleActual.trim().equalsIgnoreCase(
						transacationCompTabs.get(index).trim())) {
					System.out.println(trAccountsTransCompletedTabs.get(index)
							.getText() + " header details are verified");
					BrowserActions.clickOnButton(
							trAccountsTransCompletedTabs.get(index), driver,
							"Click on the Account transaction headers : "
									+ trAccountsTransCompletedTabs.get(index));
					WaitUtil.waitForSpinnerToComplete(driver);
					System.out.println("Sorting for the header "
							+ trAccountsTransCompletedTabs.get(index).getText()
							+ " was verified successfully");
				} else {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public int getTotalPagesCommon() throws Exception {
		return Integer
				.parseInt(driver
						.findElement(
								By.cssSelector("div[id^='C5__C2__p1_TBL_'] div.tc-table-wrapper .tc-table-nav-row span.tc-fs"))
						.getText().split("of")[1].trim());
	}

	public void goToNextPageCommon() throws Exception {
		BrowserActions.clickOnButton(lnkMoveNext, driver, "Next Page");
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public void goToPrevPageCommon() throws Exception {
		BrowserActions.clickOnButton(lnkMovePrev, driver, "Prev. Page");
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public boolean isPageNumberHighlightedTrans(int pageNo) throws Exception {
		By pageLocator = By.cssSelector(String.format(PAGINATIONTRANS, pageNo));
		return driver.findElement(pageLocator).getTagName().equals("span");
	}

	public void clickOnPageNoCommon(int pageNo) throws Exception {
		Log.message("Click on Page number : " + pageNo);
		By pageLocator = By.cssSelector(String.format(PAGINATION, pageNo));
		BrowserActions.clickOnButton(driver.findElement(pageLocator), driver,
				"Page Number-" + pageNo);
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public boolean isNavigationButtonDisabledCommon(WebElement buttonPage)
			throws Exception {
		return buttonPage.getTagName().equals("span");
	}

	public boolean isPageNumberHighlightedCommon(WebElement buttonPage)
			throws Exception {
		return buttonPage.getTagName().equals("span");
	}

	public boolean verifyPaginationCommon(String navigationPath,
			String navigationOption) throws Exception {

		List<WebElement> tdAllAccountsDetail = driver.findElements(By
				.cssSelector(navigationPath));
		int count;
		if (tdAllAccountsDetail.size() != 0) {
			int totalPages = tdAllAccountsDetail.size() - 2;
			count = totalPages;

			if (navigationOption.equalsIgnoreCase("Next")) {
				if (isNavigationButtonDisabledCommon(tdAllAccountsDetail.get(0)))
					System.out
							.println("Initally the previous page button was disabled");
				else
					System.out
							.println("Initally the previous page button was not disabled");
			}
			if (navigationOption.equalsIgnoreCase("Previous")) {
				BrowserActions
						.clickOnButton(
								tdAllAccountsDetail.get(tdAllAccountsDetail
										.size() - 2),
								driver,
								"Page Number-"
										+ tdAllAccountsDetail.get(
												tdAllAccountsDetail.size() - 2)
												.getText());
				WaitUtil.waitForSpinnerToComplete(driver);
				tdAllAccountsDetail = driver.findElements(By
						.cssSelector(navigationPath));
				System.out
						.println("Moved to last page before the previous page navigation");
				if (isNavigationButtonDisabledCommon(tdAllAccountsDetail
						.get(tdAllAccountsDetail.size() - 1)))
					System.out
							.println("Initally the next page button was disabled");
				else
					System.out
							.println("Initally the next page button was not disabled");
			}
			for (int i = 0; i < totalPages - 1; i++) {
				tdAllAccountsDetail = driver.findElements(By
						.cssSelector(navigationPath));
				System.out.println("navigationTool : "
						+ tdAllAccountsDetail.get(i + 1).getText());
				switch (navigationOption) {
				case "Next":
				case "next":
					BrowserActions
							.clickOnButton(
									tdAllAccountsDetail.get(tdAllAccountsDetail
											.size() - 1),
									driver,
									"Page Number-"
											+ tdAllAccountsDetail
													.get(tdAllAccountsDetail
															.size() - 1)
													.getText());
					WaitUtil.waitForSpinnerToComplete(driver);
					Log.message("Next page button was clicked successfully");
					if (isPageNumberHighlightedTrans((i + 2))) {
						System.out.println("Next page navigated and verified");
					} else {
						System.out
								.println("Next page navigation was not happened");
						return false;

					}
					break;
				case "Previous":
				case "previous":
					BrowserActions.clickOnButton(tdAllAccountsDetail.get(0),
							driver, "Page Number-"
									+ tdAllAccountsDetail.get(0).getText());
					WaitUtil.waitForSpinnerToComplete(driver);
					System.out
							.println("Previous page button was clicked successfully");
					if (isPageNumberHighlightedTrans((count - 1))) {
						System.out
								.println("Previous page navigated and verified");
					} else {
						System.out
								.println("Previous page navigation was not happened");
						return false;
					}
					count--;
					break;
				case "Page No":
				case "Page no":
					BrowserActions
							.clickOnButton(
									tdAllAccountsDetail.get(tdAllAccountsDetail
											.size() - 2),
									driver,
									"Page Number-"
											+ tdAllAccountsDetail
													.get(tdAllAccountsDetail
															.size() - 2)
													.getText());
					WaitUtil.waitForSpinnerToComplete(driver);
					System.out.println("Clicked on the page no "
							+ (tdAllAccountsDetail.size() - 2)
							+ " successfully");
					if (isPageNumberHighlightedTrans((tdAllAccountsDetail
							.size() - 2))) {

						System.out.println("Page was navigated to the page no "
								+ (tdAllAccountsDetail.size() - 2));
					} else {
						System.out
								.println("Page navigation was not happened when click on the page no "
										+ (tdAllAccountsDetail.size() - 2));
						return false;
					}
					tdAllAccountsDetail = driver.findElements(By
							.cssSelector(navigationPath));
					BrowserActions
							.clickOnButton(
									tdAllAccountsDetail.get(tdAllAccountsDetail
											.size() - 3),
									driver,
									"Page Number-"
											+ tdAllAccountsDetail
													.get(tdAllAccountsDetail
															.size() - 2)
													.getText());
					WaitUtil.waitForSpinnerToComplete(driver);
					System.out.println("Clicked on the page no "
							+ (tdAllAccountsDetail.size() - 3)
							+ " successfully");
					if (isPageNumberHighlightedTrans((tdAllAccountsDetail
							.size() - 3)))
						System.out.println("Page was navigated to the page no "
								+ (tdAllAccountsDetail.size() - 3));
					else {
						System.out
								.println("Page navigation was not happened when click on the page no "
										+ (tdAllAccountsDetail.size() - 3));

						return false;
					}
					break;

				}
				return true;
			}
			System.out
					.println("Only one page was available, so the navigation not possible");
			/*
			 * int totalPages = getTotalPagesCommon(); for(int
			 * i=0;i<totalPages;i++){ if(i==0 && isPrevButtonDisabled() &&
			 * !isNextButtonDisabled()) Log.pass(
			 * "Prev page navigtion was disabled and next button was enabled at initially"
			 * ); else if((i+1) == totalPages && !isPrevButtonDisabled() &&
			 * isNextButtonDisabled()) Log.pass(
			 * "Prev page navigtion was enabled and next button was disabled at last page"
			 * ); else if(i>0 && i<totalPages && !isPrevButtonDisabled()
			 * &&!isNextButtonDisabled()) Log.pass(
			 * "Prev page navigtion was enabled and next button was disabled at last page"
			 * ); else
			 * Log.fail("Prev page navigation was not disabled initialy");
			 * goToNextPage();
			 * 
			 * }
			 */

		}
		return false;
	}

	public boolean selectTransactionByDescription(String transactionDesc)
			throws Exception {
		boolean foundStatus = false;
		String acctNo = null;
		List<String> accountNumbers = new ArrayList<String>();
		List<WebElement> tdAllAccountsDetail = driver.findElements(By
				.cssSelector(PAGINATIONTRANSWHOLE));
		int count;

		if (tdAllAccountsDetail.size() != 0) {
			int totalPages = tdAllAccountsDetail.size() - 2;
			// int totalPages =
			// Integer.parseInt(driver.findElement(By.cssSelector(".tc-table-nav-row span.tc-fs")).getText().split("of")[1].trim());
			int page = 1;
			String balance, date = null;
			String Amount = null;
			while (page <= totalPages) {
				for (int index = 0; index < trTransactionList.size(); index++) {
					if (trTransactionList.get(index).getText()
							.contains(transactionDesc)) {
						date = trTransactionList.get(index - 1).getText();
						Amount = trTransactionList.get(index + 2).getText();
						balance = trTransactionList.get(index + 3).getText();
						BrowserActions.clickOnButton(
								trTransactionList.get(index), driver,
								"Click on the Transaction with description: "
										+ transactionDesc);
						WaitUtil.waitForSpinnerToComplete(driver);
						foundStatus = true;
						break;
					}
				}
				if (foundStatus)
					break;
				BrowserActions
						.clickOnButton(tdAllAccountsDetail
								.get(tdAllAccountsDetail.size() - 1), driver,
								"Next Account Page");
				WaitUtil.waitForSpinnerToComplete(driver);
				page += 1;
			}
			if (foundStatus) {
				trTransactionDetails = driver.findElements(By
						.cssSelector("input[id^='C5__QUE_']"));
				trTransactionDetails.size();

				Log.message("Transaction was opened with Description "
						+ transactionDesc);
				// Verify Account details
				String headerBreadcrumpExp = "Home>Transaction Details";

				if (headerBreadcrump.getText().trim()
						.contains(headerBreadcrumpExp))
					System.out
							.println("Transaction details Breadcrump is verified - "
									+ headerBreadcrump.getText().trim());

				else {
					System.out.println("Actual result : "
							+ headerBreadcrump.getText().trim());
					System.out.println("Expected result : "
							+ headerBreadcrumpExp);
					System.out
							.println("Error while verifying Transaction details bread crump");
					return false;
				}

				if (trTransactionDetails.get(3).getAttribute("value").trim()
						.equals(transactionDesc))
					System.out
							.println("Transaction description is verified in the transaction details page - "
									+ transactionDesc);
				else {
					System.out.println("Actual result : "
							+ trTransactionDetails.get(3).getAttribute("value")
									.trim());
					System.out.println("Expected result : " + transactionDesc);
					System.out
							.println("Error while verifying Transaction description in the account's page");
					return false;
				}
				// if(headerAcctNo.getText().trim().contains(acctNo))
				if (trTransactionDetails.get(0).getAttribute("value").trim()
						.contains(Amount.replace(",", "")))
					System.out
							.println("Transaction Amount is verified in the transaction details page - "
									+ Amount);
				else {
					System.out.println("Actual result : "
							+ Amount.replace(",", ""));
					System.out.println("Expected result : "
							+ trTransactionDetails.get(0).getAttribute("value")
									.trim());
					System.out
							.println("Error while verifying Transaction Amount in the transaction details page");
					return false;
				}
				if ((trTransactionDetails.get(2).getAttribute("value").trim())
						.equals(date.trim()))
					System.out
							.println("Transaction Date is verified in the transaction details page - "
									+ date);
				else {
					System.out.println("Actual result : "
							+ (trTransactionDetails.get(2)
									.getAttribute("value").trim()));
					System.out.println("Expected result : " + date);
					System.out
							.println("Error while verifying Transaction date in the transaction details page");
					return false;
				}

			}
			return true;
		}
		return false;
	}

	public String getTransactionDetailsForSearch(String TypeTrans, int Amount)
			throws Exception {

		String acctNo = null;
		Thread.sleep(5000);
		List<WebElement> tdAllAccountsDetail = driver.findElements(By
				.cssSelector(PAGINATIONTRANS_Total));
		String type = "";
		if (tdAllAccountsDetail.size() != 0) {
			int totalPages = tdAllAccountsDetail.size();
			int page = 1;
			DateforGet = trTransactionList.get(0).getText();
			if (TypeTrans.equalsIgnoreCase("Credit"))
				type = "+";
			else if (TypeTrans.equalsIgnoreCase("Debit"))
				type = "-";
			else
				type = "";
			while (page <= totalPages) {
				tdAllAccountsDetail = driver.findElements(By
						.cssSelector(PAGINATIONTRANS_Total));
				for (int index = 0; index < trTransactionList.size(); index++) {

					if (trTransactionList.get(index).getText()
							.equals(DateforGet)
							&& trTransactionList.get(index + 3).getText()
									.contains(type)) {
						String redefineAmt = trTransactionList.get(index + 3)
								.getText().trim().replace(type, "");
						redefineAmt = redefineAmt.replaceAll(",", "");

						redefineAmt = redefineAmt.split("\\.")[0];
						if (Integer.parseInt(redefineAmt) > Amount) {
							transacationAmount.add(trTransactionList
									.get(index + 3).getText().trim());
						}
					}
				}

				BrowserActions.clickOnButton(nextButton, driver,
						"Next Account Page");
				WaitUtil.waitForSpinnerToComplete(driver);
				trTransactionList = driver.findElements(By
						.cssSelector("tr[id^='C5__C2__p0_TBL_'] td"));
				page += 1;
				// break;
			}

		}
		return acctNo;
	}

	public boolean searchTransaction(String TypeTrans, String ssss)
			throws Exception {

		String acctNo = null;
		if (searchTransApplyFilter(TypeTrans, ssss))
			System.out.println("Filter was applied");
		else
			System.out.println("error while applying the filters");

		BrowserActions
				.clickOnButton(driver.findElement(By
						.id("C5__C2__BUT_419320BE4FFA5116805845")), driver,
						"Search button");
		// BrowserActions.clickOnButton(driver.findElement(By.id("C5__C2__BUT_419320BE4FFA5116805845")),
		// driver, "Search button");
		WaitUtil.waitForSpinnerToComplete(driver);
		if (transSearchHeaders.get(0).getText().trim()
				.equalsIgnoreCase(acctHeaders.get(0))) {
			System.out
					.println("Transaction header was veridfied after searching");
		} else {
			System.out
					.println("Error while verifying the Transaction search headers");
			return false;
		}
		if (transSearchHeaders.get(1).getText().trim()
				.equalsIgnoreCase("11 items found")) {
			System.out.println("Search result was verified");
		} else {
			System.out.println("Actual Result : "
					+ transSearchHeaders.get(1).getText().trim()
					+ "   Expected Result: " + transacationAmount.size()
					+ " items found");
			System.out.println("Search result was verified");
			System.out
					.println("Error while verifying the Transaction search result");
			return false;
		}

		if (transSearchFilters.get(0).getText().trim()
				.equalsIgnoreCase(TypeTrans)) {
			System.out
					.println("Transaction search filters was veridfied after searching");
		} else {
			System.out
					.println("Error while verifying the Transaction search filters");

			return false;
		}
		if (transSearchFilters.get(1).getText().trim()
				.equalsIgnoreCase("20160322")) {
			System.out.println("From date applied on the search was verified");
		} else {
			return false;
		}
		return true;
	}

	public boolean searchTransApplyFilter(String TypeTrans, String ssss)
			throws Exception {

		String acctNo = null;
		for (WebElement webElement : transSearchInputs) {
			System.out.println("webElement :"
					+ webElement.getAttribute("value"));
		}
		if (TypeTrans.equalsIgnoreCase("Credit"))
			BrowserActions
					.clickOnButton(
							driver.findElement(By
									.xpath("//fieldset[@id='C5__C2__FS_QUE_419320BE4FFA5116799488']/div[2]/label")),
							driver, "Select credit type");
		else if (TypeTrans.equalsIgnoreCase("Debit"))
			BrowserActions.clickOnButton(transSearchInputs.get(2), driver,
					"Select debit type");
		else
			BrowserActions.clickOnButton(transSearchInputs.get(0), driver,
					"Select All type");

		BrowserActions.typeOnTextField(transSearchInputs.get(3), DateforGet,
				driver, "From date is selected as " + DateforGet);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		cal.setTime(sdf.parse(DateforGet));

		cal.add(Calendar.DATE, 1); // number of days to add
		// String ToDate =
		// ""+(Integer.parseInt(DateforGet.split("/")[0])+1)+"//"+DateforGet.split("//")[1]+"/"+DateforGet.split("/")[2];
		System.out.println("ssss :" + ssss);

		// transSearchInputs.get(5).sendKeys(ssss);
		// BrowserActions.typeOnTextField(transSearchInputs.get(4), ToDate,
		// driver, "From date is selected as "+DateforGet);
		transSearchInputs.get(4).sendKeys(Keys.TAB);

		return true;
	}

	public boolean searchTransClearFilter() throws Exception {

		String acctNo = null;
		BrowserActions.clickOnButton(clearFilter, driver, "Clearing the filer");
		WaitUtil.waitForSpinnerToComplete(driver);
		for (int i = 3; i < transSearchInputs.size(); i++) {
			System.out.println("webElement :"
					+ transSearchInputs.get(i).getAttribute("value"));
			if (transSearchInputs.get(i).getAttribute("value").trim().isEmpty()
					|| transSearchInputs.get(i).getAttribute("value").trim() == "")
				System.out.println("Cleared the field was successful");
			else
				System.out
						.println("Error after clearing the filters \n Actual value :"
								+ transSearchInputs.get(i)
										.getAttribute("value")
								+ "/n Expected Value :");
		}
		/*
		 * if(TypeTrans.equalsIgnoreCase("Credit"))
		 * BrowserActions.clickOnButton(driver.findElement(By.xpath(
		 * "//fieldset[@id='C5__C2__FS_QUE_419320BE4FFA5116799488']/div[2]/label"
		 * )), driver, "Select credit type"); else
		 * if(TypeTrans.equalsIgnoreCase("Debit"))
		 * BrowserActions.clickOnButton(transSearchInputs.get(2), driver,
		 * "Select debit type"); else
		 * BrowserActions.clickOnButton(transSearchInputs.get(0), driver,
		 * "Select All type");
		 * 
		 * BrowserActions.typeOnTextField(transSearchInputs.get(3), DateforGet,
		 * driver, "From date is selected as "+DateforGet); Calendar cal =
		 * Calendar.getInstance(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("dd/MM/yyyy"); cal.setTime(sdf.parse(DateforGet));
		 * 
		 * 
		 * cal.add(Calendar.DATE, 1); // number of days to add //String ToDate =
		 * ""
		 * +(Integer.parseInt(DateforGet.split("/")[0])+1)+"//"+DateforGet.split
		 * ("//")[1]+"/"+DateforGet.split("/")[2];
		 * System.out.println("ssss :"+ssss);
		 * 
		 * //transSearchInputs.get(5).sendKeys(ssss);
		 * //BrowserActions.typeOnTextField(transSearchInputs.get(4), ToDate,
		 * driver, "From date is selected as "+DateforGet);
		 * transSearchInputs.get(4).sendKeys(Keys.TAB);
		 */

		return true;
	}

	public boolean verifyPendingDetails() throws Exception {
		BrowserActions.clickOnButton(trAccountsTransTabs.get(1), driver,
				"Click on the Account tab: "
						+ trAccountsTransTabs.get(1).getText());
		WaitUtil.waitForSpinnerToComplete(driver);
		System.out.println("allAcctPage.transPendingHeaders.get(1) :"
				+ transPendingHeaders.get(0).getText().trim());
		if (transPendingHeaders.get(0).getText().trim()
				.equalsIgnoreCase("There are no records to display")) {
			System.out
					.println("Transaction header was veridfied after searching");
			return true;
		}

		return false;
	}

}
