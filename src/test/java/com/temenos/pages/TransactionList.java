package com.temenos.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.temenos.pojo.Transaction;
import com.temenos.support.BrowserActions;

public class TransactionList {

	private WebDriver driver;

	@FindBy(css = "#C6__p1_QUE_ACA949ED59CB7DCE116445 a")
	private WebElement thDate;

	@FindBy(css = "#C6__p1_QUE_419320BE4FFA5116136079 a")
	private WebElement thDescription;

	@FindBy(css = "#C6__p1_QUE_F3ADD3FD9C498ED241233 a")
	private WebElement thAccount;

	@FindBy(css = "#C6__GROUP_FS_QUE_ACA949ED59CB7DCE114151 #C6__p4_QUE_ACA949ED59CB7DCE114151")
	private WebElement thImage;

	@FindBy(css = "#C6__p1_QUE_ACA949ED59CB7DCE114151 a")
	private WebElement thNotes;

	@FindBy(css = "#C6__p1_QUE_09914FCEAE1F08421960458 a")
	private WebElement thCurrency;

	@FindBy(css = "#C6__p1_QUE_0CC412380407D7A61641473 a")
	private WebElement thAmount;

	@FindBy(css = "table[summary='Recent Transactions List'] tbody tr")
	private List<WebElement> trAllTransactions;

	@FindBy(css = "table[summary='Recent Transactions List'] tbody tr td:nth-child(9)")
	private List<WebElement> tdAllTransactionsDetail;

	public TransactionList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Transaction[] getAllTransactions() throws Exception {
		Transaction[] allTxn = new Transaction[trAllTransactions.size()];
		int index = 0;
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		for (WebElement element : trAllTransactions) {
			Transaction txn = new Transaction();
			try {
				txn.setDate(fmt.parse(element
						.findElement(
								By.cssSelector("td[headers='C6__p1_QUE_ACA949ED59CB7DCE116445'] span"))
						.getText().trim()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txn.setDesc(element
					.findElement(
							By.cssSelector("td[headers='C6__p1_QUE_419320BE4FFA5116136079'] span"))
					.getText().trim());
			txn.setAccount(element
					.findElement(
							By.cssSelector("td[headers='C6__p1_QUE_F3ADD3FD9C498ED241233'] span"))
					.getText().trim());
			txn.setCurrency(element
					.findElement(
							By.cssSelector("td[headers='C6__p1_QUE_09914FCEAE1F08421960458'] span"))
					.getText().trim());
			String amt = element
					.findElement(
							By.cssSelector("td[headers='C6__p1_QUE_0CC412380407D7A61641473'] .tc-form-control"))
					.getText().trim();

			String[] amt1 = amt.split(",");

			if (amt1.length > 1) {
				amt = amt1[0] + amt1[1];
			}

			txn.setAmount(Double.parseDouble(amt));
			allTxn[index++] = txn;
		}
		return allTxn;
	}

	public void sortBy(String columnName, boolean asc) throws Exception {
		switch (columnName) {
		case "Description":
			if ((asc && null == thDescription.getAttribute("aria-sort"))
					|| (asc && !thDescription.getAttribute("aria-sort").equals(
							"ascending"))) {
				BrowserActions.clickOnButton(thDescription, driver,
						"Description Ascending Order Button");
			} else if ((!asc && null == thDescription.getAttribute("aria-sort"))
					|| (!asc && !thDescription.getAttribute("aria-sort")
							.equals("descending"))) {
				BrowserActions.clickOnButton(thDescription, driver,
						"Description Descending Order Button");
			}
			break;

		case "Account":
			if ((asc && null == thAccount.getAttribute("aria-sort"))
					|| (asc && !thAccount.getAttribute("aria-sort").equals(
							"ascending"))) {
				BrowserActions.clickOnButton(thAccount, driver,
						"Account Ascending Order Button");
			} else if ((!asc && null == thAccount.getAttribute("aria-sort"))
					|| (!asc && !thAccount.getAttribute("aria-sort").equals(
							"descending"))) {
				BrowserActions.clickOnButton(thAccount, driver,
						"Account Descending Order Button");
			}
			break;

		case "Date":
			if ((asc && null == thDate.getAttribute("aria-sort"))
					|| (asc && !thDate.getAttribute("aria-sort").equals(
							"ascending"))) {
				BrowserActions.clickOnButton(thDate, driver,
						"Date Ascending Order Button");
			} else if ((!asc && null == thDate.getAttribute("aria-sort"))
					|| (!asc && !thDate.getAttribute("aria-sort").equals(
							"descending"))) {
				BrowserActions.clickOnButton(thDate, driver,
						"Date Descending Order Button");
			}
			break;

		case "Currency":
			if ((asc && null == thCurrency.getAttribute("aria-sort"))
					|| (asc && !thCurrency.getAttribute("aria-sort").equals(
							"ascending"))) {
				BrowserActions.clickOnButton(thCurrency, driver,
						"Currency Ascending Order Button");
			} else if ((!asc && null == thCurrency.getAttribute("aria-sort"))
					|| (!asc && !thCurrency.getAttribute("aria-sort").equals(
							"descending"))) {
				BrowserActions.clickOnButton(thCurrency, driver,
						"Currency Descending Order Button");
			}
			break;

		case "Amount":
			if ((asc && null == thAmount.getAttribute("aria-sort"))
					|| (asc && !thAmount.getAttribute("aria-sort").equals(
							"ascending"))) {
				BrowserActions.clickOnButton(thAmount, driver,
						"Amount Ascending Order Button");
			} else if ((!asc && null == thAmount.getAttribute("aria-sort"))
					|| (!asc && !thAmount.getAttribute("aria-sort").equals(
							"descending"))) {
				BrowserActions.clickOnButton(thAmount, driver,
						"Amount Descending Order Button");
			}
			break;
		}
	}
}
