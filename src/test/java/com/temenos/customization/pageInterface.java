package com.temenos.customization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.temenos.pages.TransfersTab;
import com.temenos.support.BrowserActions;
import com.temenos.support.WaitUtil;


import com.temenos.pages.ManagePayee;


public interface pageInterface {

    class MortgageLoan extends com.temenos.testscripts.MortgageLoan {
    	
    	
    }


    class ManagePayee extends com.temenos.pages.ManagePayee {

    }

	}

	class ManagePayees extends com.temenos.testscripts.ManagePayees {
	}

	class AccountsPage extends com.temenos.pages.AccountsPage{

		public AccountsPage(WebDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
		}

		
		public TransfersTab goToTransfersTab() {
			WaitUtil.sleep(3);
			//BrowserActions.clickOnButton(tabTransfers, driver, "Transfers Tab");
			//return new TransfersTab(driver).get();
			System.out.println("...................Custom Method.....................");
			return null;
		}
	}


