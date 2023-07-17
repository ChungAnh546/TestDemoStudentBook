package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.common.helpers.ValidateUIHelpers;

public class SignInPage {

    WebDriver driver;
    private ValidateUIHelpers validateUIHelpers;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        validateUIHelpers = new ValidateUIHelpers(this.driver);
    }

    //  Element at Sign In page
    private By emailInput = By.xpath("//input[@id='inputEmail']");
    private By passwordInput = By.xpath("//input[@id='inputPassword']");
    private By signinBtn = By.xpath("//button[@id='btnSignIn']");

    public DashboardPage signIn(String emailValue, String passwordValue) {
        validateUIHelpers.waitForPageLoaded();
        Assert.assertTrue(validateUIHelpers.verifyElementText(signinBtn, "Sign In"), "Không phải trang Sign In");
        validateUIHelpers.setText(emailInput, emailValue);
        validateUIHelpers.setText(passwordInput, passwordValue);
        validateUIHelpers.clickElement(signinBtn);

        return new DashboardPage(this.driver);
    }
}
