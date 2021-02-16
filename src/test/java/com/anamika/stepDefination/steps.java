package com.anamika.stepDefination;

import com.anamika.Utilities.TestBase;
import com.anamika.kayakPO.poKayak;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class steps extends TestBase {
    public poKayak kayak;
    WebDriver driver;

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Before
    public void settingUpEnvironment() throws Exception {
        sErrorMessage = "";
        sClassNameForScreenShot = getClass().getSimpleName();
        TriggerDependencies();
        TestBase.driver.get(oCons.getAppURL());
        kayak = new poKayak(TestBase.driver);

    }
    @After
    public void close() throws Exception {
        ShuttingDownAllDependencies();
    }
    @Given("User goes to Kayak site")
    public void user_goes_to_kayak_site() {
        System.out.println("test");
    }

    @When("User clicks on Flights link")
    public void user_clicks_on_flights_link() throws Exception {
        kayak.clickOnFligths();
    }

    @When("User enters from {string} airport")
    public void user_enters_from_airport(String from) throws Exception {
        kayak.enterFrom(from);
        kayak.nearbyFrom();
    }

    @When("User enters to {string} airport")
    public void user_enters_to_airport(String to) throws Exception {
        kayak.enterTo(to);
        kayak.nearbyTo();
    }

    @When("User enters date {string} and {string}")
    public void user_enters_date_and(String sDate, String retDate) throws Exception {
        kayak.enterDate(sDate,retDate);
    }

    @When("User clicks on search button")
    public void user_clicks_on_search_button() throws Exception {
        kayak.kSearch();
    }

    @Then("Result pane is displayed and first one {string} selected")
    public void result_pane_is_displayed_and_first_one_selected(String from) throws Exception {
        kayak.result(from);
    }
}
