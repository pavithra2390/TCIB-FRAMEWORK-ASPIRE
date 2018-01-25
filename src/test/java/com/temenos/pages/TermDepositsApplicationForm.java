package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class TermDepositsApplicationForm extends
		LoadableComponent<TermDepositsApplicationForm> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "#C4__C1__FMT_1746DDCFA48877DA523254")
	private WebElement pageTitle;

	@FindBy(css = "#C4__C1__QUE_1746DDCFA48877DA545561")
	private WebElement productGroupText;

	@FindBy(css = "#C4__C1__QUE_1746DDCFA48877DA545587")
	private WebElement productext;

	@FindBy(css = "#C4__C1__QUE_1746DDCFA48877DA545591")
	private WebElement currency;

	@FindBy(css = "#C4__C1__QUE_1746DDCFA48877DA545599")
	private WebElement term;

	@FindBy(css = "#C4__C1__QUE_1746DDCFA48877DA545603")
	private WebElement amount;

	@FindBy(css = "#C4__C1__QUE_1746DDCFA48877DA545607")
	private WebElement doYouWantToPayImmediatelyDrop;

	@FindBy(css = "#C4__C1__BUT_1746DDCFA48877DA546761")
	private WebElement cancelButton;

	@FindBy(css = "#C4__C1__BUT_1746DDCFA48877DA546751")
	private WebElement continueButton;

	public TermDepositsApplicationForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Term Deposits Application Form page!",
					driver);
		} else {
			Log.message("Navigated to Term Deposits Application Form page!",
					driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

}