package com.w2a.TestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.BaseClass.Testbase;

public class LoginTest extends Testbase {
	@Test
	public void loginWithBankManager() {
		driver.findElement(By.xpath(prop.getProperty("Login_with_loginWithBankManager_button"))).click();
		log.debug("Clicking on BankManager login button");
		boolean result = false;//isElementPresent(driver.findElement(By.cssSelector(prop.getProperty("Customer_button"))));
		log.debug("Finding element Customer bank button");
		if(result==true) {
			log.info("Login successfully");	
		}
		else {
			log.info("Failed to login");
		}
		Assert.assertTrue(result);
		
	}

}
