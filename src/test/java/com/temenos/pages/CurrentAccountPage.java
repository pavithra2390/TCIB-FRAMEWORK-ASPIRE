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

public class CurrentAccountPage extends LoadableComponent<CurrentAccountPage> {

	private WebDriver driver;
	
	private boolean isFailedFirstTime;
	
	@FindBy(css="#C4__C2__HEAD_26B2531682588B1B1795189")
	private WebElement pageTitle;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R1")
	private WebElement currentAccountText;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R2")
	private WebElement currentAccountGeneralChargeText;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R3")
	private WebElement currentAccountPreferredText;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R4")
	private WebElement premiumAccountText;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R5")
	private WebElement staffAccountCurrentText;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R6")
	private WebElement studentAccountText;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R7")
	private WebElement groupCheckingText;
	
	@FindBy(css="#C4__C2__TXT_26B2531682588B1B1806453_R8")
	private WebElement groupSubsidiaryText;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R1")
	private WebElement currentAccountApplyButton ;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R2")
	private WebElement currentAccountGeneralChargeButton;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R3")
	private WebElement currentAccountPreferredButton ;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R4")
	private WebElement premiumAccountButton;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R5")
	private WebElement staffAccountCurrentButton ;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R6")
	private WebElement studentAccountButton;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R7")
	private WebElement groupCheckingButton ;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1792403_R8")
	private WebElement groupSubsidiaryTextButton;
	

	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1825964")
	private WebElement backButton;
	
	@FindBy(css="#ANCHOR_TABLE_26B2531682588B1B_FormTable_6_PAG_BOTTOM_1")
	private WebElement pageNumberOneEnabled;
	
	@FindBy(css="#ANCHOR_TABLE_26B2531682588B1B_FormTable_6_PAG_BOTTOM_2")
	private WebElement pageNumberTwoEnabled;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1825964")
	private WebElement pageNumberOne;
	
	@FindBy(css="#C4__C2__BUT_26B2531682588B1B1825964")
	private WebElement pageNumberTwo;
	

	public CurrentAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error {
		if(!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
			isFailedFirstTime = true;
			throw new Error();				
		} else if(isFailedFirstTime) {
			Log.fail("Not navigated to Current Account page!", driver);			
		} else {
			Log.message("Navigated to Current Account page!", driver);
		}		
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}
	
}