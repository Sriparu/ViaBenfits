package com.viabenfits.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.viabenfits.factory.DriverFactory;
import com.viabenfits.utils.ConfigReader;

public class BaseTest {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    protected WebDriver driver;  // Protected field for easy access in test classes

    protected WebDriver driver() {
        return tlDriver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driverInstance = DriverFactory.createDriver();   // create NEW driver per thread
        tlDriver.set(driverInstance);
        this.driver = driverInstance;  // Set the protected field too
        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
        }
        tlDriver.remove();
    }

}
