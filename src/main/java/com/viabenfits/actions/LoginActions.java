package com.viabenfits.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.viabenfits.pages.LoginPage;

public class LoginActions {

    private final LoginPage loginPage;

    public LoginActions(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public WebElement clickSignIn() {
        //loginPage.clickSignIn();
        return loginPage.getSignInButton();
    }

    public String getLoginPageTitle() {
        return loginPage.getPageTitle();
    }
}
