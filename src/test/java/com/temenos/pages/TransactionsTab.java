package com.temenos.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class TransactionsTab extends LoadableComponent<TransactionsTab> {

    private WebDriver driver;

    private boolean isFailedFirstTime;

    @FindBy(css = "#C5__C2__HEAD_419320BE4FFA5116111154")
    private WebElement pageTitle;

    @FindBy(css = "a[oldtitle='Show Options']")
    private WebElement lnkShowOptions;

    @FindBy(xpath = "//div[@id='C5__C2__FMT_9BA2256B00894D29114354']/div[1]/div[1]/div[1]/div[1]/span")
    private WebElement tabCompleted;

    @FindBy(css = "#C4__C2__TAB_2")
    private WebElement tabPending;

    @FindBy(css = "#C5__C2__TAB_5")
    private WebElement tabSearch;

    private Headers headers;

    private Footers footers;

    public TransactionsTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        headers = new Headers(driver);
        footers = new Footers(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        if (!isFailedFirstTime && !WaitUtil.waitForElement(driver, pageTitle)) {
            isFailedFirstTime = true;
            throw new Error();
        } else if (isFailedFirstTime) {
            Log.fail("Not navigated to Transactions Tab!", driver);
        } else {
            Log.message("Navigated to Transactions Tab!", driver);
        }
    }

    @Override
    protected void load() {
        WaitUtil.waitForSpinnerToComplete(driver);
    }

    public CompletedTab clickOnCompletedTab() throws Exception {

        BrowserActions.clickOnButtonJS(tabCompleted, driver, "Completed");
        // Log.message("Completed tab is clicked!");
        return new CompletedTab(driver);
    }

    public PendingTab clickOnPendingTab() throws Exception {
        BrowserActions.clickOnButton(tabPending, driver, "Pending Tab");
        return new PendingTab(driver);
    }

    public SearchTab clickOnSearchTab() throws Exception {
        BrowserActions.clickOnButton(tabCompleted, driver, "Search Tab");
        return new SearchTab(driver);
    }

    public ShowOptions clickOnShowOptions() throws Exception {
        BrowserActions.clickOnButton(lnkShowOptions, driver, "Show Options");
        return new ShowOptions(driver);
    }

    public class CompletedTab {

        private WebDriver driver;

        @FindBy(css = "a[oldtitle='Next']")
        private WebElement lnkNext;

        @FindBy(css = "a[oldtitle='Back']")
        private WebElement lnkBack;

        @FindBy(xpath = "//table[@id=\"C5__C2__TBL_D64FDE45ED3559A01277734\"]/tbody/tr[1]/td[1]")
        private WebElement tdData;
        private TransactionList transactionList;

        public CompletedTab(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
            transactionList = new TransactionList(driver);
        }

        public TransactionList getTransactionList() {
            return transactionList;
        }

        public String[] getColumnNames() throws Exception {
            int nocols = driver
                    .findElements(
                            By.cssSelector("table[id='C5__C2__TBL_D64FDE45ED3559A01277734'] th"))
                    .size();
            String colNames[] = new String[4];

            for (int i = 0; i <= nocols - 4; i++) {
                colNames[i] = driver
                        .findElement(
                                By.xpath("//table[@id=\"C5__C2__TBL_D64FDE45ED3559A01277734\"]/thead/tr/th["
                                        + (i + 1) + "]")).getText();
            }
            return colNames;
        }

        public TransactionDetails goToTransactionDetails() throws Exception {
            BrowserActions.clickOnButton(tdData, driver, "First Transaction");
            return new TransactionDetails(driver);
        }
    }

    public class PendingTab {

        private WebDriver driver;

        @FindBy(css = "a[oldtitle='Next']")
        private WebElement lnkNext;

        @FindBy(css = "a[oldtitle='Back']")
        private WebElement lnkBack;

        private TransactionList transactionList;

        public PendingTab(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
            transactionList = new TransactionList(driver);
        }

        public TransactionList getTransactionList() {
            return transactionList;
        }
    }

    public class SearchTab {
        private WebDriver driver;

        @FindBy(css = "a[oldtitle='Next']")
        private WebElement lnkNext;

        @FindBy(css = "input[name*='PAYTYPE']")
        private List<WebElement> radioPaymentTypes;

        @FindBy(css = "input[name*='SEARCHSTARTDATE']")
        private WebElement txtFromDate;

        @FindBy(css = "input[name*='SEARCHENDDATE']")
        private WebElement txtToDate;

        @FindBy(css = "input[name*='MINAMT']")
        private WebElement txtMinAmt;

        @FindBy(css = "input[name*='MAXAMT']")
        private WebElement txtMaxAmt;

        @FindBy(css = "input[name*='DESCRIPTION']")
        private WebElement txtDescription;

        @FindBy(css = "select[name*='TRANSACTIONCODE']")
        private WebElement txtTransactionType;

        @FindBy(css = "a[oldtitle='Search']")
        private WebElement lnkSearch;

        @FindBy(css = "a[oldtitle='Clear All']")
        private WebElement lnkClearAll;

        public SearchTab(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }

    public Headers getHeaders() {
        return headers;
    }

    public Footers getFooters() {
        return footers;
    }

    public class TransactionDetails {
        private WebDriver driver;

        @FindBy(css = "#C5__TAB_6A332AB325C8A67C1225111")
        private WebElement tabOverView;

        @FindBy(css = "#C5__BUT_2B877470A88FCA0E28328")
        private WebElement lnkAddPhoto;

        @FindBy(css = "#C5__BUT_A7F3C3D9BDB7EFD3104454")
        private WebElement lnkAddNote;

        @FindBy(css = "#C5__BUT_A7F3C3D9BDB7EFD3102782")
        private WebElement deleteNote;

        @FindBy(css = "#C5__BUT_A7F3C3D9BDB7EFD3103621")
        private WebElement editNote;

        @FindBy(css = "#C5__QUE_A7F3C3D9BDB7EFD3101122")
        private WebElement txtAreaNote;

        @FindBy(css = "textarea[name*='NOTES_READONLY']")
        private WebElement txtAreaNoteRO;

        @FindBy(css = "#C5__BUT_A7F3C3D9BDB7EFD3102788")
        private WebElement lnkSave;

        @FindBy(css = "#C5__BUT_9EDE62656FE56F6689890")
        private WebElement lnkBack;

        public TransactionDetails(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public String getOverViewTitle() throws Exception {
            WaitUtil.sleep(3);
            return BrowserActions.getText(driver, tabOverView, "Overview tab");
        }

        public TransactionDetails addPhoto() throws Exception {
            BrowserActions.clickOnButton(lnkAddPhoto, driver, "Add Photo Link");
            return new TransactionDetails(driver);
        }

        public TransactionDetails addNote(String note) throws Exception {
            WaitUtil.sleep(3);
            BrowserActions.clickOnButton(lnkAddNote, driver, "Add Note Link");
            BrowserActions.typeOnTextFieldUsingJS(txtAreaNote, note, driver,
                    "Note on transaction");
            BrowserActions.clickOnButton(lnkSave, driver, "Save Button");
            return new TransactionDetails(driver);
        }

        public TransactionDetails deleteNote() throws Exception {
            WaitUtil.sleep(3);
            BrowserActions.clickOnButton(editNote, driver, "Add Note Link");
            WaitUtil.sleep(3);
            BrowserActions.clickOnButton(deleteNote, driver, "Add Note Link");
            return new TransactionDetails(driver);
        }

        public boolean verifyNoteText() throws Exception {
            WaitUtil.sleep(3);
            return BrowserActions.elementDisplayed(driver, lnkAddNote);
        }

        public TransactionsTab goBack() throws Exception {
            BrowserActions.clickOnButton(lnkBack, driver, "Go Back Link");
            return new TransactionsTab(driver);
        }

        public String getNoteMessage() throws Exception {
            return BrowserActions
                    .getText(driver, txtAreaNoteRO, "Note message");
        }
    }
}
