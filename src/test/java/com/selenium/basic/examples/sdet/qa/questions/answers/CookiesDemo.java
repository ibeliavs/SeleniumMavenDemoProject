package com.selenium.basic.examples.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

public class CookiesDemo {

    WebDriver driver;
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
    public void testingCookies(){
        driver.get("https://demo.nopcommerce.com/");

        /// Capture cookies from the browser
        Set<Cookie> cookies = driver.manage().getCookies();

        for(Cookie cookie: cookies) {
           // System.out.println(cookie);
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }

        // Adding cookie to browser
        Cookie cookieObj = new Cookie("my_cookei", "12345");
        driver.manage().addCookie(cookieObj);

        cookies = driver.manage().getCookies();
        for(Cookie cookie: cookies) {
            // System.out.println(cookie);
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }

        //Two way to delete cookie
       // driver.manage().deleteCookie(cookieObj);
        driver.manage().deleteCookieNamed("my_cookei");
        ///driver.manage().deleteAllCookies();

    }
}
