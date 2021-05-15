package com.w2a.BaseClass;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.w2a.Utilities.ExcelReader;

public class PageActions extends Testbase {

	public static void login(String userName, String password) {
		SeleniumActions.navigate(prop.getProperty("TaskURL"));
		SeleniumActions.click("Site_Username_Password_id");
		SeleniumActions.type("Site_Username_id", userName);
		SeleniumActions.type("Site_Pasword_id", password);
		SeleniumActions.click("Site_Loginbutton_id");	
	}
	
	
	public static void clickOnDonateBtn_fromAnywhere() {
		WebElement ele; 
		try {
//			javaScriptClick("Donate_button_xpath");
			SeleniumActions.click("Donate_button_xpath");
		} catch (Exception e) {
			ele = wait.until(ExpectedConditions.visibilityOf(SeleniumActions.getElement("smallDonateModalHomePage_id")));
			boolean flag = ele.isDisplayed();
			if (flag) {
				SeleniumActions.click("smallDonateModalHomePageDonaeBtn_id");
			}
		}
		
		
		
		
	}
	
	public static void fillUpTransactionDetailsForm(Map<String,String>testData){
		boolean isCurrencyInRupee = !SeleniumActions.Selectedoption("Rupee_xpath");
		log.debug("Checking for selected currency as Rupee");
		if (isCurrencyInRupee) {
			Select Currency = new Select(SeleniumActions.getElement("Currency_id"));
			Currency.selectByIndex(1);
		}
		SeleniumActions.click("1000_id");
		System.out.println("Clicked on 1000 ruppee");
		log.info("Clicking on 1000 Amount");
		donateDemographic(testData);
	}
	
	public static void donateDemographic(Map<String,String> testData){
		SeleniumActions.jsEnterText(SeleniumActions.getElement("Full_name_id"), testData.get("fullName"));
		System.out.println("Entered Full name");
		SeleniumActions.jsEnterText(SeleniumActions.getElement("Email_id"), testData.get("email"));
		System.out.println("Entered Email_id");
		SeleniumActions.jsEnterText(SeleniumActions.getElement("Phone_id"), testData.get("mobNo"));
		System.out.println("Entered mobile num");
		SeleniumActions.jsEnterText(SeleniumActions.getElement("City_id"), testData.get("city"));
				
		SeleniumActions.click("Donate_button_id");
	
	}
	
	public static void validCardFlow() {
		SeleniumActions.type("cardNumber_id", "1234567890123");
		SeleniumActions.type("cardExpiryDate_id", "0124");
		SeleniumActions.type("cardCvvNumber_id", "123");
		SeleniumActions.click("contributeBtn_id");
	}
	


}
