package com.temenos.testscripts;

import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.CheckPayeeDetails1;
import com.temenos.pages.Headers;
import com.temenos.pages.Headers.SideBar;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.ManagePayee;
import com.temenos.pages.NewPayee;
import com.temenos.pages.NewPayee.InternationalPayee;
import com.temenos.pages.PayeeDetails;
import com.temenos.pages.TransactionList;
import com.temenos.pages.TransactionsTab;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WebDriverFactory;

public class ManagePayees extends BaseTest {
	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("ManagePayee");
	}

	@Test(description = "Verify user can add a new domestic payee.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_001(String browser) {
		Log.testCaseInfo("Verify user can add a new domestic payee");
		HashMap<String, String> values = tde.readData("TC001");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			ManagePayee managePayee = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoPersonWithInGBTabFromAddPayee()
					.gotoPayeeDetailsWithIBANPaymentMode()
					.enterPayeeDetails(iban, name).clickOverviewDetails1()
					.continueAndSave().clickOnAccount(name, bic, accNo, iban);
			Log.message("<b>Expected : Verifying the Iban Value.</b>");
			Log.softAssertThat(managePayee.getPayeeIBAN().equals(iban),
					"Payee IBAN number is verified",
					"Payee IBAN number is not verified", driver);
			Log.message("<b>Expected : Verifying the Payee name.</b>");
			Log.softAssertThat(managePayee.getPayeeName().equals(name),
					"Payee name is verified", "Payee name is not verified",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify required alert message are showing in application!", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_010(String browser) {
		Log.testCaseInfo("Verify required alert message are showing in application!");
		WebDriver driver = null;
		LoginPage loginPage = null;
		Headers headers = null;

		String ibanMessage = "Please enter IBAN number";
		String payeeMessage = "Please enter payee name";
		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			PayeeDetails payeeDetails = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoPersonWithInGBTabFromAddPayee()
					.gotoPayeeDetailsWithIBANPaymentMode()
					.clickOverviewDetailsWithoutValue();
			String iban = payeeDetails.verifyAlertforIBAN();
			String name = payeeDetails.verifyAlertforPayee();
			Log.message("<b>Expected : Verifying the alert message of IBAN.</b>");
			Log.softAssertThat(iban.equals(ibanMessage), "The alert message '"
					+ iban + "' is shown in application", "The alert message '"
					+ iban + "' is not shown in application", driver);
			Log.message("<b>Expected : Verifying the alret message of payeename.</b>");
			Log.softAssertThat(name.equals(payeeMessage), "The alert message '"
					+ name + "' is shown in application", "The alert message '"
					+ name + "' is not shown in application", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "verify user can add a new domestic payee with BIC /Swift with account Number", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_002(String browser) {
		Log.testCaseInfo("verify user can add a new domestic payee with BIC /Swift with account Number");
		HashMap<String, String> values = tde.readData("TC002");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			ManagePayee managePayee = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoPersonWithInGBTabFromAddPayee()
					.gotoPayeeDetailsWithSWIFTBICPaymentMode()
					.enterPayeeDetailsBICOption(bic, accNo, name)
					.clickOverviewDetails1().continueAndSave()
					.clickOnAccount(name, bic, accNo, iban);
			Log.message("<b>Expected : Verifying the BIC Value.</b>");
			Log.softAssertThat(managePayee.getBICValue().equals(bic),
					"BIC is verified", "BIC is not verified", driver);
			Log.message("<b>Expected : Verifying the payeename Value.</b>");
			Log.softAssertThat(managePayee.getPayeeName().equals(name),
					"Payee name is verified", "Payee name is not verified",
					driver);
			Log.message("<b>Expected : Verifying the payee account number .</b>");
			Log.softAssertThat(managePayee.getPayeeAccountNumber()
					.equals(accNo), "Payee account number is verified",
					"Payee account number is not verified", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	// @Test(description =
	// "verify user can add a new domestic payee in Multiple NCC With Account Number payment method",
	// dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	@Test(description = "verify user can add a new domestic payee in Multiple NCC With Account Number payment method", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_003(String browser) {
		Log.testCaseInfo("verify user can add a new domestic payee in Multiple NCC With Account Number payment method");
		HashMap<String, String> values = tde.readData("TC002");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			ManagePayee managePayee = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoPersonWithInGBTabFromAddPayee()
					.gotoPayeeDetailsWithMultipleNCCPaymentMode()
					.enterPayeeDetailsNCC(bic, accNo, name)
					.clickOverviewDetails1().continueAndSave()
					.clickOnAccount(name, bic, accNo, iban);
			Log.message("<b>Expected : Verifying the BIC Value.</b>");
			Log.softAssertThat(managePayee.getBICValue().equals(bic),
					"BIC is verified", "BIC is not verified", driver);
			Log.message("<b>Expected : Verifying the payeename Value.</b>");
			Log.softAssertThat(managePayee.getPayeeName().equals(name),
					"Payee name is verified", "Payee name is not verified",
					driver);
			Log.message("<b>Expected : Verifying the payee account number.</b>");
			Log.softAssertThat(managePayee.getPayeeAccountNumber()
					.equals(accNo), "Payee account number is verified",
					"Payee account number is not verified", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can add a new domestic payee in Domestic SEPA Payments payment method", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_004(String browser) {
		Log.testCaseInfo("Verify user can add a new domestic payee in Domestic SEPA Payments payment method");
		HashMap<String, String> values = tde.readData("TC003");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			ManagePayee managePayee = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoPersonWithInGBTabFromAddPayee()
					.gotoPayeeDetailsWithDSEPAPaymentMode()
					.enterPayeeDetailsSEPA(iban, name).clickOverviewDetails1()
					.continueAndSave().clickOnAccount(name, bic, accNo, iban);
			Log.message("<b>Expected : Verifying the payeename.</b>");
			Log.softAssertThat(managePayee.getPayeeName().equals(name),
					"Payee name is verified", "Payee name is not verified",
					driver);
			Log.message("<b>Expected : Verifying the Iban.</b>");
			Log.softAssertThat(managePayee.getPayeeIBAN().equals(iban),
					"IBAN is verified", "IBAN is not verified", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can add a company within GB searching by company name", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_005(String browser) {
		Log.testCaseInfo("Verify user can add a company within GB searching by company name");
		HashMap<String, String> values = tde.readData("TC004");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		String reference = values.get("Reference");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			ManagePayee managePayee = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoCompanyWithInGBTab().gotoPayeeDetails(name, reference)
					.clickOverviewDetails1().continueAndSave()
					.clickOnAccount(name, bic, reference, iban);
			Log.message("<b>Expected : Verifying the Reference.</b>");
			Log.softAssertThat(managePayee.getReference().equals(reference),
					"Reference is verified", "Reference is not verified",
					driver);
			Log.message("<b>Expected : Verifying the Nickname Value.</b>");
			Log.softAssertThat(managePayee.getNickName().equals(name),
					"Nick name is verified", "Nick name is not verified",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can add a company within GB searching by company Account details", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_006(String browser) {
		Log.testCaseInfo("Verify user can add a company within GB searching by company Account details");
		HashMap<String, String> values = tde.readData("TC005");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		String reference = values.get("Reference");
		String sortCode = values.get("SortCode");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			PayeeDetails payeeDetails = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoCompanyWithInGBTab()
					.gotoPayeeDetailsByAccount(accNo, sortCode, reference);
			String companyName = payeeDetails.getCompanyName();

			ManagePayee managePayee = payeeDetails.clickOverviewDetails1()
					.continueAndSave().selectName(companyName, reference);
			Log.message("<b>Expected : Verifying the Reference.</b>");
			Log.softAssertThat(managePayee.getReference().equals(reference),
					"Reference is verified", "Reference is not verified",
					driver);
			Log.message("<b>Expected : Verifying the Nick name.</b>");
			Log.softAssertThat(managePayee.getNickName().equals(companyName),
					"Nick name is verified", "Nick name is not verified",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can add international payee with Swift/BIC With Account Number payment method", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_007(String browser) {
		Log.testCaseInfo("Verify user can add international payee with Swift/BIC With Account Number payment method");
		HashMap<String, String> values = tde.readData("TC004");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		String reference = values.get("Reference");
		String companyName = values.get("CompanyName");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			ManagePayee managePayee = headers.clickOnSideBar()
					.clickOnManagePayees().clickOnAddANewPayeeButton()
					.gotoPersonOutSideGBTab()
					.gotoPayeeDetails(companyName, reference)
					.enterPayeeDetailsBICOptionOutGB(bic, accNo, name)
					.clickOverviewDetails1().continueAndSave()
					.selectID(name, accNo);

			Log.message("Expected : Verify the added international payee BIC value is displaying correctly");
			Log.softAssertThat(
					managePayee.getBICValue().equals(bic),
					"BIC value of added international payee is displaying correctly",
					"BIC value of added international payee is not displaying correctly",
					driver);

			Log.message("Expected : Verify the added international payee Payment name value is displaying correctly");
			Log.softAssertThat(
					managePayee.getPayeeName().equals(name),
					"Payment name value of added international payee is displaying correctly",
					"Payment name of added international payee value is not displaying correctly",
					driver);

			Log.message("Expected : Verify the added international payee Payment account number is displaying correctly");
			Log.softAssertThat(
					managePayee.getPayeeAccountNumber().equals(accNo),
					"Payment account number of added international payee is displaying correctly",
					"Payment account number is not displaying correctly",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify user can add international payee with Multiple NCC With Account Number payment method", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_008(String browser) {
		Log.testCaseInfo("Verify user can add international payee with Multiple NCC With Account Number payment method");
		HashMap<String, String> values = tde.readData("TC002");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		String country = values.get("Country");
		String currency = values.get("Currency");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;

		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			SideBar sidebar = headers.clickOnSideBar();

			ManagePayee managePayee = sidebar.clickOnManagePayees();

			NewPayee newpayee = managePayee.clickOnAddANewPayeeButton();

			InternationalPayee internPayee = newpayee.gotoPersonOutSideGBTab();

			internPayee.selectPayeeCountry(country);

			internPayee.clickEnterPaymentDetail();

			PayeeDetails payeedetails = internPayee
					.gotoPayeeDetailsWithMultipleNCCPaymentMode();

			payeedetails.enterPayeeDetailsNCC(bic, accNo, name);

			payeedetails.selectCurrencyInMultipleNCCPayeeDetails(currency);

			CheckPayeeDetails1 checkPayeeDetail = payeedetails
					.clickOverviewDetails1();

			checkPayeeDetail.continueAndSave().clickOnAccount(name, bic, accNo,
					iban);

			Log.message("Expected : Verify the BIC value of added international payee with Multiple NCC is displaying correctly");
			Log.softAssertThat(
					managePayee.getBICValue().equals(bic),
					"The BIC value of added international payee with Multiple NCC is displaying",
					"The BIC value of added international payee with Multiple NCC is not displaying",
					driver);

			Log.message("Expected : Verify the Payee name of added international payee with Multiple NCC is displaying correctly");
			Log.softAssertThat(
					managePayee.getPayeeName().equals(name),
					"Payee name of added international payee with Multiple NCC is displaying",
					"Payee name of added international payee with Multiple NCC is not displaying",
					driver);

			Log.message("Expected : Verify the Payee account number of added international payee with Multiple NCC is displaying correctly");
			Log.softAssertThat(
					managePayee.getPayeeAccountNumber().equals(accNo),
					"Payee account number of added international payee with Multiple NCC is displaying",
					"Payee account number of added international payee with Multiple NCC is not displaying",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether on clicking the manage payee link the saved payee details appears.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_009(String browser) {
		Log.testCaseInfo("Verify whether on clicking the manage payee link the saved payee details appears");
		HashMap<String, String> values = tde.readData("TC002");
		String iban = values.get("IBAN_Number");
		String name = values.get("PayeeName");
		String accNo = values.get("Acc_No");
		String bic = values.get("BIC");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AccountsPage account = null;
		TransactionsTab.PendingTab pendingTab = null;
		TransactionList txnList = null;
		Headers headers = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			headers = new Headers(driver).get();
			ManagePayee managePayee = headers.clickOnSideBar()
					.clickOnManagePayees();

			Log.message("Expected : Verify the saved payee details such as 'Payee Name', 'Payee Account', 'Reference' are displayed in the manage payee page");
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(Arrays.asList("tdPayeeName",
							"tdPayeeAccount", "tdPayeeReference"), managePayee,
							driver),
					"The saved payee details such as 'Payee Name', 'Payee Account', 'Reference' are displayed in the manage payee page",
					"Some details are not displayed in the manage payee page",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.testCaseResult();
			driver.quit();
			Log.endTestCase();
		}
	}
}