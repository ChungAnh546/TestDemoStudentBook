//package com.test;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.common.Init;
//import com.pages.SwitchPage;
//import com.testcases.PerformTestBook;
//import com.testcases.PerformTestStudent;
//import com.testcases.SignInTest;
//
//public class TestStudent extends Init {
//	SignInTest signIn;
//	PerformTestStudent student;
//	SwitchPage switchPage;
//
//
//
////	@Test(priority = 0)
////	public void performLogin() throws InterruptedException {
////		signIn = new SignInTest(driver);
////		try {
////			signIn.signInPage();
////			Thread.sleep(2000);
//////		driver.switchTo().alert().accept();
////
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
////
////	@Test(priority = 1)
////	public void checkLogin() {
////		// TODO Auto-generated method stub
////		// SignInTest signIn = new SignInTest(driver);
////		signIn = new SignInTest(driver);
////		Assert.assertEquals(signIn.checkSignIn(), true);
////		System.out.println("login success");
////	}
//
//	@Test(priority = 2)
//	public void performAddStudent() {
//		student = new PerformTestStudent(driver);
//		switchPage = new SwitchPage(driver);
//		try {
//			switchPage.switchPage("Sinh viên");
//			Assert.assertEquals(student.preformAddStudent(), true);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test(priority = 3)
//	private void checkAddStudent() {
//		// TODO Auto-generated method stub
//		student = new PerformTestStudent(driver);
//		try {
//			Assert.assertEquals(student.checkAddStudent(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//
//	}
//
//	@Test(priority = 4)
//	public void performUpdateStudent() {
//		student = new PerformTestStudent(driver);
//		switchPage = new SwitchPage(driver);
//		try {
//			switchPage.switchPage("Sinh viên");
//
//			Assert.assertEquals(student.preformUpdateStudent(), true);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test(priority = 5)
//	public void checkUpdateStudent() {
//		student = new PerformTestStudent(driver);
//		try {
//			Assert.assertEquals(student.checkUpdateStudent(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//	}
//
//	@Test(priority = 6)
//	public void preformDeleteStudent() {
//		student = new PerformTestStudent(driver);
//		try {
//			Assert.assertEquals(student.preformDeleteStudent(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//	}
//
//	@Test(priority = 7)
//	public void checkDeleteStudent() {
//		student = new PerformTestStudent(driver);
//		try {
//			Assert.assertEquals(student.checkDeleteStudent(), true);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//	}
//}
