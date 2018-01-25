package com.temenos.customization;

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
import com.temenos.pages.*;
import com.temenos.support.*;

public class CustomizationPage extends LoadableComponent<CustomizationPage> implements pageInterface {

    private WebDriver driver;

    private Headers headers;

    private Footers footers;

    private boolean isFailedFirstTime;

    public CustomizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        headers = new Headers(driver);
        footers = new Footers(driver);
    }

    @Override
    protected void load() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void isLoaded() throws Error {
        // TODO Auto-generated method stub

    }



}
