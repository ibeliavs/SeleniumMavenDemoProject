package com.ilia.driver;

import com.ilia.utils.ReadPropertyFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public final class Driver {
    private Driver() {
    }

    public static void initDriver() throws Exception {
        if (Objects.isNull(DriverManager.getDriver())) {
//        System.setProperty("webdriver.chrome.driver", FrameworkConstants.getChromeDriverPath());
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            DriverManager.setDriver(driver);
            DriverManager.getDriver().get(ReadPropertyFile.getValue("Lrl"));
        }
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
