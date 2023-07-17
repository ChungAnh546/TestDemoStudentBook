package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.common.helpers.CaptureHelpers;
import com.common.helpers.ExcelHelpers;
import com.common.helpers.ValidateUIHelpers;

public class StudentPage {
	WebDriver driver;
	private ValidateUIHelpers validateUIHelpers;
	JavascriptExecutor js;
	SoftAssert softassert;
	private WebDriverWait wait;

	public StudentPage(WebDriver driver) {
		this.driver = driver;
		validateUIHelpers = new ValidateUIHelpers(this.driver);

	}

	private By nameInput = By.xpath("//input[@id='inputName']");
	private By mssvInput = By.xpath("//input[@id='inputMSSV']");
	private By sdtInput = By.xpath("//input[@id='inputDienThoai']");
	private By diaChiInput = By.xpath("//input[@id='inputDiaChi']");
	private By addBtn = By.xpath("//button[@id='createSinhVien']");
	private By updateBtn = By.xpath("//button[@id='updateSinhVien']");

	private By closeUpdate = By.xpath("//button[@id='closeModalUpdate']");
	private By closeBtn = By.xpath("//button[@id='closeModalSinhVien']");
	private By createStudentBtn = By.xpath("//button[@id='btnCreatetableSinhVien']");
	private By updateStudentBtn = By.xpath("//button[@id='btnToggleUpdate']");
	private By deleteStudentBtn = By.xpath("//button[@id='btnXoa']");
	private By confirmdeleteStudentBtn = By.xpath("//button[contains(text(),'delete')]");
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
	private By mssvInputUpdate = By.xpath("//input[@id='inputMSSVUpdate']");
	private By sdtInputUpdate = By.xpath("//input[@id='inputDienThoaiUpdate']");
	private By diaChiInputUpdate = By.xpath("//input[@id='inputDiaChiUpdate']");
	private By tbNoFound = By.xpath("//td[contains(text(),'No matching records found')]");
	private By SwalNotification = By.xpath("//h2[@id='swal2-title']");

	public boolean addStudent(String nameValue, String authorValue, String priceValue, String publicValue,ExcelHelpers excel,int i) {

		try {

			validateUIHelpers.waitForPageLoaded();
			js = (JavascriptExecutor) driver;
			String titlePage = js.executeScript("return document.title;").toString();
			Assert.assertEquals(titlePage, "Sinh viên");
			validateUIHelpers.clickElement(createStudentBtn);

			validateUIHelpers.setText(nameInput, nameValue);
			Assert.assertNotNull(nameInput);
			validateUIHelpers.setText(mssvInput, authorValue);
			validateUIHelpers.setText(sdtInput, priceValue);
			validateUIHelpers.setText(diaChiInput, publicValue);
			validateUIHelpers.clickElement(addBtn);
			
			checkValueAddStudent(excel,i);
			Thread.sleep(500);
			Assert.assertNotNull(driver.findElement(SwalNotification), "Thêm thông tin sinh viên thành công");
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
	private void setResultExcel(ExcelHelpers excel,boolean result,String col,int i) throws Exception {
		try {

			

			if (result) {

				excel.setCellData("Successful", col, i);
			} else {
				excel.setCellData("Fail", col, i);

			}

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}
	public boolean checkValueAddStudent(ExcelHelpers excel,int i) {
		
		try {

		
			boolean resultName = validateUIHelpers.checkElementClass("is-invalid", "inputName");
			boolean resultAuthor = validateUIHelpers.checkElementClass("is-invalid", "inputMSSV");
			boolean resultPrice = validateUIHelpers.checkElementClass("is-invalid", "inputDienThoai");
			boolean resultPublic = validateUIHelpers.checkElementClass("is-invalid", "inputDiaChi");
			setResultExcel(excel,!resultName,"resultName",i);
			setResultExcel(excel,!resultAuthor,"resultMSSV",i);
			setResultExcel(excel,!resultPrice,"resultPhoneNumber",i);
			setResultExcel(excel,!resultPublic,"resultAddress",i);
			
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

	}
	public boolean checkValueUpdateStudent(ExcelHelpers excel,int i) {
		
		try {

			
			boolean resultName = validateUIHelpers.checkElementClass("is-invalid", "inputNameUpdate");
			boolean resultAuthor = validateUIHelpers.checkElementClass("is-invalid", "inputMSSVUpdate");
			boolean resultPrice = validateUIHelpers.checkElementClass("is-invalid", "inputDienThoaiUpdate");
			boolean resultPublic = validateUIHelpers.checkElementClass("is-invalid", "inputDiaChiUpdate");
			setResultExcel(excel,!resultName,"resultNameUpdate",i);
			setResultExcel(excel,!resultAuthor,"resultMSSVUpdate",i);
			setResultExcel(excel,!resultPrice,"resultPhoneNumberUpdate",i);
			setResultExcel(excel,!resultPublic,"resultAddressUpdate",i);
			
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

	}

	public By GetByByValueTable(String value) {
		return By.xpath("//td[contains(text(),'" + value + "')]");
	}

	public boolean checkStudent(String nameValue, String authorValue, String priceValue, String publicValue) {
		try {
			if(nameValue.equals("")) {
				CaptureHelpers.captureScreenshot(driver, "student_resultcheck_name"+nameValue);
				return false;
			}
			validateUIHelpers.waitForPageLoaded();
			searchTableStudent(nameValue);
			Thread.sleep(200);
			if (!validateUIHelpers.verifyElementExist(GetByByValueTable(nameValue))) {
				CaptureHelpers.captureScreenshot(driver, "student_resultcheck_name"+nameValue);
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
			CaptureHelpers.captureScreenshot(driver, "student_resultcheck_name"+nameValue);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public void searchTableStudent(String data) {
		validateUIHelpers.waitForPageLoaded();
		validateUIHelpers.setText(searchInput, data);
	}

	public boolean checkdeleteStudent(String name) {
		try {
			validateUIHelpers.waitForPageLoaded();
			if(name.equals("")) {
				
				return true;
			}
			searchTableStudent(name);
			if (validateUIHelpers.verifyElementExist(tbNoFound)) {
				return true;
			} else {
				if (validateUIHelpers.verifyElementExist(GetByByValueTable(name))) {
					CaptureHelpers.captureScreenshot(driver, "student_resultcheckDelete_name"+name);
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

	public boolean deleteStudent(String name) {
		try {
			if(name.equals("")) {
				CaptureHelpers.captureScreenshot(driver, "student_resultDelete_name"+name);
				return false;
			}
			validateUIHelpers.waitForPageLoaded();
			searchTableStudent(name);
			if (!validateUIHelpers.verifyElementExist(GetByByValueTable(name))) {
				CaptureHelpers.captureScreenshot(driver, "student_resultDelete_name"+name);
				return false;
			}
			if (!validateUIHelpers.verifyElementText(nameTable,name)) {
				CaptureHelpers.captureScreenshot(driver, "student_resultDelete_name"+name);
				return false;
			}
			validateUIHelpers.clickElement(checkBox);

			Assert.assertNotNull(checkBox);
			validateUIHelpers.clickElement(deleteStudentBtn);

			Assert.assertNotNull(confirmdeleteStudentBtn);
			validateUIHelpers.clickElement(confirmdeleteStudentBtn);
			Assert.assertNotNull(driver.findElement(SwalNotification), "Xóa thông tin Sinh viên thành công");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean updateStudent(String nameBookUpdate, String nameValue, String authorValue, String priceValue,
			String publicValue,ExcelHelpers excel,int i) {

		try {
			if (nameBookUpdate.equals("")) {
				CaptureHelpers.captureScreenshot(driver, "student_resultUpdate_name"+nameBookUpdate);
				return false;
			}
			validateUIHelpers.waitForPageLoaded();
			js = (JavascriptExecutor) driver;
			String titlePage = js.executeScript("return document.title;").toString();
			Assert.assertEquals(titlePage, "Sinh viên");

			searchTableStudent(nameBookUpdate);
			if (!validateUIHelpers.verifyElementExist(GetByByValueTable(nameBookUpdate))) {
				CaptureHelpers.captureScreenshot(driver, "student_resultUpdate_name"+nameBookUpdate);
				return false;
			}
			if(!validateUIHelpers.verifyElementText(nameTable, nameBookUpdate)) {
				CaptureHelpers.captureScreenshot(driver, "student_resultUpdate_name"+nameBookUpdate);
				return false;
			}

			validateUIHelpers.clickElement(checkBox);
			validateUIHelpers.clickElement(updateStudentBtn);
			if (!nameValue.equals("")) {
				validateUIHelpers.setText(nameInputUpdate, nameValue);
			}

			Assert.assertNotNull(nameInputUpdate);
			if (!authorValue.equals("")) {
				validateUIHelpers.setText(mssvInputUpdate, authorValue);
			}
			if (!priceValue.equals("")) {
				validateUIHelpers.setText(sdtInputUpdate, priceValue);
			}
			if (!publicValue.equals("")) {
				validateUIHelpers.setText(diaChiInputUpdate, publicValue);
			}
			validateUIHelpers.clickElement(updateBtn);
			checkValueUpdateStudent(excel,i);
			Thread.sleep(200);
			Assert.assertNotNull(driver.findElement(SwalNotification), "Chỉnh sửa thông tin Sinh viên thành công");
			Thread.sleep(1500);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			validateUIHelpers.clickElement(closeUpdate);
			return false;
		}
	}
}
