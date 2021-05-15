
package com.w2a.BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Testbase {
	public static Properties prop;
	public static FileInputStream Config;
	public static FileInputStream OR;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Logger log = Logger.getLogger("devpinoyLogger");

	@BeforeSuite
	public void setup() throws IOException {
		prop = new Properties();
		Config = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\config.Properties");
		prop.load(Config);
		log.debug("Loading config property file");
		OR = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\OR.properties");
		prop.load(OR);
		log.debug("Loding Locator property file");
		
	}

	@BeforeMethod
	public void browserLaunch() {
		if (prop.getProperty("Browser").equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + prop.getProperty("chromeDriverPath_append"));
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, 10);
			log.info("Received Browser = Chrome from config property file");
			log.debug("Launching Chrome driver");

		}
		if (prop.getProperty("Browser").equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + prop.getProperty("geckodriver_append"));
			driver = new FirefoxDriver();
			wait = new WebDriverWait(driver, 10);
			log.info("Received Browser = Firefox from config property file");
			log.debug("Launching Firefox driver");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("TaskURL"));
		log.info("Received SiteURL= " + prop.getProperty("TaskURL"));
		log.debug("Navigating to " + prop.getProperty("TaskURL"));
		driver.manage().window().maximize();
		log.debug("Maximizing the screen");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("Implicit_TimeOut")),
				TimeUnit.SECONDS);
		log.info("Adding Implicit timeout as " + prop.getProperty("Implicit_TimeOut") + "sec");
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.debug("Closing broswer");
	}
}
