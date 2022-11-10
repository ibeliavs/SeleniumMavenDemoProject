package ui.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CaptureScreenshots {
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
    public void captureScreenshots() throws IOException {
        driver.get("https://demo.nopcommerce.com/");
        //Full Page screenshot
        //Interface casting because we can't create an object from the interface
        TakesScreenshot ts = (TakesScreenshot) driver;
        //Takes a screenshot and keeps it in the buffer in file format
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File targetFile = new File("src/test/resources/screenshots/page.png");

        // copy file from one location to another
        FileUtils.copyFile(srcFile, targetFile);

        //Selenium 4 feature
        //Screenshot of section/portion of the page
        WebElement section = driver.findElement(By.xpath("//div[@class='product-grid home-page-product-grid']"));
        File srs = section.getScreenshotAs(OutputType.FILE);
        File target = new File("src/test/resources/screenshots/sectionpage.png");
        // copy file from one location to another
         FileUtils.copyFile(srs, target);

        //Screenshot of section/portion of the page
        WebElement element = driver.findElement(By.xpath("//div[@class='header-logo']//a[1]"));
        File srsEl = section.getScreenshotAs(OutputType.FILE);
        File targetEl = new File("src/test/resources/screenshots/elementpage.png");
        // copy file from one location to another
        FileUtils.copyFile(srsEl, targetEl);
    }
}
