package com.temenos.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Footers {

	private WebDriver driver;
	
	@FindBy(css="a[oldtitle='Facebook']")
	private WebElement lnkFacebook;

	@FindBy(css="a[oldtitle='Twitter']")
	private WebElement lnkTwitter;
	
	@FindBy(css="a[oldtitle='LinkedIn']")
	private WebElement lnkLinkedIn;
	
	@FindBy(css="a[title='Google+']")
	private WebElement lnkGooglePlus;
	
	@FindBy(css="a[oldtitle='Accessibility+']")
	private WebElement lnkAccessibility;
	
	@FindBy(css="a[oldtitle='Legal']")
	private WebElement lnkLegal;
	
	@FindBy(css="a[title='privacy']")
	private WebElement lnkPrivacy;
	
	@FindBy(css="a[oldtitle='Cookie policy']")
	private WebElement lnkCookiePolicy;
		
	@FindBy(css="a[oldtitle='2016 Temenos']")
	private WebElement lnkCopyRights;
	
	@FindBy(css="a[oldtitle='Tweets by @Temenos']")
	private WebElement lnkTweetsByTemenos;
	
	public Footers(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
