package com.temenos.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.temenos.pages.Headers.Settings;
import com.temenos.pages.Headers.SideBar;
import com.temenos.pages.HomePage;
import com.temenos.pages.LoginPage;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;
import com.temenos.support.WebDriverFactory;

public class SettingTest extends BaseTest {

	@Test(description = "Verify Setting font functionality.", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	public void verifySetingsFunctionality(String browser) {
		Log.testCaseInfo("Verify Setting functionality.");

		WebDriver driver = null;
		LoginPage loginPage = null;
		HomePage homePage = null;
		Settings setting = null;
		SideBar sideBar = null;
		Properties prop = new Properties();

		try {
			driver = WebDriverFactory.get(browser);
			prop.load(new FileInputStream(new File(
					"src//main//resources//config.properties")));

			Log.message("Login to the application..");
			loginPage = new LoginPage(driver, url);
			loginPage.login(username, password);
			homePage = new HomePage(driver).get(); // first time driver will be
													// passed

			Log.message("Default Html value "
					+ driver.findElement(By.tagName("html")).getAttribute(
							"class"));
			WaitUtil.sleep(10);
			setting = homePage.getHeaders().clickOnSettings();

			setting.clickOnSmallButton();
			Log.message("Expected : Verify the small font is displaying correctly");
			
			Log.softAssertThat(prop.getProperty("smallFontValue").
					contains(driver.findElement(By.tagName("html")).getAttribute("class")) ,
					"Small font is displaying correctly", 
					"Small font is not displaying correctly", driver);
			
			setting.clickOnMedButton();
			
			
			Log.message("Expected : Verify the medium font is displaying correctly");
			
			Log.softAssertThat(prop.getProperty("mediumFontValue").
					contains(driver.findElement(By.tagName("html")).getAttribute("class")) ,
					"Medium font is displaying correctly", 
					"Medium font is not displaying correctly", driver);
			
			setting.clickOnLargeButton();
			Log.message("Expected : Verify the Large font is displaying correctly");
			Log.softAssertThat(prop.getProperty("bigFontValue").
					contains(driver.findElement(By.tagName("html")).getAttribute("class")) ,
					"Large font is displaying correctly", 
					"Large font is not displaying correctly", driver);
			WaitUtil.sleep(3);
			//Header ON
			setting.clickOnOffLockHeaderButton();
			Log.message("Expected : Verify the Header Fixed On mode is displaying correctly");
			Log.softAssertThat(prop.getProperty("locked").
					contains(driver.findElement(By.tagName("html")).getAttribute("class")) ,
					"Header Fixed On mode is displaying correctly", 
					"Header Fixed On mode is not displaying correctly", driver);
			
			WaitUtil.sleep(3);
			// HEADER OFF
			setting.clickOnOffLockHeaderButton();
			Log.message("Expected : Verify the Header Fixed OFF mode is displaying correctly");
			Log.softAssertThat(prop.getProperty("unlocked").
					contains(driver.findElement(By.tagName("html")).getAttribute("class")) ,
					"Header Fixed OFF mode is displaying correctly", 
					"Header Fixed OFF mode is not displaying correctly", driver);
			
			WaitUtil.sleep(3);
			// ON
			setting.clickOnOffCompactModeButton();
			
			Log.message("Expected : Verify the On Compact Mode is displaying correctly");
			Log.softAssertThat(setting.getCompactCheckboxEnable(),
					"Compact Mode ON mode  displaying correctly", 
					"Compact Mode ON mode is not displaying correctly", driver);
			
			/*Log.softAssertThat(prop.getProperty("compactOn").
					contains(driver.findElement(By.tagName("html")).getAttribute("class")) ,
					"ON Compact mode is displaying correctly", 
					"ON Compact mode is not displaying correctly", driver);*/
			WaitUtil.sleep(3);
			// OFF
			setting.clickOnOffCompactModeButton();
			Log.message("Expected : Verify the OFF Compact Mode is displaying correctly");
			Log.softAssertThat(!setting.getCompactCheckboxEnable(),
					"Compact Mode OFF mode  displaying correctly", 
					"Compact Mode OFF mode is not displaying correctly", driver);
			
			WaitUtil.sleep(3);
			
			// ON
			setting.clickOnOffBoxModeButton();
			Log.message("Expected : Verify the BoxView On mode is displaying correctly");
			Log.softAssertThat(setting.getBoxVideCheckbox(),
					"Box View ON mode  displaying correctly", 
					"Box View ON mode is not displaying correctly", driver);
			
			WaitUtil.sleep(3);
			// OFF
			setting.clickOnOffBoxModeButton();
			Log.message("Expected : Verify the BoxView OFF mode is displaying correctly");
			Log.softAssertThat(!setting.getBoxVideCheckbox(),
					"Box View OFF mode  displaying correctly", 
					"Box View OFF mode is not displaying correctly", driver);
			
			WaitUtil.sleep(3);
			
			Log.message("Expected: Verify the Opening Side Bar is displayed");
			sideBar = homePage.getHeaders().clickOnSideBar();
			Log.softAssertThat(sideBar.verifyOpenSideMenuBar(),
					"Side Menu Bar is opened properly!!",
					"Side Menu Bar is not opened!!", driver);
			
			
			// OFF
			
			Log.message("Expected: Verify the Opening Side Bar is closed properly");
			sideBar = homePage.getHeaders().clickOnSideBar();
			WaitUtil.sleep(3);
			Log.softAssertThat(sideBar.verifyCloseSideMenuBar(),
					"Side Menu Bar is closed properly!!",
					"Side Menu Bar is not closed properly!!", driver);
			
		
			setting.clickOnSmallButton();
			WaitUtil.sleep(3);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.testCaseResult();
			Log.endTestCase();
		}
	}
}
