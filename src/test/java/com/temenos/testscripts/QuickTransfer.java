package com.temenos.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.QuickTransferTab;
import com.temenos.pages.QuickTransferTab.manageTransferred;
import com.temenos.pages.TransactionList;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WaitUtil;
import com.temenos.support.WebDriverFactory;

public class QuickTransfer extends BaseTest {

	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("QuickTransfer");
	}

	@Test(description = "Verify the user can navigate to the quick transfer tab from home page.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_QUICKTRANSFER_001(String browser) {
		Log.testCaseInfo("Verify the user can navigate to the quick transfer tab from home page.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		QuickTransferTab quickTransferTab = null;
		Properties prop = new Properties();
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			quickTransferTab = homePage.goToQuickTransferTab();
			Log.message("<b>Expected : Verify the user able to navigate to Quick Transfer page</b>");
			WaitUtil.sleep(10);
			Log.softAssertThat(quickTransferTab.verifyPageMovement(),
					"The user able to navigated to Quick Transfer page!",
					"The user not able to navigate to Quick Transfer page!",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the user can make quick transfer", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_QUICKTRANSFER_002(String browser) {
		Log.testCaseInfo("Verify the user can make quick transfer");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;

		String accountNumberFrom;
		String accountNumberTo;
		String amount;

		HashMap<String, String> values = tde.readData("TC001");
		System.out.println(values);
		System.out.println(values.get("account_number_from"));
		System.out.println(values.get("account_number_to"));
		System.out.println(values.get("amount"));

		accountNumberFrom = values.get("account_number_from");
		accountNumberTo = values.get("account_number_to");
		amount = values.get("amount");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();

			QuickTransferTab quickTransferTab = homePage.goToQuickTransferTab();
			WaitUtil.sleep(10);
			Log.message("<b>Expected : The user should able to make quick transfer of $\n</b>"
					+ amount
					+ "\nfrom Account number\n"
					+ accountNumberFrom.split("-")[0]
					+ "\nto Account number\n"
					+ accountNumberTo.split("-")[0] + "");
			quickTransferTab.selectFromAccount(accountNumberFrom);
			quickTransferTab.selectToAccount(accountNumberTo);
			quickTransferTab.enterAmount(amount);
			quickTransferTab.clickTransferButton();
			WaitUtil.sleep(10);
			Log.softAssertThat(
					quickTransferTab.getTransfferedAmount().equals(amount),
					"The user can able to make quick transfer of $\n" + amount
							+ "\nfrom Account number\n"
							+ accountNumberFrom.split("-")[0]
							+ "\nto Account number "
							+ accountNumberTo.split("-")[0] + "",
					"The user cannot able to make quick transfer of $\n"
							+ amount + "\n from Account number\n "
							+ accountNumberFrom.split("-")[0]
							+ " \nto Account number "
							+ accountNumberTo.split("-")[0] + "", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the currency field is uneditable", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_QUICKTRANSFER_003(String browser) {
		Log.testCaseInfo("Verify whether the currency field is uneditable");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();

			QuickTransferTab quickTransferTab = homePage.goToQuickTransferTab();
			WaitUtil.sleep(3);
			Log.message("<b>Expected : The currency field in quick transfer page should be read only!</b>");
			Log.softAssertThat(
					quickTransferTab.getCurrencyAttribute().trim()
							.equals("true"),
					"The currency field in quick transfer page is read only!",
					"the currency field in quick transfer page is not read only!",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the application does not accept the payer and payee as same account number", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_QUICKTRANSFER_004(String browser) {
		Log.testCaseInfo("Verify whether the application does not accept the payer and payee as same account number");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		String accountNumberFrom;
		boolean flag = false;

		HashMap<String, String> values = tde.readData("TC001");
		System.out.println(values);
		System.out.println(values.get("account_number_from"));

		accountNumberFrom = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();

			QuickTransferTab quickTransferTab = homePage.goToQuickTransferTab();
			WaitUtil.sleep(3);
			Log.message("<b>Expected : The payer account number and payee account number should not be same</b>");
			String[] acNos = quickTransferTab.selectFromAccount(
					accountNumberFrom).getToAcoountNos();
			for (int i = 0; i < acNos.length; i++) {
				if (acNos[i].equals(accountNumberFrom)) {
					flag = true;
				}
			}
			Log.softAssertThat(
					!flag,
					"The payer account number and payee account number is not same",
					"The payer account number and payee account number is same",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	// added negative test cases.
	@Test(description = "Verify Quick Transfer With Negative Amount", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_QUICKTRANSFER_005(String browser) {
		Log.testCaseInfo("Verify Quick Transfer With Negative Amount");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		QuickTransferTab quickTransferTab = null;
		TransactionList txnList = null;
		manageTransferred QTlist = null;
		Properties prop = new Properties();

		// from account should have 1000 amount value
		String accountNumberFrom;
		String accountNumberTo;
		String amount;

		HashMap<String, String> values = tde.readData("TC002");
		System.out.println(values);
		System.out.println(values.get("account_number_from"));
		System.out.println(values.get("account_number_to"));
		System.out.println(values.get("amount"));
		accountNumberFrom = values.get("account_number_from");
		accountNumberTo = values.get("account_number_to");
		amount = values.get("amount");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			quickTransferTab = homePage.goToQuickTransferTab();
			WaitUtil.sleep(10);
			Log.message("<b>Expected : The user should not allowed to transfer amount in negative value</b>");
			quickTransferTab.selectFromAccount(accountNumberFrom);
			quickTransferTab.selectToAccount(accountNumberTo);
			quickTransferTab.enterAmount(amount);
			quickTransferTab.clickTransferButton();
			WaitUtil.sleep(10);
			Assert.assertEquals("amount value error message is displayed",
					prop.getProperty("minimunAmountValue"),
					quickTransferTab.verifyMinimumValue());
			Log.softAssertThat(
					prop.getProperty("minimunAmountValue").equals(
							quickTransferTab.verifyMinimumValue()),
					"The user is not allowed to transfer amount in negative value",
					"The user is allowed to transfer amount in negative value",
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
}