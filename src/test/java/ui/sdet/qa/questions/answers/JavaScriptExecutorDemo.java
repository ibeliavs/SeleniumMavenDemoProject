package ui.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class JavaScriptExecutorDemo {
    WebDriver driver;
    WebElement element;
    @BeforeTest
    public void setupTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testJavascriptExecutor() throws IOException {
        driver.get("https://demo.nopcommerce.com/");
        //syntax
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript(Script, args);

        // Drawing border surround element & take screenshot
        element = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        JavaScriptUtil.drawBorder(element, driver);

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File target = new File("src/test/resources/screenshots/error.png");
        FileUtils.copyFile(src, target);

        //Flash highlight  element on the page
        JavaScriptUtil.flash(element, driver);

        // Getting title of the page
        String title = JavaScriptUtil.getTitleByJS(driver);
        System.out.println(title);

        // click action if direct method not working
        element = driver.findElement(By.xpath("//a[@class='ico-register']"));
        JavaScriptUtil.clickElementByJs(element, driver);

        // generate alert
        JavaScriptUtil.generateAlert(driver, "Testing Alert");
        driver.switchTo().alert().accept();

        // Refreshing the page
        //driver.navigate().refresh();
        JavaScriptUtil.refreshBrowserByJS(driver);

        // Scroll down up
        JavaScriptUtil.scrollPageDown(driver);

        // Scroll down up
        JavaScriptUtil.scrollPageUp(driver);

        // Zoom page
        JavaScriptUtil.zoomPageByJs(driver);

    }
}
