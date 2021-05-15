package com.w2a.BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumActions extends Testbase {

	public static boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}
	
	public static void type(String locatorKey,String data) {
		WebElement ele = getElement(locatorKey);
		ele.clear();
		ele.sendKeys(data);
	}
	
	public static void click(String locatorKey) {
		
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locatorKey))).click();
		
	}
	
	public static void jsEnterText(WebElement element,String text) {
		
		element.click(); 
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("arguments[0].value='"+text+"';", element);
		
	}
	public static void navigate(String locatorKey) {
		driver.get(locatorKey);
	}
	
	public static boolean  Selectedoption(String locatorKey) {
		return getElement(locatorKey).isSelected()	;
	}
	public static void backspace() {
		//navigate(prop.getProperty("TaskURL"));
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.BACK_SPACE).perform();
	}
	
	public static void scrollDown() {
	//navigate(prop.getProperty("TaskURL"));
	    Actions actions = new Actions(driver);
	   actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();	
	}
	public static void javaScriptClick(String locatorKey) {
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", getElement(locatorKey));
	}
	
	public static WebElement getElement(String locatorKey) {

		WebElement element = null;

		try {

			if (locatorKey.endsWith("_id")) {

				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				log.debug("Finding element " + prop.getProperty(locatorKey));

			} else if (locatorKey.endsWith("_name")) {

				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				log.debug("Finding element " + prop.getProperty(locatorKey));

			} else if (locatorKey.endsWith("_classname")) {

				element = driver.findElement(By.className(prop.getProperty(locatorKey)));
				log.debug("Finding element " + prop.getProperty(locatorKey));

			} else if (locatorKey.endsWith("_cssselector")) {

				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				log.debug("Finding element " + prop.getProperty(locatorKey));

			} else if (locatorKey.endsWith("_xpath")) {

				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				log.debug("Finding element " + prop.getProperty(locatorKey));

			}

		} catch (Exception e) {

			log.debug("Element  not found", e);

		}

		highlightElements(element);
		return element;

	}
	
	public static void highlightElements(WebElement ele) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "color: yellow; border: 2px solid yellow;");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO: handle exception
		}
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "");
	}
}
