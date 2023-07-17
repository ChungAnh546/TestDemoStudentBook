package com.testcases;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.common.helpers.ExcelHelpers;
import com.pages.BookPage;
import com.pages.SignInPage;

public class PerformTestBook {
	private WebDriver driver;
	private BookPage bookPage;
	private ExcelHelpers excel;
	private int RowNumberEnd = 6;

	public PerformTestBook(WebDriver driver) {
		this.driver = driver;
		try {
			excel = new ExcelHelpers();
			excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheet1");
			// RowNumberEnd = 5;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean CheckValueInput() {
		try {
//		bookPage = new BookPage(driver);
//		for (int i = 1; i < RowNumberEnd; i++) {
//			
////		List<Boolean>ls =	bookPage.checkValueAddBook(excel.getCellData("name", i), excel.getCellData("author", i),
////					excel.getCellData("price", i), excel.getCellData("public", i),excel);
//		int size =ls.size();
//		if(size==0) {
//			excel.setCellData("Fail", "resultName", i);
//			excel.setCellData("Fail", "resultPrice", i);
//			excel.setCellData("Fail", "resultAuthor", i);
//			excel.setCellData("Fail", "resultPublic", i);
//			return true;
//		}	
//		if(ls.get(0)) {
//			excel.setCellData("Successful", "resultName", i);
//		}else {
//			excel.setCellData("Fail", "resultName", i);
//		}
//		if(ls.get(1)) {
//			excel.setCellData("Successful", "resultAuthor", i);
//		}else {
//			excel.setCellData("Fail", "resultAuthor", i);
//		}
//		if(ls.get(2)) {
//			excel.setCellData("Successful", "resultPrice", i);
//		}else {
//			excel.setCellData("Fail", "resultPrice", i);
//		}
//		if(ls.get(3)) {
//			excel.setCellData("Successful", "resultPublic", i);
//		}else {
//			excel.setCellData("Fail", "resultPublic", i);
//		}
//
//		}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
			// TODO: handle exception
		}
	}

	public boolean preformAddBook() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		bookPage = new BookPage(driver);
		//System.out.println(RowNumberEnd);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {

				boolean checkAdd = bookPage.addBook(excel.getCellData("name", i), excel.getCellData("author", i),
						excel.getCellData("price", i), excel.getCellData("public", i), excel, i);
				if (checkAdd == true) {
					excel.setCellData("Successful", "resultAdd", i);

				} else {
					excel.setCellData("Fail", "resultAdd", i);

				}

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

	}

	public boolean preformUpdateBook() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		bookPage = new BookPage(driver);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				if (bookPage.updateBook(excel.getCellData("name", i), excel.getCellData("nameUpdate", i),
						excel.getCellData("authorUpdate", i), excel.getCellData("priceUpdate", i),
						excel.getCellData("publicUpdate", i), excel, i)) {
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

	public boolean preformDeleteBook() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		bookPage = new BookPage(driver);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				Thread.sleep(1500);
				String nameUpdate = excel.getCellData("nameUpdate", i);
				if (nameUpdate == null) {
					if (bookPage.deleteBook(excel.getCellData("name", i))) {
						excel.setCellData("Successful", "resultDelete", i);

					} else {
						excel.setCellData("Fail", "resultDelete", i);

					}
				} else {
					if (bookPage.deleteBook(nameUpdate)) {

						excel.setCellData("Successful", "resultDelete", i);

					} else {
						if (excel.getCellData("resultAdd", i).equals("Fail")) {
							excel.setCellData("Fail", "resultDelete", i);
						}else {
						if (bookPage.deleteBook(excel.getCellData("name", i))) {
							excel.setCellData("Successful", "resultDelete", i);
						} else {
							excel.setCellData("Fail", "resultDelete", i);
						}
					}
					}
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean checkAddBook() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		bookPage = new BookPage(driver);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {

				if (bookPage.checkBook(excel.getCellData("name", i), excel.getCellData("author", i),
						excel.getCellData("price", i), excel.getCellData("public", i))) {
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

	public boolean checkDeleteBook() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		bookPage = new BookPage(driver);
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				String nameUpdate = excel.getCellData("nameUpdate", i);
				if (nameUpdate == null) {
					if (bookPage.checkDeleteBook(excel.getCellData("name", i))) {
						excel.setCellData("Successful", "resultCheckDelete", i);

					} else {
						excel.setCellData("Fail", "resultCheckDelete", i);

					}
				} else {
					if (bookPage.checkDeleteBook(nameUpdate)) {
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

	public boolean checkUpdateBook() throws Exception {

		// excel.setExcelFile("src/test/resources/DataTestBook.xlsx", "Sheeti");
		bookPage = new BookPage(driver);
		String nameUpdate;
		String authorUpdate;
		String priceUpdate;
		String publicUpdate;
		try {

			for (int i = 1; i < RowNumberEnd; i++) {
				if (!excel.getCellData("nameUpdate", i).equals("")) {
					nameUpdate = excel.getCellData("nameUpdate", i);
				} else {
					nameUpdate = excel.getCellData("name", i);
				}
				if (!excel.getCellData("authorUpdate", i).equals("")) {
					authorUpdate = excel.getCellData("authorUpdate", i);
				} else {
					authorUpdate = excel.getCellData("author", i);
				}
				if (!excel.getCellData("priceUpdate", i).equals("")) {
					priceUpdate = excel.getCellData("priceUpdate", i);
				} else {
					priceUpdate = excel.getCellData("price", i);
				}
				if (!excel.getCellData("publicUpdate", i).equals("")) {
					publicUpdate = excel.getCellData("publicUpdate", i);
				} else {
					publicUpdate = excel.getCellData("public", i);
				}

				if (bookPage.checkBook(nameUpdate, authorUpdate, priceUpdate, publicUpdate)) {
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
