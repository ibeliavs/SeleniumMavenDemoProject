package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.Zip;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;

public class ReadWrite {
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
    public void readFromProperties(){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/properties/testdata.properties");
            properties.load(fileInputStream);
            String browser = properties.getProperty("browser");
            String url = properties.getProperty("url");

            System.out.println(browser);
            System.out.println(url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void writeToProperties(){
        Properties properties = new Properties();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/test/resources/properties/customer.properties");
            properties.setProperty("testdata", "12233344");
            properties.store(fileOutputStream, null);

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    @Test
    public void uploadFileTest(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //driver.manage().window().maximize();
        WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        File file = new File("./PastSimple.png");

        uploadFile.sendKeys(file.getAbsolutePath());
        System.out.println(file.getAbsolutePath());
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        boolean displayed = driver.findElement(By.xpath("//span[.='Delete']")).isDisplayed();
        Assert.assertTrue(displayed);
    }

    @Test
    public void zipFileTest(){
        try {
            //using selenium class
            String zip = Zip.zip(new File("/src/test/resources/screenshots"));

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("images.zip"), 10000);
            byte[] decode = Base64.getDecoder().decode(zip);
            stream.write(decode);
            stream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void unzipFileTest(){
        try {
            //using selenium class
            Zip.unzip(new FileInputStream("images.zip"), new File("/src/test/resources/screenshots"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
