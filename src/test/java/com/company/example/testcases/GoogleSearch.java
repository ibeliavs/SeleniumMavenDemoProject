package com.company.example.testcases;

import com.company.example.pages.GoogleSearchPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleSearch {
    private WebDriver driver = null;

    @BeforeClass
    public void setUpClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupTest(){
        driver = new ChromeDriver();
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void searchTest(){
        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        GoogleSearchPageObject searchPageObject = new GoogleSearchPageObject(driver);
        searchPageObject.setSearchText("Ilia");
        searchPageObject.clickSearchButton();
    }
}
