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

public class ChequesTab extends LoadableComponent<ChequesTab> {

	private WebDriver driver;
	
	private boolean isFailedFirstTime;
	
	@FindBy(css="#C4__C7__FMT_D447D1221CCA3DE3243823")
	private WebElement pageTitle;
	
	@FindBy(css="#C4__C7__TAB_D447D1221CCA3DE3243999")
	private WebElement tabChequeStatus;
	
	@FindBy(css="#C4__C7__TAB_D447D1221CCA3DE3245798")
	private WebElement tabStopChequePayment;
	
	@FindBy(css="#C4__C7__TAB_D447D1221CCA3DE3245803")
	private WebElement tabChequeBook;
	
	public ChequesTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error {
		if(!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
			isFailedFirstTime = true;
			throw new Error();				
		} else if(isFailedFirstTime) {
			Log.fail("Not navigated to Cheaque page!", driver);			
		} else {
			Log.message("Navigated to Cheaque page!", driver);
		}		
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}
	
	public ChequeStatusTab clickOnChequeStatusTab() throws Exception{
		BrowserActions.clickOnButton(tabChequeStatus, driver, "Cheque Status Tab");
		return new ChequeStatusTab(driver);
	}
	
	public StopChequePaymentTab clickOnStopChequePaymentTab() throws Exception{
		BrowserActions.clickOnButton(tabStopChequePayment, driver, "Stop Cheque Payment Tab");
		return new StopChequePaymentTab(driver);
	}
	
	public ChequeBookTab clickOnChequeBookTab() throws Exception{
		BrowserActions.clickOnButton(tabChequeBook, driver, "ChequeBook Tab");
		return new ChequeBookTab(driver);
	}
	
	public class ChequeStatusTab {
		
		private WebDriver driver;
		
		public ChequeStatusTab(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			}		
	}
	
	public class StopChequePaymentTab {
		
		private WebDriver driver;
		@FindBy(css="input[name*='C4__C7__WORKINGELEMENTS[1].DOYOUWANTTOSTOP'][value='1']")
		private List<WebElement> radioSingleCheque;
		
		@FindBy(css="input[name*='C4__C7__WORKINGELEMENTS[1].DOYOUWANTTOSTOP'][value='2']")
		private List<WebElement> radioRangeofCheques;
		
		@FindBy(css="#C4__C7__QUE_38D92DC8DCC3DD8C25305")
		private WebElement chequeNumber;
		
		@FindBy(css="##C4__C7__QUE_38D92DC8DCC3DD8C25307")
		private WebElement fromChequeNumber;
		
		@FindBy(css="#C4__C7__QUE_38D92DC8DCC3DD8C25305")
		private WebElement reasonForStop;
		
		@FindBy(css="#C4__C7__QUE_38D92DC8DCC3DD8C25311")
		private WebElement toChequeNumber;
		
		@FindBy(css="##C4__C7__BUT_38D92DC8DCC3DD8C25313")
		private WebElement stopCheque;
		
			
		public StopChequePaymentTab(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			}		
	}
	
	public class ChequeBookTab {
		private WebDriver driver;
				@FindBy(css="#C4__C7__BUT_38D92DC8DCC3DD8C25275")
		private WebElement requestNewChequeBook;
    			
		public ChequeBookTab(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			}		
		}
}