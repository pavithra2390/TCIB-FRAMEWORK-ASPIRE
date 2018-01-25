package com.temenos.pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.support.BrowserActions;
import com.temenos.support.Log;
import com.temenos.support.Utils;
import com.temenos.support.WaitUtil;

public class Headers extends LoadableComponent<Headers> {

	private WebDriver driver;

	@FindBy(css = ".tc-menu-trigger")
	private WebElement lnkSidMenuBar;

	@FindBy(css = "a[oldtitle='Messages']")
	private WebElement lnkMessages;

	@FindBy(css = "#C1__BUT_F6A61BDCCE0BE222609599")
	private WebElement lnkSettings;

	@FindBy(css = "a[oldtitle='Log out']")
	private WebElement lnkLogout;

	public Headers(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Settings clickOnSettings() throws Exception {
		BrowserActions.clickOnButton(lnkSettings, driver, "Settings link");
		return new Settings(driver);
	}

	public SideBar clickOnSideBar() throws Exception {
		BrowserActions.clickOnButton(lnkSidMenuBar, driver, "Side Menu bar");
		return new SideBar(driver);
	}

	public void clickOnLogout() throws Exception {
		BrowserActions.clickOnButton(lnkLogout, driver, "Logout Link");
	}

	public BreadCrumb getBreadCrumb() throws Exception {
		return new BreadCrumb(driver);
	}

	public class Settings {

		private WebDriver driver;

		@FindBy(css = "#C1__SETTINGS_POPUP fieldset div:nth-child(3)")
		private WebElement lnkFontSmall;

		@FindBy(css = "#C1__SETTINGS_POPUP fieldset div:nth-child(4)")
		private WebElement lnkFontMedium;

		@FindBy(css = "#C1__SETTINGS_POPUP fieldset div:nth-child(5)")
		private WebElement lnkFontLarge;

		@FindBy(id = "C1__QUE_F6A61BDCCE0BE222641218")
		private WebElement onOffCompactMode;

		@FindBy(id = "C1__QUE_F6A61BDCCE0BE222641241")
		private WebElement onOffLockHeader;

		@FindBy(xpath = "//*[@id='C1__SETTINGS_POPUP']//*[contains(@class,'onoffswitch')]/input[contains(@name,'BOXMODE')]/parent::div")
		private WebElement onOffBoxMode;

		@FindBy(xpath = "//*[@id='C1__SETTINGS_POPUP']//*[contains(@class,'onoffswitch')]/input[contains(@name,'SHOWSIDEBAR')]/parent::div")
		private WebElement onOffShowSidebar;

		@FindBy(id = "C1__QUE_F6A61BDCCE0BE222641246")
		private WebElement checkBoxMode;

		@FindBy(id = "C1__QUE_F6A61BDCCE0BE222641256")
		private WebElement checkLockFooter;

		public boolean getBoxVideCheckbox() throws Exception {

			return checkBoxMode.isSelected();
		}

		public boolean getLockFooterCheckboxEnable() throws Exception {

			return checkLockFooter.isSelected();
		}

		public boolean getCompactCheckboxEnable() throws Exception {

			return onOffCompactMode.isSelected();
		}

		public Settings(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public Settings clickOnMedButton() throws Exception {
			BrowserActions.clickOnButton(lnkFontMedium, driver, "medium font");
			return this;
		}

		public Settings clickOnSmallButton() throws Exception {
			BrowserActions.clickOnButton(lnkFontSmall, driver, "Small font");
			return this;
		}

		public Settings clickOnLargeButton() throws Exception {
			BrowserActions.clickOnButton(lnkFontLarge, driver, "Large font");
			return this;
		}

		public Settings clickOnOffCompactModeButton() throws Exception {
			BrowserActions.clickOnButton(onOffCompactMode, driver,
					"on Off Compact ");
			return this;
		}

		public Settings clickOnOffLockHeaderButton() throws Exception {
			BrowserActions.clickOnButton(onOffLockHeader, driver,
					"On Off Lock Header ");
			return this;
		}

		public Settings clickOnOffBoxModeButton() throws Exception {
			BrowserActions.clickOnButton(onOffBoxMode, driver,
					"On Off Box Mode ");

			return this;
		}

		public Settings clickOnOffShowSidebarButton() throws Exception {
			BrowserActions.clickOnButton(checkLockFooter, driver,
					"On Off Show Sidebar ");
			return this;
		}

		public Settings verifyOnOffCompactModeButton() throws Exception {
			// Log.message("verify On Off Compact Mode "+onOffCompactChecked.getAttribute("checked"));
			return this;
		}

	}

	public class SideBar {

		private WebDriver driver;

		@FindBy(css = ".tetris_menuItem:nth-child(1) a")
		private WebElement lnkHome;

		@FindBy(css = ".tetris_menuItem:nth-child(2) a")
		private WebElement lnkCustomerProfile;

		@FindBy(css = ".tetris_menuItem:nth-child(3) a")
		private WebElement lnkAppSettings;

		@FindBy(css = ".tetris_menuItem:nth-child(4) a")
		private WebElement lnkManagePayees;

		@FindBy(css = ".tetris_menuItem:nth-child(5) a")
		private WebElement lnkBranchOrAtmLocator;

		@FindBy(css = ".tetris_menuItem:nth-child(6) a")
		private WebElement lnkExchangeRates;

		@FindBy(css = "#C1__BUT_F6A61BDCCE0BE222609605")
		private WebElement lnkLogout;

		@FindBy(css = "#C1__HEAD_BB27E1453D85757F2846335")
		private WebElement lbeUserId;

		@FindBy(css = "#C5__HEAD_2F71C70C2458B7A5211149")
		private WebElement lblYourProfile;

		@FindBy(css = "#C5__TAB_48D258774FD2468F212014")
		private WebElement tabCustomerPreference;

		@FindBy(css = "#C5__C2__HEAD_04BD8318E85CF79B160571")
		private WebElement lblLanguage;

		@FindBy(css = "#C5__C2__BUT_04BD8318E85CF79B160708")
		private WebElement btnEdit;

		@FindBy(css = "#C5__C2__QUE_04BD8318E85CF79B160806")
		private WebElement drpLanguage;

		@FindBy(css = "#C5__C2__QUE_04BD8318E85CF79B160806>option[selected='selected']")
		private WebElement drpLanguageRO;

		@FindBy(css = "#C5__C1__p4_BUT_A117FA02FB501A47138314>a>span")
		private WebElement btnCancel;

		@FindBy(css = "#C5__C2__BUT_04BD8318E85CF79B160932")
		private WebElement btnSave;

		@FindBy(css = "#C5__C2__QUE_04BD8318E85CF79B160700>option[selected='selected']")
		private WebElement txtSelectedValue;

		@FindBy(css = "#C5__C2__QUE_04BD8318E85CF79B161067[disabled='disabled']")
		private WebElement txtBaseCurrency;

		@FindBy(css = "#C5__C1__row_QUE_701CAE441BD094F772809>div>div>input")
		private WebElement txtPhoneNumber;

		@FindBy(css = "#C5__C1__QUE_701CAE441BD094F7208264")
		private WebElement editPhoneNumber;

		@FindBy(css = "#C5__C1__BUT_701CAE441BD094F7207364>span")
		private WebElement lnkEditPhoneNumber;

		@FindBy(css = "#C5__C1__BUT_701CAE441BD094F7210022 > span")
		private WebElement lnkSave;

		@FindBy(css = "#C5__C1__p4_QUE_701CAE441BD094F772811>div>input")
		private WebElement txtEmailId;

		@FindBy(css = "#C5__C1__BUT_701CAE441BD094F7207369>span")
		private WebElement lnkEditEmailId;

		@FindBy(css = "#C5__C1__QUE_701CAE441BD094F7208274")
		private WebElement editMaildId;

		@FindBy(css = "#C5__C1__p4_BUT_701CAE441BD094F7210887>a")
		private WebElement lnkSaveEmail;

		@FindBy(css = "#C5__C1__BUT_A117FA02FB501A47140070")
		private WebElement lnkCancelEmail;

		@FindBy(css = "#C5__FS_QUE_53732DBF2F0589AB518376>div>label")
		private WebElement chkBranch;

		@FindBy(css = "#C5__FS_QUE_53732DBF2F0589AB518383>div>label")
		private WebElement chkAtm;

		@FindBy(css = "#C5__HEAD_92A826BAE1870C18506755_R1")
		private WebElement lblBranch;

		@FindBy(css = "#C5__p1_HEAD_92A826BAE1870C18506755_R1>div>h2")
		private WebElement lblAtm;

		@FindBy(css = "#C5__HEAD_3C8901607E1395361391599")
		private WebElement lblBranchName;

		@FindBy(css = "#C5__BUT_53732DBF2F0589AB616950_R1")
		private WebElement expBranchAtmName;

		@FindBy(css = "#C5__BUT_53732DBF2F0589AB616950_R1")
		private WebElement expAtmName;

		@FindBy(css = "#C5__HEAD_3C8901607E1395361391599")
		private WebElement lblAtmName;

		@FindBy(css = "#C5__BUT_3C8901607E13953666815")
		private WebElement btnClose;

		@FindBy(css = "span.tc-menu-icon.icon-logout span")
		private WebElement btnlogoutSide;

		@FindBy(css = "#C5__C2__p4_BUT_A117FA02FB501A47138314>a>span")
		private WebElement btnCancelLang;

		public SideBar(WebDriver driver) throws Exception {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		public HomePage clickOnHome() throws Exception {
			BrowserActions.clickOnButton(lnkHome, driver, "Home Link");
			return new HomePage(driver);
		}

		public CustomerProfilePage clickOnCustomerProfile() throws Exception {
			BrowserActions.clickOnButton(lnkCustomerProfile, driver,
					"Customer profile Link");
			return new CustomerProfilePage(driver);
		}

		public AppSettingsPage clickOnAppSettings() throws Exception {
			BrowserActions.clickOnButton(lnkAppSettings, driver,
					"App Settings Link");
			return new AppSettingsPage(driver);
		}

		public ManagePayee clickOnManagePayees() throws Exception {
			BrowserActions.clickOnButton(lnkManagePayees, driver,
					"Manage Payees Link");
			return new ManagePayee(driver).get();
		}

		public BranchOrATMLocatorPage clickOnBranchOrATMLocator()
				throws Exception {
			BrowserActions.clickOnButton(lnkBranchOrAtmLocator, driver,
					"Branch/ATM Locator Link");
			return new BranchOrATMLocatorPage(driver);
		}

		public ExchangeRatesPage clickOnExchangeRates() throws Exception {
			BrowserActions.clickOnButton(lnkExchangeRates, driver,
					"Exchange Rates Link");
			return new ExchangeRatesPage(driver);
		}

		public LoginPage verifyLogOut() throws Exception {
			BrowserActions.clickOnButton(lnkLogout, driver, "Logout Link");
			return new LoginPage(driver);
		}

		public boolean verifyOpenSideMenuBar() throws Exception {
			WaitUtil.waitForElement(driver, lbeUserId, 5);
			if (lbeUserId.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		}

		public boolean verifyCloseSideMenuBar() throws Exception {
			Thread.sleep(3000);
			if (lbeUserId.isDisplayed()) {
				return false;
			} else {
				return true;
			}
		}

		public void clickOnCurtomerPreferences() throws Exception {
			WaitUtil.waitForElement(driver, lblYourProfile, 5);
			BrowserActions.clickOnButton(tabCustomerPreference, driver,
					"Clicked on Customer Preference");
		}

		public void clickOnEditInCustomerPreference() throws Exception {
			WaitUtil.waitForElement(driver, lblLanguage, 5);
			BrowserActions.clickOnButton(btnEdit, driver,
					"Edit button is clicked");
		}

		public void clickOnDropdownLanguage(String language) throws Exception {
			BrowserActions.selectFromDropDown(drpLanguage, language, driver,
					"Dropdown");
		}

		public void clickOnCancel() throws Exception {
			BrowserActions.clickOnButton(btnCancel, driver,
					"Cancel button is clicked");
		}

		public void clickOnSaveButton() throws Exception {
			BrowserActions.clickOnButton(btnSave, driver,
					"Save button is clicked");

		}

		public String getTextFrmLanguage() throws Exception {
			String Lan = BrowserActions.getText(driver, txtSelectedValue,
					"Language before changing");
			return Lan;
		}

		public String getTextFrmLanguageAfterSave() throws Exception {
			String Lan = BrowserActions.getText(driver, drpLanguageRO,
					"Language before changing");
			return Lan;
		}

		public boolean verifyBaseCurrencyIsNotEnabled() throws Exception {
			if (!lnkHome.isDisplayed()) {
				return false;
			} else {
				return true;
			}
		}

		public String getTextfromPhoneNumber(String value) throws Exception {
			Utils.waitForPageLoad(driver);
			WaitUtil.waitForSpinnerToComplete(driver);
			WaitUtil.waitForElement(driver, txtPhoneNumber, 5);
			String before = BrowserActions.getTextFromAttribute(driver,
					txtPhoneNumber, value, "Phone Number");
			return before;
		}

		public void clickOnEditPhoneNumber() throws Exception {
			WaitUtil.waitForElement(driver, lnkEditPhoneNumber, 5);
			BrowserActions.clickOnButton(lnkEditPhoneNumber, driver,
					"Edit phone Number is clicked");
		}

		public void enterPhoneNumber(String phone) throws Exception {
			WaitUtil.waitForElement(driver, editPhoneNumber, 5);
			BrowserActions.typeOnTextField(editPhoneNumber, phone, driver,
					"Phone Number");
		}

		public void clickOnSave() throws Exception {
			BrowserActions.clickOnButton(lnkSave, driver,
					"Clicked on save button");
		}

		public String getTextfromEmailId(String value) throws Exception {
			WaitUtil.waitForElement(driver, txtEmailId, 5);
			String before = BrowserActions.getTextFromAttribute(driver,
					txtEmailId, value, "Email id");
			return before;
		}

		public void clickOnEditEmailId() throws Exception {
			WaitUtil.waitForElement(driver, lnkEditEmailId, 5);
			BrowserActions.clickOnButton(lnkEditEmailId, driver,
					"Edit Email id is clicked");
		}

		public void enterEmailId(String email) throws Exception {
			WaitUtil.waitForElement(driver, editMaildId, 5);
			BrowserActions.typeOnTextField(editMaildId, email, driver,
					"Phone Number");
		}

		public void clickOnSaveEmail() throws Exception {
			BrowserActions.clickOnButton(lnkSaveEmail, driver,
					"Clicked on save button");
		}

		public void clickOnCancelEmail() throws Exception {
			BrowserActions.clickOnButton(lnkCancelEmail, driver,
					"Cancel button is clicked");
		}

		public void clickOnCheckboxBranch() throws Exception {
			WaitUtil.waitForElement(driver, chkBranch, 5);
			BrowserActions.clickOnButton(chkBranch, driver,
					"Branch checkbox is clicked");
		}

		public void clickOnCheckboxAtm() throws Exception {
			WaitUtil.waitForElement(driver, chkAtm, 5);
			BrowserActions.clickOnButton(chkAtm, driver,
					"Atm checkbox is clicked");
		}

		public String getTextFromBranch() throws Exception {
			WaitUtil.waitForElement(driver, lblBranch, 5);
			String BranchName = BrowserActions.getText(driver, lblBranch,
					"Branch Name");
			return BranchName;
		}

		public String getTextFromAtm() throws Exception {
			WaitUtil.waitForElement(driver, lblAtm, 5);
			String ATMName = BrowserActions.getText(driver, lblAtm, "ATM");
			return ATMName;
		}

		public void clickOnBranch() throws Exception {
			WaitUtil.waitForElement(driver, expBranchAtmName, 5);
			BrowserActions.clickOnButton(expBranchAtmName, driver,
					"Branch Is Clicked");
		}

		public void clickOnAtm() throws Exception {
			WaitUtil.waitForElement(driver, expAtmName, 5);
			BrowserActions.clickOnButton(expAtmName, driver, "ATM Is Clicked");
		}

		public String getTextFromBranchName() throws Exception {
			WaitUtil.waitForElement(driver, lblBranchName, 5);
			String labelBranchName = BrowserActions.getText(driver,
					lblBranchName, "Branch Name displayed");
			return labelBranchName;
		}

		public String getTextFromAtmName() throws Exception {
			WaitUtil.waitForElement(driver, lblAtmName, 5);
			String labelBranchName = BrowserActions.getText(driver, lblAtmName,
					"ATM Name displayed");
			return labelBranchName;
		}

		public void clickOnCancelLanguage() throws Exception {
			BrowserActions.clickOnButton(btnCancelLang, driver,
					"Cancel button is clicked");
		}

		/**
		 * Verify if expected page WebElements are present
		 * <p>
		 * If expected element is present on the current page, add to list of
		 * value/fields to actualElement list and then compare to
		 * expectedElements
		 *
		 * @param expectedElements
		 *            - expected WebElement values
		 * @param obj
		 *            - the page object the elements are on
		 * @return true if both the lists are equal, else returns false
		 * @throws Exception
		 *             - one of 4 possibilities
		 */
		public boolean verifyPageElements(List<String> expectedElements,
				Object obj) throws Exception {
			List<String> actual_elements = new ArrayList<String>();
			for (String expEle : expectedElements) {
				Field f = null;
				try {
					f = obj.getClass().getDeclaredField(expEle);
					f.setAccessible(true);
				} catch (NoSuchFieldException | SecurityException e1) {
					throw new Exception(
							"No such a field present on this page, Please check the expected list values:: "
									+ expEle);
				}
				WebElement element = null;
				try {
					element = ((WebElement) f.get(obj));
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					Log.exception(e1);
				}
				if (Utils.waitForElement(driver, element, 2)) {
					actual_elements.add(expEle);
				}
			}
			return Utils.compareTwoList(expectedElements, actual_elements);
		}

		public void clickOnCloseBtnInBranch() throws Exception {
			WaitUtil.waitForElement(driver, btnClose, 5);
			BrowserActions.clickOnButton(btnClose, driver, "Branch Is Clicked");
		}

	}

	public class BreadCrumb {

		private WebDriver driver;

		@FindBy(css = ".tc-breadcrumb .tc-breadcrumb-current")
		private WebElement lnkCurrent;

		@FindBy(css = ".tc-breadcrumb .tc-tab-highlight")
		private WebElement lnkParent;

		public BreadCrumb(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

}
