package com.temenos.customization;

import com.temenos.pages.LoginPage;
import com.temenos.pages.PayeeDetails;
import com.temenos.pages.TransfersTab;
import com.temenos.support.DataProviderUtils;
import com.temenos.support.EnvironmentPropertiesReader;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;
import com.temenos.support.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.pages.Footers;
import com.temenos.pages.Headers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Properties;

public class CustomizationTestScripts extends LoadableComponent<CustomizationTestScripts> implements suites,pageInterface  {

    private WebDriver driver;

    private Headers headers;

    private Footers footers;

    EnvironmentPropertiesReader properties = EnvironmentPropertiesReader.getInstance();
    public String username;
    public String password;
    public String url;
    private boolean isFailedFirstTime;

    public CustomizationTestScripts(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        headers = new Headers(driver);
        footers = new Footers(driver);
    }

    @Override
    protected void load() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void isLoaded() throws Error {
        // TODO Auto-generated method stub

    }

    public CustomizationTestScripts()

    {

    }

    @BeforeClass(alwaysRun = true)
    public void beforeSuite() {
        username = properties.getUsername();
        password = properties.getPassword();
        url = properties.getURL();
        System.getProperties().put("hubHost", properties.getDeviceHost());
        System.getProperties().put("devicePort", properties.getDevicePort());
    }
    class ManagePayees extends com.temenos.testscripts.ManagePayees{
    
	    @Test(description = "verify required alert message are showing in application!", dataProvider = "TCIB_DP", dataProviderClass = DataProviderUtils.class)
	    public void TC_TCIB_ManagePayees010(String browser) {
	        Log.testCaseInfo("verify user can add a new domestic payee");
	        WebDriver driver = null;
	        LoginPage loginPage = null;
	        Headers headers = null;
	
	        String ibanMessage = "Please enter IBAN number";
	        String payeeMessage = "Please enter payee name";
	        try {
	        	driver = WebDriverFactory.get(browser);
	            AccountsPage accountsPage = new AccountsPage(driver);
	            accountsPage.goToTransfersTab();
	            Log.message("Login to the application..");
	            loginPage = new LoginPage(driver, url);
	            loginPage.login(username, password);
	            
	            headers = new Headers(driver).get();
	            PayeeDetails payeeDetails = headers.clickOnSideBar().clickOnManagePayees()
	                    .clickOnAddANewPayeeButton()
	                    .gotoPersonWithInGBTabFromAddPayee()
	                    .gotoPayeeDetailsWithIBANPaymentMode()
	                    .clickOverviewDetailsWithoutValue();
	            String iban = payeeDetails.verifyAlertforIBAN();
	            String name = payeeDetails.verifyAlertforPayee();
	            
	            Log.assertThat(iban.equals(ibanMessage), "The alert message " + iban + " is shown in application", "The alert message " + iban + " is not shown in application");
	            Log.assertThat(name.equals(payeeMessage), "The alert message " + name + " is shown in application", "The alert message " + name + " is not shown in application");
	            
	        } catch (Exception e) {
	            Log.exception(e);
	        } finally {
	            Log.testCaseResult();
	            driver.quit();
	            Log.endTestCase();
	        }
	    }
   }
}
