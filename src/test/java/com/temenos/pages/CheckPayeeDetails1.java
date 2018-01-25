package com.temenos.pages;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WaitUtil;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class CheckPayeeDetails1 extends LoadableComponent<CheckPayeeDetails1> {
    private WebDriver driver;
    boolean isFailedFirstTime;

    @FindBy(css = "#C5__HEAD_B427ECF8FC5D9888292460")
    private WebElement lblPayeeCheckDetails;

    @FindBy(css = "#C5__C4__p4_BUT_68769533CF2E49EA635015")
    private WebElement btnConfirmSave;

    @FindBy(css = "label[for='C5__C4__QUE_C9FC5C1C611E19E9121268_0']")
	WebElement termsCondition;

    @FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].NICKNAME[1].PAYEENICKNAME']")
    private WebElement nickName;

    @FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].DEBITACCOUNT_READONLY']")
    private WebElement accountNumer;

    @FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].PAYMENTAMOUNT_READONLY']")
    private WebElement amount;

    @FindBy(css = "select[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].PAYMENTCURRENCY_READONLY']")
    private WebElement currency;

    @FindBy(css = "input[name*='IBANBEN_READONLY']")
    private WebElement Iban;

    @FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYEENAME[1].PAYEENAME_READONLY']")
    private WebElement payeename;

    @FindBy(css = "select[name*='C5__C4__SCREENELEMENTS[1].SELECTEDPAYMENTMETHOD_READONLY']")
    private WebElement paymentmethod;

    @FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].BENEFICIARYACCOUNTNO_READONLY']")
    private WebElement swiftPayeeAccountNo;

    @FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].BIC[1].BENEFICIARYBIC_READONLY']")
    private WebElement swiftBic;

    @FindBy(css = "input[name*='C5__C4__WORKINGELEMENTS[1].DEBIC[1].INSTITUTION[1].DEBICINSTITUTION_READONLY']")
    private WebElement swiftBicInst;

    @FindBy(css = "input[name*='C5__C4__SCREENELEMENTS[1].PAYMENTORDERINPUT[1].BENBANKCLEARINGCODE_READONLY']")
    private WebElement nccClearingCode;

    @FindBy(css = "#C5__C4__QUE_C9FC5C1C611E19E9110778")
    private WebElement moneyType;

	@FindBy(css = "#C5__BUT_5B7BF686F521E7E846534")
	private WebElement continueSave;

	public CheckPayeeDetails1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void isLoaded() throws Error {
        if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, lblPayeeCheckDetails)) {
            isFailedFirstTime = true;
            throw new Error();
        } else if (isFailedFirstTime) {
            Log.fail("Not navigated to CheckPayeeDetails page!", driver);
        } else {
            Log.message("Navigated to CheckPayeeDetails page!", driver);
        }
    }

    @Override
    protected void load() {
        WaitUtil.waitForSpinnerToComplete(driver);
    }

    public CheckPayeeDetails enterPayeeNickname(String name) throws Exception {
        BrowserActions.typeOnTextField(nickName, name, driver, "Enter Nickname in the textbox");
        return new CheckPayeeDetails(driver).get();
    }

    public CheckPayeeDetails clickingTermsCondition() throws Exception{
        BrowserActions.clickOnButton(termsCondition, driver, "Terms and Condition");
        return new CheckPayeeDetails(driver).get();
    }


    public ManagePayee continueAndSave() throws Exception{
        BrowserActions.clickOnButton(continueSave, driver, "Click on Continuesave");
        return new ManagePayee(driver).get();
    }

    public PaymentSuccess continueAndSave1() throws Exception{
        BrowserActions.clickOnButton(btnConfirmSave, driver, "Click on Continuesave");
        return new PaymentSuccess(driver).get();
    }

    public CheckPayeeDetails1 verifyPaymentDetail(String tcId) throws Exception {
    	TestDataExtractor tde = new TestDataExtractor("Payments");
    	HashMap<String, String> values = tde.readData(tcId);
    	 if(accountNumer.getAttribute("value").equalsIgnoreCase(values.get("account_number_from")))
    	 {
    		 Log.pass("Verified Account number is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Account number is not matching!!! \n Actual : "+accountNumer.getAttribute("value")+" \n Expected "+ values.get("account_number_from"));
    	 }
    	 if(amount.getAttribute("value").equalsIgnoreCase(values.get("amount")))
    	 {
    		 Log.pass("Verified amount is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Amount is not matching!!! ");
    	 }
    	 if(currency.getAttribute("value").equalsIgnoreCase(values.get("currency")))
    	 {
    		 Log.pass("Verified Currency is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Currency is not matching!!!");
    	 }
    	 if(Iban.getAttribute("value").trim().equalsIgnoreCase(values.get("iban").trim()))
    	 {
    		 Log.pass("Verified Iban is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Iban is not matching!!! /n Actual:" + Iban.getAttribute("value")+"/n Expected:" +values.get("iban"));
    	 }
    	 if(payeename.getAttribute("value").trim().equalsIgnoreCase(values.get("payeename").trim()))
    	 {
    		 Log.pass("Verified Iban is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Iban is not matching!!!");
    	 }
    	 if(paymentmethod.getAttribute("value").trim().equalsIgnoreCase("1"))
    	 {
    		 Log.pass("Verified Payment Method is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Payment method is not matching!!!");
    	 }
    	 return new CheckPayeeDetails1(driver).get();
    }

    public CheckPayeeDetails1 verifyPaymentDetailSwiftBic(String tcId) throws Exception {
    	TestDataExtractor tde = new TestDataExtractor("Payments");
    	HashMap<String, String> values = tde.readData(tcId);
    	 if(accountNumer.getAttribute("value").equalsIgnoreCase(values.get("account_number_from")))
    	 {
    		 Log.pass("Verified Account number is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Account number is not matching!!! \n Actual : "+accountNumer.getAttribute("value")+" \n Expected "+ values.get("account_number_from"));
    	 }
    	 if(amount.getAttribute("value").equalsIgnoreCase(values.get("amount")))
    	 {
    		 Log.pass("Verified amount is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Amount is not matching!!! ");
    	 }
    	 if(currency.getAttribute("value").equalsIgnoreCase(values.get("currency")))
    	 {
    		 Log.pass("Verified Currency is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Currency is not matching!!!");
    	 }
    	 if(swiftPayeeAccountNo.getAttribute("value").trim().equalsIgnoreCase(values.get("accountno").trim()))
    	 {
    		 Log.pass("Verified Swift Account No is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Swift Account No is not matching!!!");
    	 }
    	 if(payeename.getAttribute("value").trim().equalsIgnoreCase(values.get("payeename").trim()))
    	 {
    		 Log.pass("Verified Iban is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Iban is not matching!!!");
    	 }
		 if(swiftBic.getAttribute("value").trim().equalsIgnoreCase(values.get("bic").trim()))
    	 {
    		 Log.pass("Verified BIC is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("BIC is not matching!!!");
    	 }
		  if(swiftBicInst.getAttribute("value").trim().equalsIgnoreCase(values.get("bic").trim()))
    	 {
    		 Log.pass("Verified BIC Institution is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("BIC Institution is not matching!!!");
    	 }
		  if(moneyType.getText().trim().equalsIgnoreCase("Immediately (as soon as possible)"))
			 {
				 Log.pass("Verified when payment is levaing your account!!!");
			 }
			 else
	    	 {
	    		 Log.fail("Payment is levaing your account is not matching!!!");
	    	 }
		 if(paymentmethod.getAttribute("value").trim().equalsIgnoreCase("2"))
    	 {
    		 Log.pass("Verified Payment Method is matching!!!");
    	 }
    	 else
    	 {
    		 Log.fail("Payment method is not matching!!!/n Acutal:" +paymentmethod.getAttribute("value")+"/n Expected:Swift/BIC");
    	 }

    	 return new CheckPayeeDetails1(driver).get();
    }

    public CheckPayeeDetails verifyPaymentDetailNCC(String tcId) throws Exception{
        TestDataExtractor tde = new TestDataExtractor("Payments");
        HashMap<String, String> values = tde.readData(tcId);
        if (accountNumer.getAttribute("value").equalsIgnoreCase(values.get("account_number_from"))) {
            Log.pass("Verified Account number is matching!!!");
        } else {
            Log.fail("Account number is not matching!!! \n Actual : " + accountNumer.getAttribute("value") + " \n Expected " + values.get("account_number_from"));
        }
        if (amount.getAttribute("value").equalsIgnoreCase(values.get("amount"))) {
            Log.pass("Verified amount is matching!!!");
        } else {
            Log.fail("Amount is not matching!!! ");
        }
        if (currency.getAttribute("value").equalsIgnoreCase(values.get("currency"))) {
            Log.pass("Verified Currency is matching!!!");
        } else {
            Log.fail("Currency is not matching!!!");
        }
        if (swiftPayeeAccountNo.getAttribute("value").trim().equalsIgnoreCase(values.get("accountno").trim())) {
            Log.pass("Verified Swift Account No is matching!!!");
        } else {
            Log.fail("Swift Account No is not matching!!!");
        }
        if (payeename.getAttribute("value").trim().equalsIgnoreCase(values.get("payeename").trim())) {
            Log.pass("Verified Iban is matching!!!");
        } else {
            Log.fail("Iban is not matching!!!");
        }
        if (nccClearingCode.getAttribute("value").trim().equalsIgnoreCase(values.get("clearingcode").trim())) {
            Log.pass("Verified Clearing code is matching!!!");
        } else {
            Log.fail("Clearing code is not matching!!!");
        }

        if (moneyType.getText().trim().equalsIgnoreCase("Immediately (as soon as possible)")) {
            Log.pass("Verified when payment is levaing your account!!!");
        } else {
            Log.fail("Payment is levaing your account is not matching!!!");
        }
        if (paymentmethod.getAttribute("value").trim().equalsIgnoreCase("4")) {
            Log.pass("Verified Payment Method is matching!!!");
        } else {
            Log.fail("Payment method is not matching!!!/n Acutal:" + paymentmethod.getAttribute("value") + "/n Expected:Swift/BIC");
        }

        return new CheckPayeeDetails(driver).get();
    }

    public CheckPayeeDetails verifyPaymentDetailCompany(String tcId) throws Exception{
        TestDataExtractor tde = new TestDataExtractor("Payments");
        HashMap<String, String> values = tde.readData(tcId);
        if (accountNumer.getAttribute("value").equalsIgnoreCase(values.get("account_number_from"))) {
            Log.pass("Verified Account number is matching!!!");
        } else {
            Log.fail("Account number is not matching!!! \n Actual : " + accountNumer.getAttribute("value") + " \n Expected " + values.get("account_number_from"));
        }
        if (amount.getAttribute("value").equalsIgnoreCase(values.get("amount"))) {
            Log.pass("Verified amount is matching!!!");
        } else {
            Log.fail("Amount is not matching!!! ");
        }
        if (currency.getAttribute("value").equalsIgnoreCase(values.get("currency"))) {
            Log.pass("Verified Currency is matching!!!");
        } else {
            Log.fail("Currency is not matching!!!");
        }
        if (moneyType.getText().trim().equalsIgnoreCase("Immediately (as soon as possible)")) {
            Log.pass("Verified when payment is levaing your account!!!");
        } else {
            Log.fail("Payment is levaing your account is not matching!!!");
        }
        return new CheckPayeeDetails(driver).get();
    }

}


