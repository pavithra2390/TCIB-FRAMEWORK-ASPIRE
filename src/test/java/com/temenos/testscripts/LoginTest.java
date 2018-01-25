package com.temenos.testscripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.TransactionList;
import com.temenos.pages.TransactionsTab.PendingTab;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.WebDriverFactory;

public class LoginTest extends BaseTest {

	@Test(description = "Verify login functionality.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_LoginTest_001(String browser) throws Exception {
		Log.testCaseInfo("Verify login functionality.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		PendingTab pendingTab = null;
		TransactionList txnList = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get(); // first time driver will be
													// passed
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}
}
