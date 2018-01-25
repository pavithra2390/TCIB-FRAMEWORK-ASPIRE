package com.temenos.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.DiscoverPage;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WaitUtil;
import com.temenos.support.WebDriverFactory;

public class DiscoverTest extends BaseTest {

	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("Discover");
	}

	@Test(description = "Verify application Navigated to Discover page and discover page details are displayed", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_001(String browser) {
		Log.testCaseInfo("Verify application Navigated to Discover page and discover page details are displayed");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC001");

		List<String> expectedElements = Arrays.asList("cA_SALabel",
				"termDepositsLabel");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			Log.message("<b>Expected : Verify application Navigated to Discover page and Current and savings account and Term deposit option are displayed</b>");
			discoverPage = homePage.gotoDiscoverPage();
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedElements,
							discoverPage, driver),
					"Application Navigated to Discover Page succesfully and Current and savings account and Term deposit option are displayed",
					"Not navigated to Discover Page and Current and savings account and Term deposit option are not displayed",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	// Combined TC001&TC002 Test script//

	@Test(description = "Verify the New Current Account Creation page details are displayed", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_003(String browser) {
		Log.testCaseInfo("Verify the New Current Account Creation page details are displayed");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		List<String> expectedElements = Arrays
				.asList("btnProductGroupForAccCreation");
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			Log.message("<b>Expected : Verify the application navigate to New Current Account Creation page</b>");
			discoverPage.selectCASA();
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedElements,
							discoverPage, driver),
					"Application navigated succesfully to New Current Account creation Page.",
					"Application not navigated to New Current Account creation Page.",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the New Term Deposits page details are displayed ", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_002(String browser) {
		Log.testCaseInfo("Verify the New Term Deposits page details are displayed");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		List<String> expectedElements = Arrays.asList("btnProductGroup",
				"btnstatus");
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.verifyDiscoverPageDetails();
			Log.message("<b>Expected : Verify the application navigated to New Term Deposits creation page </b>");
			discoverPage.selectTermDeposits();
			Log.softAssertThat(
					verifyPageElementsAreDisplayed(expectedElements,
							discoverPage, driver),
					"Application navigated succesfully to New Term Deposits creation Page.",
					"Application not navigated to New Term Deposits creation Page.",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 3 month term and pay the amount Immediately from account ", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_019(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 3 month term and pay the amount Immediately from account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_019");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			discoverPage.selectAccount(Account);
			Log.message("All the Deposit details are entered and Payment is done Immediately after deposit creation");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message("<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 3 month term and pay the amount Immediately from account</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Successfully Created the Term Deposits details with selection of Short Term Deposit with 3 month term and payed the amount Immediately from account",
					"Not able to Create the Term Deposits details with selection of Short Term Deposit with 3 month term and pay the amount Immediately from account",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 6 month term and pay the amount Immediately from account ", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_020(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 6 month term and pay the amount Immediately from account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_020");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			discoverPage.selectAccount(Account);
			Log.message("All the Deposit details are entered and Payment is done Immediately after deposit creation");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message("<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 6 month term and pay the amount Immediately from account</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Successfully Created the Term Deposits details with selection of Short Term Deposit with 6 month term and payed the amount Immediately from account",
					"Not able to Create the Term Deposits details with selection of Short Term Deposit with 6 month term and pay the amount Immediately from account",
					driver);
			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 9 month term and pay the amount Immediately from account ", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_021(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 9 month term and pay the amount Immediately from account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_021");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			discoverPage.selectAccount(Account);
			Log.message("All the Deposit details are entered and Payment is done Immediately after deposit creation");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message("<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 9 month term and pay the amount Immediately from account</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Successfully Created the Term Deposits details with selection of Short Term Deposit with 9 month term and payed the amount Immediately from account",
					"Not able to Create the Term Deposits details with selection of Short Term Deposit with 9 month term and pay the amount Immediately from account",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 12 month term and pay the amount Immediately from account ", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_022(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 12 month term and pay the amount Immediately from account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_022");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			discoverPage.selectAccount(Account);
			Log.message("All the Deposit details are entered and Payment is done Immediately after deposit creation");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message(
					"<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 12 month term and pay the amount Immediately from account</b>",
					driver);
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Successfully Created the Term Deposits details with selection of Short Term Deposit with 12 month term and payed the amount Immediately from account",
					"Not able to Create the Term Deposits details with selection of Short Term Deposit with 12 month term and pay the amount Immediately from account",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 3 month term and Payment should not be done Immediately", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_023(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 3 month term and Payment should not be done Immediately");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_023");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			Log.message("All the Deposit details are entered and Payment is not done Immediately");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message("<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 3 month term and make sure Payment is not be done Immediately</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Succesfully Created the Term Deposits details with selection of Short Term Deposit with 3 month term and Payment is not done Immediately",
					"Not able to Create the Term Deposits details with selection of Short Term Deposit with 3 month term.",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 6 month term and Payment should not not done Immediately", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_024(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 6 month term and Payment should not be done Immediately");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_024");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			Log.message("All the Deposit details are entered and Payment is not done Immediately");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message("<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 6 month term and make sure Payment is not be done Immediately</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Successfully Created the Term Deposits details with selection of Short Term Deposit with 6 month term and payment is not immediately",
					"Not able to Create the Term Deposits details with selection of Short Term Deposit with 6 month term",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 9 month term and Payment should not not done Immediately", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_025(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 9 month term and Payment should not not done Immediately");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_025");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			Log.message("All the Deposit details are entered and Payment is not done Immediately");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message("<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 9 month term and make sure payment is not done Immediately</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Successfully created the Term Deposits details with selection of Short Term Deposit with 9 month term and payment is not done Immediately.",
					"Not able to create the Term Deposits details with selection of Short Term Deposit with 9 month term.",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Term Deposits is created with selection of Short Term Deposit with 12 month term and Payment should not be done Immediately", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_026(String browser) {
		Log.testCaseInfo("Verify the Term Deposits is created with selection of Short Term Deposit with 12 month term and Payment should not be done Immediately");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_026");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");
		String Term = values.get("Term");
		String amount = values.get("Amount");
		String status = values.get("Status");
		String Account = values.get("Account");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectTermDeposits();
			Log.message("Short Term Deposit is selected");
			discoverPage.selectProductGroup(Value);
			discoverPage.selectProduct(Product);
			discoverPage.selectTerm(Term);
			discoverPage.enterAmount(amount);
			discoverPage.PayImmediately(status);
			Log.message("All the Deposit details are entered and Payment is not done Immediately");
			discoverPage.clickOnContinueInNewDepositCreation();
			Log.message("Navigated to create new deposit creation page", driver);
			discoverPage.clickOnCreateNewDepositCreation();
			discoverPage.clickOnCreateNewDepositCreation();
			String Colour = discoverPage.getAttributeValueOfUniqueNumber();
			Log.message("<b>Expected : Should Create the Term Deposits details with selection of Short Term Deposit with 12 month term and Make sure payment is not done Immediately</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueId()),
					"Successfully Created the Term Deposits details with selection of Short Term Deposit with 12 month term and Payment is not done Immediately",
					"Not able to Create the Term Deposits details with selection of Short Term Deposit with 12 month term.",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Savings accounts is created with selection of Conditional Savings Account", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_013(String browser) {
		Log.testCaseInfo("Verify the Savings accounts is created with selection of Conditional Savings Account");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_013");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectCASA();
			discoverPage.selectProductGroupForAccountCreation(Value);
			discoverPage.selectProductForAccountCreation(Product);
			Log.assertThat(
					discoverPage.getTextForCurrency().contains("USD"),
					"The Currency displayed is USD in savings account creation",
					"The Currency displayed is not USD in savings account creation",
					driver);
			discoverPage.clickOnContinueInNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			Log.message("Savings Account is created");
			String Colour = discoverPage
					.getAttributeValueOfUniqueNumberForAcc();
			Log.message("<b>Expected : Verify the Savings accounts is created with selection of Conditional Savings Account from product option</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueIdForAcc()),
					"Successfully Created the Savings accounts details with selection of Conditional Savings Account",
					"Unable to Create the Savings accounts details with selection of Conditional Savings Account",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Savings accounts is created", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_014(String browser) {
		Log.testCaseInfo("Verify the Savings accounts is created");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_014");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectCASA();
			discoverPage.selectProductGroupForAccountCreation(Value);
			discoverPage.selectProductForAccountCreation(Product);
			Log.assertThat(
					discoverPage.getTextForCurrency().contains("USD"),
					"The Currency displayed is USD in savings account creation",
					"The Currency displayed is not USD in savings account creation",
					driver);
			discoverPage.clickOnContinueInNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			Log.message("Savings Account is created");
			String Colour = discoverPage
					.getAttributeValueOfUniqueNumberForAcc();
			Log.message("<b>Expected : Verify the Savings accounts is created</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueIdForAcc()),
					"Successfully Created the Savings accounts by selecting saving account from product",
					"Unable to Create the Savings accounts", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Savings accounts is created with selection of Savings Account (Child) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_015(String browser) {
		Log.testCaseInfo("Verify the Savings accounts is created with selection of Savings Account (Child) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_015");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectCASA();
			discoverPage.selectProductGroupForAccountCreation(Value);
			discoverPage.selectProductForAccountCreation(Product);
			Log.assertThat(
					discoverPage.getTextForCurrency().contains("USD"),
					"The Currency displayed is USD in savings account creation",
					"The Currency displayed is not USD in savings account creation",
					driver);
			discoverPage.clickOnContinueInNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			Log.message("Savings Account is created");
			String Colour = discoverPage
					.getAttributeValueOfUniqueNumberForAcc();
			Log.message("<b>Expected : Verify the Savings accounts is created with selection of Savings Account (Child) from product option</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueIdForAcc()),
					"Successfully Created the Savings accounts with selection of Savings Account (Child) from product option",
					"Unable to Create the Savings accounts details with selection of Savings Account (Child) from product option",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Savings accounts is created with selection of Savings Account (Foreign Resident) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_016(String browser) {
		Log.testCaseInfo("Verify the Savings accounts is created with selection of Savings Account (Foreign Resident) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_016");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectCASA();
			discoverPage.selectProductGroupForAccountCreation(Value);
			discoverPage.selectProductForAccountCreation(Product);
			discoverPage.clickOnContinueInNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			Log.message("Savings Account is created");
			String Colour = discoverPage
					.getAttributeValueOfUniqueNumberForAcc();
			Log.message("<b>Expected : Verify the Savings accounts is created with selection of Savings Account (Foreign Resident) from product option</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueIdForAcc()),
					"Successfully created the Savings accounts with selection of Savings Account (Foreign Resident) from product option",
					"Unable to Create the Savings accounts details with selection of Savings Account (Foreign Resident) from product option",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Savings accounts is created with selection of Savings Account (Notice) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_017(String browser) {
		Log.testCaseInfo("Verify the Savings accounts is created with selection of Savings Account (Notice) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_017");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectCASA();
			discoverPage.selectProductGroupForAccountCreation(Value);
			discoverPage.selectProductForAccountCreation(Product);
			Log.assertThat(
					discoverPage.getTextForCurrency().contains("USD"),
					"The Currency displayed is USD in savings account creation",
					"The Currency displayed is not USD in savings account creation",
					driver);
			discoverPage.clickOnContinueInNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			Log.message("Savings Account is created");
			String Colour = discoverPage
					.getAttributeValueOfUniqueNumberForAcc();
			Log.message("<b>Expected : Verify the Savings accounts is created with selection of Savings Account (Notice) from product option</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueIdForAcc()),
					"Successfully created the Savings accounts details with selection of Savings Account (Notice) from product option",
					"Unable to Create the Savings accounts details with selection of Savings Account (Notice) from product option",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Savings accounts is created with selection of Savings Account (Staff) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_Discover_018(String browser) {
		Log.testCaseInfo("Verify the Savings accounts is created with selection of Savings Account (Staff) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		Properties prop = new Properties();

		// from account should have 1000 amount value
		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_018");
		String Value = values.get("ProductGroup");
		String Product = values.get("Product");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();
			discoverPage.selectCASA();
			discoverPage.selectProductGroupForAccountCreation(Value);
			discoverPage.selectProductForAccountCreation(Product);
			Log.assertThat(
					discoverPage.getTextForCurrency().contains("USD"),
					"The Currency displayed is USD in savings account creation",
					"The Currency displayed is not USD in savings account creation",
					driver);
			discoverPage.clickOnContinueInNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			discoverPage.clickOnCreateNewAccCreation();
			Log.message("Savings Account is created");
			String Colour = discoverPage
					.getAttributeValueOfUniqueNumberForAcc();
			Log.message("<b>Expected : Verify the Savings accounts is created with selection of Savings Account (Staff) from product option</b>");
			Log.softAssertThat(
					(Colour.equals("rgba(35, 193, 98, 1)") && discoverPage
							.verifyUniqueIdForAcc()),
					"Successfully created the Savings accounts details with selection of Savings Account (Staff) from product option",
					"Unable to Create the Savings accounts details with selection of Savings Account (Staff) from product option",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current account is created with selection of current account from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_004(String browser) {
		Log.testCaseInfo("Verify the current account is created with selection of current account from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_004");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Current Account (General Charge) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_005(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Current Account (General Charge) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_005");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Current Account (Linked Rate) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_006(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Current Account (Linked Rate) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_006");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Current Account (Preferred)from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_007(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Current Account (Preferred)from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_007");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Current Account (Premium) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_008(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Current Account (Premium) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_008");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Current Account (Staff) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_009(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Current Account (Staff) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_009");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Current Account (Student) from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_010(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Current Account (Student) from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_010");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Group Checking Account from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_011(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Group Checking Account from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_011");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

			WaitUtil.sleep(5);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the current accounts is created with selection of Group Subsidiary Account from product option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_Discover_012(String browser) {
		Log.testCaseInfo("Verify the current accounts is created with selection of Group Subsidiary Account from product option");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		DiscoverPage discoverPage = null;

		HashMap<String, String> values = tde.readData("TC_TCIB_Discover_012");
		String product_group = values.get("ProductGroup");
		String product = values.get("Product");
		String curr = values.get("Currency");

		// from account should have 1000 amount value
		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			discoverPage = homePage.gotoDiscoverPage();

			discoverPage.selectCASA();

			discoverPage.selectProductGroupForAccountCreation(product_group);

			discoverPage.selectProductForAccountCreation(product);

			String currency = discoverPage.getTextForCurrency();

			Log.assertThat(currency.equalsIgnoreCase(curr),
					"The Currency is correctly displayed",
					"The Currency is not displaying correctly", driver);

			discoverPage.clickOnContinueInNewAccCreation();

			discoverPage.clickOnCreateNewAccCreation();

			// Expected creation output is not displaying

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