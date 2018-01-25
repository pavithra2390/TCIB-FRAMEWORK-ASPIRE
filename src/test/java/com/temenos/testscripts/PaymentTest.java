package com.temenos.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.CheckPayeeDetails;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.NewPayeePayments;
import com.temenos.pages.NewPayeePayments.CompanyPayee;
import com.temenos.pages.NewPayeePayments.DomesticPayee;
import com.temenos.pages.NewPayeePayments.InternationalPayee;
import com.temenos.pages.PaymentSuccess;
import com.temenos.pages.PaymentsPayeeDetails;
import com.temenos.pages.PaymentsTab;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.Utils;
import com.temenos.support.WaitUtil;
import com.temenos.support.WebDriverFactory;

public class PaymentTest extends BaseTest {

	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("Payments");
	}

	@Test( description = "Verify Payments tab displayed via Current Account.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_001(String browser) throws Exception {
		Log.testCaseInfo("Verify Payments tab displayed via Current Account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_001");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application.");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Payments tab displayed via Savings Account.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_002(String browser) throws Exception {
		Log.testCaseInfo("Verify Payments tab displayed via Savings Account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_003");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application.");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();

			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.message("<b>Expected: Verifying the payment page is successfully displayed.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Savings Account",
					"Payment page is not displayed in Savings Account", driver);

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify New and Saved Payee is displayed in the payment page.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_003(String browser) throws Exception {
		Log.testCaseInfo("Verify New and Saved Payee is displayed in the payment page.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_003");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			// Log.message(i++
			// +". Verifying the account details are correctly displayed..");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected: Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected: Verifying the payment page is successfully displayed both Save and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify default page is pointing the saved payee.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_004(String browser) {
		Log.testCaseInfo("Verify default page is pointing the saved payee.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_004");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login");
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify the sorting function in Saved Payees - Sort by Payee name", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_005(String browser) {
		Log.testCaseInfo("Verify the sorting function in Saved Payees - Sort by Payee name");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_005");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login");
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			NewPayeePayments newPayee = new NewPayeePayments(driver);
			boolean paye = newPayee.verifySort("Payee Name");
			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

//	@Test(description = "Verify the sorting function in Saved Payees - Sort by Account number", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_006(String browser) {
		Log.testCaseInfo("Verify the sorting function in Saved Payees - Sort by Account number");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_006");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login");
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			NewPayeePayments newPayee = new NewPayeePayments(driver);
			boolean paye = newPayee.verifySort("Account No");
			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify the sorting function in Saved Payees - Sort by Reference", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_007(String browser) {
		Log.testCaseInfo("Verify the sorting function in Saved Payees - Sort by Reference");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_007");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			NewPayeePayments newPayee = new NewPayeePayments(driver);
			boolean paye = newPayee.verifySort("Reference");
			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify saved payee page navigation.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_008(String browser) {
		Log.testCaseInfo("Verify saved payee page navigation.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_008");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++ +" .Verifying the payee list is clickable!!!");
			WaitUtil.waitForSpinnerToComplete(driver);
			Utils.waitForPageLoad(driver);
			paymentpage.clickOnPayeeDetails();
			WaitUtil.sleep(2);
			Log.message("<b>Expected: Verifying the payment method are displayed after the IBAN/SwiftBIC/MultipleNCC!!!</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(Arrays.asList(
							"paymentIBAN", "paymentSwiftBIC",
							"paymentMultipleNCC"), paymentpage),
					"Payment Methods are displayed after the details clicked!!!",
					"Payment Methods are not displayed after the details clicked!!!",
					driver);

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

//	@Test(description = "Verify saved payee payment options.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_009(String browser) {
		Log.testCaseInfo("Verify saved payee payment options.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_009");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++ +" .Verifying the payee list is clickable!!!");
			WaitUtil.waitForSpinnerToComplete(driver);
			Utils.waitForPageLoad(driver);
			paymentpage.clickOnPayeeDetails();
			Log.message("<b> Expected: Verifying the payment method are displayed after the IBAN/SwiftBIC/MultipleNCC!!!</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(Arrays.asList(
							"paymentIBAN", "paymentSwiftBIC",
							"paymentMultipleNCC"), paymentpage),
					"Payment Methods are displayed after the details clicked!!!",
					"Payment Methods are not displayed after the details clicked!!!",
					driver);
			paymentpage.clickOnIbanPayment();

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify new payee using current account.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_010(String browser) throws Exception {
		Log.testCaseInfo("Verify new payee using current account.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_010");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(accountPage.getAccountNumber().contains(accountNumber), "Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"), paymentpage, driver);
			Log.softAssertThat(paymentpage.VerifyPageElementDisplayed(Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Account", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed with both saved and new payee tab.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(Arrays.asList("tabSavePayee", "tabNewPayee"), paymentpage),
					"Payment page is displayed with both Saved Payee and New Payee tabs",
					"Payment page is not displayed with both Saved Payee and New Payee tabs", driver);
			Log.message("<b>Expected : Verifying saved payee tab is selected by default in the Payments page.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement("tabSavePayee", "selected", "true", paymentpage),
					"Saved payee tab is selected by default in the Payments page!!!",
					"saved payee tab is not selected by default in the Payments page.!!!", driver);
			// Log.message(i++" .Verifying the payment page an clicking the New Payee
			// options.");
			WaitUtil.waitForSpinnerToComplete(driver); // WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message(
					"<b>Expected: Verifying new payee option displayed PersonWithinGB/CompanyWithinGB/PersonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList("newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB", "newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PersonWithinGB/CompanyWithinGB/PersonOutsideGB",
					"New payee is not displayed all the 3 options PersonWithinGB/CompanyWithinGB/PersonOutsideGB",
					driver);

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify new payee using saving account.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_011(String browser) throws Exception {
		Log.testCaseInfo("Verify new payee using saving account.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_011");
		String accountNumber = values.get("account_number_from");
		int i = 1;
		try {

			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(accountPage.getAccountNumber().contains(accountNumber), "Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"), paymentpage, driver);
			Log.softAssertThat(paymentpage.VerifyPageElementDisplayed(Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Saving Account",
					"Payment page is not displayed in Saving Account", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed with both saved and new payee tab.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(Arrays.asList("tabSavePayee", "tabNewPayee"), paymentpage),
					"Payment page is displayed with both Saved Payee and New Payee tabs",
					"Payment page is not displayed with both Saved Payee and New Payee tabs", driver);
			Log.message("<b>Expected : Verifying saved payee tab is selected by default in the Payments page.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement("tabSavePayee", "selected", "true", paymentpage),
					"Saved payee tab is selected by default in the Payments page!!!",
					"saved payee tab is not selected by default in the Payments page.!!!", driver);

			// Log.message(i++" .Verifying the payment page an clicking the New Payee
			// options.");
			WaitUtil.waitForSpinnerToComplete(driver); // WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message(
					"<b>Expected: Verifying new payee option displayed PersonWithinGB/CompanyWithinGB/PersonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList("newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB", "newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed with all the 3 options PersonWithinGB/CompanyWithinGB/PersonOutsideGB",
					"New payee is not displayed with all the 3 options PersonWithinGB/CompanyWithinGB/PersonOutsideGB",
					driver);

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify add the new payee in current account using person with in GB using IBAN.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_012(String browser) throws Exception {
		Log.testCaseInfo("Verify add the new payee in current account using person with in GB using IBAN..");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_012");
		String accountNumber = values.get("account_number_from");
		String iban = values.get("iban");
		String payeename = values.get("payeename");
		String amount = values.get("amount");
		String currency = values.get("currency");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(accountPage.getAccountNumber().contains(accountNumber), "Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"), paymentpage, driver);
			Log.softAssertThat(paymentpage.VerifyPageElementDisplayed(Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(Arrays.asList("tabSavePayee", "tabNewPayee"), paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee", driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement("tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!", "Default page is not displayed the saved payee!!!",
					driver);
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message(
					"<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList("newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB", "newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			DomesticPayee domestic = newpayee.gotoPersonWithInGBTabFromAddPayee();
			// Log.message(i++
			// +" .Verify and selecting the IBAN payment method in person within GB.");
			PaymentsPayeeDetails paydetails = domestic.gotoPaymentsPayeeDetailsWithIBANPaymentMode();

			// Log.message(i++ +" .Entering the IBAN number and Payee name.");
			paydetails.enterPayeeDetails(iban, payeename);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			// Log.message(i++ +" .Entering the nick name.");
			Log.message("<b>Expected: Verifying the payee confirmation details</b>");
			checkpayee.verifyPaymentDetail("TC_TCIB_PAYMENT_012");
			// checkpayee.enterPayeeNickname(payeename);
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected: Verifying the Payment success details</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_012");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e,driver);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}	
	//@Test(description = "Verify add the new payee in current account using person with in GB with Swift/BIC With Account Number .", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_013(String browser) {
		Log.testCaseInfo("Verify add the new payee in current account using person with in GB with Swift/BIC With Account Number .");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_013");
		String accountNumber = values.get("account_number_from");
		String iban = values.get("iban");
		String payeename = values.get("payeename");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String bic = values.get("bic");
		String accountno = values.get("accountno");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			DomesticPayee domestic = newpayee
					.gotoPersonWithInGBTabFromAddPayee();
			// Log.message(i++
			// +" .Verify and selecting the IBAN payment method in person within GB.");
			PaymentsPayeeDetails paydetails = domestic
					.gotoPaymentsPayeeDetailsWithSWIFTBICPaymentMode();
			// Log.message(i++
			// +" .Entering the Swift/BIC number and Payee name.");
			paydetails.enterPayeeDetailsBICOption(bic, accountno, payeename);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected: Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailSwiftBic("TC_TCIB_PAYMENT_013");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected: Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_013");
			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			// driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in current account using person with in GB with Swift/BIC With Account Number .", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_014(String browser) {
		Log.testCaseInfo("Verify add the new payee in current account using person with in GB with Swift/BIC With Account Number .");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_014");
		String accountNumber = values.get("account_number_from");
		String iban = values.get("iban");
		String payeename = values.get("payeename");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String clearingcode = values.get("clearingcode");
		String clearingchannel = values.get("clearingchannel");
		String accountno = values.get("accountno");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			DomesticPayee domestic = newpayee
					.gotoPersonWithInGBTabFromAddPayee();
			// Log.message(i++
			// +" .Verify and selecting the IBAN payment method in person within GB.");
			PaymentsPayeeDetails paydetails = domestic
					.gotoPaymentsPayeeDetailsWithMultipleNCCPaymentMode();
			// Log.message(i++
			// +" .Entering the NCC CODE, number and Payee name.");
			paydetails.enterPayeeDetailsNCC(clearingcode, accountno, payeename);
			// Log.message(i++ +" .Selecting the clearing channel.");
			paydetails.selectClearingChannel(clearingchannel);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailNCC("TC_TCIB_PAYMENT_014");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_014");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in saving account using person with in GB using IBAN.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_015(String browser) {
		Log.testCaseInfo("Verify add the new payee in saving account using person with in GB using IBAN..");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_015");
		String accountNumber = values.get("account_number_from");
		String iban = values.get("iban");
		String payeename = values.get("payeename");
		String amount = values.get("amount");
		String currency = values.get("currency");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			DomesticPayee domestic = newpayee
					.gotoPersonWithInGBTabFromAddPayee();
			// Log.message(i++
			// +" .Verify and selecting the IBAN payment method in person within GB.");
			PaymentsPayeeDetails paydetails = domestic
					.gotoPaymentsPayeeDetailsWithIBANPaymentMode();

			// Log.message(i++ +" .Entering the IBAN number and Payee name.");
			paydetails.enterPayeeDetails(iban, payeename);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetail("TC_TCIB_PAYMENT_015");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();

			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_015");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in saving account using person with in GB with Swift/BIC With Account Number .", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_016(String browser) {
		Log.testCaseInfo("Verify add the new payee in saving account using person with in GB with Swift/BIC With Account Number .");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_016");
		String accountNumber = values.get("account_number_from");
		String iban = values.get("iban");
		String payeename = values.get("payeename");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String bic = values.get("bic");
		String accountno = values.get("accountno");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			DomesticPayee domestic = newpayee
					.gotoPersonWithInGBTabFromAddPayee();
			// Log.message(i++
			// +" .Verify and selecting the IBAN payment method in person within GB.");
			PaymentsPayeeDetails paydetails = domestic
					.gotoPaymentsPayeeDetailsWithSWIFTBICPaymentMode();
			// Log.message(i++ +" .Entering the IBAN number and Payee name.");
			paydetails.enterPayeeDetailsBICOption(bic, accountno, payeename);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailSwiftBic("TC_TCIB_PAYMENT_016");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_016");
			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in saving account using person with in GB with Swift/BIC With Account Number .", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_017(String browser) {
		Log.testCaseInfo("Verify add the new payee in saving account using person with in GB with Swift/BIC With Account Number .");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_017");
		String accountNumber = values.get("account_number_from");
		String iban = values.get("iban");
		String payeename = values.get("payeename");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String clearingcode = values.get("clearingcode");
		String clearingchannel = values.get("clearingchannel");
		String accountno = values.get("accountno");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			DomesticPayee domestic = newpayee
					.gotoPersonWithInGBTabFromAddPayee();
			// Log.message(i++
			// +" .Verify and selecting the IBAN payment method in person within GB.");
			PaymentsPayeeDetails paydetails = domestic
					.gotoPaymentsPayeeDetailsWithMultipleNCCPaymentMode();
			// Log.message(i++
			// +" .Entering the NCC CODE, number and Payee name.");
			paydetails.enterPayeeDetailsNCC(clearingcode, accountno, payeename);
			// Log.message(i++ +" .Selecting the clearing channel.");
			paydetails.selectClearingChannel(clearingchannel);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailNCC("TC_TCIB_PAYMENT_017");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_017");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in current account using company with in GB  With company name.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_018(String browser) {
		Log.testCaseInfo("Verify add the new payee in current account using company with in GB  With company name.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_018");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			CompanyPayee company = newpayee.gotoCompanyWithInGBTab();
			PaymentsPayeeDetails paydetails = company
					.gotoPaymentsPayeeDetails(companyname);
			// Log.message(i++ +" .Selecting the currency form the dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailCompany("TC_TCIB_PAYMENT_018");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess
					.verifyPaymentSuccessDetailCompany("TC_TCIB_PAYMENT_018");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in current account using company with in GB  With account number.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_019(String browser) {
		Log.testCaseInfo("Verify add the new payee in current account using company with in GB  With account number.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_019");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		String accountno = values.get("accountno");
		String sortingcode = values.get("clearingcode");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			CompanyPayee company = newpayee.gotoCompanyWithInGBTab();
			PaymentsPayeeDetails paydetails = company
					.gotoPaymentsPayeeDetailsByAccount(accountno, sortingcode);
			// Log.message(i++ +" .Selecting the currency form the dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailCompany("TC_TCIB_PAYMENT_019");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess
					.verifyPaymentSuccessDetailCompany("TC_TCIB_PAYMENT_019");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

//	@Test(description = "Verify add the new payee in saving account using company with in GB  With company name.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_020(String browser) {
		Log.testCaseInfo("Verify add the new payee in saving account using company with in GB  With company name.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_020");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			CompanyPayee company = newpayee.gotoCompanyWithInGBTab();
			PaymentsPayeeDetails paydetails = company
					.gotoPaymentsPayeeDetails(companyname);
			// Log.message(i++ +" .Selecting the currency form the dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailCompany("TC_TCIB_PAYMENT_020");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess
					.verifyPaymentSuccessDetailCompany("TC_TCIB_PAYMENT_020");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in current account using company with in GB  With account number.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_021(String browser) {
		Log.testCaseInfo("Verify add the new payee in current account using company with in GB  With account number.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_021");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		String accountno = values.get("accountno");
		String sortingcode = values.get("clearingcode");
		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			CompanyPayee company = newpayee.gotoCompanyWithInGBTab();
			PaymentsPayeeDetails paydetails = company
					.gotoPaymentsPayeeDetailsByAccount(accountno, sortingcode);
			// Log.message(i++ +" .Selecting the currency form the dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailCompany("TC_TCIB_PAYMENT_021");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess
					.verifyPaymentSuccessDetailCompany("TC_TCIB_PAYMENT_021");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

//	@Test(description = "Verify add the new payee in current account using company with in GB  With account number.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_022(String browser) {
		Log.testCaseInfo("Verify add the new payee in current account using company with in GB  With account number.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_022");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		String accountno = values.get("accountno");
		String sortingcode = values.get("clearingcode");
		String country = values.get("country");
		String payeename = values.get("payeename");
		String bic = values.get("bic");

		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ + ". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			InternationalPayee outside = newpayee.gotoPersonOutSideGBTab();
			PaymentsPayeeDetails paydetails = outside
					.gotoPaymentsPayeeDetails(country);
			// paydetails.selectingPaymentMethod();
			// Log.message(i++
			// +" .Entering the Swift/BIC number and Payee name.");
			paydetails.enterPayeeDetailsBICOption(bic, accountno, payeename);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b> Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailSwiftBic("TC_TCIB_PAYMENT_022");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_022");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in current account using company with in GB  With account number.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_PAYMENT_023(String browser) throws Exception {
		Log.testCaseInfo("Verify add the new payee in current account using company with in GB  With account number.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_023");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		String accountno = values.get("accountno");
		String sortingcode = values.get("clearingcode");
		String country = values.get("country");
		String payeename = values.get("payeename");
		String clearingcode = values.get("clearingcode");
		String clearingchannel = values.get("clearingchannel");
		String bic = values.get("bic");

		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			InternationalPayee outside = newpayee.gotoPersonOutSideGBTab();
			PaymentsPayeeDetails paydetails = outside
					.gotoPaymentsPayeeDetails1(country);
			// paydetails.selectingPaymentMethod();
			// Log.message(i++
			// +" .Entering the NCC CODE, number and Payee name.");
			paydetails.enterPayeeDetailsNCC(clearingcode, accountno, payeename);
			// Log.message(i++ +" .Selecting the clearing channel.");
			paydetails.selectClearingChannel(clearingchannel);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailNCC("TC_TCIB_PAYMENT_023");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_023");
			Log.testCaseResult();

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in saving account using company with in GB  With account number.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_PAYMENT_024(String browser) {
		Log.testCaseInfo("Verify add the new payee in saving account using company with in GB  With account number.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_024");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		String accountno = values.get("accountno");
		String sortingcode = values.get("clearingcode");
		String country = values.get("country");
		String payeename = values.get("payeename");
		String bic = values.get("bic");

		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			InternationalPayee outside = newpayee.gotoPersonOutSideGBTab();
			PaymentsPayeeDetails paydetails = outside
					.gotoPaymentsPayeeDetails(country);
			// paydetails.selectingPaymentMethod();
			// Log.message(i++
			// +" .Entering the Swift/BIC number and Payee name.");
			paydetails.enterPayeeDetailsBICOption(bic, accountno, payeename);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailSwiftBic("TC_TCIB_PAYMENT_024");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_024");

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	//@Test(description = "Verify add the new payee in current account using company with in GB  With account number.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_PAYMENT_025(String browser) throws Exception {
		Log.testCaseInfo("Verify add the new payee in current account using company with in GB  With account number.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_PAYMENT_025");
		String accountNumber = values.get("account_number_from");
		String amount = values.get("amount");
		String currency = values.get("currency");
		String companyname = values.get("companyname");
		String accountno = values.get("accountno");
		String sortingcode = values.get("clearingcode");
		String country = values.get("country");
		String payeename = values.get("payeename");
		String clearingcode = values.get("clearingcode");
		String clearingchannel = values.get("clearingchannel");
		String bic = values.get("bic");

		int i = 1;
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			// Log.message(i++ +". Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			Log.message("<b>Expected : Verifying the account details are correctly displayed.</b>");
			Log.softAssertThat(
					accountPage.getAccountNumber().contains(accountNumber),
					"Account number is matching!",
					"Account number is not matching!", driver);
			PaymentsTab paymentpage = accountPage.goToPaymentsTab();
			Log.message("<b>Expected : Verifying the payment page is successfully displayed.</b>");
			verifyPageElementsAreDisplayed(Arrays.asList("paymentsTitle"),
					paymentpage, driver);
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("paymentsTitle"), paymentpage),
					"Payment page is successfully displayed in Current Account",
					"Payment page is not displayed in Current Login", driver);
			Log.message("<b>Expected : Verifying the payment page is displayed both saved and new payee.</b>");
			Log.softAssertThat(
					paymentpage.VerifyPageElementDisplayed(
							Arrays.asList("tabSavePayee", "tabNewPayee"),
							paymentpage),
					"Payment page is displayed both Saved Payee and New Payee",
					"Payment page is not displayed both Saved Payee and New Payee",
					driver);
			Log.message("<b>Expected : Verifying the payment page is default selected the saved payee.</b>");
			Log.softAssertThat(paymentpage.verifyAttributeForElement(
					"tabSavePayee", "selected", "true", paymentpage),
					"Default page is displayed the saved payee!!!",
					"Default page is not displayed the saved payee!!!", driver);
			// Log.message(i++
			// +" .Verifying the payment page an clicking the New Payee options.");
			WaitUtil.waitForSpinnerToComplete(driver);
			// WaitUtil.sleep(2);
			NewPayeePayments newpayee = paymentpage.clickOnNewPayeeTab();
			Log.message("<b>Expected: Verifying new payee option displayed PresonWithinGB/CompanyWithinGB/PresonOutsideGB.</b>");
			Log.softAssertThat(
					newpayee.VerifyPageElementDisplayed(Arrays.asList(
							"newPayeePresonWithinGB",
							"newPayeeCompanyWithinGB",
							"newPayeePresonOutSideGB"), newpayee),
					"New payee is displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					"New payee is not displayed all the 3 options PresonWithinGB/CompanyWithinGB/PresonOutsideGB",
					driver);
			// Log.message(i++ +" .Verify and navigate to Person within GB.");
			InternationalPayee outside = newpayee.gotoPersonOutSideGBTab();
			PaymentsPayeeDetails paydetails = outside
					.gotoPaymentsPayeeDetails1(country);
			// paydetails.selectingPaymentMethod();
			// Log.message(i++
			// +" .Entering the NCC CODE, number and Payee name.");
			paydetails.enterPayeeDetailsNCC(clearingcode, accountno, payeename);
			// Log.message(i++ +" .Selecting the clearing channel.");
			paydetails.selectClearingChannel(clearingchannel);
			// Log.message(i++ +" .Selecting the currency from from dropdown.");
			paydetails.selectCurrency(currency);
			paydetails.seletcingImmediatePayment();
			// Log.message(i++
			// +" .Entering the Amount,Payee reference and Customer reference.");
			paydetails.enterPaymentDetails(amount);
			// Log.message(i++ +" .Selecting the save payee option.");
			paydetails.selectingSavePayee("No");
			// Log.message(i++ +" .Clicking the payment overiew details.");
			CheckPayeeDetails checkpayee = paydetails.clickOverviewDetails();
			Log.message("<b>Expected : Verifying the payment confirmation details.</b>");
			checkpayee.verifyPaymentDetailNCC("TC_TCIB_PAYMENT_025");
			// Log.message(i++ +" .Accepting the terms and condition.");
			checkpayee.clickingTermsCondition();
			// Log.message(i++ +" .Clicking the Pay button.");
			PaymentSuccess paymentsuccess = checkpayee.continueAndSave1();
			Log.message("<b>Expected : Verifying the payment success details.</b>");
			paymentsuccess.verifyPaymentSuccessDetail("TC_TCIB_PAYMENT_025");
			Log.testCaseResult();

			WaitUtil.sleep(2);

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

}