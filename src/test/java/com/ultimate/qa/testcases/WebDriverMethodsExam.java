package com.ultimate.qa.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebDriverMethodsExam {
    WebDriver driver;
    WebElement element;
    JavascriptExecutor javascriptExecutor;

    @Before
    public void setUpTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        javascriptExecutor = (JavascriptExecutor) driver;
    }
    @After
    public void tearDown(){
       driver.quit();
    }

    @Test
    public void actionFocusTest(){
        driver.get("https://example.cypress.io/commands/actions");
        element = driver.findElement(By.id("password1"));
        //we can use element.click(); to set focus instead of using Actions
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        String attribute = element.findElement(By.xpath("//*[@for='password1']"))
                .getAttribute("style");
        assertTrue(attribute.contains("color: orange;"));
    }

    @Test
    public void testCookies(){
        driver.get("https://example.cypress.io/commands/cookies");
        //Set a cookie clicking buttons
        // Get a cookie named "token"
        // Assert that a cookie has value 123ABC
        driver.findElement(By.cssSelector("(//button[text()='Set Cookie'])[1]")).click();
        Cookie token = driver.manage().getCookieNamed("token");
        assertEquals("123ABC",token.getValue());
    }
    @Test
    public void scrollToBottom() throws InterruptedException {
        driver.navigate().to("https://ultimateqa.com/complicated-page");

        //javascriptExecutor.executeScript("alert('Hi')");
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollTop)");

        // get page title
        String script = "return document.title;";
        // javascriptExecutor.executeScript(script);
        String title = (String) javascriptExecutor.executeScript("return document.title;");
        System.out.println(title);

        //scroll
        element = driver.findElement(By.xpath("(//button[@name='et_builder_submit_button'])[2]"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

        //highlight button
        javascriptExecutor.executeScript("arguments[0].style.border='5px solid red'", element);

    }
}
