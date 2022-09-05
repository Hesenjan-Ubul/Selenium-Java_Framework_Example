package com.shopapotheke.login;

import com.shopapotheke.utilities.TestUtility;
import io.github.sukgu.support.ElementFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.github.sukgu.support.FindElementBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class LoginPage {

    WebDriver driver;
    TestUtility testUtility;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        ElementFieldDecorator decorator = new ElementFieldDecorator(new DefaultElementLocatorFactory(driver));
        PageFactory.initElements(decorator, this);
        testUtility = new TestUtility(driver);
    }

    @FindBy(css = "#loginForm-eMail")
    WebElement emailAddressField;

    @FindBy(css = "#loginForm-password")
    WebElement passwordField;

    @FindBy(id = "login-submit-btn")
    WebElement anmeldenButton;

    @FindElementBy(css = "button.sc-gsDKAQ.dZIwB")
    WebElement acceptCookiesLink;

    @FindBy(css = ".u-padding--left.l-flex.l-flex__column.l-flex--start > span")
    WebElement accountNumber;

    @FindBy(css = ".l-flex__primary.u-no-margin.u-padding--ends.m-Notification__message")
    WebElement loginFailureMessage;


    public void acceptCookies() {
        testUtility.waitForElementToBeClickable(acceptCookiesLink);
        acceptCookiesLink.click();
    }

    public void fillAndLogin(String emailAddress, String Password) {
        testUtility.waitForElementPresent(emailAddressField);
        emailAddressField.sendKeys(emailAddress);
        passwordField.sendKeys(Password);
        testUtility.waitForElementToBeClickable(anmeldenButton);
        anmeldenButton.click();
        testUtility.sleepXSeconds(3);
    }

    public void validLogin(String emailAddress, String Password) {
        acceptCookies();
        fillAndLogin(emailAddress, Password);
        testUtility.waitForElementPresent(accountNumber);
    }

    public void invalidLoginTestNG(String emailAddress, String Password) {
        fillAndLogin(emailAddress, Password);
        testUtility.waitForElementPresent(loginFailureMessage);
    }

    public void invalidLoginCucumber(String emailAddress, String Password) {
        acceptCookies();
        invalidLoginTestNG(emailAddress, Password);
    }

    public boolean verifySuccessfulLogin() {
        return accountNumber.getText().contains("Kundennummer");
    }

    public boolean verifyUnsuccessfulLogin() {
        return loginFailureMessage.isDisplayed();
    }
}
