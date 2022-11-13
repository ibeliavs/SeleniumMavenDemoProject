package com.ultimate.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ultimate.qa.pages.LoginPage;
import com.ultimate.qa.pages.ProductsPage;

public class LoginTest extends BaseTest{
    @Test
    public void shouldOpen(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isLoaded());
    }

    @Test
    public void shouldLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isLoaded());

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(new ProductsPage(driver).isLoaded());

    }
}
