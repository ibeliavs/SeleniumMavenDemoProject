package com.qa.freecrm.testcases;

import com.qa.freecrm.base.TestBase;
import com.qa.freecrm.pages.HomePage;
import com.qa.freecrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;

    public LoginPageTest(){
        super();
    }
    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void loginPageTitle(){
        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title, "Free CRM software for any business with sales, support and customer relationship management");
    }

    @Test(priority = 2)
    public void loginTest(){
        homePage = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
    }

}