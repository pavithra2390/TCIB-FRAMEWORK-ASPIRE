package com.temenos.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class BranchOrATMLocatorPage extends LoadableComponent<BranchOrATMLocatorPage> {

private WebDriver driver;
	
	private boolean isFailedFirstTime;

	@FindBy(css="input[id='C5__SearchMapInput']")
	private WebElement txtSearchBox;
	
	@FindBy(xpath="//input[contains(@name,'BRANCH')]/parent::*")
	private WebElement checkBoxBranch;
	
	@FindBy(xpath="//input[contains(@name,'ATM')]/parent::*")
	private WebElement checkBoxATM;
	
	@FindBy(css="div[title='Locations'] div[class='responsive-row']")
	private List<WebElement> searchResults;
	
	@FindBy(css="a[oldtitle='Get Current Location']")
	private WebElement lnkCurrentLocation;
	
	private Headers headers;
	
	private Footers footers;
	
	public BranchOrATMLocatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		headers = new Headers(driver);
		footers = new Footers(driver);
	}
	
	@Override
	protected void isLoaded() throws Error {
		if(!isFailedFirstTime && !WaitUtil.waitForElement(driver, txtSearchBox)) {
			isFailedFirstTime = true;
			throw new Error();				
		} else if(isFailedFirstTime) {
			Log.fail("Not navigated to Branch Or ATM Locator Page!", driver);			
		} else {
			Log.message("Navigated to Branch Or ATM Locator Page!", driver);
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
	
	public boolean verifyBranchOrATMLocator() throws Exception{
		WaitUtil.waitForElement(driver, txtSearchBox, 15);
		if(txtSearchBox.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
}
