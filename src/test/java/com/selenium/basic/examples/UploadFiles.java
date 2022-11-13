package com.selenium.basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class UploadFiles {
    RemoteWebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void uploadMultipleFilesRemote() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String url = "";
        driver = new RemoteWebDriver(new URL(url), caps);
        driver.get("https://www.naukri.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //type="file" using element for upload file
        WebElement uploadEle = driver.findElement(By.id(""));//type="file"

        // To locate local file in running remote location
        LocalFileDetector detector = new LocalFileDetector();
        ((RemoteWebElement)uploadEle).setFileDetector(detector);
        // for single file
        File file1 = detector.getLocalFile("./ssd.gif");
        uploadEle.sendKeys(file1.getAbsolutePath());

        // for multiple files use \n character
        File file3 = detector.getLocalFile("./ssd1.gif");
        File file2 = detector.getLocalFile("./ssd2.gif");
        uploadEle.sendKeys(file2.getAbsolutePath(), "\n", file3.getAbsolutePath());
    }
    @Test
    public void localRunUsingChrome(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);
        driver.get("https://www.naukri.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //type="file" using element for upload file
        WebElement uploadEle = driver.findElement(By.id(""));//type="file"
        uploadEle.sendKeys("./ssd.gif");
    }
}
