package com.temenos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class ScheduleTab extends LoadableComponent<ScheduleTab> {

	private WebDriver driver;
	boolean isFailedFirstTime;

	@Override
	protected void load() {
		WaitUtil.waitForSpinnerToComplete(driver);

	}

	@Override
	protected void isLoaded() throws Error {
		WaitUtil.waitForSpinnerToComplete(driver);
		if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, lblSchedule)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, lblSchedule)) {
			Log.fail("Not navigated to Schedule Page!", driver);
		} else {
			Log.message("Navigated to Schedule Page!", driver);
		}
	}

	public ScheduleTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#C5__TAB_2")
	private WebElement tabSchedule;

	@FindBy(css = "#C5__C3__HEAD_2F8E223FAE9F70151702421")
	private WebElement lblSchedule;

	@FindBy(css = "#C5__C3__TAB_D71EF3AD9156EF3C145062")
	private WebElement lnkOverdue;

	@FindBy(css = "#C5__C3__TAB_D71EF3AD9156EF3C147539")
	private WebElement lnkfuturepayment;

	@FindBy(css = "#C5__C3__TAB_D71EF3AD9156EF3C147544")
	private WebElement lnkPaidInstallment;

	public String getPaidInstallments() throws Exception {
		return BrowserActions.getText(driver, lnkPaidInstallment,
				"Paid installments link").split(" ")[2].trim();
	}

	public ScheduleTab clickPaidInstallments() throws Exception {
		BrowserActions.clickOnButton(lnkPaidInstallment, driver,
				"Paid installment link");
		return new ScheduleTab(driver).get();
	}

	public String getFuturePayments() throws Exception {
		return BrowserActions.getText(driver, lnkfuturepayment,
				"Future payments link").split(" ")[2].trim();
	}

	public ScheduleTab clickFuturePayments() throws Exception {
		BrowserActions.clickOnButton(lnkfuturepayment, driver,
				"Future payments link");
		return new ScheduleTab(driver).get();
	}

	public String getOverDuePayments() throws Exception {
		return BrowserActions.getText(driver, lnkOverdue, "Over due link")
				.split(" ")[2].trim();
	}

	public ScheduleTab clickOverDuePayments() throws Exception {
		BrowserActions.clickOnButton(lnkOverdue, driver, "Over due link");
		return new ScheduleTab(driver).get();
	}

	public String[] getColumnNamesofPaidInstallments() throws Exception {
		int nocols = driver
				.findElements(
						By.xpath("//div[@id=\"C5__C3__p1_GRP_2027F61A94E2F670118030\"]/div/div"))
				.size();
		String colNames[] = new String[3];

		for (int i = 0; i < nocols; i++) {
			colNames[i] = driver
					.findElement(
							By.xpath("//div[@id=\"C5__C3__p1_GRP_2027F61A94E2F670118030\"]/div/div["
									+ (i + 1) + "]")).getText();
		}
		return colNames;
	}

	public String[] getColumnNamesofFuturePayments() throws Exception {
		int nocols = driver
				.findElements(
						By.xpath("//div[@id=\"C5__C3__p1_GRP_2027F61A94E2F670109461\"]/div/div"))
				.size();
		String colNames[] = new String[3];

		for (int i = 0; i < nocols; i++) {
			colNames[i] = driver
					.findElement(
							By.xpath("//div[@id=\"C5__C3__p1_GRP_2027F61A94E2F670109461\"]/div/div["
									+ (i + 1) + "]")).getText();
		}
		return colNames;
	}

	public String[] getColumnNamesofOverdue() throws Exception {
		int nocols = driver
				.findElements(
						By.xpath("//div[@id=\"C5__C3__p1_GRP_2027F61A94E2F670103897\"]/div/div"))
				.size();
		String colNames[] = new String[3];

		for (int i = 0; i < nocols; i++) {
			colNames[i] = driver
					.findElement(
							By.xpath("//div[@id=\"C5__C3__p1_GRP_2027F61A94E2F670103897\"]/div/div["
									+ (i + 1) + "]")).getText();
		}
		return colNames;
	}

}
