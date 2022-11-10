package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestNGDataDrivenDemo {
    WebDriver driver;
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(String browser) {
        WebDriverManager.chromedriver().setup();
        System.out.println(browser);
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // The test method is the same as in the previous examples,
    // except with TestNG we pass the data provider method like this:
    @Test (dataProvider = "provideProductData")
    public void priceCheckTest(String itemName, String price) {
        // The xPath for the element is a bit complex. Basically what we are looking for is the div that contains the product price
        // and has the same ancestor as the div that contains the itemName (so we know we are checking the price for our specific product)
        String xPath = String.format
                ("//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']",
                        itemName);
        WebElement priceElement = driver.findElement(By.xpath(xPath));
        System.out.println(priceElement.getText());
        Assert.assertEquals(priceElement.getText(), price);
    }

    // This time the objects returned have 2 values:
    // One for the product name, and one for the price
    @DataProvider(name = "provideProductData")
    public Object[][] provideProductData() {
        return new Object[][]{
                {"Sauce Labs Backpack", "$29.99"},
                {"Sauce Labs Bike Light", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "$15.99"},
                {"Sauce Labs Fleece Jacket", "$49.99"},
                {"Sauce Labs Onesie", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99"}};
    }
}
