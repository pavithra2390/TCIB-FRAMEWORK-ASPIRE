package com.temenos.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class ExchangeRatesPage extends LoadableComponent<ExchangeRatesPage> {

	private WebDriver driver;
	
	private boolean isFailedFirstTime;

	@FindBy(xpath="//input[@value='SELL']/parent::*")
	private WebElement tabSellRates;
	
	@FindBy(xpath="//input[@value='BUY']/parent::*")
	private WebElement tabBuyRates;
	
	@FindBy(css=".tc-nav-item-first")
	private WebElement lnkBack;
	
	@FindBy(css=".tc-nav-item-last")
	private WebElement lnkNext;
	
	@FindBy(css=".tc-table-nav-row > span:nth-child(1)")
	private WebElement labelPageNumber;
	
	@FindBy(css="table[summary='Currency'] tbody tr td[class!='edgeConnectDisabled']:nth-child(1)")
	private List<WebElement> currencyNamesList;
	
	@FindBy(css="table[summary='Currency'] tbody tr td[class!='edgeConnectDisabled']:nth-child(2)")
	private List<WebElement> currencyValuesList;
	
	private By lnkPageNumber = By.cssSelector("a[id$='BOTTOM_%s']");
	
	private Headers headers;
	
	private Footers footers;
	
	public ExchangeRatesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		headers = new Headers(driver);
		footers = new Footers(driver);
	}
	
	@Override
	protected void isLoaded() throws Error {
		if(!isFailedFirstTime && !WaitUtil.waitForElement(driver, tabSellRates)) {
			isFailedFirstTime = true;
			throw new Error();				
		} else if(isFailedFirstTime) {
			Log.fail("Not navigated to Exchange Rates Page!", driver);			
		} else {
			Log.message("Navigated to Exchange Rates Page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}
	
	public Headers getHeaders() {
		return headers;
	}
	
	public Footers getFooters() {
		return footers;
	}
	
	public boolean verifyExchangeRates() throws Exception{
		WaitUtil.waitForElement(driver, tabSellRates, 15);
		if(tabSellRates.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
}
