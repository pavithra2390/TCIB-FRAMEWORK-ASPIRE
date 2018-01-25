package com.temenos.testscripts;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.TransactionList;
import com.temenos.pojo.Transaction;
import com.temenos.support.BrowserActions;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WebDriverFactory;

public class AllAccounts_RecentTransactionTest extends BaseTest {
	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("Transfers");
	}

	@Test(description = "Verify all columns are displayed.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AccountPageRecentTransaction_001(String browser) {
		Log.testCaseInfo("Verify all columns are displayed");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		TransactionList txnList = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			txnList = allAccountsTab.getTransactionList();

			BrowserActions.scrollPage(driver);
			Log.message("<b>Expected : Verify whether the following columns are displayed [Date, Description, Account, Image, Notes, Currency, Amount]..</b>");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList("thDate",
							"thDescription", "thAccount", "thImage", "thNotes",
							"thCurrency", "thAmount"), txnList, driver),
					"All Columns are displayed correctly!",
					"Some of the columns are not displayed!", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Recent Transactions Sorting functionality.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_AccountPageRecentTransaction_002(String browser) {
		Log.testCaseInfo("Verify Recent Transactions Sorting functionality.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		TransactionList txnList = null;
		Transaction[] expectedTxn = null;
		Transaction[] actualTxn = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			txnList = allAccountsTab.getTransactionList();

			expectedTxn = txnList.getAllTransactions();
			BrowserActions.scrollPage(driver);
			Arrays.sort(expectedTxn, Transaction.dateAsc);

			// sorting is not working in the page
			txnList.sortBy("Date", true);
			Log.heading("Verifying Date by Sorting Ascending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Date in Asc order is working fine!",
					"Sort By Date in Asc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.dateDesc);
			txnList.sortBy("Date", false);
			Log.heading("Verifying Date by Sorting Descending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Date in Desc order is working fine!",
					"Sort By Date in Desc order is not working!", driver);
			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.descriptionAsc);
			txnList.sortBy("Description", true);
			Log.heading("Verifying Description by Sorting Ascending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Description in Asc order is working fine!",
					"Sort By Description in Asc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.descriptionDesc);
			txnList.sortBy("Description", false);
			Log.heading("Verifying Description by Sorting Descending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Description in Desc order is working fine!",
					"Sort By Description in Desc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.accountAsc);
			txnList.sortBy("Account", true);
			Log.heading("Verifying Account by Sorting Ascending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Account in Asc order is working fine!",
					"Sort By Account in Asc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.accountDesc);
			txnList.sortBy("Account", false);
			Log.heading("Verifying Account by Sorting Descending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Account in Desc order is working fine!",
					"Sort By Account in Desc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.currencyAsc);
			txnList.sortBy("Currency", true);
			Log.heading("Verifying Currency by Sorting Ascending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Currency in Asc order is working fine!",
					"Sort By Currency in Asc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.currencyDesc);
			txnList.sortBy("Currency", false);
			Log.heading("Verifying Currency by Sorting Descending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Currency in Desc order is working fine!",
					"Sort By Currency in Desc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.amountAsc);
			txnList.sortBy("Amount", true);
			Log.heading("Verifying Amount by Sorting Ascending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Amount in Asc order is working fine!",
					"Sort By Amount in Asc order is not working!", driver);

			expectedTxn = txnList.getAllTransactions();
			Arrays.sort(expectedTxn, Transaction.amountDesc);
			txnList.sortBy("Amount", false);
			Log.heading("Verifying Amount by Sorting Descending..", driver);
			actualTxn = txnList.getAllTransactions();
			Log.softAssertThat(compareTxnValues(expectedTxn, actualTxn),
					"Sort By Amount in Desc order is working fine!",
					"Sort By Amount in Desc order is not working!", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	public void print(Transaction[] allTxn) {
		for (int index = 0; index < allTxn.length; index++) {
			System.out.println(allTxn[index]);
		}
	}

	public boolean compareTxnValues(Transaction[] expected, Transaction[] actual) {
		print(expected);
		print(actual);
		if (expected.length != actual.length) {
			return false;
		}

		for (int index = 1; index < expected.length; index++) {
			if (!expected[index].getAccount()
					.equals(actual[index].getAccount())
					|| !(expected[index].getAmount() == actual[index]
							.getAmount())
					|| !expected[index].getDesc().equals(
							actual[index].getDesc())
					|| !expected[index].getCurrency().equals(
							actual[index].getCurrency())
					|| !expected[index].getDate().equals(
							actual[index].getDate())) {
				return false;
			}
		}
		return true;
	}
}
