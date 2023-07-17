package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.common.helpers.ValidateUIHelpers;

public class SwitchPage {
	WebDriver driver;
	private ValidateUIHelpers validateUIHelpers;

	public SwitchPage(WebDriver driver) {
		this.driver = driver;
		validateUIHelpers = new ValidateUIHelpers(this.driver);
	}
	public void switchPage(String titlePage) {
	switch (titlePage) {
	case "Sách": {
		validateUIHelpers.clickElement(By.xpath("//a[@href='/book/search']"));
		break;
	}
	case "Sinh viên": {
		validateUIHelpers.clickElement(By.xpath("//a[@href='/student/search']"));
		break;
	}
	case "Quản lý mượn sách": {
		validateUIHelpers.clickElement(By.xpath("//a[@href='/student-book/search']"));
		break;
	}
	default:
		validateUIHelpers.clickElement(By.xpath("//a[@href='/student-book/search']"));
		throw new IllegalArgumentException("Unexpected value: " + titlePage);
	}
	}
}
