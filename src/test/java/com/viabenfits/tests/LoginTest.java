package com.viabenfits.tests;

import com.viabenfits.actions.LoginActions;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.viabenfits.base.BaseTest;
import com.viabenfits.listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    private LoginActions loginActions;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginActions = new LoginActions(driver());
    }

    @Test(description = "Simple login page smoke test")
    public void loginPageSmoke() {
        String title = loginActions.getLoginPageTitle();
        Assert.assertNotNull(title, "Page title should be present");
    }

    @Test(description = "Simple sign-in test")
    //This is test to verify git push
    public void signIn() {

        loginActions.clickSignIn().click();
    }

    @Test(description = "Simple login test")
    public void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginActions.clickSignIn()));
        loginActions.clickSignIn().click();
    }
}
