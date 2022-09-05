package com.shopapotheke.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static org.openqa.selenium.remote.BrowserType.*;


public class TestBase {

    public WebDriver driver;
    BrowserType browserType;

    public void browserSetUp(String url) {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            JenkinsBrowserSetup jenkinsBrowserSetup = new JenkinsBrowserSetup();
            boolean useHeadless = jenkinsBrowserSetup.setHeadlessModeIfLinux(chromeOptions);
            if (!useHeadless) {
                chromeOptions.addArguments("--disable-blink-features");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setPageLoadStrategy(NORMAL);
            }
            driver = new ChromeDriver(chromeOptions);
            if (!useHeadless) {
                driver.manage().window().maximize();
            }
            driver.get(url);
        }
    }

//    public void browserSetUp(String url) {
//        if (driver == null) {
//            String browserName = String.valueOf(browserType);
//            switch (browserName) {
//                case FIREFOX -> {
//                    WebDriverManager.firefoxdriver().setup();
//                    FirefoxOptions firefoxOptions = new FirefoxOptions();
//                    driver = new FirefoxDriver(firefoxOptions);
//                }
//                case SAFARI -> {
//                    WebDriverManager.safaridriver().setup();
//                    SafariOptions safariOptions = new SafariOptions();
//                    driver = new SafariDriver(safariOptions);
//                }
//                case EDGE -> {
//                    WebDriverManager.edgedriver().setup();
//                    EdgeOptions edgeOptions = new EdgeOptions();
//                    driver = new EdgeDriver(edgeOptions);
//                }
//                default -> {
//                    WebDriverManager.chromedriver().setup();
//                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.addArguments("--disable-blink-features");
//                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
//                    chromeOptions.setPageLoadStrategy(NORMAL);
//                    driver = new ChromeDriver(chromeOptions);
//                }
//            }
//            driver.manage().window().maximize();
//            driver.get(url);
//        }
//    }


    public void closeBrowser() {
        try {
            if ((driver != null)) {
                Thread.sleep(5000);
                driver.quit();
                driver = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
