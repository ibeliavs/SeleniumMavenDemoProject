package com.selenium.basic.examples;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderCaseTestNG {

    // in the different class need to provide dataProviderClass class
    @Test(dataProvider = "LoginDataProvider", dataProviderClass = DataProviderCaseTestNG.class)
    public void logintest(String email, String password) {
        System.out.println(email + "   " + password);
    }

    // Data provider indices controls specific data sets, starts from zero.Default is all.
    @DataProvider(name = "LoginDataProvider", indices = {0, 1})
    public Object[][] getData() {
        Object[][] data = {
                {"abc@gmail.com", "abc"},
                {"xyz@gmail.com", "xyz"},
                {"xyz@gmail.com", "xyz"},
                {"xyz@gmail.com", "xyz"}
        };
        return data;
    }
}
