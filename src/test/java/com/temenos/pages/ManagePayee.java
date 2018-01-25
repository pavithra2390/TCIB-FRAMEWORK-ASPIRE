package com.temenos.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class ManagePayee extends LoadableComponent<ManagePayee> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(xpath = "//h2[contains(string(), \"Manage Payees\")]")
	private WebElement lbeManagePayees;

	@FindBy(xpath = "//h2[contains(string(), \"Your transaction created successfully\")]")
	private WebElement successMsg;

	@FindBy(xpath = "//span[contains(string(),\"Return to Beneficiary List\")]")
	private WebElement btnReturn;

	@FindBy(css = "#C4__p1_HEAD_F59A98CD98F1A342126299>div")
	private WebElement lbeSavedPayees;

	@FindBy(css = "#C4__HEAD_3E590269BD319CB11696514")
	private WebElement lbeNoRecordsMessage;

	// @FindBy(css = "a[oldTitle='Add a new payee']")
	@FindBy(css = "#C5__BUT_22B9C56E3E5035D873875")
	private WebElement btnAddANewPayee;

	String elementid = "#C5__TBL_22B9C56E3E5035D873877";

	@FindBy(css = "input[name*='IBANBEN_READONLY']")
	private WebElement txtPayeeIBAN;

	@FindBy(css = "input[name*='BENACCTNO_READONLY']")
	private WebElement txtPayeeAccNo;

	@FindBy(css = "input[name*='BIC_READONLY']")
	private WebElement txtBIC;

	@FindBy(css = "input[name*='BENCUSTOMER_READONLY']")
	private WebElement txtPayeeName;

	@FindBy(css = "input[name*='NICKNAME_READONLY']")
	private WebElement txtNickName;

	@FindBy(css = "input[name*='CUSTOMERREF_READONLY']")
	private WebElement txtReference;

	@FindBy(css = "#C5__TBL_22B9C56E3E5035D873877_next")
	private WebElement lnkMoveNext;

	@FindBy(css = "#C5__TBL_22B9C56E3E5035D873877 tr td")
	private List<WebElement> tblId;
	
	@FindBy(css = "#C5__p1_QUE_22B9C56E3E5035D873879")
	private WebElement tdPayeeName;
	
	@FindBy(css = "#C5__p1_QUE_22B9C56E3E5035D873881")
	private WebElement tdPayeeAccount;
	
	@FindBy(css = "#C5__p1_QUE_22B9C56E3E5035D873883")
	private WebElement tdPayeeReference;
	
	public ManagePayee(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ManagePayee() {

	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, lbeManagePayees)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to ManagePayee page!", driver);
		} else {
			Log.message("Navigated to Managepayee page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);
	}

	public NewPayee clickOnAddANewPayeeButton() throws Exception {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", btnAddANewPayee);
		WaitUtil.sleep(2);
		BrowserActions.clickOnButton(btnAddANewPayee, driver,
				"Click on Add A New Payee Button");
		return new NewPayee(driver).get();
	}

	public boolean verifyOpenAppSettings() throws Exception {
		WaitUtil.waitForElement(driver, lbeManagePayees, 15);
		if (lbeManagePayees.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void verifySuccessMessage() throws Exception {
		WaitUtil.waitForElement(driver, successMsg, 15);
		Assert.assertEquals(successMsg.isDisplayed(), true);
	}

	public ManagePayee clickOnAccount(String name, String bic, String accNo,
			String iban) throws Exception {
		BrowserActions.clickOnButton(btnReturn, driver, "");
		int totalPages = getTotalPages();
		int page = 1;
		while (page <= totalPages) {
			for (int index = 0; index < tblId.size(); index++) {
				System.out.println(tblId.get(index).getText());
				if (tblId.get(index).getText().contains(name)) {
					if (tblId.get(index + 1).getText().equals(iban)
							| tblId.get(index + 1).getText().equals(accNo)
							| tblId.get(index + 2).getText().equals(accNo)) {
						try {
							tblId.get(index).click();
							return new ManagePayee(driver);
						} catch (Exception e) {
							((JavascriptExecutor) driver).executeScript(
									"arguments[0].scrollIntoView(true);",
									tblId.get(index));
							WaitUtil.sleep(2);
							tblId.get(index).click();
							return new ManagePayee(driver);
						}
					}
				}
			}
			goToNextPage();
			page += 1;
		}
		throw new Error("No matching records found!");
	}

	public ManagePayee selectName(String name, String reference)
			throws Exception {
		BrowserActions.clickOnButton(btnReturn, driver, "");
		int totalPages = getTotalPages();
		int page = 1;
		while (page <= totalPages) {
			for (int index = 0; index < tblId.size(); index++) {
				System.out.println(tblId.get(index).getText());
				if (tblId.get(index).getText().contains(name)) {
					if (tblId.get(index + 1).getText().equals(name)
							| tblId.get(index + 2).getText().equals(reference)) {
						try {
							tblId.get(index).click();
							return new ManagePayee(driver).get();
						} catch (Exception e) {
							((JavascriptExecutor) driver).executeScript(
									"arguments[0].scrollIntoView(true);",
									tblId.get(index));
							WaitUtil.sleep(2);
							tblId.get(index).click();
							return new ManagePayee(driver).get();
						}
					}
				}
			}
			goToNextPage();
			page += 1;
		}
		throw new Error("No matching records found!");
	}

	public ManagePayee selectID(String name, String accNo) throws Exception {
		BrowserActions.clickOnButton(btnReturn, driver, "");
		int totalPages = getTotalPages();
		int page = 1;
		while (page <= totalPages) {
			for (int index = 0; index < tblId.size(); index++) {
				System.out.println(tblId.get(index).getText());
				if (tblId.get(index).getText().contains(name)) {
					if (tblId.get(index + 1).getText().equals(accNo)) {
						try {
							tblId.get(index).click();
							return new ManagePayee(driver).get();
						} catch (Exception e) {
							((JavascriptExecutor) driver).executeScript(
									"arguments[0].scrollIntoView(true);",
									tblId.get(index));
							WaitUtil.sleep(2);
							tblId.get(index).click();
							return new ManagePayee(driver).get();
						}
					}
				}
			}
			goToNextPage();
			page += 1;
		}
		throw new Error("No matching records found!");
	}

	public String getPayeeAccountNumber() throws Exception {
		return BrowserActions.getTextFromAttribute(driver, txtPayeeAccNo,
				"value", "IBAN");
	}

	public String getBICValue() throws Exception {
		return BrowserActions.getTextFromAttribute(driver, txtBIC, "value",
				"BIC");
	}

	public String getPayeeIBAN() throws Exception {
		return BrowserActions.getTextFromAttribute(driver, txtPayeeIBAN,
				"value", "IBAN");
	}

	public String getPayeeName() throws Exception {
		return BrowserActions.getTextFromAttribute(driver, txtPayeeName,
				"value", "Payee Name");
	}

	public String getNickName() throws Exception {
		return BrowserActions.getTextFromAttribute(driver, txtNickName,
				"value", "Payee Name");
	}

	public String getReference() throws Exception {
		return BrowserActions.getTextFromAttribute(driver, txtReference,
				"value", "Payee Name");
	}

	public int getTotalPages() throws Exception {
		return Integer
				.parseInt(driver
						.findElement(
								By.cssSelector("#C5__TBL_22B9C56E3E5035D873877_paginate>span>a:nth-last-of-type(1)"))
						.getText());
	}

	public void goToNextPage() throws Exception {
		BrowserActions.clickOnButton(lnkMoveNext, driver, "Next Page");
		WaitUtil.waitForSpinnerToComplete(driver);
	}
}