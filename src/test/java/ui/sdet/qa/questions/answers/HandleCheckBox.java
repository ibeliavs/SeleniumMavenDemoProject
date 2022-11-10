package ui.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HandleCheckBox {
    RemoteWebDriver driver;
    WebElement element;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkBoxSelectTest() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");
        // select checkbox
        element = driver.findElement(By.id("monday"));
        element.click();
        Assert.assertTrue(element.isSelected());
    }

    @Test
    public void selectAllTest() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");
        //select all checkBox
        List<WebElement> list = driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
        for (WebElement item:list) {
            item.click();
        }
    }

    @Test
    public void selectMultipleTest() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");
        //select multiple checkBox Monday and Sunday
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
        for (WebElement checkbox:checkboxes) {
            String checkboxId = checkbox.getAttribute("id");
            if(checkboxId.equalsIgnoreCase("monday")
                    || checkboxId.equalsIgnoreCase("sunday") ){
                checkbox.click();
            }
        }
    }

    @Test
    public void selectSomeTest() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");
        //select last 2 or 3 checkBoxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
        int total = checkboxes.size();
        for (int i=total-2; i< total; i++) {
            checkboxes.get(i).click();
        }
    }
}
