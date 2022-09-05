package com.shopapotheke.utilities;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestUtility {

    WebDriverWait wait;

    public TestUtility(WebDriver driver) {
        int timeout = Integer.parseInt(ApplicationConfig.readFromConfigProperties(
                "config-qa.properties", "timeout"));
        wait = new WebDriverWait(driver, timeout);
    }

    public void waitForElementPresent(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //static wait
    public void sleepXSeconds(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
