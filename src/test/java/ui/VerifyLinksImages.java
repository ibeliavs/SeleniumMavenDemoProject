package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VerifyLinksImages {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyLinksTest(){
        driver.get("https://amazon.ca/");

        List<WebElement> listOfLinks = driver.findElements(By.tagName("a"));
        int linkCount = listOfLinks.size();
        System.out.println("Total size of Links: " + linkCount);

        List<String> urlList = new ArrayList<>();
        for(WebElement link : listOfLinks){
            //long ways to check one by one 3 minutes
           // verifyLinks(link.getAttribute("href"));

            //better way is to store links in list
           urlList.add(link.getAttribute("href"));
        }

        //Java 8 feature
        //sequence 159 sec
        //urlList.stream().forEach(e ->verifyLinks(e));
        // then execute in parallel mode 16 sec
        urlList.parallelStream().forEach(e -> verifyLinks(e));
    }

    @Test
    public void verifyImageTest(){

        driver.get("https://www.google.com/");

        // Storing all elements with img tag in a list of WebElements
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Total number of Images on the Page are " + images.size());


        //checking the links fetched.
        for(int i = 0; i < images.size(); i++) {
            WebElement image= images.get(i);
            String imageURL= image.getAttribute("src");
            System.out.println("URL of Image " + (i+1) + " is: " + imageURL);
            verifyLinks(imageURL);

            //Validate image display using JavaScript executor
            try {
                boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);", image);
                if (imageDisplayed) {
                    System.out.println("DISPLAY - OK");
                }else {
                    System.out.println("DISPLAY - BROKEN");
                }
            }
            catch (Exception e) {
                System.out.println("Error Occurred");
            }
        }
    }

    public void verifyLinks(String linkUrl)
    {
        if( linkUrl == null || linkUrl.isEmpty())
            System.out.println("URL is empty." + linkUrl);

        try
        {
            URL url = new URL(linkUrl); //convert string to url format

            //Now we will be creating url connection and getting the response code
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.connect();

            //A URL that has a 2xx HTTP status code is valid and URLs have 4xx and 5xx HTTP status codes are invalid.
            if(conn.getResponseCode() >= 400)
            {
                System.out.println("HTTP STATUS - " + conn.getResponseMessage() + "is a broken link " + linkUrl);
            } else {
                //Fetching and Printing the response code obtained
                System.out.println("HTTP STATUS - " + conn.getResponseMessage() + "is a valid link " + linkUrl);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
