package com.testcases;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.common.BaseSetup;
import com.common.helpers.ExcelHelpers;
import com.pages.SignInPage;

public class SignInTest {

	private WebDriver driver;
	private SignInPage signInPage;
	private ExcelHelpers excel;

	public SignInTest(WebDriver driver) {
		this.driver = driver;

	}

	public void signInPage() throws Exception {
		excel = new ExcelHelpers();
		excel.setExcelFile("src/test/resources/DataTestSignIn.xlsx", "Sheet1");
		signInPage = new SignInPage(driver);
		// Đọc data từ file excel
		signInPage.signIn(excel.getCellData("username", 1), excel.getCellData("password", 1));
//	/	Thread.sleep(2000);
//        System.out.println(excel.getCellData("username", 2));
		// Ghi data vào file excel
		// excel.setCellData("anhtester.com", 5, 0);

		// Chú ý: dòng và cột trong code nó hiểu bắt đầu từ 0

		// Thread.sleep(2000);
	}

//    public void signInPageReadExcelDynamic() throws Exception {
//
//        //Setup đường dẫn của file excel
//        excel.setExcelFile("src/test/resources/DataTest.xlsx", "Sheet1");
//
//        signInPage = new SignInPage(driver);
//        driver.get("https://crm.anhtester.com");
//
//        for (int i = 0; i < 6; i++) {
//          //  signInPage.signIn(excel.getCellData("username", i), excel.getCellData("password", i));
//            Thread.sleep(1000);
//        }
//
//        // Ghi nhiều dòng vào file
//        for (int i = 1; i < 6; i++) {
//            excel.setCellData("AN01", i, 3);
//        }
//    }
	public boolean checkSignIn() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String location =  js.executeScript("return window.location.href;").toString();
		if(location.contains("http://localhost:8081/login")) {
			return false;
		}
		return true;
	}

	public void closeBrowser() {
//        driver.close();
	}

}
