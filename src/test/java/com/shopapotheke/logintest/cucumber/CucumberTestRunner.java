package com.shopapotheke.logintest.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty:target/cucumber-pretty.txt", "html:target/cucumber-html-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml",
                "usage:target/cucumber-usage.json"
        },
        features = {"src/test/resources/features"},
        tags = "@UILoginTest",
        glue = {"com/shopapotheke/logintest/cucumber"}
)
public class CucumberTestRunner {

}
