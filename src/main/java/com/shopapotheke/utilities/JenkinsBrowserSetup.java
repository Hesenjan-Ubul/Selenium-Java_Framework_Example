package com.shopapotheke.utilities;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class JenkinsBrowserSetup {

    public boolean setHeadlessModeIfLinux(ChromeOptions chromeOptions) {

        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("linux")) {
            System.out.println("Using headless browser.");
            chromeOptions.addArguments(Arrays.asList("--headless", "--disable-gpu"));
            chromeOptions.addArguments("window-size=1200,1100");
            return true;
        } else
            return false;
    }

}
