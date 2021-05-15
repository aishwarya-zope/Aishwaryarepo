package com.w2a.TestCases;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.BaseClass.PageActions;
import com.w2a.BaseClass.SeleniumActions;
import com.w2a.Utilities.ExcelReader;

public class ValidationOnCard extends PageActions {

	@Test(dataProvider = "DataforValidation_Card", priority = 2)
	public void InvalidData_CardNumber_More_than_19_digits(Map<String, Map<String, String>> h) {
		Map<String, String> testData = h.get("InvalidData_CardNumber_More_than_19_digits");
		login(testData.get("username"), testData.get("password"));
		log.info("Logged in successfully");
		clickOnDonateBtn_fromAnywhere();
		log.info("Click on Donate button");
		fillUpTransactionDetailsForm(testData);
		// validCardFlow();
		SeleniumActions.type("cardNumber_id", testData.get("cardNumber"));
		SeleniumActions.click("cardNumberIcon_xpath");
		String errorMessage = "Please enter value between 13 and 19 characters long";
//		String ActualErrorMessage = wait.until(ExpectedConditions.visibilityOf(SeleniumActions.getElement("ErrorInvalid_xpath"))).getText().trim();
		String ActualErrorMessage = SeleniumActions.getElement("ErrorInvalid_xpath").getText().trim();
		Assert.assertTrue(errorMessage.contains(ActualErrorMessage));
	}

	@Test(dataProvider = "DataforValidation_Card", priority = 1)
	public void InvalidData_CardNumber_Kept_blank_and_moving_to_next_tab(Map<String, Map<String, String>> h) {
		Map<String, String> testData = h.get("InvalidData_CardNumber_Kept_blank_and_moving_to_next_tab");
		login(testData.get("username"), testData.get("password"));
		log.info("Logged in successfully");
		clickOnDonateBtn_fromAnywhere();
		log.info("Click on Donate button");
		fillUpTransactionDetailsForm(testData);
		SeleniumActions.getElement("cardNumber_id").click();
		SeleniumActions.click("cardExpiryDate_id");
		String ExpectedErrorMessage = "Enter a Valid card";

		String ActualErrorMessage = wait
				.until(ExpectedConditions.visibilityOf(SeleniumActions.getElement("ErrorInvalid_xpath"))).getText();

		Assert.assertTrue(ExpectedErrorMessage.equals(ActualErrorMessage));
	}
	@Test(dataProvider = "DataforValidation_Card", priority = 3)
	public void InvalidData_CardNumber_More_than_13_digits(Map<String, Map<String, String>> h) {
		Map<String, String> testData = h.get("InvalidData_CardNumber_More_than_13_digits");
		login(testData.get("username"), testData.get("password"));
		log.info("Logged in successfully");
		clickOnDonateBtn_fromAnywhere();
		log.info("Click on Donate button");
		fillUpTransactionDetailsForm(testData);
		SeleniumActions.type("cardNumber_id", testData.get("cardNumber"));
		SeleniumActions.click("cardNumberIcon_xpath");
		String errorMessage = "Please enter value between 13 and 19 characters long";
//		String ActualErrorMessage = wait.until(ExpectedConditions.visibilityOf(SeleniumActions.getElement("ErrorInvalid_xpath"))).getText().trim();
		String ActualErrorMessage = SeleniumActions.getElement("ErrorInvalid_xpath").getText().trim();
		Assert.assertTrue(errorMessage.contains(ActualErrorMessage));

	}

	@Test(dataProvider = "DataforValidation_Card", priority = 4)
	public void validData(Map<String, Map<String, String>> h) {		
		Map<String, String> testData = h.get("validData");
		login(testData.get("username"), testData.get("password"));
		log.info("Logged in successfully");
		clickOnDonateBtn_fromAnywhere();
		log.info("Click on Donate button");
		fillUpTransactionDetailsForm(testData);
		SeleniumActions.type("cardNumber_id", testData.get("cardNumber"));
		SeleniumActions.type("Month_year_xpath", testData.get("MM/YY"));
		SeleniumActions.type("CVV_xpath", testData.get("CVV"));
		SeleniumActions.click("contributeBtn_id");
	}

	@DataProvider
	public Object[][] DataforValidation_Card(Method method) {
		System.out.println(method.getName());
		String mName = method.getName();
		ExcelReader excel = new ExcelReader(prop.getProperty("Data_Provider_Sheet"));
		boolean a = excel.isSheetExist("Sheet1");
		log.debug("Sheet is present=" + a);
		int rowcount = excel.getRowCount("Sheet1");
		log.debug("Rowcount =" + rowcount);
//		int colcount = excel.getColumnCount("Sheet1");
//		log.debug("Colcount=" + colcount);
		Map<String, Map<String, String>> table = new HashMap<String, Map<String, String>>();
		Object[][] obj = new Object[1][1];
		for (int i = 2; i <= rowcount; i++) {
			String data = excel.getCellData("Sheet1", 1, i);
			String[] eachData = data.split(",");
			Map<String, String> finalData = new HashMap<String, String>();
			for (String x : eachData) {
				String[] keyValue = x.split("=");
				finalData.put(keyValue[0], keyValue[1]);
			}
			String methodName = excel.getCellData("Sheet1", 0, i);
			if (methodName.equalsIgnoreCase(mName)) {
				table.put(methodName, finalData);
				obj[0][0] = table;
			}

		}	
		return obj;
	}

}
