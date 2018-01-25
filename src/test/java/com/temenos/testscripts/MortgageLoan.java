package com.temenos.testscripts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.PayOverdueTab;
import com.temenos.pages.ScheduleTab;
import com.temenos.pages.TransactionsTab;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WebDriverFactory;

public class MortgageLoan extends BaseTest {
	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("MortgageLoan");
	}

	@Test(description = "Verify user can view the loan details on clicking the loan", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_001(String browser) {
		Log.testCaseInfo("Verify user can view the loan details on clicking the loan");
		HashMap<String, String> values = tde
				.readData("TC_TCIB_MortgageLoan_001");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNo = values.get("account_number");
		List<String> expectedElements = Arrays.asList("tabAllAccounts",
				"tabFavorites", "tabQuickTransfers");
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			Log.message("<b>Expected : The All Accounts, Favorites and Quick transfer tabs should be displayed in the page.</b>");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedElements, homePage,
							driver),
					"The All Accounts, Favorites and Quick transfer tabs are displayed in the page.",
					"The All Accounts, Favorites and Quick transfer tabs not displayed in the page.",
					driver);

			allAccountsTab = homePage.goToAllAccountsTab();
			List<String> expectedElementMortgage = Arrays.asList(
					"tabTransactions", "tabSchedule", "tabPayOverdue");

			accountPage = allAccountsTab.clickOnAccount(accountNo);

			Log.message("<b>Expected :The Mortgage account\n</b>" + accountNo
					+ "<b>should be displayed under all accounts list.</b>");

			Log.softAssertThat((Object.class.isInstance(accountPage)),
					"The Mortgage loan account number \n:." + accountNo
							+ "\nis displayed and clicked",
					"The Mortgage Loan account number \n" + accountNo
							+ "\nis not displayed and clicked.", driver);
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedElementMortgage,
							accountPage, driver),
					"The Transactions, Schedules and pay over due tabs are displayed in the page.",
					"The Transactions, Schedules and pay over due tabs not displayed in the page.",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can view the completed loan Transaction", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_002(String browser) {
		Log.testCaseInfo("Verify user can view the completed loan Transaction");
		boolean flag = false;
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountType = "Mortgage Loan";
		HashMap<String, String> values = tde
				.readData("TC_TCIB_MortgageLoan_001");
		String accountNo = values.get("account_number");

		List<String> expectedElements = Arrays.asList("tabCompleted",
				"tabSearch");
		List<String> columns = Arrays.asList("Date", "Description",
				"Amount ($)", "Outstanding ($)");
		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			TransactionsTab transactionsTab = homePage.goToAllAccountsTab()
					.clickOnAccount(accountNo).goToTransactionsTab();
			Log.message("<b>Expected : The tabs completed, Search should be displayed.</b>");
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedElements,
							transactionsTab, driver),
					"The tabs completed, Search are displayed in the page.",
					"The tabs completed, Search are not displayed in the page.",
					driver);

			String[] names = transactionsTab.clickOnCompletedTab()
					.getColumnNames();

			Log.message("<b>Expected : The columns " + columns
					+ "\n should be displayed in the page.</b>");

			for (int i = 0; i < columns.size() - 1; i++) {
				if (columns.get(i).equals(names[i])) {
					flag = true;
				}
			}
			Log.softAssertThat(flag, "The columns \n" + columns
					+ "\nare displayed", "The columns \n" + columns
					+ "\nare not displayed.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can view the transaction details of completed transaction", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_003(String browser) {
		Log.testCaseInfo("Verify user can view the transaction details of completed transaction");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_MortgageLoan_001");
		String accountNo = values.get("account_number");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			String title = homePage.goToAllAccountsTab()
					.clickOnAccount(accountNo).goToTransactionsTab()
					.clickOnCompletedTab().goToTransactionDetails()
					.getOverViewTitle();
			Log.message("<b>Expected : The user should able to view the transaction details completed transaction</b>");
			Log.softAssertThat(
					title.trim().equals("Overview"),
					"The user is able to view the transaction details of completed transaction",
					"The user is not able to view the transaction details of completed transaction",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can add the note to a transaction", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_004(String browser) {
		Log.testCaseInfo("Verify user can add note to a transaction");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_MortgageLoan_001");
		String accountNo = values.get("account_number");

		String message = "Note for the test case 004";
		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();

			TransactionsTab.TransactionDetails transactionDetails = homePage
					.goToAllAccountsTab().clickOnAccount(accountNo)
					.goToTransactionsTab().clickOnCompletedTab()
					.goToTransactionDetails().addNote(message);
			Log.message("The note has been added successfully");
			String addedMessage = transactionDetails.goBack()
					.clickOnCompletedTab().goToTransactionDetails()
					.getNoteMessage();
			Log.message("<b>Expected : The user should able to add the note "
					+ message + "for the selected transaction</b>");
			Log.softAssertThat(
					addedMessage.trim().equals(message),
					"The note "
							+ message
							+ "\n has been added successfully to the transaction",
					"The note " + message + "\nis not added to the transaction",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can delete note to a transaction", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_012(String browser) {
		Log.testCaseInfo("Verify user can delete note to a transaction");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_MortgageLoan_001");
		String accountNo = values.get("account_number");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			TransactionsTab.TransactionDetails transactionDetails = homePage
					.goToAllAccountsTab().clickOnAccount(accountNo)
					.goToTransactionsTab().clickOnCompletedTab()
					.goToTransactionDetails().deleteNote();
			Log.message("The note has been deleted successfully");
			Log.message("<b>Expected: The user should able to delete the note added for the transaction.</b>");
			Log.softAssertThat(transactionDetails.verifyNoteText(),
					"The note for the transaction in loan account\n"
							+ accountNo + "\nis deleted.",
					"The note for the transaction in \n" + accountNo
							+ "\nis not deleted.", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "verify user can view the schedule of paid installments", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_005(String browser) {
		Log.testCaseInfo("verify user can view the schedule of paid installments");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_MortgageLoan_001");
		String accountNo = values.get("account_number");
		List<String> columns = Arrays.asList("Payment Date", "Amount ($)",
				"Outstanding Amount ($)");
		boolean flag = false;
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			ScheduleTab scheduleTab = homePage.goToAllAccountsTab()
					.clickOnAccount(accountNo).goToScheduleTab();

			String installments = scheduleTab.getPaidInstallments();
			Log.message("The no of paid installments are : " + installments);
			Log.message("<b>Expected: The user should be able to see the no of installments paid by customer.</b>");
			Log.softAssertThat(installments != null,
					"The user is able to see the no of installments as \n"
							+ installments + "\npaid by customer",
					"The user is not able to see the installments.", driver);

			String[] names = scheduleTab.clickPaidInstallments()
					.getColumnNamesofPaidInstallments();
			Log.message("<b>Expected : The user should view the schedule of paid installments with "
					+ columns + "\n should be displayed in the page.</b>");

			for (int i = 0; i < columns.size() - 1; i++) {
				if (columns.get(i).equals(names[i])) {
					flag = true;
				}
			}
			Log.softAssertThat(flag,
					"The user is able to view the schedule of paid installments with \n"
							+ columns + "\n.",
					"The user is not able to view the schedule of paid installments with \n"
							+ columns + "\n.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "verify user can view the schedule of future installments", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_006(String browser) {
		Log.testCaseInfo("verify user can view the schedule of future installments");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountType = "Mortgage Loan";
		String message = "Mortgage Loan 801245";
		List<String> columns = Arrays.asList("Payment Date", "Amount ($)",
				"Outstanding Amount ($)");
		boolean flag = false;

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			ScheduleTab scheduleTab = homePage.goToAllAccountsTab()
					.clickOnAccount(accountType).goToScheduleTab();

			Log.message("The no of paid installments are : "
					+ scheduleTab.getFuturePayments());

			String[] names = scheduleTab.clickFuturePayments()
					.getColumnNamesofFuturePayments();

			Log.message("<b>Expected : The user should view the future installments with "
					+ columns + "\n in the page.</b>");

			for (int i = 0; i < columns.size() - 1; i++) {
				if (columns.get(i).equals(names[i])) {
					flag = true;
				}
			}
			Log.softAssertThat(flag,
					"The user should view the future installments with \n"
							+ columns + "\n.",
					"The user should view the future installments with \n"
							+ columns + "\n.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "verify user can view the schedule of overdue installments", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_007(String browser) {
		Log.testCaseInfo("verify user can view the schedule of overdue installments");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountType = "Mortgage Loan";
		List<String> columns = Arrays.asList("Payment Date", "Amount ($)",
				"Outstanding Amount ($)");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			ScheduleTab scheduleTab = homePage.goToAllAccountsTab()
					.clickOnAccount(accountType).goToScheduleTab();

			Log.message("The no of paid installments are : "
					+ scheduleTab.getOverDuePayments());
			boolean flag = false;
			String[] names = scheduleTab.clickOverDuePayments()
					.getColumnNamesofOverdue();
			Log.message("<b>Expected : The user should see the schedule as\n "
					+ columns + "\n in the page.</b>");

			for (int i = 0; i < columns.size() - 1; i++) {
				if (columns.get(i).equals(names[i])) {
					flag = true;
				}
			}
			Log.softAssertThat(flag,
					"The user able to see the schedule with  \n" + columns
							+ "\n",
					"The user cannot able to see the schedules with \n"
							+ columns + "\nare not displayed.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the pay overdue tab appears when the loan has overdue installment", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_008(String browser) {
		Log.testCaseInfo("Verify whether the pay overdue tab appears when the loan has overdue installment");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		String accountType = "Mortgage Loan";
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			AccountsPage accountsPage = homePage.goToAllAccountsTab()
					.clickOnAccount(accountType);
			Log.message("<b>Expected: The pay overdue tab should be appeared when the loan has overdue installment</b>");
			Log.softAssertThat(
					accountsPage.getPayOverdueTitle().trim()
							.equals("Pay Overdue"),
					"The PayOverDue tab is displayed if the loan has the overdue installment!",
					"The PayOverDue tab is not displayed if the loan has the overdue installment!",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can make a overdue payment", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_009(String browser) {
		Log.testCaseInfo("Verify user can make a overdue payment");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		String accountType = "Mortgage Loan";
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			PayOverdueTab payOverdueTab = homePage.goToAllAccountsTab()
					.clickOnAccount(accountType).goToPayOverDueTab();

			String ref = payOverdueTab.payAmount().getTransactionReference();
			Log.message("<b>Expected: The user should be allowed to pay the overdue amount.</b>");
			Log.softAssertThat(ref.contains("Transaction reference"),
					"The payment has been made successfully!",
					"The payment is failed!", driver);

			Log.message(ref);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}

	}

	@Test(description = "Verify whether the overdue installments becomes zero on paying the overdue", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_010(String browser) {
		Log.testCaseInfo("Verify whether the overdue installments becomes zero on paying the overdue");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		String accountType = "Mortgage Loan";

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			PayOverdueTab payOverdueTab = homePage.goToAllAccountsTab()
					.clickOnAccount(accountType).goToPayOverDueTab();

			String ref = payOverdueTab.payAmount().getTransactionReference();
			Log.message("<b>Expected: The customer should be able to pay the overdue payment..</b>");
			Log.softAssertThat(ref.contains("Transaction reference"),
					"The payment has been made successfully!",
					"The payment is failed!", driver);

			payOverdueTab.back();
			Log.message("<b>Expected: The overdue amount should be zero after the payment made by the customer.</b>");
			Log.softAssertThat(
					payOverdueTab.getTotalOverDue().trim().equals("$0.00"),
					"The overdue amount is zero!",
					"Overdue amount is not Zero!", driver);
			Log.message(ref);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify 'Show More' and 'show Less” link in the page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_MortgageLoan_011(String browser) {
		Log.testCaseInfo("Verify 'Show More' and 'show Less' link in the page");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		String accountType = "Mortgage Loan";
		String message = "Mortgage Loan 801245";
		List<String> columns = Arrays.asList("Principal (Due)",
				"Principal (Delinquent)", "Principal Interest (Due)",
				"Principal Interest (Delinquent)",
				"Penalty Interest (Accrued)", "New Arrangement Fee (Due)",
				"Tax Amount (Due)", "Tax Amount (Delinquent)");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			boolean flag = false;
			homePage = new HomePage(driver).get();
			AccountsPage accountsPage = homePage.goToAllAccountsTab()
					.clickOnAccount(accountType).clickShowMoreLess();
			String[] namesofOverdue = accountsPage.getColumnNamesofOverdue();
			Log.message("<b>Expected : When the user click on 'ShowMore' the details of loan account with\n"
					+ columns + "\n should be displayed in the page.</b>");

			for (int i = 0; i < columns.size() - 1; i++) {
				if (columns.get(i).equals(namesofOverdue[i])) {
					flag = true;
				}
			}
			Log.softAssertThat(flag, "The details of loan account with \n"
					+ columns + "\nare displayed",
					"The details of loan account with columns \n" + columns
							+ "\nare not displayed.", driver);

			accountsPage.clickShowMoreLess();
			Log.message("<b>Expected : When the user click on 'ShowLess' the details of loan account with columns"
					+ columns + "\n should not be displayed in the page.</b>");
			flag = false;
			String[] namesCollapse = accountsPage.getColumnNamesofOverdue();
			for (int i = 0; i < columns.size() - 1; i++) {
				if (!columns.get(i).equals(namesCollapse[i])) {
					flag = true;
				}
			}
			Log.softAssertThat(flag, "The details of loan account with \n"
					+ columns + "\nare not displayed",
					"The details of loan account with \n" + columns
							+ "\nare displayed.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
}