package com.ultimate.qa.enumDataDriven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EnumDataDrivenTests {
    WebDriver driver;

    private WebDriver getDriver() {
        return new ChromeDriver();
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Using the @ParameterizedTest annotation, we let our test know it will run multiple times with different parameters
    // The @EnumSource lets the test know that the source is the Enum class provided as a parameter
    @Test
   // @EnumSource(User.class)
    public void loginTest(User user) {
        driver.findElement(By.id("user-name")).sendKeys(user.toString());
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }
}
