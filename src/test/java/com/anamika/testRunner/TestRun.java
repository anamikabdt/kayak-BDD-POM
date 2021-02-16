package com.anamika.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"features/kayakTc.feature"},
        glue = {"com.anamika.stepDefination"},
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:src/Reports/rep.html"}
)
public class TestRun extends AbstractTestNGCucumberTests {

}
