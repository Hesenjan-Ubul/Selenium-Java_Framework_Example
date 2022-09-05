package com.shopapotheke.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGResultListener implements ITestListener {

    ScreenShotUtility screenShotUtility = new ScreenShotUtility();

    public void onTestSuccess(ITestResult result) {
        Log4j.info(result.getMethod().getMethodName() + ":  Passed.");
    }

    public void onTestFailure(ITestResult result) {
        Log4j.error(result.getMethod().getMethodName() + ":  Failed.");
        screenShotUtility.takeScreenshot("ScreenShot_Images", result.getMethod().getMethodName(),
                (WebDriver) result.getTestContext().getAttribute("driver"));
    }

    public void onTestSkipped(ITestResult result) {
        Log4j.info(result.getMethod().getMethodName() + ":  Skipped.");
    }

}
