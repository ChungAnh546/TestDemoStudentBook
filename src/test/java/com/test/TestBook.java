//package com.test;
//
//import org.testng.annotations.Test;
//import org.testng.AssertJUnit;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.common.Init;
//import com.common.helpers.ValidateUIHelpers;
//import com.pages.SwitchPage;
//import com.testcases.PerformTestBook;
//import com.testcases.SignInTest;
//
//public class TestBook extends Init {
//	SignInTest signIn;
//	PerformTestBook book;
//	SwitchPage switchPage;
//
//
//
//	@Test(priority = 0)
//	public void performLogin() throws InterruptedException {
//		signIn = new SignInTest(driver);
//		try {
//			signIn.signInPage();
//			Thread.sleep(2000);
////		driver.switchTo().alert().accept();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test(priority = 1)
//	public void checkLogin() {
//		// TODO Auto-generated method stub
//		// SignInTest signIn = new SignInTest(driver);
//		signIn = new SignInTest(driver);
//		Assert.assertEquals(signIn.checkSignIn(), true);
//		System.out.println("login success");
//	}
//
//	@Test(priority = 2)
//	public void performAddBook() {
//		book = new PerformTestBook(driver);
//		switchPage = new SwitchPage(driver);
//		try {
//			switchPage.switchPage("Sách");
//			Assert.assertEquals(book.preformAddBook(), true);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test(priority = 3)
//	private void checkAddBook() {
//		// TODO Auto-generated method stub
//		book = new PerformTestBook(driver);
//		try {
//			Assert.assertEquals(book.checkAddBook(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//
//	}
//	
//
//	@Test(priority = 4)
//	public void performUpdateBook() {
//		book = new PerformTestBook(driver);
//		switchPage = new SwitchPage(driver);
//		try {
//			switchPage.switchPage("Sách");
//
//			Assert.assertEquals(book.preformUpdateBook(), true);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test(priority = 5)
//	public void checkUpdateBook() {
//		book = new PerformTestBook(driver);
//		try {
//			Assert.assertEquals(book.checkUpdateBook(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//	}
//
//	@Test(priority = 6)
//	public void preformDeleteBook() {
//		book = new PerformTestBook(driver);
//		try {
//			Assert.assertEquals(book.preformDeleteBook(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//	}
//
//	@Test(priority = 7)
//	public void checkDeleteBook() {
//		book = new PerformTestBook(driver);
//		try {
//			Assert.assertEquals(book.checkDeleteBook(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//	}
//}
