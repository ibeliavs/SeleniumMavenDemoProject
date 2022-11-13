package com.ultimate.qa.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;


public class WebDriverMethodQuizTest {
    private WebDriver driver;
    private WebElement element;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teatDown() {
        driver.quit();
    }

    @Test
    public void quizOneTest() {
        /**
         * Select option 1 from dropdown
         * Assert that option 1 is selected
         * Assert that option 2 is Not Selected
         */
        driver.get("https://the-internet.herokuapp.com/dropdown");
        element = driver.findElement(By.id("dropdown"));
        WebElement option1 = element.findElement(By.xpath("//option[text()='Option 1']"));
        WebElement option2 = element.findElement(By.cssSelector("option[value='2']"));
        option1.click();
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
    }

    @Test
    public void hoverTest() {
        /**
         * Hover over the first image
         * Assert that on hover there is text that is displayed below "name: user1"
         */
        driver.get("https://the-internet.herokuapp.com/hovers");
        element = driver.findElement(By.cssSelector("div.figure"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element = driver.findElement(By.xpath("//*[text()='name: user1']"));
        assertTrue("Text name: user1 should be displayed ", element.isDisplayed());
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

        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("You selected a context menu"));
        alert.accept();
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
        actions.sendKeys(Keys.ARROW_RIGHT).perform();
        WebElement result = driver.findElement(By.id("result"));
        assertEquals("Clicked right arrow key","You entered: RIGHT", result.getText());
    }

    @Test
    public void getCSSValueTest() {
        /**
         * find element with text "Clickable Icon"
         * assert href attribute = https://ultimateqa.com/link-success/
         * Get CSS value: "background-origin"
         * Assert that it equals "padding-box"
         */
        //driver.navigate().to("https://ultimateqa.com/simple-html-elements-for-automation/");
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        element = driver.findElement(By.linkText("Clickable Icon"));
        assertEquals("https://ultimateqa.com/link-success/", element.getAttribute("href"));
        assertEquals("padding-box", element.getCssValue("background-origin"));
    }
}
