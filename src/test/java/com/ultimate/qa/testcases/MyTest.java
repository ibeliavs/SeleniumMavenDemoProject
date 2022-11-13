package com.ultimate.qa.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MyTest {
    WebDriver driver;
    WebElement element;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void elementsQuiz1(){
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element =  wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        Assert.assertTrue(element.isDisplayed());

    }

    @Test
    public void firstTest() {
        /**
         * Select option 1 from dropdown
         * Assert that option 1 is selected
         * Assert that option 2 is Not Selected
         */
        driver.get("https://the-internet.herokuapp.com/dropdown");
        element = driver.findElement(By.id("dropdown"));
        element.click();

        WebElement option1 = element.findElement(By.xpath("//*[contains(text(),'Option 1')]"));
        option1.click();
        Assert.assertTrue(option1.isSelected());

        WebElement option2 = element.findElement(By.cssSelector("option[value='2']"));
        Assert.assertFalse(option2.isSelected());

    }

    @Test
    public void hoverImage(){
        /**
         * Hover over the first image
         * Assert that on hover there is text that is displayed below "name: user1"
         */
        driver.get("https://the-internet.herokuapp.com/hovers");
        element = driver.findElement(By.xpath("//div[@class='figure'][1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        element = driver.findElement(By.xpath("//*[contains(text(), 'name: user1')]"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void rightClickTest() {
        /**
         * right click
         * close alert
         */
        driver.get("https://the-internet.herokuapp.com/context_menu");
        element = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();

        driver.switchTo().alert().accept();
    }

    @Test
    public void keyPressTest() {
        /**
         * sen right arrow key to the input box
         * assert that you got this text back "You entered: RIGHT"
         */
        driver.get("https://the-internet.herokuapp.com/key_presses");
        element = driver.findElement(By.id("target"));
        element.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(element).sendKeys(Keys.ARROW_RIGHT).pause(1000).perform();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: RIGHT");
    }
}
