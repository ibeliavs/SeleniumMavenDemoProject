package com.selenium.basic.examples.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestQuze {

    @Test
    public void test1(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        Assert.assertEquals(driver.getTitle(),"Google");

        driver.findElement(By.name("ssss")).click();
        driver.findElement(By.name("sss")).sendKeys("sss");
        driver.get("http://google.com/");
        driver.quit();
    }
}
