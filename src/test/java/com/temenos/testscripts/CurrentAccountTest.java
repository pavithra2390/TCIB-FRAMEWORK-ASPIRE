package com.temenos.testscripts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
//import com.temenos.pages.AccountsPage;
import com.temenos.pages.AllAccountsTab;
//import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.Utils;
import com.temenos.support.WebDriverFactory;
//import com.temenos.pages.TransactionList;
//import com.temenos.pages.TransactionsTab.PendingTab;

public class CurrentAccountTest extends BaseTest {
	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("CurrentAccount");
	}

	@Test(description = "Verify the accounts wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_001(String browser) {
		Log.testCaseInfo("Verify the accounts wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		AccountsPage accountPage = null;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_001");

		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		String accountNumber = values.get("AccountNumber");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountPage = allAcctPage.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verify the Savings account wizards conatins 'Transactions', 'Transfers', 'Payments', 'Standing Orders', 'Direct debits', 'Cheques', 'Alerts'");
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedTabs, accountPage,
							driver),
					"Selected Savings Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
							+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Savings Account page is not having with 7 tabs.",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the tabs switching in the selected account page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_002(String browser) {
		Log.testCaseInfo("Verify the tabs switching in the selected account page");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_002");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify whether able to Switch between the tabs in the selected account page");
			Log.softAssertThat(
					allAcctPage.verifyTabAccountPage(7),
					"Successfully able to switch between the tabs in Account Page.",
					"Not able to switch between the tabs", driver);
		}

		catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Show more and Show less option in the Accounts wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_003(String browser) {
		Log.testCaseInfo("Verify the Show more and Show less option in the Accounts wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_003");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			// homePage = new HomePage(driver).get(); // first time driver will
			// be passed
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected :Verify the Show more and Show less option in the Accounts wizard</b>");
			Log.softAssertThat(
					allAcctPage.verifyShowLessAcctDetails(),
					"Show more/less option in account wizard is working as expected",
					"Show more/less option in account wizard is working is not working",
					driver);

			// allAcctPage.verifyTabAccountPage(7);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the tabs variations for loans", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_004(String browser) {
		Log.testCaseInfo("Verify the tabs variations for loans");

		WebDriver driver = null;
		LoginPage loginPage = null;
		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_004");
		List<String> expectedTabs = Arrays.asList("Transactions", "Schedule",
				"PayOverdue");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to loan account", driver);
			Log.message("<b>Expected :Verify the tabs variations for loans</b>");
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedTabs, allAcctPage,
							driver),
					"Successfully able to view Transaction,Schedule and Pay Overdue Tabs in Loan Account",
					"Not able to find the wizards of loan account", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the transactions tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_005(String browser) {
		Log.testCaseInfo("Verify the transactions tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_005");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			// homePage = new HomePage(driver).get(); // first time driver will
			// be passed
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify the tabs available in transaction tab");
			Log.softAssertThat(
					allAcctPage.verifyTabTransactionPage(values
							.get("account_type")),
					"Complete and search tabs are available in Transaction",
					"Complete and search tabs are not available in Transaction",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the tab switching in the transactions wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_006(String browser) {
		Log.testCaseInfo("Verify the tab switching in the transactions wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_006");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify the tabs Switching in transaction tab");
			Log.softAssertThat(
					allAcctPage.verifyTabTransactionPage(values
							.get("account_type")),
					"Successfully able to navigate to the available tabs in transaction",
					"Not able to navigate to tabs in Transaction ", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Show options button in the transactions wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_007(String browser) {
		Log.testCaseInfo("Verify the Show options button in the transactions wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_007");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify the Show Option in Transaction Wizard");
			Log.softAssertThat(allAcctPage.verifyShowOption(),
					"Show option in Transaction Wizard is working as expected",
					"Show option in transaction wizard is not working ", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the print option in the transaction wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_008(String browser) {
		Log.testCaseInfo("Verify the print option in the transaction wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_008");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			// homePage = new HomePage(driver).get(); // first time driver will
			// be passed
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			allAcctPage.verifyShowOption();
			Log.message("<b>Expected : Verify the print option in the transaction wizard");
			Log.softAssertThat(
					allAcctPage.verifyShowOptionFunction(
							values.get("print_option"), accountNumber),
					"Pdf downloand and csv download option available in show option - Transaction Wizard",
					"Pdf downloand and csv download option is not available in show option - Transaction Wizard ",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the PDF download option in the transaction wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_009(String browser) {
		Log.testCaseInfo("Verify the PDF download option in the transaction wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_009");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Utils.deleteFiles();
			allAcctPage.verifyShowOption();
			allAcctPage.verifyShowOptionFunction(values.get("print_option"),
					accountNumber);
			Log.message("<b>Expected : Verify the PDF download option in the transaction wizard");
			Log.softAssertThat(
					Utils.checkFile("pdf"),
					"Successfully able to download in pdf in th transaction wizard",
					"Not able to download in pdf in th transaction wizard",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the CSV download option in the transaction wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_010(String browser) {
		Log.testCaseInfo("Verify the CSV download option in the transaction wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_010");

		try {
			driver = WebDriverFactory.get(browser);

			Log.message("Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			// homePage = new HomePage(driver).get(); // first time driver will
			// be passed
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Utils.deleteFiles();
			allAcctPage.verifyShowOption();
			allAcctPage.verifyShowOptionFunction(values.get("print_option"),
					accountNumber);
			Log.message("<b>Expected : Verify the csv download option in the transaction wizard");
			Log.softAssertThat(
					Utils.checkFile("csv"),
					"Successfully able to download in csv in the transaction wizard",
					"Not able to download in csv in the transaction wizard",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Sorting option in the completed tab of transaction wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_011(String browser) {
		Log.testCaseInfo("Verify the Sorting option in the completed tab of transaction wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_011");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			// homePage = new HomePage(driver).get(); // first time driver will
			// be passed
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify the Sorting option in the completed tab of transaction wizard</b>");
			Log.softAssertThat(
					allAcctPage.verifyTabAccountTransaction(),
					"Successfully able to sort in completed tab of transaction wizard",
					"Not able to sort in completed tab of transaction wizard",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Next button in the completed tab of transaction wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_012(String browser) {
		Log.testCaseInfo("Verify the Next button in the completed tab of transaction wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_012");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify the Next button in the completed tab of transaction wizard");
			Log.softAssertThat(
					allAcctPage.verifyPaginationCommon(
							values.get("Navigation_Tool_Path"), "Next"),
					"Successfully clicked on Next button in the completed tab of transaction wizard",
					"Not able to click on Next button in the completed tab of transaction wizard",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Previous button in the completed tab of transaction wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_013(String browser) {
		Log.testCaseInfo("Verify the Previous button in the completed tab of transaction wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_013");

		String accoNumber = values.get("AccountNumber");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			/*
			 * accountNumber = allAcctPage.selectAnyAccountofType(values
			 * .get("account_type"));
			 */
			allAcctPage.clickOnAccount(accoNumber);
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify the Previous button and Page No in the completed tab of transaction wizard");
			Log.softAssertThat(
					(allAcctPage.verifyPaginationCommon(
							values.get("Navigation_Tool_Path"), "Previous") && allAcctPage
							.verifyPaginationCommon(
									values.get("Navigation_Tool_Path"),
									"Page No")),
					"Successfully clicked on Previous and Page no button in the completed tab of transaction wizard",
					"Not able to click on Previous and Page no button in the completed tab of transaction wizard",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the transaction details are displayed  ", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_014(String browser) {
		Log.testCaseInfo("Verify whether the transaction details are displayed  ");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_014");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			Log.message("<b>Expected : Verify whether the transaction details are displayed </b>");
			Log.softAssertThat(allAcctPage
					.selectTransactionByDescription(values
							.get("Transaction_Description")),
					"Transaction details are displayed correctly",
					"Transaction details are not displayed correctly", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the pending details are displayed", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_015(String browser) {
		Log.testCaseInfo("Verify whether the pending details are displayed");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_015");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			// homePage = new HomePage(driver).get(); // first time driver will
			// be passed
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			allAcctPage.verifyTabTransactionPage(values.get("account_type"));
			Log.message("Verified Transaction Page");
			Log.message("<b>Expected :Verify whether the pending details are displayed");

			Log.softAssertThat(allAcctPage.verifyPendingDetails(),
					"The Pending details are displayed correctly",
					"The pending details are not displayed", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the searching of the account details can be done", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_016(String browser) {
		Log.testCaseInfo("Verify whether the searching of the account details can be done");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_016");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			allAcctPage.getTransactionDetailsForSearch(
					values.get("Credit/Debit"),
					Integer.parseInt(values.get("To date")));
			allAcctPage.verifyTabTransactionPage(values.get("account_type"));
			Log.message("<b>Expected : Verify whether the searching of the account details can be done ");
			Log.softAssertThat(allAcctPage.searchTransaction(
					values.get("Credit/Debit"), values.get("To date")),
					"Successfully able to search the account details",
					"Not able to search the account details", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the fields can be cleared", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CURRENTACCOUNT_017(String browser) {
		Log.testCaseInfo("Verify whether the fields can be cleared");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;
		HashMap<String, String> values = tde
				.readData("TC_TCIB_CURRENTACCOUNT_017");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Account details", driver);
			allAcctPage.getTransactionDetailsForSearch(
					values.get("Credit/Debit"),
					Integer.parseInt(values.get("To date")));
			allAcctPage.verifyTabTransactionPage(values.get("account_type"));
			Log.message("Verified the Tabs in Transaction page");
			allAcctPage.searchTransApplyFilter(values.get("Credit/Debit"),
					values.get("To date"));
			Log.message("Applied Search filter in transaction Page");
			Log.message("<b>Expected : Verify whether the searching of the account details can be done ");
			Log.softAssertThat(allAcctPage.searchTransClearFilter(),
					"Successfully able to search the account details",
					"Not able to search the account details", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}
}
