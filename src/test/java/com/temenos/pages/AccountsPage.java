package com.temenos.pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.ElementLayer;
import com.temenos.support.Log;
import com.temenos.support.Utils;
import com.temenos.support.WaitUtil;

public class AccountsPage extends LoadableComponent<AccountsPage> {

    private WebDriver driver;

    @FindBy(css = "#C5__TAB_2")
    private WebElement transferTab;

    @FindBy(css = "#C5__TAB_3")
    private WebElement paymentsTab;

    @FindBy(css = "#C5__TAB_4")
    private WebElement standingOrdTab;

    @FindBy(css = "#C5__TAB_5")
    private WebElement DirectDebitTab;

    @FindBy(css = "#C5__TAB_6")
    private WebElement chequesTab;

    @FindBy(css = "#C5__TAB_2B43D3DAB27FE832127243")
    private WebElement alertsTab;

    @FindBy(css = "#C5__C1__QUE_7FF09F1E4657A0DE167038")
    private WebElement labelBalance;

    @FindBy(css = "#C5__C1__QUE_7FF09F1E4657A0DE703674")
    private WebElement labelAvailableFunds;

    // Mortgage fields

    @FindBy(css = "#C5__C1__p1_HEAD_C1F553AF78F2748F93353 .tc-card-title")
    private WebElement labelMortgage;

    @FindBy(css = "#C4__C1__row_HEAD_C1F553AF78F2748F93634")
    private WebElement labelMortgageAccountNumber;

    @FindBy(css = "#C5__C1__QUE_7F37BCD24078011F325711")
    private WebElement labelMortgageBalance;

    // Term deposit fields
    @FindBy(css = "#C5__C1__HEAD_9769AD5D51543845291581")
    private WebElement labelTermDepositAccountNumber;

    @FindBy(css = "#C5__C1__BUT_7F37BCD24078011F325725")
    private WebElement lnkShowMoreLess;

    @FindBy(css = "table[summary='Product List Navigation'] tr")
    private List<WebElement> allAccounts;

    @FindBy(css = "a[oldtitle='Expand']")
    private WebElement lnkShowMoreLessForAccounts;

    @FindBy(xpath = ".//*[contains(text(),'Transactions')]/parent::div")
    private WebElement tabTransactions;

    @FindBy(css = "#C4__C1__COL_7FF09F1E4657A0DE165799 .tc-card-title")
    private WebElement labelAccountType;

    @FindBy(xpath = ".//*[contains(text(),'Transfers')]/parent::div")
    private WebElement tabTransfers;

    @FindBy(xpath = ".//*[contains(text(),'Standing orders')]/parent::div")
    private WebElement tabStandingOrd;

    @FindBy(xpath = ".//*[contains(text(),'Payments')]/parent::div")
    private WebElement tabPayments;

    @FindBy(xpath = ".//*[contains(text(),'Standing orders')]/parent::div")
    private WebElement tabStandingOrders;

    @FindBy(xpath = ".//*[contains(text(),'Direct debits')]/parent::div")
    private WebElement tabDirectDebts;

    @FindBy(xpath = ".//*[contains(text(),'Cheques')]/parent::div")
    private WebElement tabCheques;

    @FindBy(xpath = ".//*[contains(text(),'Alerts')]/parent::div")
    private WebElement tabAlerts;

    @FindBy(xpath = ".//*[text()='Schedule']/parent::div")
    private WebElement tabSchedule;

    @FindBy(xpath = ".//*[text()='Pay Overdue']/parent::div")
    private WebElement tabPayOverdue;

    @FindBy(xpath = ".//*[text()='End of Term Actions']/parent::div")
    private WebElement tabEndOfTermActions;

    @FindBy(css = "#C4__C1__COL_7FF09F1E4657A0DE165799 #C4__C1__row_HEAD_7FF09F1E4657A0DE165859")
    private WebElement labelAccountNumber;

    @FindBy(css = "#C5__C5__TBL_176BE187C511D0CC10622_info")
    private WebElement lblPageCountTxt;


    private Headers headers;

    private Footers footers;

    private boolean isFailedFirstTime;

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        headers = new Headers(driver);
        footers = new Footers(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        WaitUtil.waitForSpinnerToComplete(driver);
        if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, tabTransactions)) {
            isFailedFirstTime = true;
            throw new Error();
        } else if (isFailedFirstTime && !WaitUtil.waitForElement(driver, tabTransactions)) {
            Log.fail("Not navigated to Accounts Page!", driver);
        } else {
            Log.message("Navigated to Accounts Page!", driver);
        }
    }

    @Override
    protected void load() {
        WaitUtil.waitForSpinnerToComplete(driver);
    }

    public TransactionsTab goToTransactionsTab() throws Exception{
        WaitUtil.sleep(3);
    	BrowserActions.clickOnButton(tabTransactions, driver, "Transactions Tab");
        return new TransactionsTab(driver).get();
    }

    public TransfersTab goToTransfersTab() throws Exception{
        WaitUtil.sleep(3);
        BrowserActions.clickOnButton(tabTransfers, driver, "Transfers Tab");
        return new TransfersTab(driver).get();
    }

    public StandingOrderTab goToStandingOrderTab() throws Exception{
        BrowserActions.clickOnButton(tabStandingOrd, driver, "Standing Order Tab");
        Utils.waitForPageLoad(driver);
        Utils.waitForElement(driver, lblPageCountTxt);
        return new StandingOrderTab(driver).get();
    }


    public PaymentsTab goToPaymentsTab() throws Exception{
        Utils.waitForPageLoad(driver);
        BrowserActions.clickOnButton(tabPayments, driver, "Payments Tab");
        WaitUtil.waitForSpinnerToComplete(driver);
        Utils.waitForPageLoad(driver);
        return new PaymentsTab(driver);
    }

    public Headers getHeaders() {
        return headers;
    }

    public Footers getFooters() {
        return footers;
    }

    public ScheduleTab goToScheduleTab() throws Exception{
        BrowserActions.clickOnButtonJS(tabSchedule, driver, "Schedule Tab");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click()", tabSchedule);
        return new ScheduleTab(driver).get();
    }

    public String getAccountNumber() throws Exception{
        return BrowserActions.getText(driver, labelAccountNumber, "Account Number");
    }

    public String getMortgageAccountNumber() throws Exception{
        return BrowserActions.getText(driver, labelMortgageAccountNumber, "Mortgage Account Number");
    }

    public String getAccountType() throws Exception{
        return BrowserActions.getText(driver, labelAccountType, "Account Type");
    }

    public String getTermDepositAccountNumber() throws Exception{
        return BrowserActions.getText(driver, labelTermDepositAccountNumber, "Term Deposit Account Number");
    }

    public boolean verifyPageElementsAreDisplayed(List<String> expectedTabs, Object obj, WebDriver driver) throws Exception{
        Log.message("Verifying whether the expected page elements are displayed..", driver);
        List<String> missingElements = new ArrayList<String>();
        for (String tab : expectedTabs) {
            try {
                Field f = obj.getClass().getDeclaredField(tab);
                f.setAccessible(true);
                if (!((WebElement) f.get(obj)).isDisplayed()) {
                    missingElements.add(tab);
                }
            } catch (Exception e) {
                e.printStackTrace();
                missingElements.add(tab);
            }
        }

        Log.softAssertThat(missingElements.isEmpty(), expectedTabs + " are displayed correctly.",
                missingElements + " are not displayed.");


        return PaymentsTab.compareTwoList(expectedTabs, missingElements);
    }

    public boolean VerifyPageElementDisplayed(List<String> expectedElements, Object obj) throws Exception {
        List<String> actual_elements = new ArrayList<String>();
        for (String expEle : expectedElements) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such a field present on this page, Please check the expected list values:: " + expEle);
            }
            WebElement element = null;
            try {
                element = ((WebElement) f.get(obj));
                //	BrowserActions.scrollToViewElement(element, driver);
                if (element.isDisplayed()) {
                    actual_elements.add(expEle);
                }
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                Log.exception(e1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return PaymentsTab.compareTwoList(expectedElements, actual_elements);
    }

    public String getPayOverdueTitle() throws Exception{
        return BrowserActions.getText(driver, tabPayOverdue, "Transaction tab");
    }

    public PayOverdueTab goToPayOverDueTab() throws Exception{
        WaitUtil.sleep(2);
        BrowserActions.clickOnButton(tabPayOverdue, driver, "Pay overdue Tab");
        return new PayOverdueTab(driver).get();
    }

    public String[] getColumnNamesofOverdue() throws Exception{
        int nocols = driver.findElements(By.xpath("//table[@id=\"C5__C1__TBL_7F37BCD24078011F325809\"]/tbody/tr")).size();
        String colNames[] = new String[nocols];
        for (int i = 0; i < nocols; i++) {
            colNames[i] = driver.findElement(By.xpath("//table[@id=\"C5__C1__TBL_7F37BCD24078011F325809\"]/tbody/tr[" + (i + 1) + "]")).getText();
        }
        return colNames;
    }

    public AccountsPage clickShowMoreLess() throws Exception{
        BrowserActions.clickOnButtonJS(lnkShowMoreLess, driver, "Show more less");
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].click()", lnkShowMoreLess);
        return new AccountsPage(driver).get();
    }


}
