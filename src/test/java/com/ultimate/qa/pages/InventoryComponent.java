package com.ultimate.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryComponent extends BasePage{
    public InventoryComponent(WebDriver driver) {
        super(driver);
    }

    public void addRandomItemToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }
}
