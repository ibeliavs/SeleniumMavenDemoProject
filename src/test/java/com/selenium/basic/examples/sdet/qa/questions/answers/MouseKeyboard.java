package com.selenium.basic.examples.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class MouseKeyboard {
    private WebDriver driver;
    private WebElement element;
    private Actions actions;

    @BeforeTest
    public void setupTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    @AfterTest
    public void tearDown(){
      driver.quit();
    }

    // include Dynamic Waits in Selenium
    // Implicitly, Explicitly and Fluent waits example
    @Test
    public void onHoverTest(){
        driver.get("https://ebay.com/");
        driver.manage().window().maximize();

       // Using implicitly wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        element = driver.findElement(By.xpath("//a[text()='Electronics']//parent::li"));
        // On Hover
        actions.moveToElement(element).perform(); //perform function doing inside build().perform();

        // Using explicitly wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='Apple iPhone']"))).click();

        // Using fluent wait
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .withMessage("Any Custom message")
                .ignoring(NoSuchElementException.class);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='Apple iPhone']"))).click();
        //To verify use Assert and verify url driver.getCurrentUrl()
    }
    @Test
    public void dragAndDropTest(){
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        element = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(element, target).perform();
    }

    @Test
    public void resizeTest(){
        driver.get("https://jqueryui.com/resizable/");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='content']/iframe")));

        element = driver.findElement(By.xpath("//*[@id='resizable']/div[3]"));
        Point location = element.getLocation();
        actions.dragAndDropBy(element, location.getX()+50, location.getY()+ 50).perform();
    }

    @Test
    public void sliderTest(){
        driver.get("https://jqueryui.com/slider/#colorpicker");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='content']/iframe")));

        element = driver.findElement(By.xpath("//*[@id='blue']/span"));
        // some more methods
        element.getLocation();
        element.getSize();

        actions.dragAndDropBy(element, 100, 125).perform();
        //move back from the current location
        actions.dragAndDropBy(element, -100, 125).perform();
    }

    @Test
    public void rightClickTest(){
        driver.get("https://jqueryui.com/slider/#colorpicker");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='content']/iframe")));
        element = driver.findElement(By.id("swatch"));
        actions.contextClick(element).perform();
    }

    @Test
    public void keyboardEventsTest() throws InterruptedException {
        driver.get("https://extendsclass.com/text-compare.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //source text area to copy for MAC use Keys.COMMAND for Windows Keys.CONTROL
        element = driver.findElement(By.xpath("//*[@id='dropZone']//pre/span"));
        actions.keyDown(element, Keys.COMMAND).sendKeys("a").sendKeys("c").perform();

        //Target area to place text
        element = driver.findElement(By.xpath("//*[@id='dropZone2']//pre/span"));
        actions.keyDown(element, Keys.COMMAND).sendKeys("a").sendKeys("v").perform();
    }

    //
    @Test
    public void keyBoardEventsFacebook(){
       driver.get("https://www.sugarcrm.com/request-demo/");
       driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

       driver.findElement(By.name("email")).sendKeys("test@test.com");

       //using tab to navigate on form Keys.Tab or Keys.ENTER to select
        Action action = actions.sendKeys(Keys.TAB)
                .sendKeys("First Name")
                .sendKeys(Keys.TAB)
                .sendKeys("Last Name")
                .sendKeys(Keys.TAB)
                //fill all form like this
                .build();
        action.perform();

    }
    @Test
    public void mouseOverTest(){
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");
        Actions actions1 = new Actions(driver);
        element = driver.findElement(By.xpath("//button[text()='Automation Tools']"));
        actions1.moveToElement(element).perform();
        driver.findElement(By.xpath("//a[text()='TestNG']")).click();
    }

    @Test
    public void captureToolTip(){
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");
        WebElement element1 = driver.findElement(By.xpath("//a[text()='TestNG']"));

        //get attribute title for tooltip
        String tooltip = element1.getAttribute("title");
    }

    @Test
    public void openLinkInNewWindow(){
        driver.get("https://www.sugarcrm.com/request-demo/");
        //Open link in new Tab
        String chordKeys = Keys.chord(Keys.CONTROL, Keys.RETURN);
        driver.findElement(
                By.xpath("(//a[contains(@class,'ubermenu-target ubermenu-item-layout-default')])[1]"))
                .sendKeys(chordKeys);
    }

}

