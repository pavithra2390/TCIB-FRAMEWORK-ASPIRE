package com.temenos.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.Log;
import com.temenos.support.TestDataExtractor;
import com.temenos.support.WaitUtil;

public class PaymentSuccess extends LoadableComponent<PaymentSuccess> {
	private WebDriver driver;
	boolean isFailedFirstTime;

	@FindBy(css = "#C5__C4__FMT_41769076BAB5EA4E846651")
	private WebElement readyElement;

	@FindBy(css = "#C5__C4__QUE_9FA60FB496BBD108183051")
	private WebElement accountNumber;

	@FindBy(css = "#C5__C4__QUE_9FA60FB496BBD108184540")
	private WebElement amount;

	@FindBy(css = "#C5__C4__QUE_9FA60FB496BBD108187518")
	private WebElement payeeName;

	public PaymentSuccess(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, readyElement)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Paymentsuccess page!", driver);
		} else {
			Log.message("Navigated to Paymentsuccess page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public void verifyPaymentSuccessDetail(String tcId)
			throws Exception {
		TestDataExtractor tde = new TestDataExtractor("Payments");
		HashMap<String, String> values = tde.readData(tcId);
		if (accountNumber.getText().equalsIgnoreCase(
				values.get("account_number_from"))) {
			Log.pass("Verified Account number is matching!!!");
		} else {
			Log.fail("Account number is not matching!!!");
		}
		if (amount.getText().equalsIgnoreCase(values.get("amount"))) {
			Log.pass("Verified amount is matching!!!");
		} else {
			Log.fail("Amount is not matching!!! ");
		}

		if (payeeName.getText().equalsIgnoreCase(values.get("payeename"))) {
			Log.pass("Verified Iban is matching!!!");
		} else {
			Log.fail("Iban is not matching!!!");
		}
		//return new CheckPayeeDetails(driver).get();
	}

	public void verifyPaymentSuccessDetailCompany(String tcId)
			throws Exception {
		TestDataExtractor tde = new TestDataExtractor("Payments");
		HashMap<String, String> values = tde.readData(tcId);
		if (accountNumber.getText().equalsIgnoreCase(
				values.get("account_number_from"))) {
			Log.pass("Verified Account number is matching!!!");
		} else {
			Log.fail("Account number is not matching!!!");
		}
		if (amount.getText().equalsIgnoreCase(values.get("amount"))) {
			Log.pass("Verified amount is matching!!!");
		} else {
			Log.fail("Amount is not matching!!! ");
		}

		//return new CheckPayeeDetails(driver).get();
	}

}
