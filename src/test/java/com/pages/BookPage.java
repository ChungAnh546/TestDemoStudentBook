package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
///import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.common.helpers.CaptureHelpers;
import com.common.helpers.ExcelHelpers;
import com.common.helpers.ValidateUIHelpers;

public class BookPage {
	WebDriver driver;
	private ValidateUIHelpers validateUIHelpers;
	JavascriptExecutor js;
	SoftAssert softassert;
//	private WebDriverWait wait;

	public BookPage(WebDriver driver) {
		this.driver = driver;
		validateUIHelpers = new ValidateUIHelpers(this.driver);

	}

	private By nameInput = By.xpath("//input[@id='inputName']");
	private By authorInput = By.xpath("//input[@id='inputAuthor']");
	private By priceInput = By.xpath("//input[@id='inputPrice']");
	private By publicInput = By.xpath("//input[@id='inputProducer']");
	private By addBtn = By.xpath("//button[@id='createBook']");
	private By updateBtn = By.xpath("//button[@id='updateBook']");

	private By closeUpdate = By.xpath("//button[@id='closeModalUpdate']");
	private By closeBtn = By.xpath("//button[@id='closeModalBook']");
	private By createBookBtn = By.xpath("//button[@id='btnCreateTableBook']");
	private By updateBookBtn = By.xpath("//button[@id='btnToggleUpdate']");
	private By deleteBookBtn = By.xpath("//button[@id='btnXoa']");
	private By confirmDeleteBookBtn = By.xpath("//button[contains(text(),'delete')]");
	private By nameTable = By.xpath("//td[@class='dtr-control']");
	private By priceTable = By.xpath(
			"/html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[4]");
	private By authorTable = By.xpath(
			"/html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[3]");
	private By publicTable = By.xpath(
			"/html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[5]");
	private By searchInput = By.xpath("//input[@class='form-control form-control-sm']");
	private By checkBox = By.xpath("//input[@name='select']");
	private By nameInputUpdate = By.xpath("//input[@id='inputNameUpdate']");
	private By authorInputUpdate = By.xpath("//input[@id='inputAuthorUpdate']");
	private By priceInputUpdate = By.xpath("//input[@id='inputPriceUpdate']");
	private By publicInputUpdate = By.xpath("//input[@id='inputProducerUpdate']");
	private By tbNoFound = By.xpath("//td[contains(text(),'No matching records found')]");
	private By SwalNotification = By.xpath("//h2[@id='swal2-title']");

	public boolean addBook(String nameValue, String authorValue, String priceValue, String publicValue,ExcelHelpers excel,int i) {

		try {

			validateUIHelpers.waitForPageLoaded();
			js = (JavascriptExecutor) driver;
			String titlePage = js.executeScript("return document.title;").toString();
			Assert.assertEquals(titlePage, "Sách");
			validateUIHelpers.clickElement(createBookBtn);

			validateUIHelpers.setText(nameInput, nameValue);
			Assert.assertNotNull(nameInput);
			validateUIHelpers.setText(authorInput, authorValue);
			validateUIHelpers.setText(priceInput, priceValue);
			validateUIHelpers.setText(publicInput, publicValue);
			validateUIHelpers.clickElement(nameInput);
			
			validateUIHelpers.clickElement(addBtn);
			checkValueAddBook(excel,i);
			Thread.sleep(500);
			Assert.assertNotNull(driver.findElement(SwalNotification), "Thêm thông tin sách thành công");
			Thread.sleep(1500);
			while (driver.findElement(addBtn).isDisplayed()) {
				validateUIHelpers.clickElement(closeBtn);
				Thread.sleep(500);
			}
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			validateUIHelpers.clickElement(closeBtn);
			return false;
		}

	}

	public By GetByByValueTable(String value) {
		return By.xpath("//td[contains(text(),'" + value + "')]");
	}

	public boolean checkBook(String nameValue, String authorValue, String priceValue, String publicValue) {
		try {
			if (nameValue.equals("")) {
				CaptureHelpers.captureScreenshot(driver, "book_resultcheck_name"+nameValue);
				return false;
			}
			validateUIHelpers.waitForPageLoaded();
			searchTableBook(nameValue);
			Thread.sleep(200);
			if (!validateUIHelpers.verifyElementExist(GetByByValueTable(nameValue))) {
				CaptureHelpers.captureScreenshot(driver, "book_resultcheck_name"+nameValue);
				return false;
			}

			// td[contains(text(),'Chung cầm bút 2')]
			boolean checkName = validateUIHelpers.verifyElementText(GetByByValueTable(nameValue), nameValue);

			Assert.assertNotNull(nameTable);
			boolean checkAuthor = validateUIHelpers.verifyElementText(GetByByValueTable(authorValue), authorValue);

			boolean checkPrice = validateUIHelpers.verifyElementText(GetByByValueTable(priceValue), priceValue);
			boolean checkPublic = validateUIHelpers.verifyElementText(GetByByValueTable(publicValue), publicValue);

			if (checkName == true && checkAuthor == true && checkPrice == true && checkPublic == true) {
				return true;
			}
			CaptureHelpers.captureScreenshot(driver, "book_resultcheck_name"+nameValue);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public void searchTableBook(String data) {
		validateUIHelpers.waitForPageLoaded();
		validateUIHelpers.setText(searchInput, data);
	}

	public boolean checkDeleteBook(String name) {
		try {
			validateUIHelpers.waitForPageLoaded();
			if (name.equals("")) {
				return true;
			}
			searchTableBook(name);
			if (validateUIHelpers.verifyElementExist(tbNoFound)) {
				return true;
			} else {
				if (validateUIHelpers.verifyElementExist(GetByByValueTable(name))) {
					CaptureHelpers.captureScreenshot(driver, "book_resultCheckDelete_name"+name);
					return false;
				} else {
					return true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	private void setResultExcel(ExcelHelpers excel,boolean result,String col,int i) throws Exception {
		try {

			

			if (result) {

				excel.setCellData("Successful", col, i);
			} else {
				CaptureHelpers.captureScreenshot(driver, col +"_row"+i);
				excel.setCellData("Fail", col, i);

			}

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}

	public boolean checkValueAddBook(ExcelHelpers excel,int i) {
	
		try {

			
			boolean resultName = validateUIHelpers.checkElementClass("is-invalid", "inputName");
			boolean resultAuthor = validateUIHelpers.checkElementClass("is-invalid", "inputAuthor");
			boolean resultPrice = validateUIHelpers.checkElementClass("is-invalid", "inputPrice");
			boolean resultPublic = validateUIHelpers.checkElementClass("is-invalid", "inputProducer");
			setResultExcel(excel,!resultName,"resultName",i);
			setResultExcel(excel,!resultAuthor,"resultAuthor",i);
			setResultExcel(excel,!resultPrice,"resultPrice",i);
			setResultExcel(excel,!resultPublic,"resultPublic",i);
			
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

	}
	public boolean checkValueUpdateBook(ExcelHelpers excel,int i) {
		
		try {

			
			boolean resultName = validateUIHelpers.checkElementClass("is-invalid", "inputNameUpdate");
			boolean resultAuthor = validateUIHelpers.checkElementClass("is-invalid", "inputAuthorUpdate");
			boolean resultPrice = validateUIHelpers.checkElementClass("is-invalid", "inputPriceUpdate");
			boolean resultPublic = validateUIHelpers.checkElementClass("is-invalid", "inputProducerUpdate");
			setResultExcel(excel,!resultName,"resultNameUpdate",i);
			setResultExcel(excel,!resultAuthor,"resultAuthorUpdate",i);
			setResultExcel(excel,!resultPrice,"resultPriceUpdate",i);
			setResultExcel(excel,!resultPublic,"resultPublicUpdate",i);
			
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

	}

	public boolean deleteBook(String name) {
		try {
			if (name.equals("")) {
				CaptureHelpers.captureScreenshot(driver, "book_resultDelete_name"+name);
				return false;
			}
			validateUIHelpers.waitForPageLoaded();
			searchTableBook(name);
			if (!validateUIHelpers.verifyElementExist(GetByByValueTable(name))) {
				CaptureHelpers.captureScreenshot(driver, "book_resultDelete_name"+name);
				return false;
			}
			if (!validateUIHelpers.verifyElementText(nameTable, name)) {
				CaptureHelpers.captureScreenshot(driver, "book_resultDelete_name"+name);
				return false;
			}
			validateUIHelpers.clickElement(checkBox);

			Assert.assertNotNull(checkBox);
			validateUIHelpers.clickElement(deleteBookBtn);

			Assert.assertNotNull(confirmDeleteBookBtn);
			validateUIHelpers.clickElement(confirmDeleteBookBtn);
			Assert.assertNotNull(driver.findElement(SwalNotification), "Xóa thông tin sách thành công");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean updateBook(String nameBookUpdate, String nameValue, String authorValue, String priceValue,
			String publicValue,ExcelHelpers excel,int i) {

		try {
			if (nameBookUpdate.equals("")) {
				CaptureHelpers.captureScreenshot(driver, "book_resultUpdate_name"+nameBookUpdate);
				return false;
			}
			validateUIHelpers.waitForPageLoaded();
			js = (JavascriptExecutor) driver;
			String titlePage = js.executeScript("return document.title;").toString();
			Assert.assertEquals(titlePage, "Sách");

			searchTableBook(nameBookUpdate);
			if (!validateUIHelpers.verifyElementExist(GetByByValueTable(nameBookUpdate))) {
				CaptureHelpers.captureScreenshot(driver, "book_resultUpdate_name"+nameBookUpdate);
				return false;
			}

			validateUIHelpers.clickElement(checkBox);
			validateUIHelpers.clickElement(updateBookBtn);
			if (!nameValue.equals("")) {
				validateUIHelpers.setText(nameInputUpdate, nameValue);
			}

			Assert.assertNotNull(nameInputUpdate);
			if (!authorValue.equals("")) {
				validateUIHelpers.setText(authorInputUpdate, authorValue);
			}
			if (!priceValue.equals("")) {
				validateUIHelpers.setText(priceInputUpdate, priceValue);
			}
			if (!publicValue.equals("")) {
				validateUIHelpers.setText(publicInputUpdate, publicValue);
			}
			validateUIHelpers.clickElement(updateBtn);
			checkValueUpdateBook(excel,i);
			Thread.sleep(200);
			Assert.assertNotNull(driver.findElement(SwalNotification), "Chỉnh sửa thông tin sách thành công");
			Thread.sleep(1500);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			validateUIHelpers.clickElement(closeUpdate);
			return false;
		}
	}
}
