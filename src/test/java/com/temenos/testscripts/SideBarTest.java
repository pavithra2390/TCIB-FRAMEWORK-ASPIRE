package com.temenos.testscripts;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.temenos.pages.AppSettingsPage;
import com.temenos.pages.BranchOrATMLocatorPage;
import com.temenos.pages.CustomerProfilePage;
import com.temenos.pages.CustomerProfilePage.ContactDetails;
import com.temenos.pages.ExchangeRatesPage;
import com.temenos.pages.Headers;
import com.temenos.pages.Headers.SideBar;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.pages.ManagePayee;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.EnvironmentPropertiesReader;
import com.temenos.support.Log;
import com.temenos.support.WebDriverFactory;

public class SideBarTest extends BaseTest {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	private String workbookName = "testdata\\CustomerProfile.xls";
	private String sheetName = "GlobalNavigation";

	@Test(description = "Verify Side Bar is opened and closed properly when tab.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_SideBar_001(String browser) {
		Log.testCaseInfo("Verify Side Bar is opened and closed properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.message("<b> Expected : Verify Side Bar is opened succesfully");
			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Bar is opened properly!", "Side Bar is not opened!",
					driver);

			sideBar = headers.clickOnSideBar();

			Log.message("<b> Expected : Verify Side Bar is Closed succesfully</b>");
			Log.softAssertThat(sideBar.verifyCloseSideMenuBar(),
					"Side Bar is closed properly!", "Side Bar is not closed!",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Home is opened properly when tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_Home_001(String browser) {
		Log.testCaseInfo("Verify Home is opened properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		HomePage homepage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");

			homepage = sideBar.clickOnHome();

			Log.message("<b> Expected : Verify Home page is opened succesfully</b>");
			Log.softAssertThat(homepage.verifyOpenHomePage(),
					"Home Page is opened properly!",
					"Home Page is not opened!", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Customer Profile is opened properly when tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomeProfile_001(String browser) {
		Log.testCaseInfo("Verify Customer Profile is opened properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");

			customerProfilePage = sideBar.clickOnCustomerProfile();

			Log.message("<b> Expected : Verify Customer Profile page is opened succesfully</b>");
			Log.softAssertThat(customerProfilePage.verifyOpenCustomerPage(),
					"Customer Profile page is opened properly!",
					"Customer Profile page is not opened!", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify App Settings opened properly when tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_AppSettings_001(String browser) {
		Log.testCaseInfo("Verify App Settings opened properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		AppSettingsPage appSettingsPage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");

			appSettingsPage = sideBar.clickOnAppSettings();

			Log.message("<b> Expected : Verify App Settings page is opened succesfully</b>");
			Log.softAssertThat(appSettingsPage.verifyOpenAppSettings(),
					"App Settings page opened properly!",
					"App Settings page not opened!", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Manage Payees page is opened properly when tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_ManagePayees_001(String browser) {
		Log.testCaseInfo("Verify Manage Payees page is opened properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		ManagePayee managePayee = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");

			managePayee = sideBar.clickOnManagePayees();

			Log.message("<b> Expected : Verify Manage Payees page is opened succesfully</b>");
			Log.softAssertThat(managePayee.verifyOpenAppSettings(),
					"Manage Payees opened properly!",
					"Manage Payees not opened!", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Branch/ATM Locator page is opened properly when tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_Branch_ATM_Locator_001(String browser) {
		Log.testCaseInfo("Verify Branch/ATM Locator page is opened properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		BranchOrATMLocatorPage branchOrATMLocatorPage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			branchOrATMLocatorPage = sideBar.clickOnBranchOrATMLocator();

			Log.message("<b> Expected : Verify Branch Or ATM Locator page is opened succesfully</b>");
			Log.softAssertThat(
					branchOrATMLocatorPage.verifyBranchOrATMLocator(),
					"Branch/ATM Locator opened properly!",
					"Branch/ATM Locator not opened!", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Exchange Rates page is opened properly when tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_Exchange_Rates_001(String browser) {
		Log.testCaseInfo("Verify Exchange Rates page is opened properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		ExchangeRatesPage exchangeRatesPage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");

			exchangeRatesPage = sideBar.clickOnExchangeRates();

			Log.message("<b> Expected : Verify Exchange Rates page is opened succesfully</b>");
			Log.softAssertThat(exchangeRatesPage.verifyExchangeRates(),
					"Exchange Rates opened properly!",
					"Exchange Rates not opened!", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify LogOut page is opened properly when tab", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_LogOut_001(String browser) {
		Log.testCaseInfo("Verify LogOut page is opened properly when tab");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		String expectedUrl = "";
		String actualUrl = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");

			sideBar.verifyLogOut();
			Log.message("<b> Expected : Verify Application is logged out succesfully</b>");
			Log.softAssertThat(loginPage.checkPageLoaded(),
					"Application is logged out properly!",
					"Application is not logged out!", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	/* ----------------- */

	@Test(description = "Verify whether the cancel button retains the language which is already present", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_025(String browser) {
		Log.testCaseInfo("Verify whether the cancel button retains the language which is already present");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String language = "French";

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();
			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			customerProfilePage = sideBar.clickOnCustomerProfile();
			sideBar.clickOnCurtomerPreferences();
			Log.message("Navigated to Customer Preference Page", driver);

			String LanguageBeforeEdit = sideBar.getTextFrmLanguage();
			Log.message("Language before changing:" + LanguageBeforeEdit,
					driver);
			sideBar.clickOnEditInCustomerPreference();

			sideBar.clickOnDropdownLanguage(language);
			sideBar.clickOnCancelLanguage();

			String LanguageAfterEdit = sideBar.getTextFrmLanguage();
			Log.message("Language after changing:" + LanguageAfterEdit);
			Log.message("<b>Expected :  On Clicking Cancel the language should be retained</b>");
			Log.softAssertThat(LanguageBeforeEdit.equals(LanguageAfterEdit),
					"On Clicking Cancel the language is retained",
					"On Clicking Cancel the language is not retained", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the user can change the language in the preferences tab of customer profile page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_ChnageLanguage(String browser) {
		Log.testCaseInfo("Verify whether the user can change the language in the preferences tab of customer profile page");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String language = "French";
		String language1 = "English";

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();
			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			customerProfilePage = sideBar.clickOnCustomerProfile();
			sideBar.clickOnCurtomerPreferences();
			Log.message("Navigated to Customer Preference Page");

			String LanguageBeforeEdit = sideBar.getTextFrmLanguage();
			Log.message("Language before changing:" + LanguageBeforeEdit,
					driver);

			sideBar.clickOnEditInCustomerPreference();
			sideBar.clickOnDropdownLanguage(language);
			sideBar.clickOnSaveButton();

			String LanguageAfterEdit = sideBar.getTextFrmLanguage();
			Log.message("Language after changing:" + LanguageAfterEdit, driver);
			Log.message("<b>Expected :  On Clicking Save the language should be changed</b>");
			Log.softAssertThat(!(LanguageBeforeEdit.equals(LanguageAfterEdit)),
					"On Clicking save the language is changed",
					"On Clicking save the language is not changed", driver);
			sideBar.clickOnEditInCustomerPreference();
			sideBar.clickOnDropdownLanguage(language1);
			sideBar.clickOnSaveButton();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the base currency is uneditable", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_026(String browser) {
		Log.testCaseInfo("Verify whether the base currency is uneditable");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();
			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			customerProfilePage = sideBar.clickOnCustomerProfile();
			sideBar.clickOnCurtomerPreferences();
			Log.message("Navigated to Customer Preference Page", driver);

			sideBar.clickOnEditInCustomerPreference();
			Log.message("<b>Expected :  Base currency Should not be Editable</b>");
			Log.softAssertThat(sideBar.verifyCloseSideMenuBar(),
					"Base currency is not Editable",
					"Base currency is Editable", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether user can change display of total balance", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_027(String browser) {
		Log.testCaseInfo("Verify whether user can change display of total balance");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		AppSettingsPage AppsettingsPage = null;

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			String Total = loginPage.getTextfromTotalBal();
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			AppsettingsPage = sideBar.clickOnAppSettings();
			Log.message("Navigated to App settings page");

			AppsettingsPage.clickOnPushBtn();
			String AfterTotal = loginPage.getTextfromTotalBal();
			Log.message("<b>Expected :  The Total balance should be changed successfully</b>");
			Log.softAssertThat(!Total.equals(AfterTotal),
					"The Total balance is changed successfully",
					"The Total balance is not changed successfully", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether user can change display of total balance", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_028(String browser) {
		Log.testCaseInfo("Verify whether user can change display of total balance");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		AppSettingsPage AppsettingsPage = null;

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");

			AppsettingsPage = sideBar.clickOnAppSettings();
			Log.message("Navigated to App settings page");
			Log.message("<b> Expected : Push and Profie picture change should be available</b>");
			Log.softAssertThat(AppsettingsPage.verifyButtonAppSettings(),
					"Push Button  change is available",
					"Push Button change is not available", driver);

			Log.softAssertThat(AppsettingsPage.clickOnRemoveProfile(),
					"Profile picture has been removed successfully",
					"Profile picture is not removed", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the user can change the phone number", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_029(String browser) {
		Log.testCaseInfo("Verify whether the user can change the phone number");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		ContactDetails Contactdetails = null;
		String phone = "454565432178";
		String phone2 = "454647373221";

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			customerProfilePage = sideBar.clickOnCustomerProfile();
			String PhoneNumberBefore = sideBar.getTextfromPhoneNumber("value");

			Log.message(
					"The Phone Number before changing:" + PhoneNumberBefore,
					driver);
			sideBar.clickOnEditPhoneNumber();

			sideBar.enterPhoneNumber(phone);
			sideBar.clickOnSave();
			String PhoneNumberAfter = sideBar.getTextfromPhoneNumber("value");

			Log.message("The Phone Number before changing:" + PhoneNumberAfter,
					driver);
			sideBar.clickOnEditPhoneNumber();
			sideBar.enterPhoneNumber(phone2);
			sideBar.clickOnSave();

			Log.message("<b> Expected : The Phone Number should be changed successfully</b>");
			Log.softAssertThat(!PhoneNumberBefore.equals(PhoneNumberAfter),
					"The Phone Number is changed successfully",
					"The Phone Number is not changed successfully", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the user can enter a phone number with different country codes", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_030(String browser) {
		Log.testCaseInfo("Verify the user can enter a phone number with different country codes");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String phone = "+1 212-385-2066";
		String phone2 = "+44 1896 754450";
		String phone3 = "044 6600 0666";

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!");
			customerProfilePage = sideBar.clickOnCustomerProfile();

			sideBar.clickOnEditPhoneNumber();
			sideBar.enterPhoneNumber(phone);
			sideBar.clickOnSave();
			String USPhoneNumber = sideBar.getTextfromPhoneNumber("value");

			Log.message("The Phone Number 1st Country:" + USPhoneNumber);
			sideBar.clickOnEditPhoneNumber();
			sideBar.enterPhoneNumber(phone2);
			sideBar.clickOnSave();
			String UKPhoneNumber = sideBar.getTextfromPhoneNumber("value");
			Log.message("The Phone Number 2nd Country:" + UKPhoneNumber);

			sideBar.clickOnEditPhoneNumber();
			sideBar.enterPhoneNumber(phone3);
			sideBar.clickOnSave();
			String INDPhoneNumber = sideBar.getTextfromPhoneNumber("value");
			Log.message("The Phone Number 3rd Country:" + INDPhoneNumber);

			Log.message("<b> Expected : The Phone Number of different country should be entered succesfully</b>");
			Log.softAssertThat(
					!USPhoneNumber.equals(UKPhoneNumber)
							&& !USPhoneNumber.equals(INDPhoneNumber)
							&& !UKPhoneNumber.equals(INDPhoneNumber),
					"The Phone Number of different country is entered succesfully",
					"The Phone Number of different country is not changed successfully",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether clicking cancel button retains the phone number which is already present", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_032(String browser) {
		Log.testCaseInfo("Verify whether clicking cancel button retains the phone number which is already present");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String phone = "+1 212-385-2066";
		String phone2 = "44444445555";

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			customerProfilePage = sideBar.clickOnCustomerProfile();
			String PhoneNumberBefore = sideBar.getTextfromPhoneNumber("value");
			Log.message(
					"The Phone Number before changing:" + PhoneNumberBefore,
					driver);
			sideBar.clickOnEditPhoneNumber();
			sideBar.enterPhoneNumber(phone);
			sideBar.clickOnCancel();
			String PhoneNumberAfter = sideBar.getTextfromPhoneNumber("value");
			Log.message("The Phone Number before changing:" + PhoneNumberAfter,
					driver);
			sideBar.clickOnEditPhoneNumber();
			sideBar.enterPhoneNumber(phone2);
			sideBar.clickOnSave();
			Log.message("<b> Expected : The Phone Number entered newly should not be saved</b>");
			Log.softAssertThat(
					PhoneNumberBefore.equals(PhoneNumberAfter),
					"The Phone Number entered newly is not be saved",
					"The Phone Number entered newly is saved though Cancel button is clicked",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the user can change the email-id in the contact details tab of customer profile page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_033(String browser) {
		Log.testCaseInfo("Verify whether the user can change the email-id in the contact details tab of customer profile page");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String email = "test@test.com";
		String email1 = "random@test.com";

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);
			customerProfilePage = sideBar.clickOnCustomerProfile();
			String EmailBefore = sideBar.getTextfromEmailId("value");
			Log.message("The Email id before changing:" + EmailBefore);
			sideBar.clickOnEditEmailId();
			sideBar.enterEmailId(email);
			sideBar.clickOnSaveEmail();
			String EmailAfter = sideBar.getTextfromEmailId("value");
			Log.message("The Email id After changing:" + EmailAfter);
			sideBar.clickOnEditEmailId();
			sideBar.enterEmailId(email1);
			sideBar.clickOnSaveEmail();
			Log.message("<b> Expected : The Email Id should be saved successfully</b>");
			Log.softAssertThat(!EmailBefore.equals(EmailAfter),
					"The Email Id is saved successfully",
					"The Email Id is not saved successfully", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the application accepts invalid email id", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_034(String browser) {
		Log.testCaseInfo("Verify whether the application accepts invalid email id");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String email = "12";

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			customerProfilePage = sideBar.clickOnCustomerProfile();
			String EmailBefore = sideBar.getTextfromEmailId("value");
			Log.message("The Email id before changing:" + EmailBefore);
			sideBar.clickOnEditEmailId();
			sideBar.enterEmailId(email);
			sideBar.clickOnSaveEmail();
			String EmailAfter = sideBar.getTextfromEmailId("value");
			Log.message("The Email id After changing:" + EmailAfter);
			Log.message("<b> Expected : The Invalid Email Id should not be saved successfully</b>");
			Log.softAssertThat(EmailBefore.equals(EmailAfter),
					"The Invalid Email Id is not saved successfully",
					"The Inavlid Email Id is saved successfully", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the cancel button retains the email id which is already present", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_035(String browser) {
		Log.testCaseInfo("Verify whether the cancel button retains the email id which is already present");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String email = "testcheck@test.com";

		try {
			driver = WebDriverFactory.get(browser);
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);
			customerProfilePage = sideBar.clickOnCustomerProfile();
			String EmailBefore = sideBar.getTextfromEmailId("value");
			Log.message("The Email id before changing:" + EmailBefore);
			sideBar.clickOnEditEmailId();
			sideBar.enterEmailId(email);
			sideBar.clickOnCancelEmail();
			String EmailAfter = sideBar.getTextfromEmailId("value");
			Log.message("The Email id After changing:" + EmailAfter);
			Log.message("<b> Expected : The Email Id should not be saved as we clicked on cancel</b>");
			Log.softAssertThat(
					EmailBefore.equals(EmailAfter),
					"The Email Id should not be saved as we clicked on cancel",
					"The Email Id should is saved though we have clicked on cancel",
					driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the filtering of Branch and ATM buttons in Branch / ATM Locator page", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_036(String browser) {
		Log.testCaseInfo("Verify the filtering of Branch and ATM buttons in Branch / ATM Locator page");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		BranchOrATMLocatorPage branchOrATMLocatorPage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);
			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			branchOrATMLocatorPage = sideBar.clickOnBranchOrATMLocator();
			sideBar.clickOnCheckboxBranch();
			sideBar.clickOnCheckboxAtm();
			sideBar.clickOnCheckboxBranch();
			Log.message("<b> Expected : The Branches should be displayed</b>");
			Log.softAssertThat(sideBar.getTextFromBranch().contains("Branch"),
					"The Branches are displayed",
					"The Branches are not displayed", driver);
			sideBar.clickOnCheckboxBranch();
			sideBar.clickOnCheckboxAtm();
			Log.message("<b> Expected : The ATM should be displayed</b>");
			Log.softAssertThat(sideBar.getTextFromAtm().contains("ATM"),
					"The ATM branches are displayed",
					"The ATM branches are not displayed", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the user can view the details of corresponding branch/ATM on clicking the particular branch", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_040(String browser) {
		Log.testCaseInfo("Verify the user can view the details of corresponding branch/ATM on clicking the particular branch");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		BranchOrATMLocatorPage branchOrATMLocatorPage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			branchOrATMLocatorPage = sideBar.clickOnBranchOrATMLocator();

			sideBar.clickOnCheckboxBranch();
			sideBar.clickOnCheckboxAtm();
			sideBar.clickOnCheckboxBranch();
			Log.message("Branch Checkbox is selected");

			String Branch = sideBar.getTextFromBranch();
			sideBar.clickOnBranch();
			Log.message("Branch button is clicked");

			String BranchName = sideBar.getTextFromBranchName();
			Log.message("<b> Expected :  The Branch details should be displayed</b>");
			Log.softAssertThat(Branch.equals(BranchName),
					"The Branches are displayed",
					"The Branches are not displayed", driver);

			headers.clickOnSideBar();
			sideBar.clickOnBranchOrATMLocator();
			sideBar.clickOnCheckboxBranch();
			sideBar.clickOnCheckboxAtm();
			sideBar.clickOnCheckboxAtm();
			Log.message("ATM Checkbox is selected!");

			String Atm = sideBar.getTextFromAtm();
			sideBar.clickOnAtm();
			String AtmName = sideBar.getTextFromAtmName();

			Log.message("<b> Expected : The ATM details should be displayed</b>");
			Log.softAssertThat(Atm.equals(AtmName),
					"The ATM branches are displayed",
					"The ATM branches are not displayed", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the opening hours and facilities page is closed", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_043(String browser) {
		Log.testCaseInfo("Verify whether the opening hours and facilities page is closed");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		BranchOrATMLocatorPage branchOrATMLocatorPage = null;

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);

			branchOrATMLocatorPage = sideBar.clickOnBranchOrATMLocator();
			sideBar.clickOnCheckboxBranch();
			sideBar.clickOnCheckboxAtm();
			sideBar.clickOnCheckboxBranch();

			Log.softAssertThat(sideBar.verifyPageElements(
					Arrays.asList("lblBranch"), sideBar),
					"Branches are displayed", "Branches are not displayed",
					driver);

			sideBar.clickOnBranch();
			sideBar.clickOnCloseBtnInBranch();

			Log.message("<b> Expected :  The opening hours and facilities page should be closed</b>");
			Log.softAssertThat(sideBar.verifyPageElements(
					Arrays.asList("lblBranch"), sideBar),
					"<b>The opening hours and facilities page is closed",
					"<b>The opening hours and facilities page is not closed",
					driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify whether the application accepts invalid phone number", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void TC_TCIB_CustomerPreference_031(String browser) {
		Log.testCaseInfo("Verify whether the application accepts invalid phone number");

		WebDriver driver = null;
		LoginPage loginPage = null;
		SideBar sideBar = null;
		CustomerProfilePage customerProfilePage = null;
		String phone = "abc";

		try {
			driver = WebDriverFactory.get(browser);

			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);

			Headers headers = new Headers(driver);

			sideBar = headers.clickOnSideBar();

			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!",
					"Side Menu Bar is not opened!", driver);
			
			customerProfilePage = sideBar.clickOnCustomerProfile();

			String PhoneNumberBefore = sideBar.getTextfromPhoneNumber("value");
			Log.message("The Phone Number before changing:" + PhoneNumberBefore);

			sideBar.clickOnEditPhoneNumber();

			sideBar.enterPhoneNumber(phone);
			sideBar.clickOnSave();

			String PhoneNumberAfter = sideBar.getTextfromPhoneNumber("value");
			Log.message("<b> Expected : The application should not accept invalid phone number</b>");
			Log.softAssertThat(PhoneNumberAfter.matches("[0-9]+"),
					"<b>The Phone Number field is not accepting the invalid format:"
							+ PhoneNumberAfter,
					"<b>The Phone Number is accepting the invalid format:"
							+ PhoneNumberAfter, driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
}
