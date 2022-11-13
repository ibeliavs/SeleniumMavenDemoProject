package com.selenium.basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;


public class FramesAlertHandle {
    private WebDriver driver;
    private WebElement element;

    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
       // driver.quit();
    }

    @Test(priority = 1)
    public void testBaseAlert() {
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.switchTo().frame("iframeResult");
        driver.findElement(By.xpath("//button[text()='Try it']")).click();

        String alertText = driver.switchTo().alert().getText();
        assertEquals(alertText, "Hello! I am an alert box!");

        driver.switchTo().alert().accept();

        //Switch back to parent from iframe to get parent frame title
        driver.switchTo().parentFrame();
    }

    @Test(priority = 2)
    public void testPromptAlert() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.switchTo().frame("iframeResult");
        driver.findElement(By.xpath("//button[text()='Try it']")).click();

        // Prompt Alert with input box
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.sendKeys("James Bond");//Prompt input box
        alert.accept();

        //Switch back to get title
        driver.switchTo().parentFrame();
        System.out.println(driver.getTitle());
    }

    //Simple alert OK - accept and CANSEl- dismiss
    @Test(priority = 3)
    public void testConfirmAlert() {
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//div[text()='Java Script Alert Box']//following-sibling::button")).click();

        driver.switchTo().alert().accept();//OK

        driver.findElement(By.xpath("(//button[text()='Click Me'])[2]")).click();
        driver.switchTo().alert().dismiss();//Cansel
    }

    @Test
    public void authenticationPopUpTest(){
        String username = "admin";
        String password = "admin";
        driver.get("https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String text = driver.findElement(By.xpath("//*[contains(text(), 'Congratulations')]")).getText().trim();
        Assert.assertTrue(text.contains("Congratulations! You must have the proper credentials"));

        String innerHTML = driver.findElement(By.xpath("//h3[text()='Basic Auth']")).getAttribute("innerHTML");
        Assert.assertEquals(innerHTML, "Basic Auth");
    }

    //Notification permission popup with Allow and Block buttons
    // Set options options.addArguments("--disable-notifications");
    @Test
    public void notificationsPermissionPopUpTest(){
        //To disable permission notification popup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriver driver1 = new ChromeDriver(options);

        driver1.get("https://www.redbus.in/");
        driver1.quit();
    }

    @Test(priority = 4)
    public void testWindows() {
        driver.get("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        driver.findElement(By.linkText("Follow On Twitter")).click();

        Set<String> allWindows = driver.getWindowHandles();
        List<String> windows = new ArrayList<>(allWindows);
        //switch to the child window
        driver.switchTo().window(windows.get(1));
        System.out.println(driver.getTitle());

        //Switch to the parent window
        driver.switchTo().window(parentWindow);
    }

    @Test(priority = 5)
    public void handlingWindows() {
        driver.get("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.linkText("Like us On Facebook")).click();

        Set<String> windowsHandles = driver.getWindowHandles();
        // List<String> windowsList = new ArrayList<>(windowsHandles);
        //driver.switchTo().window(windowsList.get(1));

        System.out.println(windowsHandles);
        Iterator<String> iterator = windowsHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.next();

        driver.switchTo().window(childWindow);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//span[text()='Create new account']")).click();
        System.out.println(driver.getTitle() + " - " + childWindow);
        driver.close();

        driver.switchTo().window(parentWindow);
        System.out.println(driver.getTitle() + " - " + parentWindow);
    }

    @Test(priority = 6)
    public void handlingFrames() {
        driver.get("https://letcode.in/frame");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //frame
        driver.switchTo().frame("firstFr");
        driver.findElement(By.name("fname")).sendKeys("name");
        driver.findElement(By.name("lname")).sendKeys("last name");

        // nested frame
        WebElement frameElement = driver.findElement(By.xpath("//iframe[@src='innerFrame']"));
        driver.switchTo().frame(frameElement);
        driver.findElement(By.name("email")).sendKeys("fff@kdd.com");
        //going one level up
        driver.switchTo().parentFrame();
        driver.findElement(By.name("fname")).clear();
        driver.findElement(By.name("fname")).sendKeys("first name");

        //default content - go to the main page from any frames
        driver.switchTo().defaultContent();
        driver.findElement(By.linkText("Log in")).click();
    }

    @Test
    public void windowsSwitchingTest() {
        driver.get("https://letcode.in/windows");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String originalWindow = driver.getWindowHandle();

        driver.findElement(By.id("home")).click();

        Set<String> windows = driver.getWindowHandles();
        windows.remove(originalWindow);

        String nextWindow = windows .iterator().next();
        //String nextWindow = String.valueOf(windows.iterator().next());
        driver.switchTo().window(nextWindow);
        assertEquals(driver.getTitle(), "LetCode - Testing Hub");
        driver.close();

        driver.switchTo().window(originalWindow);
        assertEquals(driver.getTitle(), "Window handling - LetCode");

    }
    // closing popup commercial windows
    @Test
    public void closingPopUpWindows() {
        driver.get("https://letcode.in/windows");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        //one way
        for (String windowName : allWindows) {
            if (!windowName.equals(originalWindow)) {
                driver.switchTo().window(windowName);
                driver.close();
            }
        }
        // or remove parent form the list
        allWindows.remove(originalWindow);
        for (String windowName : allWindows) {
            driver.switchTo().window(windowName);
            driver.close();
        }

        driver.switchTo().window(originalWindow);
    }

    @Test
    public void softAssertTesting() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        driver.get("https://letcode.in/windows");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("home")).click();

        Set<String> windows = driver.getWindowHandles();
        List<String> windowsList = new ArrayList<>(windows);

        driver.switchTo().window(windowsList.get(1));
        softAssert.assertEquals(driver.getTitle(), "LetCode - Testing Hub");
        driver.close();

        driver.switchTo().window(windowsList.get(0));
        softAssert.assertEquals(driver.getTitle(), "Window handling - LetCode", "Some message!");

        softAssert.assertAll();
    }
}