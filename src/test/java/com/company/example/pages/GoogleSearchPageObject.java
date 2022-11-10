package com.company.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPageObject {
    private WebDriver driver = null;
    private By textbox_search = By.name("q");
    private By button_search = By.name("btnK");
    public GoogleSearchPageObject(WebDriver driver){
        this.driver = driver;
    }
    public void setSearchText(String text){
        driver.findElement(textbox_search).sendKeys(text);
    }

    public void clickSearchButton(){
        driver.findElement(button_search).sendKeys(Keys.RETURN);
    }
}
