package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterCaseTestNG {
    WebDriver driver;
    @BeforeClass
    @Parameters({"browser", "url"}) //read from testng.xml
    void setup(String browser, String url){
        WebDriverManager.chromedriver().setup();

        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }

        driver.get(url);
    }

    @AfterClass
    void teardown(){
        driver.quit();
    }

    @Test
    void login(){
        System.out.println("Test login");
    }
}
