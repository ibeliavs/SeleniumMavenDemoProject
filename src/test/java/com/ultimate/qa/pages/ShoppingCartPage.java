package com.ultimate.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShoppingCartPage extends BasePage{
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        List<WebElement> carItems;
        try
        {
            carItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("cart_item")));
        }
        catch(TimeoutException e){
            return 0;
        }
        return carItems.size();
    }

    public void open(){
        open("cart.html");
    }
}
