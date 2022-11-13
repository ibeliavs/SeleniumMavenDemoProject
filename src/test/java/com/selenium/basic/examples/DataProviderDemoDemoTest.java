package com.selenium.basic.examples;

import com.selenium.basic.examples.common.DataProviderDemo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataProviderDemoDemoTest {
    WebDriver driver;
    @BeforeTest
    public void setupTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    //Data provider name or if not specified the method dataSet1
    @Test(dataProvider = "swagLabs", dataProviderClass = DataProviderDemo.class)
    public void testOne(String username, String password){
        System.out.println( username + "   " + password);
    }

    @Test(dataProvider = "swagLabs", dataProviderClass = DataProviderDemo.class)
    public void loginTest(String user, String password, String name){
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }
}
