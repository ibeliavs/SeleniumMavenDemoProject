package com.ultimate.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ultimate.qa.pages.LoginPage;
import com.ultimate.qa.pages.ProductsPage;
import com.ultimate.qa.pages.ShoppingCartPage;

import static org.junit.Assert.assertTrue;

public class ShoppingCartTest extends BaseTest {
    @Test
    public void shouldHaveItemInCartPage()
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        Assert.assertTrue(loginPage.isLoaded());

        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isLoaded());

        productsPage.Inventory.addRandomItemToCart();
        Assert.assertTrue(productsPage.ShoppingCart.hasItem(1));

        productsPage.ShoppingCart.open();
        Assert.assertEquals(new ShoppingCartPage(driver).getItemCount(),1);
    }
}
