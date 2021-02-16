package com.anamika.kayakTest;

import com.anamika.Utilities.TestBase;
import com.anamika.kayakPO.poKayak;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class kayakTest extends TestBase{
    poKayak kayak;

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @BeforeTest
    public void settingUpEnvironment() throws Exception {
        sErrorMessage = "";
        sClassNameForScreenShot = getClass().getSimpleName();
        driver.get(oCons.getAppURL());
        //driver.manage().deleteAllCookies();
        kayak = new poKayak(driver);
    }

    @AfterMethod
    public void settingReqURL() throws Exception {
        //driver.get(oCons.getAppURL());

    }
//(dataProvider="data-provider",dataProviderClass = com.anamika.dataProvider.DPclass.class)
//public void goToFlight(String from,String to,String sDate,String rDate) throws Exception
    @Test(dataProvider="data-provider",dataProviderClass = com.anamika.dataProvider.DPclass.class)
    public void goToFlight(String from,String to,String sDate,String rDate) throws Exception {
       // System.out.println(from + "====" + to + "===" + sDate + "===" + rDate);

        kayak.clickOnFligths();
        kayak.enterFrom(from);
        kayak.nearbyFrom();
        kayak.enterTo(to);
        kayak.nearbyTo();
        kayak.enterDate(sDate,rDate);
        kayak.kSearch();
        kayak.result(from);

    }
   /*@Test
    public void enterFlightFrom() throws Exception {
        if(kayak.enterFlightFromDetails())
            System.out.println("Test1");;
    }*/


}
