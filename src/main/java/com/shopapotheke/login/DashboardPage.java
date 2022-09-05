package com.shopapotheke.login;

import com.shopapotheke.utilities.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    WebDriver driver;
    TestUtility testUtility;
    Actions actions;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions = new Actions(driver);
    }

    @FindBy(xpath = "//li[@name=\"my-apotheke-icon\"]")
    WebElement meinApothekeLink;

    @FindBy(css = "li[name=\"my-apotheke-icon\"] button[name=\"logoutForm-submit-button\"]")
    WebElement abmeldenLink;

    @FindBy(xpath = "//li[@name=\"login-icon\"]")
    WebElement anmeldenMenu;

    @FindBy(id = "login-submit-btn")
    WebElement anmeldenButton;

    public void logout() {
        actions.moveToElement(meinApothekeLink).moveToElement(abmeldenLink).click().perform();
        testUtility.sleepXSeconds(3);
    }

    public void clickOnAnmeldenMenu() {
        anmeldenMenu.click();
        testUtility.waitForElementToBeClickable(anmeldenButton);
    }
}
