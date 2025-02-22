package org.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

 @CucumberOptions(
            features = "src/test/resources/features",
            glue = {"steps","Hooks"},
            tags = "@apitest",
            plugin = {"pretty", // Pretty output
                    "json:target/cucumber-report.json", // JSON report for reporting tools
                    "html:target/cucumber-report.html"  // HTML Report
         }
    )
    public class TestRunner extends AbstractTestNGCucumberTests {

    }

