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

public class QuickTransferTab extends LoadableComponent<QuickTransferTab> {

    private WebDriver driver;

    private boolean isFailedFirstTime;

    @FindBy(css = "#C4__TAB_1 span")
    private WebElement tabAllAccounts;

    @FindBy(css = "#C4__TAB_2 span")
    private WebElement tabQuickTransfers;

    // @FindBy(css="#C4__PaneTAB_2 select")
    // private List<WebElement> fromAndToAccount;

    // @FindBy(css="#C5__PaneTAB select")
    // private List<WebElement> fromAccount;

    @FindBy(css = "#C5__C1__QUE_F8DA95FE4D404A97490996")
    private WebElement fromAccount;

    @FindBy(css = "#C5__C1__QUE_82ABB0F847B5B79B101089")
    private WebElement toAccount;

    @FindBy(css = "##C5__C1__QUE_82ABB0F847B5B79B101089 option")
    private WebElement toAccountOptions;

    @FindBy(css = "input[name*='PAYMENTCURRENCY_READONLY']")
    private WebElement txtCurrency;

    @FindBy(css = "input[name*='PAYMENTAMOUNT']")
    private WebElement txtPaymentAmount;

    @FindBy(css = "a[title='Transfer']")
    private WebElement lnkTransfer;

    // =========================================================================

    @FindBy(css = "#C5__C1__HEAD_284400FD85D8BE5E338428")
    private WebElement transferpageLabel;

    @FindBy(css = "#C5__C1__QUE_5EC36DDD152974CE86685")
    private WebElement fromAccountNumber;

    @FindBy(css = "#C5__C1__QUE_EA43D91BD2C21B2A147662")
    private WebElement toAccountNumber;

    @FindBy(css = "#C5__C1__QUE_EE9E2CFD98FC5382273028")
    private WebElement transferredAmount;

    @FindBy(css = "#C5__C1__BUT_5EC36DDD152974CE85818")
    private WebElement pdfDownloadLink;

    @FindBy(css = "#C5__C1__BUT_5EC36DDD152974CE85824")
    private WebElement printLink;

    @FindBy(css = "#C5__C1__BUT_284400FD85D8BE5E338634")
    private WebElement newTransferButton;

    @FindBy(css = "#C5__C1__QUE_C788E057B1510D08345404")
    private WebElement transfersReferenceNumber;

    String amountAcutal = null;
    String minimumValue = null;
    // public void getTransferredAmount() {
    // System.out.println(BrowserActions.elementDisplayed(driver,
    // transferredAmount));
    // System.out.println(BrowserActions.getText(driver, transferredAmount,
    // "Transferred amount111111111111"));
    // System.out.println(BrowserActions.getText(driver, transferredAmount,
    // "Transferred amount"));
    //
    // }

    // ============================================================================

    // =============== Negative Case
    // Elements=======================================

    @FindBy(css = "#C5__C1__QUE_8A879802D57FCC04358170_ERRORMESSAGE")
    private WebElement minimumAmountValue;

    public String verifyMinimumValue() throws Exception {
        minimumValue = BrowserActions.getText(driver, minimumAmountValue,
                "verify Minimum Value");
        return minimumValue;
    }

    public boolean verifyPageMovement() throws Exception {
        return BrowserActions.elementDisplayed(driver, fromAccount);
    }

    // =============================================================================

    public String getTransfferedAmount()
            throws Exception {
        return amountAcutal = BrowserActions.getText(driver, transferredAmount,
                "Transferred amount");
    }

    private TransactionList transactionList;
    private manageTransferred manageTransferred;

    public QuickTransferTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        transactionList = new TransactionList(driver);
    }

    public class moneyTransferedPage {
        private WebDriver driver;
    }

    @Override
    protected void isLoaded() throws Error {
        if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, lnkTransfer)) {
            isFailedFirstTime = true;
            throw new Error();
        } else if (isFailedFirstTime) {
            Log.fail("Not navigated to Quick Transfer Tab!", driver);
        } else {
            Log.message("Navigated to Quick Transfer Tab!", driver);
        }
    }

    @Override
    protected void load() {
        WaitUtil.waitForSpinnerToComplete(driver);
    }

    public AllAccountsTab goToAllAccountsTab() throws Exception {
        BrowserActions
                .clickOnButton(tabAllAccounts, driver, "All Accounts Tab");
        return new AllAccountsTab(driver);
    }

    public String getCurrencyAttribute() throws Exception {
        return BrowserActions.getTextFromAttribute(driver, txtCurrency,
                "readonly", "Readobly property of currency");
    }

    public QuickTransferTab goToQuickTransferTab() throws Exception {
        BrowserActions.clickOnButton(tabQuickTransfers, driver,
                "Quick Transfer Tab");
        return new QuickTransferTab(driver);
    }

    public TransactionList getTransactionList() throws Exception {
        return transactionList;
    }

    public manageTransferred getQuickTransferTabList() throws Exception {
        return manageTransferred;
    }

    public class manageTransferred {
        private WebDriver driver;

        @FindBy(css = "#C5__C1__HEAD_284400FD85D8BE5E338428")
        private WebElement transferpageLabel;

        @FindBy(css = "#C5__C1__QUE_5EC36DDD152974CE86685")
        private WebElement fromAccountNumber;

        @FindBy(css = "#C5__C1__QUE_EA43D91BD2C21B2A147662")
        private WebElement toAccountNumber;

        @FindBy(css = "#C5__C1__QUE_EE9E2CFD98FC5382273028")
        private WebElement transferredAmount;

        @FindBy(css = "#C5__C1__BUT_5EC36DDD152974CE85818")
        private WebElement pdfDownloadLink;

        @FindBy(css = "#C5__C1__BUT_5EC36DDD152974CE85824")
        private WebElement printLink;

        @FindBy(css = "#C5__C1__BUT_284400FD85D8BE5E338634")
        private WebElement newTransferButton;

        @FindBy(css = "#C5__C1__QUE_C788E057B1510D08345404")
        private WebElement transfersReferenceNumber;

        public manageTransferred(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }

    public QuickTransferTab selectFromAccount(String accountNumberFrom)
            throws Exception {
        BrowserActions.selectFromDropDown(fromAccount, accountNumberFrom,
                driver, "QuickTransfer from account");
        return new QuickTransferTab(driver);
    }

    public QuickTransferTab selectToAccount(String accountNumberTo)
            throws Exception {
        BrowserActions.selectFromDropDown(toAccount, accountNumberTo, driver,
                "QuickTransfer To account");
        return new QuickTransferTab(driver);
    }

    public QuickTransferTab enterAmount(String amount) throws Exception {
        BrowserActions.typeOnTextField(txtPaymentAmount, amount, driver,
                "Quick Transfer Amount ");
        return new QuickTransferTab(driver);
    }

    public QuickTransferTab clickTransferButton() throws Exception {
        BrowserActions.clickOnButton(lnkTransfer, driver,
                "clicked Transfer Button");
        return new QuickTransferTab(driver);
    }

    public String[] getToAcoountNos() throws Exception {
        int nocols = driver.findElements(
                By.cssSelector("#C5__C1__QUE_82ABB0F847B5B79B101089 option"))
                .size();
        String colNames[] = new String[nocols];
        for (int i = 0; i < nocols; i++) {
            colNames[i] = driver
                    .findElement(
                            By.xpath("//select[@id=\"C5__C1__QUE_82ABB0F847B5B79B101089\"]/option["
                                    + (i + 1) + "]")).getText();
        }
        return colNames;
    }
}
