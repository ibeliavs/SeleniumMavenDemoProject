package com.qa.freecrm.base;

import com.qa.freecrm.utility.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public static WebDriver driver;
    public static Properties properties;

    public TestBase(){
        try {
            FileInputStream stream = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/java/com/qa/freecrm/config/config.properties"
            );
            properties = new Properties();
            properties.load(stream);
        } catch (Exception e){
            System.out.println("Unable to load Config Properties File " + e.getMessage());
        }
    }
    public static void initialization(){
        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            System.out.println("We do not support this browser");
        }

        //driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_TIME));
        driver.get(properties.getProperty("url"));
    }
}
