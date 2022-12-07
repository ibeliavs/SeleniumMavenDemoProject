package com.ilia.tests.koushik;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Chapter1{
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://letcode.in/signin");
    }

    @Test(priority = 1)
    public void loginTest(){
        driver.get("https://letcode.in/signin");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("ilia.beliavski@gmail.com");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Smbj6315");
        driver.findElement(By.xpath("//button[text()='LOGIN']")).click();

    }

    @Test(priority=2)
    public void inputTest(){
        driver.get("https://letcode.in/edit");
        driver.findElement(By.id("join")).sendKeys(" Yes.", Keys.TAB);
        String value = driver.switchTo().activeElement().getAttribute("value");
        String attribute = driver.findElement(By.id("getMe")).getAttribute("value");
        System.out.println(value);
        System.out.println(attribute);

    }

    @Test(priority = 3)
    public void dropdownTest(){
        driver.get("https://letcode.in/dropdowns");
        By locator = By.xpath("//select[@id='fruits']");
        WebElement drop = driver.findElement(locator);
        Select select = new Select(drop);
        select.selectByVisibleText("Apple");
        select.selectByValue("1");
        select.selectByIndex(5);
        System.out.println(select.getFirstSelectedOption().getText());

        WebElement multiDrop = driver.findElement(By.id("superheros"));
        Select heros = new Select(multiDrop);
        heros.selectByIndex(1);
        heros.selectByVisibleText("Batman");
        heros.selectByValue("ta");
        System.out.println(heros.getFirstSelectedOption().getText());
        List<WebElement> herosList = heros.getAllSelectedOptions();
        for(WebElement el:herosList){
            System.out.println(el.getText());
        }
    }
    @Test
    public void googleSearch(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://google.com");
        driver.findElement(By.xpath("//input[@class='gLFyf']")).sendKeys("Java");
        List<WebElement> listEl = driver.findElements(By.xpath(
                "//ul[@role='listbox']//li/descendant::div[starts-with(@class,'wM6W7d')]"));
        for(WebElement el:listEl){
            System.out.println(el.getText());
            if(el.getText().contains("java download")){
                el.click();
                break;
            }
        }
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
