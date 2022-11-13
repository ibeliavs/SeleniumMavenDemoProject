package com.selenium.basic.examples;

import com.company.example.pages.BaseClass;
import com.company.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

// Using Listeners on class level
// another way implement un suite level through testng xml
//@Listeners(ui.TestNGListener.class)
public class ListenerDemoTest  extends BaseClass {
    @Test
    public void launchApp(){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginToPage(excel.getStringData(0,0,0), excel.getStringData(0,0,1));

        WebElement element = driver.findElement(By.cssSelector("#item_4_title_link > div"));
        Assert.assertEquals(element.getText(), "Sauce Labs Backpackrrrr");
    }

}
