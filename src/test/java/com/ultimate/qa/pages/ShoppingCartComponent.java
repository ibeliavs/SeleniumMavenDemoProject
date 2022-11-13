package com.ultimate.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartComponent extends BasePage{
    public ShoppingCartComponent(WebDriver driver) {
        super(driver);
    }
    public boolean hasItem(int numberOfItems) {
       String numberOfItemsInCart = driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText();
       return Integer.parseInt(numberOfItemsInCart) == numberOfItems;
    }

    public void open(){
        open("cart.html");
    }
}
