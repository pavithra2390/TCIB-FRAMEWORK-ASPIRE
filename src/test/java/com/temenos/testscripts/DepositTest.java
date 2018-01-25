package com.temenos.testscripts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.LoginPage;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WebDriverFactory;

public class DepositTest extends BaseTest {
	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("DepositDetails");
	}

	@Test(description = "Verify the deposit page of call deposit account", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_DEPOSIT_001(String browser) {
		Log.testCaseInfo("Verify the deposit page of call deposit account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;
		String accountNumber;
		HashMap<String, String> values = tde.readData("TC_TCIB_DEPOSIT_001");
		List<String> expectedElements = Arrays.asList("tabTransactions",
				"tabEndOfAction");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Call deposit account", driver);
			Log.message("<b>Expected : Verify Deposit Page of call deposit contains Transactions and end of Terms Actions");
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedElements,
							allAcctPage, driver),
					"Deposit Page of call deposit account contains Transactions and end of Terms Actions",
					"Deposit Page of call deposit account does not contain Transactions and end of Terms Actions",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Show more and show less option in the Call deposit Wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_DEPOSIT_002(String browser) {
		Log.testCaseInfo("Verify the Show more and show less option in the Call deposit Wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde.readData("TC_TCIB_DEPOSIT_002");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to Call deposit account", driver);
			Log.message("<b>Expected : Verify the Show More/Less Option in call deposit Page");
			Log.softAssertThat(
					allAcctPage.verifyShowLessDepositDetails(),
					"Show More/Less options is available and working in Call deposit page",
					"Show More/Less options is not available in Call deposit page",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the tab switching option in the deposit page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_DEPOSIT_003(String browser) {
		Log.testCaseInfo("Verify the tab switching option in the deposit page");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde.readData("TC_TCIB_DEPOSIT_003");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			// homePage = new HomePage(driver).get(); // first time driver will
			// be passed
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to deposit account", driver);
			Log.message("<b>Expected : Verify whether able to switch between transaction and end of term actions.</b>");
			Log.softAssertThat(
					allAcctPage.verifyTabDepositPage(2),
					"Successfully able to switch between transaction and end of term actions.",
					"Unable to switch between transaction and end of term actions.",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the tab switching in the Transaction list wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_DEPOSIT_004(String browser) {
		Log.testCaseInfo("Verify the tab switching in the Transaction list wizard");

		WebDriver driver = null;
		LoginPage loginPage = null;
		// HomePage homePage = null;

		String accountNumber;

		HashMap<String, String> values = tde.readData("TC_TCIB_DEPOSIT_004");

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			AllAccountsTab allAcctPage = new AllAccountsTab(driver).get();
			accountNumber = allAcctPage.selectAnyAccountofType(values
					.get("account_type"));
			Log.message("Navigated to deposit account", driver);
			Log.message("<b>Expected : Verify whether able to switch in the Transaction list</b>");
			Log.softAssertThat(allAcctPage.verifyTabTransactionPage(values
					.get("account_type")),
					"Successfully able to switch in the Transaction list.",
					"Unable to switch in the Transaction list", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

}
