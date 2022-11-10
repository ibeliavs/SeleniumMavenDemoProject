package ui.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HandleBrowserWindows {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void handelWindowsTest(){
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabButton")))
                        .click();

        // 1. First method
        Set<String> setOfWindows = driver.getWindowHandles();
        Iterator<String> iterator = setOfWindows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();

        // 2. Second method convert to list collection ArrayList
        List<String> listOfWindowsId = new ArrayList<>(setOfWindows);
        String firstWindowId = listOfWindowsId.get(0);
        String secondWindowId = listOfWindowsId.get(1);

        //By default, driver points to the first opened window parent
        System.out.println(driver.getTitle());

        driver.switchTo().window(secondWindowId);
        System.out.println(driver.getCurrentUrl());

        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/sample");

        listOfWindowsId.remove(parentWindow);
        for(String window : listOfWindowsId ){
            driver.switchTo().window(window);
            System.out.println(driver.getTitle());
            driver.close();
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void openUrlsInNewTab(){

        driver.get("https://www.sugarcrm.com/request-demo/");
        //oen in new windows each
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void openUrlsInNewWindow(){

        driver.get("https://www.sugarcrm.com/request-demo/");
        //oen in new windows each
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://demo.nopcommerce.com/");
    }
}
