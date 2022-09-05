package com.shopapotheke.logintest.cucumber;

import com.shopapotheke.login.DashboardPage;
import com.shopapotheke.login.LoginPage;
import com.shopapotheke.utilities.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

public class LoginSteps extends TestBase {

    //read application config based on the environment variable
    EnvironmentUtility environmentUtility = new EnvironmentUtility();
    String configFile = environmentUtility.getConfigFileByEnvironment(Environment.QA);
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ScreenShotUtility screenShotUtility;

    @Before("@UILoginTest")
    public void before() {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "url")); // read url from config file;
        screenShotUtility = new ScreenShotUtility();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Given("the user is on the shop apotheke login page")
    public void theUserIsOnTheShopApothekeLoginPage() {
        // the login page is already opened in the before class
    }

    @When("the user tries to login with valid credentials")
    public void theUserTriesToLogin() {
        loginPage.validLogin(ApplicationConfig.readFromConfigProperties(configFile, "validEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "validPassword")); // read email and password from config file
    }

    @Then("the user should be lead to successful login")
    public void theUserShouldBeLoginSuccessfully() {
        Assert.assertTrue(loginPage.verifySuccessfulLogin());
    }

    @When("the user tries to login with invalid emailAddress and valid password")
    public void theUserTriesToLoginWithInvalidEmailAddressAndValidPassword() {
        loginPage.invalidLoginCucumber(ApplicationConfig.readFromConfigProperties(configFile, "invalidEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "validPassword")); // read email and password from config file
    }

    @When("the user tries to login with valid emailAddress and invalid password")
    public void theUserTriesToLoginWithValidEmailAddressAndInvalidPassword() {
        loginPage.invalidLoginCucumber(ApplicationConfig.readFromConfigProperties(configFile, "validEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "invalidPassword")); // read email and password from config file
    }

    @When("the user tries to login with invalid emailAddress and invalid password")
    public void theUserTriesToLoginWithInvalidEmailAddressAndInvalidPassword() {
        loginPage.invalidLoginCucumber(ApplicationConfig.readFromConfigProperties(configFile, "invalidEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "invalidPassword")); //
    }

    @Then("the user should not be lead to successful login")
    public void theUserShouldNotBeLeadToSuccessfulLogin() {
        Assert.assertTrue(loginPage.verifyUnsuccessfulLogin());
    }

    @After("@UILoginTest")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Log4j.error(scenario.getName() + ": Failed");
            screenShotUtility.takeScreenshot("ScreenShot_Images", scenario.getName(), driver);
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "ScreenShot_Images/png", scenario.getName());
        }
        if (!scenario.isFailed()) {
            Log4j.info(scenario.getName() + ": Passed");
        }
        closeBrowser();
    }

}
