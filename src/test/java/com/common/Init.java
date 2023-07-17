package com.common;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import com.common.helpers.CaptureHelpers;
import com.common.helpers.ValidateUIHelpers;
public class Init {
	protected WebDriver driver;
	protected WebDriverWait wait;
//  protected Robot robot = new Robot();
	protected Actions action ;
	protected CaptureHelpers cap;
	protected String url = null;
	ValidateUIHelpers validateUIHelpers;
	@BeforeTest
	public void beforeTest() throws Exception {
		chromeInitialization();
		setTimeout();
		CaptureHelpers.startRecord("Test");
		connectUrl();
	}

	private void chromeInitialization() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "resourses/driver/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}

	private void setTimeout() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}
	private void connectUrl() {
		validateUIHelpers = new ValidateUIHelpers(this.driver);
		validateUIHelpers.waitForPageLoaded();
		driver.get("http://192.168.10.128:8081/");
		
	}

	@AfterTest
	public void afterTest() throws Exception {
		CaptureHelpers.stopRecord();
		driver.close();
	}

}
