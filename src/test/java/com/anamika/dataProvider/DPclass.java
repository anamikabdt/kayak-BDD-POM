package com.anamika.dataProvider;

import org.testng.annotations.DataProvider;

public class DPclass {
    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"SAN","NYC","04/23/2021","06/12/2021"},{"SFO","SAN","04/21/2021","07/8/2021"},{"NYC","SAN","05/3/2021","06/12/2021"}};
    }

}
