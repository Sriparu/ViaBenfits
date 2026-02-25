package com.viabenfits.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.viabenfits.utils.ExtentManager;
import com.viabenfits.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.createTest(result.getMethod().getMethodName());
        result.setAttribute("extentTest", test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test != null) {
            test.pass("Test passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        Throwable t = result.getThrowable();
        if (test != null) {
            test.fail(t);
            // try take screenshot if driver is stored as a field in test instance
            Object instance = result.getInstance();
            try {
                WebDriver driver = (WebDriver) instance.getClass().getField("driver").get(instance);
                String path = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
                if (path != null) {
                    test.addScreenCaptureFromPath(path);
                }
            } catch (Exception e) {
                // ignore
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test != null) {
            test.skip("Test skipped");
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}
