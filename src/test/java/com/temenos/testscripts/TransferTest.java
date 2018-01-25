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

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.temenos.pages.AccountsPage;
import com.temenos.pages.AllAccountsTab;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.QuickTransferTab;
import com.temenos.pages.QuickTransferTab.manageTransferred;
import com.temenos.pages.QuickTransferTab.moneyTransferedPage;
import com.temenos.pages.TransactionList;
import com.temenos.pages.TransfersTab;
import com.temenos.pojo.Transaction;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WaitUtil;
import com.temenos.support.WebDriverFactory;

public class TransferTest extends BaseTest {
	
	TestDataExtractor tde;
	
	@BeforeClass
	public void setTestData(){
		tde = new TestDataExtractor("Transfers");		
	}
		
	@Test(description="Verify whether the number of transfers for processing is displayed", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_001(String browser) {
		Log.testCaseInfo("Verify whether the number of transfers for processing is displayed");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_001");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");
			
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
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
			
			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			Log.message("<b>Expected : Verify the number of transfers for processing is displayed</b>");
			
			Log.softAssertThat(verifyPageElementsAreDisplayed(Arrays.asList("txtNoOfTransfers"), transferTab, driver), 
					"The number of transfers available for processing is displayed", 
					"The number of transfers available for processing is not displayed",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}	
	
	@Test(description="Verify the selection of 'To' account from the dropdown", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_002(String browser) {
		Log.testCaseInfo("Verify the selection of 'To' account from the dropdown");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_002");
		String accountNumber = values.get("account_number_from");
		String accountNumberTo = values.get("account_number_to");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
				"tabTransfers", "tabPayments", "tabStandingOrders",
				"tabDirectDebts", "tabCheques", "tabAlerts");
			
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			
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
			
			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			Log.message("<b>Expected : Verify the selected 'To' account is displayed in the dropdown</b>");
			
			transferTab.selectCreditAccount(accountNumberTo);
			
			Log.message("Selecting a new 'To' account for transaction",driver);
			
			Log.softAssertThat(transferTab.getSelectedToAcc().toLowerCase().contains(accountNumberTo.toLowerCase()), 
					"The selected 'To' account is displayed in the drop down box", 
					"The selected 'To' account is not displayed in the drop down box",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}	
	
	@Test(description="Verify whether the transfer confirmation page is having the transfer details", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_003(String browser) {
		Log.testCaseInfo("Verify whether the transfer confirmation page is having the transfer details");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_003");
		String accountNumber = values.get("account_number_from");
		String accountNumberTo = values.get("account_number_to");
		String amount = values.get("amount");
		
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
		
			homePage = new HomePage(driver).get(); 
			allAccountsTab = homePage.goToAllAccountsTab();									
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			
			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			Log.message("<b>Expected : Verify whether the transfer confirmation page is having - 'Transfer Head','From Acc Number',"
					+ "'To Acc Number','Transferred Amount','Payment type','Home page button','Make Another Transfer button',"
					+ "'PDF link','Print Link','Unique Number'</b>");
			
			transferTab.selectCreditAccount(accountNumberTo);
			accountNumberTo = accountNumberTo.substring(0, 5);
			transferTab.enterAmount(amount);
			transferTab.selectImmediatePayment();
			transferTab.selectoverViewTransferDetailsButton();
			transferTab.selectTransferButton();
			
			Log.message("The transfer account details are entered and transferring the amount",driver);

			Log.softAssertThat(verifyPageElementsAreDisplayed(Arrays.asList("transferedHeadLabel","transferedFromAccountNumber","transferedToAccountNumber","transferedAmount","TransferedPaymentType",
					"goToHomePage","makeAnotherTransferButton","downloadPdfLink","printLink","unqiueNumber"), transferTab, driver), 
					"Transfer is successful and 'Transfer Head','From Acc Number','To Acc Number','Transferred Amount','Payment type','Home page button',"
					+ "'Make Another Transfer button','PDF link','Print Link','Unique Number' "
					+ "are displaying in the transfer confirmation screen", 
					"Transfer is not successfull and details are not displaying correctly",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
	
	@Test(description="Verify whether the transfer can be done immediate transfer mode", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_004(String browser) {
		Log.testCaseInfo("Verify whether the transfer can be done immediate transfer mode");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_004");
		String accountNumber = values.get("account_number_from");
		String accountNumberTo = values.get("account_number_to");
		String amount = values.get("amount");
		
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
		
			homePage = new HomePage(driver).get(); 
			allAccountsTab = homePage.goToAllAccountsTab();									
			accountPage = allAccountsTab.clickOnAccount(accountNumber);

			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			
			Log.message("<b>Expected : Verify whether the transfer confirmation page is having - 'Transfer Head','From Acc Number',"
					+ "'To Acc Number','Transferred Amount','Payment type','Home page button','Make Another Transfer button',"
					+ "'PDF link','Print Link','Unique Number'</b>");
			
			transferTab.selectCreditAccount(accountNumberTo);
			accountNumberTo = accountNumberTo.substring(0, 5);
			transferTab.enterAmount(amount);
			transferTab.selectImmediatePayment();
			transferTab.selectoverViewTransferDetailsButton();
			transferTab.selectTransferButton();
			
			Log.message("The transfer account details are entered and transferring the amount in immediate mode",driver);
			
			Log.softAssertThat(verifyPageElementsAreDisplayed(Arrays.asList("transferedHeadLabel","transferedFromAccountNumber","transferedToAccountNumber","transferedAmount","TransferedPaymentType",
					"goToHomePage","makeAnotherTransferButton","downloadPdfLink","printLink","unqiueNumber"), transferTab, driver), 
					
					"Transfer is successful and 'Transfer Head','From Acc Number','To Acc Number','Transferred Amount','Payment type','Home page button','Make Another"
					+ "transfer button','PDF link','Print Link','Unique Number' are all displaying", 
					"Transfer is not successfull and details are not displaying correctly",driver);
			
			Log.message("<b>Expected : Verify whether the transfer can be done immediate transfer mode</b>");
			Log.softAssertThat(transferTab.getTransferMessage().toLowerCase().contains("immediately"),
					"Amount transfer can be done by immediate transfer mode!",
					"Amount transfer is not working by immediate transfer mode!",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
	
	@Test(description="Verify whether the transfer can be done in a future date", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_005(String browser) {
		Log.testCaseInfo("Verify whether the transfer can be done in a future date");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_005");
		String accountNumber = values.get("account_number_from");
		String accountNumberTo = values.get("account_number_to");
		String amount = values.get("amount");
		
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
		
			homePage = new HomePage(driver).get(); 
			allAccountsTab = homePage.goToAllAccountsTab();									
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			
			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			transferTab.selectCreditAccount(accountNumberTo);
			accountNumberTo = accountNumberTo.substring(0, 5);
			transferTab.enterAmount(amount);
			transferTab.selectFutureDate();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dateobj = new Date();
			df.format(dateobj);
			Calendar c = Calendar.getInstance();
	        c.setTime(dateobj);
	        c.add(Calendar.DATE, 2);
	        Date currentDatePlusOne = c.getTime();
	        String dateStr = df.format(currentDatePlusOne);
			transferTab.enterFutureDate(dateStr);
			transferTab.selectoverViewTransferDetailsButton();
			
			Log.message("<b>Expected : Verify whether the transfer confirmation page is having - 'Transfer Head','From Acc Number',"
					+ "'To Acc Number','Transferred Amount','Payment type','Home page button','Make Another Transfer button',"
					+ "'PDF link','Print Link','Unique Number'</b>");
			
			transferTab.selectTransferButton();
			Log.message("The transfer account details are entered and transferring the amount for a future date",driver);
			
			Log.softAssertThat(transferTab.VerifyPageElementDisplayed(Arrays.asList("transferedHeadLabel","transferedFromAccountNumber","transferedToAccountNumber","transferedAmount","TransferedPaymentType",
					"goToHomePage","makeAnotherTransferButton","downloadPdfLink","printLink","unqiueNumber"), transferTab), 
					"Transfer is successful and 'Transfer Head','From Acc Number','To Acc Number','Transferred Amount','Payment type','Home page button','Make Another"
					+ "transfer button','PDF link','Print Link','Unique Number' are all displaying", 
					"Transfer is not successfull and details are not displaying correctly",driver);
			
			Log.message("<b>Expected : Verify whether the transfer can be done in a future date</b>");
			Log.softAssertThat(transferTab.getTransferMessage().toLowerCase().contains("future"),
					"The transfer can be done in a future date!",
					"The transfer cannot be done in a future date!",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
	
	@Test(description="Verify whether the transfer can be done in a recurring mode", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_006(String browser) {
		Log.testCaseInfo("Verify whether the transfer can be done in a recurring mode");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_006");
		String accountNumber = values.get("account_number_from");
		String accountNumberTo = values.get("account_number_to");
		String amount = values.get("amount");
		
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
		
			homePage = new HomePage(driver).get(); 
			allAccountsTab = homePage.goToAllAccountsTab();									
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			
			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			transferTab.selectCreditAccount(accountNumberTo);
			accountNumberTo = accountNumberTo.substring(0, 5);
			transferTab.enterAmount(amount);
			transferTab.selectRecurringPayment();
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dateobj = new Date();
			df.format(dateobj);
			Calendar c = Calendar.getInstance();
	        c.setTime(dateobj);
	        c.add(Calendar.DATE, 2);
	        Date currentDatePlusOne = c.getTime();
	        String dateStr = df.format(currentDatePlusOne);
			transferTab.enterPaymentStartDate(dateStr);
			
			transferTab.selectFrequency("Weekly");
			
			c.add(Calendar.DATE, 2);
			c.add(Calendar.MONTH, 3);
			currentDatePlusOne = c.getTime();
	        dateStr = df.format(currentDatePlusOne);
			transferTab.enterPaymentUntilDate(dateStr);
			
			transferTab.selectoverViewTransferDetailsButton();
			
			Log.message("<b>Expected : Verify whether the transfer confirmation page is having - 'Transfer Head','From Acc Number',"
					+ "'To Acc Number','Transferred Amount','Payment type','Home page button','Make Another Transfer button',"
					+ "'PDF link','Print Link','Unique Number'</b>");
			
			transferTab.selectTransferButton();
			
			Log.message("The transfer account details are entered and transferring the amount in recurring mode",driver);
			
			Log.softAssertThat(transferTab.VerifyPageElementDisplayed(Arrays.asList("transferedHeadLabel","transferedFromAccountNumber","transferedToAccountNumber","transferedAmount","TransferedPaymentType",
					"goToHomePage","makeAnotherTransferButton","downloadPdfLink","printLink"), transferTab), 
					"Transfer is successful and 'Transfer Head','From Acc Number','To Acc Number','Transferred Amount','Payment type','Home page button','Make Another"
					+ "transfer button','PDF link','Print Link' are all displaying", 
					"Transfer is not successfull and details are not displaying correctly",driver);
			
			Log.message("<b>Expected : Verify whether the transfer can be done in a recurring mode</b>");
			Log.softAssertThat(transferTab.getTransferMessage().toLowerCase().contains("recurring"),
					"The transfer can be done in a recurring mode!",
					"The transfer cannot be done in a recurring mode!",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
	
	/*@Test(description="Verify whether the number of transfers for processing are displayed", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_007(String browser) {
		Log.testCaseInfo("Verify whether the number of transfers for processing are displayed");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_007");
		String accountNumber = values.get("account_number_from");
		List<String> expectedTabs = Arrays.asList("tabTransactions",
		"tabTransfers", "tabPayments", "tabStandingOrders",
		"tabDirectDebts", "tabCheques", "tabAlerts");
			
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			
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
			
			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			Log.message("<b>Expected : Verify number of transfers schedule is displayed or not</b>");
			
			Log.softAssertThat(transferTab.VerifyPageElementDisplayed(Arrays.asList("txtNoOfTransfers"), transferTab), 
					"The number of transfers schedule is displayed", 
					"The number of transfers schedule is not displayed",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}	*/
	
	
	@Test(description="Verify future transaction details are displaying with all the attributes", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_008(String browser) {
		Log.testCaseInfo("Verify future transaction details are displaying with all the attributes");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		Properties prop = new Properties();
		HashMap<String, String> values = tde.readData("TC_TCIB_Transfer_008");
		String accountNumber = values.get("account_number_from");
			
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
		
			homePage = new HomePage(driver).get(); 
			allAccountsTab = homePage.goToAllAccountsTab();									
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			
			TransfersTab transferTab = accountPage.goToTransfersTab();
			
			Log.message("<b>Expected : Verify number of transfers schedule is displayed</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(Arrays.asList("txtNoOfTransfers"), transferTab, driver), 
					"The number of transfers schedule for the account is displayed", 
					"The number of transfers schedule for the account is not displayed",driver);
			
			transferTab.selectNoOfTransfers();
			
			WaitUtil.sleep(2);
			Log.message("<b>Expected : Verify future transaction details for the selected accounts are displaying with all the attributes</b>");
			Log.softAssertThat(verifyPageElementsAreDisplayed(Arrays.asList("scheduleDateDetails","narrativeDetails","amountDetails",
					"btnBackToPayment","seperateTransferDetailArrow"), transferTab, driver), 
					"Future transaction details 'Schedule Date', 'Narrative', 'Amount', ' Back button', 'Transfer detail arrow'"
					+ " displaying with all the attributes", 
					"Selected Account future transaction details are not displaying",driver);
			
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
	
	/*@Test(description="Transfer Current Account ", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_009(String browser) {
		Log.testCaseInfo("transfers Current Account Immediate Payment");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;
		TransfersTab transferTab = null;
		
		Properties prop = new Properties();
		
		// from account should have 1000 amount value
		String accountNumber ;
		String accountNumberTo ;
		String amount ;
		String PaymentType ;
		HashMap<String, String> values = tde.readData("TC001");
		
		accountNumber = values.get("account_number_from");
		accountNumberTo = values.get("account_number_to");
		amount = values.get("amount");
		PaymentType = values.get("paymentType");
		
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
		
			homePage = new HomePage(driver).get();
			allAccountsTab = homePage.goToAllAccountsTab();
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			transferTab = accountPage.goToTransfersTab();
	
			WaitUtil.sleep(5);
			transferTab.selectCreditAccount(accountNumberTo);
			transferTab.enterAmount(amount);
			transferTab.selectImmediatePayment();
			transferTab.selectoverViewTransferDetailsButton();
			
			transferTab.verifyFromAccountNumber(accountNumber);
			accountNumberTo = accountNumberTo.substring(0, 5);
			transferTab.verifyToAccountNumber(accountNumberTo);
			transferTab.verifyAmount(amount);
			transferTab.verifyPaymentMethod(PaymentType);
			transferTab.verifyTransferButton(amount);
			transferTab.selectTransferButton();
			
			Log.softAssertThat(transferTab.verifyTransferedDetails(),"verify Transfered Details is matching!",
					"verify Transfered Details is not matching!",driver);
			
			Log.softAssertThat(verifyPageElementsAreDisplayed(Arrays.asList("transferedHeadLabel","transferedFromAccountNumber","transferedToAccountNumber",
					"transferedAmount","TransferedPaymentType","goToHomePage","makeAnotherTransferButton","unqiueNumber"
					,"downloadPdfLink","printLink"), transferTab, driver), 
					"Future transaction details 'Schedule Date', 'Narrative', 'Amount', ' Back button', 'Transfer detail arrow'"
					+ " displaying with all the attributes", 
					"Selected Account future transaction details are not displaying",driver);
			
		WaitUtil.sleep(2);
		
	} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}	
	
	@Test(description="Transfer Current Account On a future date", dataProvider="TCIB_DP", dataProviderClass=DataProviderUtils.class,enabled=true)
	public void TC_TCIB_Transfer_010(String browser) {
		Log.testCaseInfo("Transfer Current Account On a future date");
		
		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;		
		AllAccountsTab allAccountsTab = null;
		AccountsPage accountPage = null;	
		TransfersTab transferTab = null;

		Properties prop = new Properties();
		
		// from account should have 1000 amount value
		String accountNumber ;
		String accountNumberTo ;
		String amount ;
		String futureDate;
		String PaymentType ;
		
		HashMap<String, String> values = tde.readData("TC002");
		System.out.println(values);
		
		accountNumber = values.get("account_number_from");
		accountNumberTo = values.get("account_number_to");
		amount = values.get("amount");
		futureDate = values.get("futureDate");
		PaymentType = values.get("paymentType");
		
		try {
			driver = WebDriverFactory.get(browser);	
			prop.load(new FileInputStream(new File("src//main//resources//config.properties")));
			
			
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
		
			homePage = new HomePage(driver).get(); 
			allAccountsTab = homePage.goToAllAccountsTab();									
			accountPage = allAccountsTab.clickOnAccount(accountNumber);
			transferTab = accountPage.goToTransfersTab();

			
			transferTab.selectCreditAccount(accountNumberTo);
			transferTab.enterAmount(amount);
			transferTab.selectFutureDate();
			transferTab.enterFutureDate(futureDate);
			transferTab.selectoverViewTransferDetailsButton();
			
			transferTab.verifyFromAccountNumber(accountNumber);
			accountNumberTo = accountNumberTo.substring(0, 5);
			transferTab.verifyToAccountNumber(accountNumberTo);
			transferTab.verifyAmount(amount);
			transferTab.verifyPaymentMethod(PaymentType);
			transferTab.verifyTransferButton(amount);
			transferTab.selectTransferButton();
			
			Log.softAssertThat(transferTab.verifyTransferedDetails(),"verify Transfered Details is matching!",
					"verify Transfered Details is not matching!",driver);	
			
		WaitUtil.sleep(2);
		} catch(Exception e){
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}	*/
	
}