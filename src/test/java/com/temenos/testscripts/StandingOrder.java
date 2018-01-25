package com.temenos.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.StandingOrderTab;
import com.temenos.pages.StandingOrderTab.EditStadingOrders;
import com.temenos.pages.StandingOrderTab.StandingOrdersDetails;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.Utils;
import com.temenos.support.WebDriverFactory;

public class StandingOrder extends BaseTest {

	TestDataExtractor tde;

	@BeforeClass
	public void setTestData() {
		tde = new TestDataExtractor("StandingOrder");
	}

	@Test(description = "Verify the sorting option in the standing orders tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_002(String browser) {
		Log.testCaseInfo("Verify the sorting option in the standing orders tab");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_StandinOrder_003");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage.goToStandingOrderTab();
			
			
			// sorting option was not found, There are no arrows to click on the
			// column for sorting

			Log.fail("Sorting option not found in page",driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Show options in the list of standing order wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_003(String browser) {
		Log.testCaseInfo("Verify the Show options in the list of standing order wizard");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_003");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			Log.message("<b>Expected : Verify whether the Show Options has been populated with following options : Print, PDF Download and CSV download</b>");
			standingOrdTab.clickOnShowOptions();
			Log.softAssertThat(standingOrdTab.VerifyPageElementDisplayed(Arrays.asList
					("lnkPrintOption", "lnkPDFDownloadOption","lnkCSVDownloadOption"), standingOrdTab),
					"Print, PDF Download and CSV download options are displayed.",
					"There are few missing options.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the 'List of Standing orders' PDF download option in the list of standing order wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_005(String browser) {
		Log.testCaseInfo("Verify the 'List of Standing orders' PDF download option in the list of standing order wizard");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_005");
		String accountNumber = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			StandingOrderTab standingOrdTab = accountPage.goToStandingOrderTab();

			Utils.deleteFiles();

			standingOrdTab.clickOnShowOptions();

			Log.message("<b>Expected : Verify the show options having Print option, PDF Download and CSV download options menus</b>");

			Log.softAssertThat(
					standingOrdTab.VerifyPageElementDisplayed(Arrays.asList(
							"lnkPrintOption", "lnkPDFDownloadOption",
							"lnkCSVDownloadOption"), standingOrdTab),
					"Print option, PDF Download and CSV download options are displayed in the select option menus",
					"Select options are not displayed correctly", driver);

			standingOrdTab.clickOnPDFDownloadOption();

			Log.message("<b>Expected : Verify the 'List of Standing orders' PDF file is downloading when selecting the 'PDF Download' menu in 'Show Options'</b>");

			Log.softAssertThat(Utils.checkFile("pdf"),
					"List of Standing orders downloaded as PDF file",
					"List of Standing orders is not downloaded as PDF file",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the 'List of Standing orders' CSV download option in the list of standing order wizard", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_006(String browser) {
		Log.testCaseInfo("Verify the 'List of Standing orders' CSV download option in the list of standing order wizard");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_006");
		String accountNumber = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			Utils.deleteFiles();

			standingOrdTab.clickOnShowOptions();

			Log.message("<b>Expected : Verify the show options having Print option, PDF Download and CSV download options menus</b>");

			Log.softAssertThat(
					standingOrdTab.VerifyPageElementDisplayed(Arrays.asList(
							"lnkPrintOption", "lnkPDFDownloadOption",
							"lnkCSVDownloadOption"), standingOrdTab),
					"Print option, PDF Download and CSV download options are displayed in the select option menus",
					"Select options are not displayed correctly", driver);

			standingOrdTab.clickOnCSVDownloadOption();

			Log.message("<b>Expected : Verify the 'List of Standing orders' CSV file is downloading when selecting the 'CSV Download' menu in 'Show Options'</b>");

			Log.softAssertThat(Utils.checkFile("csv"),
					"List of Standing orders downloaded as CSV file",
					"List of Standing orders is not downloaded as CSV file",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the details of standing order is displayed", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_007(String browser) {
		Log.testCaseInfo("Verify whether the details of standing order is displayed");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_007");
		String accountNumber = values.get("account_number_from");
		
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			Log.message("<b>Expected : Verify the 'Payee name', 'Account', 'Currency', 'Amount', 'Frequency', 'Next Payment', 'End date' fields are displaying in standing order list table</b>");

			Log.softAssertThat(
					standingOrdTab.VerifyPageElementDisplayed(Arrays.asList(
							"payeeNameColumn", "payeeAccColumn",
							"currencyColumn", "amountColumn",
							"frequencyColumn", "nextPaymentColumn",
							"endDateColumn"), standingOrdTab),
					"Details like 'Payee name', 'Account', 'Currency', 'Amount', 'Frequency', 'Next Payment', 'End date' are displayed in Standing order list table",
					"Details in the standing order list are not displayed correctly",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the delete standing order option in the standing order detail page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_008(String browser) {
		Log.testCaseInfo("Verify the delete standing order option in the standing order detail page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_008");
		String accountNumber = values.get("account_number_from");
		
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			int beforeDelete = standingOrdTab.getNumberOfStandingOrder();

			StandingOrdersDetails standingOrderDetails = standingOrdTab
					.clickOnViewDetailsStandingOrdArrow();

			standingOrderDetails.clickOnStandOrdDetailsDelete();

			standingOrderDetails.clickOnStandOrdPopUpDelete();

			int afterDelete = standingOrdTab.getNumberOfStandingOrder();

			Log.message("<b>Expected : Verify the selected standing order is deleted</b>");

			Log.softAssertThat(afterDelete == (beforeDelete - 1),
					"The selected standing order is deleted from the list",
					"The selected standing order is not deleted from the list", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the selected standing order is not deleted when cancelling the delete option", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_009(String browser) {
		Log.testCaseInfo("Verify the selected standing order is not deleted when cancelling the delete option");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_009");
		String accountNumber = values.get("account_number_from");
		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			List<String> expectedTabs = Arrays.asList("tabTransactions",
					"tabTransfers", "tabPayments", "tabStandingOrders",
					"tabDirectDebts", "tabCheques", "tabAlerts");
			
			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage.goToStandingOrderTab();

			int beforeDelete = standingOrdTab.getNumberOfStandingOrder();

			StandingOrdersDetails standingOrderDetails = standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			standingOrderDetails.clickOnStandOrdDetailsDelete();
			Log.message("Trying to delete the Standing order",driver);

			standingOrderDetails.clickOnStandOrdPopUpCancel();
			Log.message("Cancelling the deletion of standing order",driver);

			standingOrderDetails.clickOnStandingOrdDetailBack();

			int afterDelete = standingOrdTab.getNumberOfStandingOrder();

			Log.message("<b>Expected : Verify the selected standing order is not deleted in Standing Order tab when cancelling the delete option</b>");

			Log.softAssertThat(afterDelete == beforeDelete,
					"The selected standing order is not deleted from the list",
					"The selected standing order is deleted from the list", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Edit option in the standing order details page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_010(String browser) {
		Log.testCaseInfo("Verify the Edit option in the standing order details page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_010");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			Log.message("<b>Expected : Verify the standing order edit page is having all the fields</b>");

			Log.softAssertThat(editStandingOrder.VerifyPageElementDisplayed(
					Arrays.asList("lblEditStandingOrderTitle"),
					editStandingOrder),
					"Standing Order edit page is having all the fields",
					"Standing Order edit page is not having all the fields", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Editable fields in the edit standing order details edit page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_011(String browser) {
		Log.testCaseInfo("Verify the Editable fields in the edit standing order details edit page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_011");
		String accountNumber = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			Log.message("<b>Expected : Verify the text boxes 'Amount', 'Payment Start date', 'End date', 'Frequency' are editable</b>");

			Log.softAssertThat(
					editStandingOrder.checkAmountTextBoxEditable()
							&& editStandingOrder
									.checkPaymentStartDateTextBoxEditable()
							&& editStandingOrder
									.checkPaymentEndDateTextBoxEditable()
							&& editStandingOrder
									.checkPaymentFrequencyTextBoxEditable(),
					"Amount, Payment Start date, End date, Frequency Text Boxes are Editable",
					"Some Text Boxes are not Editable", driver);

			Log.message("<b>Expected : Verify the text boxes 'Payee Name', 'Payee Account', 'Payee Currency' are not editable</b>");

			Log.softAssertThat(
					!editStandingOrder.checkPayeeNameEditable()
							&& !editStandingOrder.checkPayeeAccEditable()
							&& !editStandingOrder.checkPayeeCurrencyEditable(),
					"Payee Name, Payee Account, Payee Currency Text Boxes are not Editable",
					"Some Text Boxes are Editable", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the amount field in the edit standing order page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_StandinOrder_012(String browser) {
		Log.testCaseInfo("Verify the amount field in the edit standing order page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_012");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();
			String text = "abc";
			editStandingOrder.enterTextInAmountTextBox(text);
			editStandingOrder.clickOnEditSaveChanges();
			// Error is not throwing need to verified
			text = "12345678901234567890";
			editStandingOrder.enterTextInAmountTextBox(text);
			editStandingOrder.clickOnEditSaveChanges();
			// Error is not throwing need to verified

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the payment start date field in the edit standing order page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_013(String browser) {
		Log.testCaseInfo("Verify the payment start date field in the edit standing order page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_013");
		String accountNumber = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			String beforeDate = editStandingOrder.getPaymentStartDate();

			editStandingOrder.selectAnyUnselectedPaymentStartDateFromCalender();

			String afterDate = editStandingOrder.getPaymentStartDate();

			Log.message("<b>Expected : Verify 'Payment Start date' is accepting when new date is selected</b>");

			Log.softAssertThat(!beforeDate.equals(afterDate),
					"'Payment Start date' is accepting when new date is selected",
					"'Payment Start date' is not accepting when new date is selected", driver);
			
			Log.message("<b>Expected : Verify 'Payment Start date' is not accepting the alphabets</b>");

			Log.softAssertThat(editStandingOrder.checkStartDateNotAcceptAlphabet(),
					"'Payment Start date' is not accepting the alphabets",
					"'Payment Start date' is accepting the alphabets", driver);

			editStandingOrder.clickOnEditSaveChanges();

			StandingOrdersDetails stadingOrdersDetails = editStandingOrder
					.clickOnEditSaveChangesPopUp();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			String date = stadingOrdersDetails.getStartDate();

			Log.message("<b>Expected : Verify the 'Payment Start date' is updated in standing order details page</b>");

			Log.softAssertThat(date.equals(afterDate),
					"The 'Payment Start date' is updated in standing order details page",
					"The 'Payment Start date' is not updated in standing order details page", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the frequency field in the edit standing order page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_014(String browser) {
		Log.testCaseInfo("Verify the frequency field in the edit standing order page");
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_014");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			String beforeFreq = editStandingOrder.getPaymentFrequency();

			editStandingOrder.selectDifferentFrequency();

			String afterFreq = editStandingOrder.getPaymentFrequency();

			Log.message("<b>Expected : Verify 'Payment Frequency' is changing when new frequency is selected</b>");

			Log.softAssertThat(!beforeFreq.equals(afterFreq),
					"'Payment Frequency' is changing when new frequency is selected",
					"'Payment Frequency' is not changing", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Final payment field in the edit standing order page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = false)
	public void TC_TCIB_StandinOrder_015(String browser) {
		Log.testCaseInfo("Verify the Final payment field in the edit standing order page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_015");
		String accountNumber = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			String dateBefore = editStandingOrder.getPaymentEndDate();

			editStandingOrder.selectAnyUnselectedFinalPaymentDateFromCalender();

			String dateAfter = editStandingOrder.getPaymentEndDate();

			Log.message("<b>Expected : Verify 'Payment End date' is updating when selecting the new date</b>");

			Log.softAssertThat(!dateBefore.equals(dateAfter),
					"'Payment End date' is updating when selecting the new date",
					"'Payment End date' is not updating when selecting the new date", driver);

			Log.message("<b>Expected : Verify 'Payment End date' is not accepting the alphabets</b>");

			Log.softAssertThat(
					editStandingOrder.checkFinalDateNotAcceptAlphabet(),
					"'Payment End date' will not accept the alphabets",
					"'Payment End date' is accpeting alphabets", driver);

			editStandingOrder.clickOnEditSaveChanges();

			StandingOrdersDetails standingOrdersDetails = editStandingOrder
					.clickOnEditSaveChangesPopUp();

			String date = standingOrdersDetails.getEndDateFromFirstRow();

			Log.message("<b>Expected : Verify updated 'Payment End date' is saved in Standing order details page</b>");

			Log.softAssertThat(date.equals(dateAfter),
					"Updated 'Payment End date' is saved in Standing order details page",
					"Updated 'Payment End date' is not saved in Standing order details page", driver);

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			editStandingOrder = standingOrdTab.clickOnStandOrdDetailsEdit();

			String startDate = editStandingOrder.getPaymentStartDate();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date newStartDate = new Date();
			newStartDate = df.parse(startDate);
			Calendar c = Calendar.getInstance();
			c.setTime(newStartDate);
			c.add(Calendar.DATE, -2);
			Date currentDateMinusTwo = c.getTime();
			String dateStr = df.format(currentDateMinusTwo);

			editStandingOrder.enterPaymentEndDate(dateStr);

			Log.fail("The error message for wrong End Date is not updating");

			// date = standingOrdersDetails.getEndDateFromFirstRow();

			// Log.message("<b>Expected : Verify 'Payment End date' is not accepting the wrong date</b>");
			//
			// Log.softAssertThat(!date.equals(dateStr),
			// "Payment End date is not changing when wrong date is given",
			// "Payment End date is changing when wrong date is given",driver);

			// The start date is not applying after clicking save changes so end
			// date is changed if wrong date is given

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the standing order details are getting saved when editing it in Standing order details page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_016(String browser) {
		Log.testCaseInfo("Verify the standing order details are getting saved when editing it in Standing order details page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_016");
		String accountNumber = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			StandingOrdersDetails standingOrdersDetails = standingOrdTab
					.clickOnViewDetailsStandingOrdArrow();

			HashMap<String, String> StndDetailBefore = standingOrdersDetails
					.getDetailsOfStandingOrder();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			editStandingOrder.selectDifferentFrequency();

			editStandingOrder.selectAnyUnselectedPaymentStartDateFromCalender();

			editStandingOrder.selectAnyUnselectedFinalPaymentDateFromCalender();

			String amt = editStandingOrder.getAmountFromTextBox();

			float amt1 = Float.valueOf(amt);

			amt1 = amt1 + 10;

			amt = Float.toString(amt1);
			
			Log.message("<b>Expected : Verify the error message is displaying when mandatory field is not filled</b>");

			editStandingOrder.enterTextInAmountTextBox("");

			editStandingOrder.clickOnEditSaveChanges();

			editStandingOrder.selectSaveChangesPopUpClose();

			Log.message("Removing the mandatory field value and Saving the changes");

			Log.softAssertThat(
					editStandingOrder.VerifyPageElementDisplayed(
							Arrays.asList("lblErrorMessage_Amt"),
							editStandingOrder),
					"Error message is displaying if mandatory field is not filled",
					"Error message is not displaying if mandatory field is not filled",
					driver);
			
			Log.message("<b>Expected : Verify the entered details are modified when all the mandatory fields are filled and saving the changes</b>");

			editStandingOrder.enterTextInAmountTextBox(amt);
			
			editStandingOrder.clickOnEditSaveChanges();

			standingOrdersDetails = editStandingOrder
					.clickOnEditSaveChangesPopUp();

			Log.message("Filling all the mandatory fields and saving the changes",driver);
			
			standingOrdersDetails = standingOrdTab
					.clickOnViewDetailsStandingOrdArrow();

			HashMap<String, String> StndDetailAfter = standingOrdersDetails
					.getDetailsOfStandingOrder();
			
			Log.softAssertThat(
					!Utils.compareTwoHashMap(StndDetailBefore, StndDetailAfter),
					"The entered details are modified when all the mandatory fields are filled and saving the changes",
					"The entered details are not modified when all the mandatory fields are filled and saving the changes",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Until further notice check box in the edit standing order page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_017(String browser) {
		Log.testCaseInfo("Verify the Until further notice check box in the edit standing order page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_017");
		String accountNumber = values.get("account_number_from");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			editStandingOrder.selectFurtherNoticeCheckBox(false);

			Log.message("<b>Expected : Verify 'Further Notice' check box is disabling</b>");

			Log.softAssertThat(!editStandingOrder.getFurtherNoticeCheckBoxStatus(),
					"'Further Notice' check box is disabled", "'Further Notice' check box is not disabled",
					driver);

			editStandingOrder.selectFurtherNoticeCheckBox(true);

			Log.message("<b>Expected: Verify 'Further Notice' check box is enabling</b>");

			Log.softAssertThat(editStandingOrder.getFurtherNoticeCheckBoxStatus(),
					"'Further Notice' check box is enabled", "'Further Notice' check box is not enabled",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Cancel option while editing standing order page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_018(String browser) {
		Log.testCaseInfo("Verify the Cancel option while editing standing order page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_018");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having all the 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			StandingOrdersDetails standingOrdersDetails = standingOrdTab
					.clickOnViewDetailsStandingOrdArrow();

			HashMap<String, String> StndDetailBefore = standingOrdersDetails
					.getDetailsOfStandingOrder();

			EditStadingOrders editStandingOrder = standingOrdTab
					.clickOnStandOrdDetailsEdit();

			editStandingOrder.selectDifferentFrequency();

			editStandingOrder.selectAnyUnselectedPaymentStartDateFromCalender();

			editStandingOrder.selectAnyUnselectedFinalPaymentDateFromCalender();
			
			String amt = editStandingOrder.getAmountFromTextBox();

			Log.message("Filling all the mandatory fields with different data", driver);
			
			float amt1 = Float.valueOf(amt);
			amt1 = amt1 + 10;
			amt = Float.toString(amt1);

			Log.message("<b>Expected : Verify the standing order details are not applied when cancelling the update</b>");
			
			editStandingOrder.enterTextInAmountTextBox(amt);
			
			editStandingOrder.selectCancelEdit();
			
			HashMap<String, String> StndDetailAfter = standingOrdersDetails
					.getDetailsOfStandingOrder();

			Log.softAssertThat(
					Utils.compareTwoHashMap(StndDetailBefore, StndDetailAfter),
					"The standing order details are not applied when cancelling the update",
					"The standing order details are applied when cancelling the update",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify application navigated to 'Standing order main' page when selcting back from 'Standing order details' page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_019(String browser) {
		Log.testCaseInfo("Verify application navigated to 'Standing order main' page when selcting back from 'Standing order details' page");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_StandinOrder_019");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			StandingOrdersDetails standingOrdersDetails = standingOrdTab
					.clickOnViewDetailsStandingOrdArrow();

			Log.message("<b>Expected : Verify application navigated to 'Standing order main' page when selecting back from 'Standing order details' page</b>");
			
			Log.message("Selecting the 'Back' option in the Standing order detail page", driver);
			
			standingOrdTab = standingOrdersDetails
					.clickOnStandingOrdDetailBack();

			Log.softAssertThat(
					standingOrdTab.VerifyPageElementDisplayed(
							Arrays.asList("lblStandingOrderHeading"),
							standingOrdTab),
					"Application navigated to 'Standing order main' page when selecting back from 'Standing order details' page",
					"Application is not navigated to 'Standing order main' page when selecting back from 'Standing order details' page",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the fields in the standing order details page are not editable", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class, enabled = true)
	public void TC_TCIB_StandinOrder_020(String browser) {
		Log.testCaseInfo("Verify the fields in the standing order details page are not editable");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde
				.readData("TC_TCIB_StandinOrder_020");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			Log.message("<b>Expected : Verify whether the Account page is having - 'Transaction', 'Transfer', 'Payments', 'Standing Orders', 'Direct Debts',"
					+ " 'Cheques', 'Alerts' tabs</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(expectedTabs, accountPage, driver), 
					"Selected Account page is having 'Transaction', 'Transfer', 'Payments', 'Standing Orders'"
					+ ", 'Direct Debts', 'Cheques', 'Alerts' tabs",
					"Selected Account page is not having with 7 tabs.", driver);

			StandingOrderTab standingOrdTab = accountPage
					.goToStandingOrderTab();

			StandingOrdersDetails standingOrdersDetails = standingOrdTab.clickOnViewDetailsStandingOrdArrow();

			Log.message("<b>Expected : Verify the fields in the standing order details page are not editable</b>");

			Log.softAssertThat(
					!standingOrdersDetails.checkFrequencyTextBoxEditable()
							&& !standingOrdersDetails
									.checkEndDateTextBoxEditable()
							&& !standingOrdersDetails
									.checkStartDateTextBoxEditable()
							&& !standingOrdersDetails
									.checkCurrencyTextBoxEditable()
							&& !standingOrdersDetails
									.checkAmountTextBoxEditable()
							&& !standingOrdersDetails
									.checkReferenceTextBoxEditable()
							&& !standingOrdersDetails
									.checkPayeeAccTextBoxEditable()
							&& !standingOrdersDetails
									.checkPayeeNameTextBoxEditable(),
					"The 'Start date', 'End date', 'Currency', 'Amount', 'Reference', 'Payee Account', "
					+ "'Payee Name' fields are not editable in the details page",
					"Some fields are editable in the details page", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

}