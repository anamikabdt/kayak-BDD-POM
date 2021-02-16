package com.anamika.kayakPO;
import com.anamika.Utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.anamika.Utilities.TestBase.*;

import static com.anamika.Utilities.TestBase.*;
import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

public class poKayak {
    Logger log = Logger.getLogger(getClass().getSimpleName());

    public poKayak(WebDriver driver) {
        TestBase.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //flights link
    @FindBy(xpath = "//a[@href='/flights' and @aria-label='Search for flights']")
    WebElement Flight_link;

    //check if filghts link goes to correct page
    @FindBy(xpath = "//div[@class='keel-container s-t-bp']/h2")
    WebElement Flight_page;

    @FindBy(xpath = "//div[contains(@id,'origin-airport-display') and @data-placeholder='From?']")
    WebElement Flight_From_outerbox;

    @FindBy(xpath = "//input[contains(@id,'-origin-airport') and @placeholder='From?']")
    WebElement Flight_From_innerbox;

    //from dropdown
    @FindBy(xpath = "//div[contains(@id,'-origin-airport-smartbox-dropdown')]/ul/li")
    List<WebElement> airportList_From;

    @FindBy(xpath = "//li[contains(@id,'-origin-airport-nearby')]")
    WebElement nearby_From;
     //To box
     @FindBy(xpath = "//*[contains(@id,'-destination-airport-display') and @data-placeholder='To?']")
     WebElement Flight_To_outerbox;

    @FindBy(xpath = "//input[contains(@id,'-destination-airport') and @placeholder='To?']")
    WebElement Flight_To_innerbox;

    @FindBy(xpath = "//*[contains(@id,'-destination-airport-smartbox-dropdown')]/ul/li")
    List<WebElement> airportList_To;

    //@FindBy(xpath = "//*[contains(@id,'-origin-airport-display-inner')]")
    @FindBy(xpath = "//*[contains(@id,'-origin-airport-display-multi-container')]/div/div[1]/div[2]")
    WebElement nearby_from_click;

    @FindBy(xpath = "//li[contains(@id,'-destination-airport-nearby')]")
    WebElement nearby_To;
    @FindBy(xpath = "//*[contains(@data-placeholder,'To?')]//*[contains(@id,'-destination-airport-display-multi-container')]/div/div[1]/div[2]")
    WebElement nearby_to_click;

    @FindBy(xpath = "//div[contains(@id,'dates-col')]")
    WebElement dates_box;

    @FindBy(xpath = "//*[@id='stl-jam-cal-nextMonth']")
    WebElement next_month;

    @FindBy(xpath = "//div[contains(@id,'-col-button-wrapper')]")
    WebElement search_btn;


    @FindBy(xpath = "//*[@id='searchResultsList']/div[1]/div[1]")
    WebElement result_exists;

    //@FindBy(xpath = "//*[@id='searchResultsList']/div[1]/div[1]//div[contains(@id,'mainInfo')]//li[1]//div[contains(@id,'-select-leg-column')]")
    @FindBy(xpath = "//div[contains(@id,'mainInfo')]//label[1][contains(@id,'-select-leg-checkbox-label')]")
    List<WebElement> result_checkBox;

   // @FindBy(xpath = "//*[@id='searchResultsList']/div[1]/div[1]//div[contains(@id,'mainInfo')]//li[1]//div[contains(@id,'-origin-airport')]/span")
    @FindBy(xpath = " //div[@class='container']/div[5]//span[1]")
    List<WebElement> result_From;


    //String dataStartDate= "06/12/2021" ;//"mm/dd/yyyy"
   // String dataReturnDate= "08/4/2021" ;//"mm/dd/yyyy"
    String month="";
    int monthCurrStartDiff = 0;
    int monthReturnStartDiff = 0;
    WebDriverWait wait = new WebDriverWait(driver, 20);
    public boolean clickOnFligths() throws Exception{
        boolean bRes_Flag = false;
        //oBroUti.waitForElementVisible(driver, Flight_link, 10);

        Flight_link = wait.until(
                ExpectedConditions.elementToBeClickable(Flight_link));
        Thread.sleep(7000);
        oBroUti.ufClick(Flight_link);
        if(oBroUti.validateStrEquals(Flight_page.getText(),"Search hundreds of flight sites at once.")){
            bRes_Flag = true;
        };
        System.out.println("checked flight page");
        return bRes_Flag;
    }
    public boolean enterFrom(String from) throws Exception{
        boolean bRes_Flag = false;
        //Thread.sleep(5000);
        Flight_From_outerbox = wait.until(
                ExpectedConditions.elementToBeClickable(Flight_From_outerbox));
        oBroUti.ufClick(Flight_From_outerbox);
        //Thread.sleep(2000);
        Flight_From_outerbox.sendKeys((Keys.BACK_SPACE),(Keys.BACK_SPACE),(Keys.BACK_SPACE));
        //Thread.sleep(6000);
        Flight_From_innerbox = wait.until(
                ExpectedConditions.elementToBeClickable(Flight_From_innerbox));
        Flight_From_innerbox.click();
        //sending keys(will come from data file
        Flight_From_innerbox.sendKeys(from);
        //match the send keys with the dropdownlist //check with others for better options
        Thread.sleep(6000);
        oBroUti.waitForElementVisible(driver,airportList_From.get(0),20);
        //have to get it from data file
        int airport_Match = oBroUti.ifOptionExistsInListMatchAttribute(airportList_From,"data-apicode", from);
        if (airport_Match!=-1) {
            airportList_From.get(airport_Match).click();
            bRes_Flag = true;
        } else {
            airportList_From.get(0).click();
            bRes_Flag = true;
        }

        return bRes_Flag;
    }
    public boolean nearbyFrom() throws Exception{
        boolean bRes_Flag = false;
       // Flight_page.click();
        //Thread.sleep(20000);
        nearby_from_click = wait.until(
                ExpectedConditions.elementToBeClickable(nearby_from_click));
        nearby_from_click.click();
        //Flight_From_innerbox.sendKeys(" ");

        Thread.sleep(3000);
        if(nearby_From.isDisplayed()){
            nearby_From.click();
            bRes_Flag = true;
        }
        Flight_page.click();
        return bRes_Flag;
    }
    public boolean enterTo(String to) throws Exception{
        boolean bRes_Flag = false;
        Flight_To_outerbox = wait.until(
                ExpectedConditions.elementToBeClickable(Flight_To_outerbox));
        oBroUti.ufClick(Flight_To_outerbox);
        Thread.sleep(2000);
        Flight_To_outerbox.sendKeys((Keys.BACK_SPACE),(Keys.BACK_SPACE),(Keys.BACK_SPACE));
        //Thread.sleep(10000);
        Flight_To_innerbox = wait.until(
                ExpectedConditions.elementToBeClickable(Flight_To_innerbox));
        Flight_To_innerbox.click();
        //sending keys(will come from data file
        //Thread.sleep(6000);
        Flight_To_innerbox.sendKeys(to);
        //match the send keys with the dropdownlist //check with others for better options
        Thread.sleep(8000);
        //have to get it from data file
        oBroUti.waitForElementVisible(driver,airportList_To.get(0),20);
        int airport_To_Match = oBroUti.ifOptionExistsInListMatchAttribute(airportList_To,"data-apicode", to);
        if (airport_To_Match!=-1) {
            airportList_To.get(airport_To_Match).click();
            bRes_Flag = true;
        } else {
            airportList_To.get(0).click();
            bRes_Flag = true;
        }
        return bRes_Flag;
    }
    public boolean nearbyTo() throws Exception{
        boolean bRes_Flag = false;
       // Thread.sleep(10000);
        nearby_to_click = wait.until(
                ExpectedConditions.elementToBeClickable(nearby_to_click));
        nearby_to_click.click();
        nearby_To = wait.until(
                ExpectedConditions.elementToBeClickable(nearby_To));
        nearby_To.click();
        /*
        Thread.sleep(3000);
        if(nearby_To.isDisplayed()){
            nearby_To.click();
        }*/
        Flight_page.click();
        Thread.sleep(2000);
        bRes_Flag = true;
        return bRes_Flag;

    }
    public boolean enterDate(String strDate,String retDate) throws Exception{
        boolean bRes_Flag = false;
        Thread.sleep(7000);
        dates_box = wait.until(
                ExpectedConditions.elementToBeClickable(dates_box));
        dates_box.click();
        String currDate = oCommUtil.getCurrentDate();
        String[] arrCurrDatepart = currDate.split("/");
        String[] arrStartDatepart = strDate.split("/");
        String[] arrReturnDatepart = retDate.split("/");
        monthCurrStartDiff =Integer.valueOf(arrStartDatepart[0]) - Integer.valueOf(arrCurrDatepart[0]) ;
        monthReturnStartDiff = Integer.valueOf(arrReturnDatepart[0]) - Integer.valueOf(arrStartDatepart[0]);
        //Thread.sleep(7000);
        if(monthCurrStartDiff>1){
            for(int i=1;i<monthCurrStartDiff;i++){
                Thread.sleep(3000);
                next_month = wait.until(
                        ExpectedConditions.elementToBeClickable(next_month));
                next_month.click();
            }
        }
        month = oCommUtil.monthNumberToName(arrStartDatepart[0]);
        String date_start = month + " " + arrStartDatepart[1];
        //System.out.println("date_start" + date_start);
        WebElement stDate = driver.findElement(By.xpath("//div[@aria-label='"+date_start+"']"));
        //Thread.sleep(3000);
        stDate = wait.until(
                ExpectedConditions.elementToBeClickable(stDate));
        stDate.click();
        Thread.sleep(2000);

        if(monthReturnStartDiff>0){
            for(int i=0;i<monthReturnStartDiff;i++){
                Thread.sleep(3000);
                next_month = wait.until(
                        ExpectedConditions.elementToBeClickable(next_month));
                next_month.click();
            }
        }
        Thread.sleep(7000);
        month = oCommUtil.monthNumberToName(arrReturnDatepart[0]);
        String date_return = month + " " + arrReturnDatepart[1];
        WebElement rDate = driver.findElement(By.xpath("//div[@aria-label='"+ date_return +"']"));
        Thread.sleep(7000);
        rDate = wait.until(
                ExpectedConditions.elementToBeClickable(rDate));
        rDate.click();
        if(rDate.isSelected()) {
            bRes_Flag = true;
        }
        return bRes_Flag;
    }
    public boolean kSearch() throws Exception{
        boolean bRes_Flag = false;
        Thread.sleep(3000);
        search_btn.click();
        bRes_Flag = true;
        if(search_btn.isSelected()){
            bRes_Flag=true;
        }
        return bRes_Flag;

    }
    public boolean result(String from) throws Exception{
        boolean bRes_Flag = false;
        Thread.sleep(5000);
        if (result_From.size()>0) {
            result_exists = wait.until(
                    ExpectedConditions.elementToBeClickable(result_exists));
            if(result_exists.isDisplayed())
            {
                Thread.sleep(15000);
                result_checkBox.get(0).click();
                Thread.sleep(5000);

                    if (result_From.get(0).getText().equalsIgnoreCase(from)) {
                        bRes_Flag = true;

                        System.out.println("test Passed");
                    }
                }


        }
        return bRes_Flag;
    }
}
