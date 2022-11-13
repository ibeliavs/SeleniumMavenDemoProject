package com.ultimate.qa.enumDataDriven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EnumExercise {
    WebDriver driver;

    private WebDriver getDriver() {
        return new ChromeDriver();
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = getDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
   // @EnumSource(SortValue.class)
    public void sortingTest(SortValue value) {
        Select sortDropdown =  new Select (driver.findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue(value.toString());
    }
}
