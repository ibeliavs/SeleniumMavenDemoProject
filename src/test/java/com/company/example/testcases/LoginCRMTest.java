package com.company.example.testcases;

import com.company.example.pages.BaseClass;
import com.company.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginCRMTest extends BaseClass {
    @Test
    public void test1(){

        testLogger = report.createTest("Login to CRM");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();

        LoginPage loginPage = new LoginPage(driver);

        testLogger.info("Starting Application");
        loginPage.loginToPage(excel.getStringData("Login", 0, 0),
                              excel.getStringData(0, 0, 1));
        testLogger.pass("Login Success");
    }
}
