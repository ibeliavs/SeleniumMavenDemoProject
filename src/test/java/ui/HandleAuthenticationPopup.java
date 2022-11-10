package ui;

import com.company.example.utility.Helper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class HandleAuthenticationPopup {
    WebDriver driver;
    @BeforeMethod
    public void setupTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }
    @AfterMethod
    public void tearDown(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            Helper. captureScreenshot(driver, result.getName());
        }
       driver.quit();
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

    @Test
    public void screenshots(){
        driver.get("https://demoqa.com/select-menu");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // screenshot of visible portion
        Date currentDate = new Date();
        String fileName = currentDate.toString().replace(" ", "-").replace(":", "-");

        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            //FileHandler.copy(screenshotAs, new File("./Screenshots/img1.png"));
            FileUtils.copyFile(screenshotAs, new File("./Screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // screenshot of element
        WebElement element = driver.findElement(By.id("withOptGroup"));
        File elementScreenshot = element.getScreenshotAs(OutputType.FILE);
        try{
            //FileHandler.copy(elementScreenshot, new File("./Screenshots/img2.png"));
            FileUtils.copyFile(elementScreenshot, new File("./Screenshots/"  + fileName + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void handlingSSlErrors(){
        //Global profile
        DesiredCapabilities handleSSLError = new DesiredCapabilities();
        handleSSLError.setAcceptInsecureCerts(true);
//        handleSSLError.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//        handleSSLError.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(handleSSLError);

        WebDriver chDriver = new ChromeDriver(chromeOptions);

        chDriver.get("https://expired.badssl.com/");

    }
}
