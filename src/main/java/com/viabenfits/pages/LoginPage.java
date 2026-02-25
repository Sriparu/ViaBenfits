package com.viabenfits.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Sign In')][1]")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
