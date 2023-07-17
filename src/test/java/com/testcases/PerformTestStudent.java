package com.testcases;

import org.openqa.selenium.WebDriver;

import com.common.helpers.ExcelHelpers;
import com.pages.StudentPage;

public class PerformTestStudent {
	private WebDriver driver;
	private StudentPage studentPage;
	private ExcelHelpers excel;
	private int RowNumberEnd = 6;

	public PerformTestStudent(WebDriver driver) {
		this.driver = driver;
		try {
			excel = new ExcelHelpers();
			excel.setExcelFile("src/test/resources/DataTestStudent.xlsx", "Sheet1");
			//RowNumberEnd = 5;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean preformAddStudent() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		studentPage = new StudentPage(driver);

		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				boolean checkAdd = studentPage.addStudent(excel.getCellData("name", i), excel.getCellData("mssv", i),
						excel.getCellData("phoneNumber", i), excel.getCellData("address", i),excel,i);
				if (checkAdd == true) {
					excel.setCellData("Successful", "resultAdd", i);

				} else {
					excel.setCellData("Fail", "resultAdd", i);

				}

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean preformUpdateStudent() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		studentPage = new StudentPage(driver);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				if (studentPage.updateStudent(excel.getCellData("name", i), excel.getCellData("nameUpdate", i),
						excel.getCellData("mssvUpdate", i), excel.getCellData("phoneNumberUpdate", i),
						excel.getCellData("addressUpdate", i),excel,i)) {
					excel.setCellData("Successful", "resultUpdate", i);

				} else {
					excel.setCellData("Fail", "resultUpdate", i);

				}

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean preformDeleteStudent() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		studentPage = new StudentPage(driver);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				Thread.sleep(1500);
				String nameUpdate = excel.getCellData("nameUpdate", i);
				if (nameUpdate == null) {
					if (studentPage.deleteStudent(excel.getCellData("name", i))) {
						excel.setCellData("Successful", "resultDelete", i);

					} else {
						excel.setCellData("Fail", "resultDelete", i);

					}
				} else {
					if (studentPage.deleteStudent(nameUpdate)) {
						excel.setCellData("Successful", "resultDelete", i);

					} else {
						if (excel.getCellData("resultAdd", i).equals("Fail")) {
							excel.setCellData("Fail", "resultDelete", i);
						}else {
						if (studentPage.deleteStudent(excel.getCellData("name", i))) {
							excel.setCellData("Successful", "resultDelete", i);
						}else {
							excel.setCellData("Fail", "resultDelete", i);
						}}

					}
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean checkAddStudent() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		studentPage = new StudentPage(driver);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				
				if (studentPage.checkStudent(excel.getCellData("name", i), excel.getCellData("mssv", i),
						excel.getCellData("phoneNumber", i), excel.getCellData("address", i))) {
					excel.setCellData("Successful", "resultCheckAdd", i);

				} else {
					excel.setCellData("Fail", "resultCheckAdd", i);

				}
				Thread.sleep(500);

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean checkDeleteStudent() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		studentPage = new StudentPage(driver);
		try {
			
			for (int i = 1; i < RowNumberEnd; i++) {
				String nameUpdate = excel.getCellData("nameUpdate", i);
				if (nameUpdate == null) {
					if (studentPage.checkdeleteStudent(excel.getCellData("name", i))) {
						excel.setCellData("Successful", "resultCheckDelete", i);

					} else {
						excel.setCellData("Fail", "resultCheckDelete", i);

					}
				} else {
					if (studentPage.checkdeleteStudent(nameUpdate)) {
						excel.setCellData("Successful", "resultCheckDelete", i);

					} else {
						excel.setCellData("Fail", "resultCheckDelete", i);

					}
				}
				Thread.sleep(500);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean checkUpdateStudent() throws Exception {
		
		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		studentPage = new StudentPage(driver);
		String nameUpdate;
		String mssvUpdate;
		String phoneNumberUpdate;
		String addressUpdate;
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				if (!excel.getCellData("nameUpdate", i).equals("")) {
					nameUpdate = excel.getCellData("nameUpdate", i);
				} else {
					nameUpdate = excel.getCellData("name", i);
				}
				if (!excel.getCellData("mssvUpdate", i).equals("")) {
					mssvUpdate = excel.getCellData("mssvUpdate", i);
				} else {
					mssvUpdate = excel.getCellData("mssv", i);
				}
				if (!excel.getCellData("phoneNumberUpdate", i).equals("")) {
					phoneNumberUpdate = excel.getCellData("phoneNumberUpdate", i);
				} else {
					phoneNumberUpdate = excel.getCellData("phoneNumber", i);
				}
				if (!excel.getCellData("addressUpdate", i).equals("")) {
					addressUpdate = excel.getCellData("addressUpdate", i);
				} else {
					addressUpdate = excel.getCellData("address", i);
				}

				if (studentPage.checkStudent(nameUpdate, mssvUpdate, phoneNumberUpdate, addressUpdate)) {
					excel.setCellData("Successful", "resultCheckUpdate", i);

				} else {
					excel.setCellData("Fail", "resultCheckUpdate", i);

				}
				Thread.sleep(500);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
}
