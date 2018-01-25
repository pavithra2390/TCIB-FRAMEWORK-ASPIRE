package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.WaitUtil;

public class MessagePage extends LoadableComponent<MessagePage> {

	private WebDriver driver;

	private boolean isFailedFirstTime;

	@FindBy(css = "div[id*='C4__p1_HEAD_'] h2[class*='tc-card-title']")
	private WebElement lbeMessageInbox;

	@FindBy(xpath = ".//*[@id='C4__BUT_9CD2BA4DB6D8D181339090_R1']")
	private WebElement lnkWelcomeToInternetBanking;

	@FindBy(xpath = ".//*[@id='C4__BUT_9CD2BA4DB6D8D181339090_R2']")
	private WebElement lnkWelcomeMessage;

	@FindBy(css = "a[oldtitle='NEW MESSAGE']")
	private WebElement lnkNewMessage;

	public MessagePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isFailedFirstTime
				&& !WaitUtil.waitForElement(driver, lbeMessageInbox)) {
			isFailedFirstTime = true;
			throw new Error();
		} else if (isFailedFirstTime) {
			Log.fail("Not navigated to Message page!", driver);
		} else {
			Log.message("Navigated to Message page!", driver);
		}
	}

	@Override
	protected void load() {
		WaitUtil.waitForPageLoad(driver);
	}

	public NewMessage goToNewMessageWindow() throws Exception {
		BrowserActions.clickOnButton(lnkNewMessage, driver,
				"Click on New Message Link");
		return new NewMessage(driver);
	}

	public WelcomeToInternetBanking goToWelcomeToInternetBankingWindow()
			throws Exception {
		BrowserActions.clickOnButton(lnkWelcomeToInternetBanking, driver,
				"Click on Welcome to Internet Banking Link");
		return new WelcomeToInternetBanking(driver);
	}

	public WelcomeMessage goToWelcomeMessageWindow() throws Exception {
		BrowserActions.clickOnButton(lnkWelcomeMessage, driver,
				"Click on Welcome Message Link");
		return new WelcomeMessage(driver);
	}

	public class NewMessage {
		private WebDriver driver;

		@FindBy(xpath = ".//*[@id='C4__HEAD_0AC9B3815265C20511756']")
		private WebElement lbeComposeMessage;

		@FindBy(css = "#C4__QUE_42D7FF66B12542DF18473")
		private WebElement drpDwnSubject;

		@FindBy(css = "a[oldtitle='Send']")
		private WebElement btnSend;

		@FindBy(css = "a[oldtitle='BACK TO INBOX']")
		private WebElement btnBackToInbox;

		public NewMessage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	}

	public class WelcomeToInternetBanking {

		private WebDriver driver;

		@FindBy(css = "#C4__HEAD_9E1326794166002C141845")
		private WebElement lbeWelcomeToInternetBanking;

		@FindBy(css = "img[src='images/icons/temenos-animated-logo.gif']")
		private WebElement imgTemenosIcon;

		@FindBy(css = "#C4__QUE_CA013C9AFB04DC95197911_R1_1")
		private WebElement lbeHelloAccName;

		@FindBy(css = "#C4__QUE_CA013C9AFB04DC95197911_R1_2")
		private WebElement lbeWelcomeToInternetBanking_Account;

		@FindBy(css = "#C4__QUE_9E1326794166002C141940_R1")
		private WebElement lbeDate;

		@FindBy(css = "#C4__QUE_9E1326794166002C141950_R1")
		private WebElement lbeTime;

		@FindBy(css = "textarea[placeholder='Please enter your message']")
		private WebElement txtAreaMessage;

		@FindBy(css = "a[oldtitle='Send']")
		private WebElement btnSend;

		@FindBy(css = "a[oldtitle='BACK TO INBOX']")
		private WebElement btnBackToInbox;

		public WelcomeToInternetBanking(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	}

	public class WelcomeMessage {

		private WebDriver driver;

		@FindBy(css = "#C4__HEAD_9E1326794166002C141845")
		private WebElement lbeWelcomeMessage;

		@FindBy(css = "img[src='images/icons/temenos-animated-logo.gif']")
		private WebElement imgTemenosIcon;

		@FindBy(css = "#C4__QUE_CA013C9AFB04DC95197911_R1_1")
		private WebElement lbeHelloAccName;

		@FindBy(css = "#C4__QUE_9E1326794166002C141940_R1")
		private WebElement lbeDate;

		@FindBy(css = "#C4__QUE_9E1326794166002C141950_R1")
		private WebElement lbeTime;

		@FindBy(css = "img[alt='Profile Image']")
		private WebElement imgProfile;

		@FindBy(css = "#C4__QUE_CA013C9AFB04DC95197911_R2_1")
		private WebElement txtCustomerMessage;

		@FindBy(css = "#C4__QUE_9E1326794166002C141940_R2")
		private WebElement lbeCustomerEntryDate;

		@FindBy(css = "#C4__QUE_9E1326794166002C141950_R2")
		private WebElement lbeCustomerEntryTime;

		@FindBy(css = "textarea[placeholder='Please enter your message']")
		private WebElement txtAreaMessage;

		@FindBy(css = "a[oldtitle='Send']")
		private WebElement btnSend;

		@FindBy(css = "a[oldtitle='BACK TO INBOX']")
		private WebElement btnBackToInbox;

		public WelcomeMessage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	}
}
