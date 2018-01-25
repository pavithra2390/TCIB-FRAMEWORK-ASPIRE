package com.temenos.testscripts;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;
import com.temenos.support.WebDriverFactory;

public class AllAccountsTest extends BaseTest {

	@Test(description = "Verify Current Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_AllAccount_001(String browser) {
		Log.testCaseInfo("Verify Current Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage accountPage = null;
		String accountNumber = "78239";
		String accountType = "Current Account";

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			accountPage = homePage.goToAllAccountsTab().clickOnAccount(
					accountNumber);

			Log.message("Expected : Verifying the account details are correctly displayed");

			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!");

			Log.softAssertThat(
					accountType.equals(accountPage.getAccountType()),
					"Account Type is matching!",
					"Account Type is not matching!");

			verifyPageElementsAreDisplayed(Arrays.asList("tabTransactions",
					"tabTransfers", "tabPayments", "tabStandingOrders",
					"tabDirectDebts", "tabCheques", "tabAlerts"), accountPage,
					driver);
			WaitUtil.sleep(10);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Current Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_AllAccount_002(String browser) {
		Log.testCaseInfo("Verify Current Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = "78239";
		String accountType = "Current Account";

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountNumber = allAccountsTab
					.getAnyAccountNumberOfType(accountType);
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("Verifying the account details are correctly displayed..");

			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!");

			Log.softAssertThat(
					accountType.equals(accountPage.getAccountType()),
					"Account Type is matching!",
					"Account Type is not matching!");

			verifyPageElementsAreDisplayed(Arrays.asList("tabTransactions",
					"tabTransfers", "tabPayments", "tabStandingOrders",
					"tabDirectDebts", "tabCheques", "tabAlerts"), accountPage,
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Savings Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_AllAccount_003(String browser) {
		Log.testCaseInfo("Verify Savings Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = null;
		String accountType = "Savings Account";

		try {
			driver = WebDriverFactory.get(browser);

			Log.message("Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountNumber = allAccountsTab
					.getAnyAccountNumberOfType(accountType);
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("Verifying the account details are correctly displayed..");

			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!");

			Log.softAssertThat(
					accountType.equals(accountPage.getAccountType()),
					"Account Type is matching!",
					"Account Type is not matching!");

			verifyPageElementsAreDisplayed(Arrays.asList("tabTransactions",
					"tabTransfers", "tabPayments", "tabStandingOrders",
					"tabDirectDebts", "tabCheques", "tabAlerts"), accountPage,
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Mortgage Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_AllAccount_004(String browser) {
		Log.testCaseInfo("Verify Mortgage Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = null;
		String accountType = "Mortgage Loan";

		try {
			driver = WebDriverFactory.get(browser);

			Log.message("Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountNumber = allAccountsTab
					.getAnyAccountNumberOfType(accountType);
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("Verifying the account details are correctly displayed..");

			Log.softAssertThat(
					accountPage.getMortgageAccountNumber().contains(
							accountNumber), "Account number is matching!",
					"Account number is not matching!");

			verifyPageElementsAreDisplayed(Arrays.asList("tabTransactions",
					"tabSchedule", "tabPayOverdue"), accountPage, driver);

			verifyPageElementsAreNotDisplayed(Arrays.asList("tabTransfers",
					"tabPayments", "tabStandingOrders", "tabDirectDebts",
					"tabCheques", "tabAlerts"), accountPage, driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Mortgage Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_AllAccount_005(String browser) {
		Log.testCaseInfo("Verify Mortgage Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = "79607";

		try {
			driver = WebDriverFactory.get(browser);

			Log.message("Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("Verifying the account details are correctly displayed..");

			Log.softAssertThat(
					accountPage.getMortgageAccountNumber().contains(
							accountNumber), "Account number is matching!",
					"Account number is not matching!");

			verifyPageElementsAreDisplayed(Arrays.asList("tabTransactions",
					"tabSchedule", "tabPayOverdue"), accountPage, driver);

			verifyPageElementsAreNotDisplayed(Arrays.asList("tabTransfers",
					"tabPayments", "tabStandingOrders", "tabDirectDebts",
					"tabCheques", "tabAlerts"), accountPage, driver);

			Log.softAssertThat(accountPage.VerifyPageElementDisplayed(Arrays
					.asList("tabTransactions", "tabSchedule", "tabPayOverdue"),
					accountPage),
					"Mortgage Account details displayed correctly.",
					"Mortgage Account details are not displayed", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Term deposit Account details displayed correctly.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AllAccount_006(String browser) {
		Log.testCaseInfo("Verify Term deposit Account details displayed correctly.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		String accountNumber = "82748";

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected :</b> Verifying the account details are correctly displayed..");

			Log.softAssertThat(accountPage.getTermDepositAccountNumber()
					.contains(accountNumber), "Account number is matching!",
					"Account number is not matching!");

			Log.message("<b>Expected :</b> Verify whether the following tabs [Transactions, End Of Term Actions] are displayed for Term Deposit..");

			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList(
							"tabTransactions", "tabEndOfTermActions"),
							accountPage, driver), "All tabs are displayed!",
					"Some tabs are not displaying!", driver);

			verifyPageElementsAreDisplayed(
					Arrays.asList("tabTransactions", "tabEndOfTermActions"),
					accountPage, driver);

			Log.message("<b>Expected :</b> Verify whether the following tabs [Transfers, Payments, Standing Orders, Direct Debts] are not displayed for Term Deposit..");

			Log.softAssertThat(
					verifyPageElementsAreNotDisplayed(Arrays.asList(
							"tabTransfers", "tabPayments", "tabStandingOrders",
							"tabDirectDebts", "tabCheques", "tabAlerts"),
							accountPage, driver),
					"All tabs are not displayed!", "Some tabs are displayed!",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify pagination functionality is working fine.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AllAccount_007(String browser) {
		Log.testCaseInfo("Verify pagination functionality is working fine.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		int pageNo = 1;
		int totalPages = 0;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();

			Log.message("<b>Expected :</b> Verify whether pagination is displayed..");

			Log.softAssertThat(allAccountsTab.isPaginationDisplayed(),
					"Pagination is displayed!", "Pagination is not displayed!",
					driver);

			Log.softAssertThat(allAccountsTab.isPageNumberHighlighted(pageNo),
					"Page Number - " + pageNo + " is highlighted!",
					"Page Number - " + pageNo + " is not highlighted!", driver);

			allAccountsTab.goToNextPage();
			pageNo += 1;

			Log.message("<b>Expected :</b> Verify whether Next button is working..");
			Log.softAssertThat(
					allAccountsTab.isPageNumberHighlighted(pageNo),
					"Page Number - " + pageNo
							+ " is highlighted and Next Button is working!",
					"Page Number - "
							+ pageNo
							+ " is not highlighted and Next Button is not working!",
					driver);

			allAccountsTab.goToPrevPage();
			pageNo -= 1;

			Log.message("<b>Expected :</b> Verify whether Previous button is working..");
			Log.softAssertThat(
					allAccountsTab.isPageNumberHighlighted(pageNo),
					"Page Number - " + pageNo
							+ " is highlighted and Prev Button is working!",
					"Page Number - "
							+ pageNo
							+ " is not highlighted and Prev Button is not working!");

			pageNo = 2;
			totalPages = allAccountsTab.getTotalPages();

			Log.message("<b>Expected :</b> Verify whether Page number is highlighted when clicking on the page numbers..");
			while (pageNo <= totalPages) {
				allAccountsTab.clickOnPageNo(pageNo);
				Log.softAssertThat(
						allAccountsTab.isPageNumberHighlighted(pageNo),
						"Page Number - " + pageNo + " is highlighted and ("
								+ pageNo + ") is working!", "Page Number - "
								+ pageNo + " is not highlighted and (" + pageNo
								+ ") button is not working!", driver);

				/*
				 * if (pageNo == totalPages) { allAccountsTab.clickOnPageNo(1);
				 * Log.softAssertThat(
				 * allAccountsTab.isPageNumberHighlighted(1),
				 * "Page Number - 1 is highlighted and (1) button is working!",
				 * "Page Number - 1 is not highlighted and (1) button is not working!"
				 * ); }
				 */
				pageNo += 1;
			}

			Log.message("<b>Expected :</b> Verify the Previous button is not clickable on first page..");
			Log.softAssertThat(allAccountsTab.isPrevButtonDisabled(),
					"Prev button is not clickable!",
					"Prev button is clickable!", driver);

			allAccountsTab.clickOnPageNo(totalPages);
			Log.message("<b>Expected :</b> Verify the last button is not clickable on last page..");
			Log.softAssertThat(allAccountsTab.isNextButtonDisabled(),
					"Next button is not clickable!",
					"Next button is clickable!", driver);

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
}
