package com.temenos.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class CurrentAccountApplicationForm extends LoadableComponent<CurrentAccountApplicationForm> {

	private WebDriver driver;
	
	private boolean isFailedFirstTime;
	
	@FindBy(css="#C4__C1__HEAD_2B73836D22D8AD2E220086")
	private WebElement pageTitle;
	
	@FindBy(css="#C4__C1__QUE_67BA320F8EDC08BB35394")
	private WebElement productGroupText;
	
	@FindBy(css="#C4__C1__QUE_67BA320F8EDC08BB35412")
	private WebElement producText;
	
	@FindBy(css="#C4__C1__QUE_67BA320F8EDC08BB35420")
	private WebElement currencyText;
	
	@FindBy(css="#C4__C1__BUT_67BA320F8EDC08BB36359")
	private WebElement cancelButton;
	
	@FindBy(css="#C4__C1__BUT_67BA320F8EDC08BB36352")
	private WebElement continueButton;
	
	public CurrentAccountApplicationForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error {
		if(!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
			isFailedFirstTime = true;
			throw new Error();				
		} else if(isFailedFirstTime) {
			Log.fail("Not navigated to Current Account Application Form page!", driver);			
		} else {
			Log.message("Navigated to Current Account Application Form page!", driver);
		}		
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}
	
}