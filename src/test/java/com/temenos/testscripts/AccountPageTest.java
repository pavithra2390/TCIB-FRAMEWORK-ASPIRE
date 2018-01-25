package com.temenos.testscripts;

import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WebDriverFactory;

public class AccountPageTest extends BaseTest {

	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("AccountDetails");
	}

	@Test(description = "Verify Current Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_AccountPage_001(String browser) throws Exception {
		Log.testCaseInfo("Verify Current Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage accountPage = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_AccountPage_001");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			accountPage = homePage.goToAllAccountsTab().clickOnAccount(
					values.get("account_number"));

			Log.message("<b>Expected : Verifying the account details are correctly displayed..</b>");

			Log.softAssertThat(
					accountPage.getAccountNumber().contains(
							values.get("account_number")),
					"Account number is matching!",
					"Account number is not matching!");

			Log.softAssertThat(
					values.get("account_type").equals(
							accountPage.getAccountType()),
					"Account Type is matching!",
					"Account Type is not matching!", driver);

			Log.message("<b>Expected : Verify whether the following tabs [Transactions, Transfers, Payments, Standing Orders, Direct Debits, "
					+ "Cheques, Alerts] are displayed for Current Account.</b>");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList(
							"tabTransactions", "tabTransfers", "tabPayments",
							"tabStandingOrders", "tabDirectDebts",
							"tabCheques", "tabAlerts"), accountPage, driver),
					"All tabs are displayed!", "Some tabs are not displayed!",
					driver);
		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify Current Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AccountPage_002(String browser) {
		Log.testCaseInfo("Verify Current Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_AccountPage_002");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountNumber = allAccountsTab.getAnyAccountNumberOfType(values
					.get("account_type"));
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed..</b>");

			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!");

			Log.softAssertThat(
					values.get("account_type").equals(
							accountPage.getAccountType()),
					"Account Type is matching!",
					"Account Type is not matching!");

			Log.message("<b>Expected : Verify whether the following tabs [Transactions, Transfers, Payments, Standing Orders, "
					+ "Direct Debits, Cheques, Alerts] are displayed for Current Account.</b>");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList(
							"tabTransactions", "tabTransfers", "tabPayments",
							"tabStandingOrders", "tabDirectDebts",
							"tabCheques", "tabAlerts"), accountPage, driver),
					"All tabs are displayed!", "Some tabs are not displayed!",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
 
	@Test(description = "Verify Savings Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AccountPage_003(String browser) throws Exception {
		Log.testCaseInfo("Verify Savings Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_AccountPage_003");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountNumber = allAccountsTab.getAnyAccountNumberOfType(values
					.get("account_type"));
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed..</b>");

			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);

			Log.softAssertThat(
					values.get("account_type").equals(
							accountPage.getAccountType()),
					"Account Type is matching!",
					"Account Type is not matching!", driver);

			Log.message("<b>Expected : Verify whether the following tabs [Transactions, Transfers, Payments, Standing Orders,"
					+ " Direct Debits, Cheques, Alerts] are displayed for Savings Account..</b>");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList(
							"tabTransactions", "tabTransfers", "tabPayments",
							"tabStandingOrders", "tabDirectDebts",
							"tabCheques", "tabAlerts"), accountPage, driver),
					"All tabs are displayed!", "Some tabs are not displayed!",
					driver);
		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Mortgage Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AccountPage_004(String browser) throws Exception {
		Log.testCaseInfo("Verify Mortgage Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_AccountPage_004");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountNumber = allAccountsTab.getAnyAccountNumberOfType(values
					.get("account_type"));
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed..</b>");

			Log.softAssertThat(
					accountPage.getMortgageAccountNumber().contains(
							accountNumber), "Account number is matching!",
					"Account number is not matching!", driver);

			Log.message("<b>Expected : Verify whether the following tabs [Transactions, Schedule, Pay Overdue] are displayed for Morgage Account..</b>");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList(
							"tabTransactions", "tabSchedule", "tabPayOverdue"),
							accountPage, driver),
					"Transaction, Schedule and Pay OverDue tabs are displayed!",
					"Transaction, Schedule and Pay OverDue tabs are not displayed!");

			Log.message("<b>Expected : Verify whether the following tabs [Transfers, Payments, Standing Orders, Direct Debits, Cheques, Alerts]"
					+ " are not displayed for Mortgage Account..</b>");

			Log.softAssertThat(
					verifyPageElementsAreNotDisplayed(Arrays.asList(
							"tabTransfers", "tabPayments", "tabStandingOrders",
							"tabDirectDebts", "tabCheques", "tabAlerts"),
							accountPage, driver),
					"Transfers, Payment, Standing Order, Direct Debts, Cheques, Alerts tabs are not displayed!",
					"Transfers, Payment, Standing Order, Direct Debts, Cheques, Alerts tabs are displayed!",
					driver);
		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify Mortgage Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AccountPage_005(String browser) {
		Log.testCaseInfo("Verify Mortgage Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_AccountPage_005");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(values
					.get("account_number"));
			Log.message("<b>Expected : Verifying the account details are correctly displayed..</b>");

			Log.softAssertThat(
					accountPage.getMortgageAccountNumber().contains(
							values.get("account_number")),
					"Account number is matching!",
					"Account number is not matching!", driver);

			Log.message("<b>Expected : Verify whether the following tabs [Transactions, Schedule, Pay Overdue] are displayed for Mortgage Account..</b>");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList(
							"tabTransactions", "tabSchedule", "tabPayOverdue"),
							accountPage, driver),
					"Transaction, Schedule and Pay OverDue tabs are displaying",
					"Transaction, Schedule and Pay OverDue tabs are not displaying",
					driver);

			Log.message("<b>Expected : Verify whether the following tabs [Transfers, Payments, Standing Orders, Direct Debits, Cheques, Alerts] "
					+ "are not displayed for Mortgage Account..</b>");

			Log.softAssertThat(
					verifyPageElementsAreNotDisplayed(Arrays.asList(
							"tabTransfers", "tabPayments", "tabStandingOrders",
							"tabDirectDebts", "tabCheques", "tabAlerts"),
							accountPage, driver),
					"Transfers, Payment, Standing Order, Direct Debts, Cheques, Alerts tabs are not displaying",
					"Transfers, Payment, Standing Order, Direct Debts, Cheques, Alerts tabs are displaying",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
}
