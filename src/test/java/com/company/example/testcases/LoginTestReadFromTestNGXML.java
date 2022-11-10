package com.company.example.testcases;

import com.company.example.utility.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestReadFromTestNGXML {
    WebDriver driver;

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Run test from Reading Browser Data from readingparameters.xml file

    @Parameters({"browser", "appURL"})
    @Test
    public void test1(String browser, String appURL) {

        driver = BrowserFactory.startApplication(browser, appURL);
    }
}
