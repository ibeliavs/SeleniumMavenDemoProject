package ebanking.testcases;

import ebanking.utility.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseClass {
    public static WebDriver driver;
    public static Logger log = LogManager.getLogger(BaseClass.class);
    public ReadConfig config = new ReadConfig();

    public String username = config.getUserName();
    public String password = config.getPassword();
    private String baseURL = config.getApplicationURL();

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            System.out.println("We do not support this browser");
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        //driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScree(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        //Takes a screenshot and keeps it in the buffer in file format
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File targetFile = new File("src/test/resources/screenshots/" + tname + ".png");

        // copy file from one location to another
        FileUtils.copyFile(srcFile, targetFile);
    }
}
