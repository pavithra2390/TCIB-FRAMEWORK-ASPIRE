package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.temenos.support.BrowserActions;

public class ShowOptions {

	private WebDriver driver;

	@FindBy(css = "a[oldtitle='Print']")
	private WebElement lnkPrint;

	@FindBy(css = "a[oldtitle='PDF Download']")
	private WebElement lnkPdfDownload;

	@FindBy(css = "a[oldtitle='CSV Download']")
	private WebElement lnkCsvDownload;

	public ShowOptions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPrint() throws Exception {
		BrowserActions.clickOnButton(lnkPrint, driver, "Print link");
	}

	public void clickOnPDFDownload() throws Exception {
		BrowserActions.clickOnButton(lnkPdfDownload, driver, "PDF Download");
	}

	public void clickOnCSVDownload() throws Exception {
		BrowserActions.clickOnButton(lnkCsvDownload, driver, "CSV Download");
	}
}
