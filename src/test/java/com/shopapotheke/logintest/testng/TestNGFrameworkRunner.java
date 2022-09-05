package com.shopapotheke.logintest.testng;

import com.shopapotheke.login.DashboardPage;
import com.shopapotheke.login.LoginPage;
import com.shopapotheke.utilities.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestNGResultListener.class)
public class TestNGFrameworkRunner extends TestBase {

    //read application config based on the environment variable
    EnvironmentUtility environmentUtility = new EnvironmentUtility();
    String configFile = environmentUtility.getConfigFileByEnvironment(Environment.QA);
    LoginPage loginPage;
    DashboardPage dashboardPage;


    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "url")); // read url from config file
        Log4j.startTestCase("Shop Apotheke Ui Login Automation Test Start");
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test(description = "Using valid credentials, a customer can successfully log in", priority = 1)
    public void validLoginTest() {
        loginPage.validLogin(ApplicationConfig.readFromConfigProperties(configFile, "validEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "validPassword")); // read email and password from config file
        Assert.assertTrue(loginPage.verifySuccessfulLogin());
        dashboardPage.logout();
    }

    @Test(description = "Using valid email and invalid password, a customer can't successfully log in", priority = 2)
    public void invalidLoginTest1() {
        loginPage.invalidLoginTestNG(ApplicationConfig.readFromConfigProperties(configFile, "validEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "invalidPassword")); // read email and password from config file
        Assert.assertTrue(loginPage.verifyUnsuccessfulLogin());
    }

    @Test(description = "Using invalid email and valid password, a customer can't successfully log in", priority = 3)
    public void invalidLoginTest2() {
        loginPage.invalidLoginTestNG(ApplicationConfig.readFromConfigProperties(configFile, "invalidEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "validPassword")); // read email and password from config file
        Assert.assertTrue(loginPage.verifyUnsuccessfulLogin());
    }

    @Test(description = "Using invalid email and invalid password, a customer can't successfully log in", priority = 4)
    public void invalidLoginTest3() {
        loginPage.invalidLoginTestNG(ApplicationConfig.readFromConfigProperties(configFile, "invalidEmailAddress"),
                ApplicationConfig.readFromConfigProperties(configFile, "invalidPassword")); // read email and password from config file
        Assert.assertTrue(loginPage.verifyUnsuccessfulLogin());
    }

    @AfterMethod
    public void logout() {
        dashboardPage.clickOnAnmeldenMenu();
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }
}
