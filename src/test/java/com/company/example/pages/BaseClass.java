package com.company.example.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.company.example.utility.ConfigDataProvider;
import com.company.example.utility.BrowserFactory;
import com.company.example.utility.ExcelDataProvider;
import com.company.example.utility.Helper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BaseClass {
    public WebDriver driver;
    public ExcelDataProvider excel;
    public ConfigDataProvider config;
    public ExtentReports report;
    public ExtentTest testLogger;

    @BeforeSuite
    public void setUpSuite() {
        excel = new ExcelDataProvider();
        config = new ConfigDataProvider();

        //To generate output in console we can use system.out.println() or log4j or
        Reporter.log("Setting up reports test can be started", true);

        //reports
        ExtentSparkReporter spark = new ExtentSparkReporter("src/test/resources/reports/Report_" + Helper.getCurrentDateTime()+ ".html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");

        report = new ExtentReports();
        report.attachReporter(spark);
    }
//    @Parameters({"browser", "url"})
//    @BeforeClass
//    public void setUp(String browser, url){
//    System.out.println("Browser from maven pom.xml file");
//    driver = BrowserFactory.startApplication(browser, url);
//}

    @BeforeClass
    public void setUp(){
        driver = BrowserFactory.startApplication(config.getBrowser(), config.getURL());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    //After each test we check result from interface ITestResult
    @AfterMethod
    public void tearDown(ITestResult result)
    {
        String screenshotPath = Helper.captureScreenshot(driver, result.getName());

        if(result.getStatus() == ITestResult.FAILURE) {
            testLogger.fail("Test failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            testLogger.pass("Test passed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            testLogger.pass("Test skip",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        report.flush();
    }
}
