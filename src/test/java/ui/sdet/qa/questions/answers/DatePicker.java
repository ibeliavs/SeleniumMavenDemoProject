package ui.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DatePicker {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDatePicker1() {
        driver.get("https://www.redbus.in/");
        String year = "2023";
        String month = "July";
        String date = "10";

        driver.findElement(By.id("onward_cal")).click();
        while (true) {
            String montYear = driver.findElement(By.xpath("//td[@class='monthTitle']")).getText();
            String[] arr = montYear.split(" ");
            String mon = arr[0];
            String yr = arr[1];
            // can use below concat or use as String moYe ="July 2023"
            // boolean b = montYear.equals(month.concat(" ").concat(year));
            if (mon.equalsIgnoreCase(month) && yr.equalsIgnoreCase(year))
                break;

            driver.findElement(By.xpath("//td[@class='next']")).click();

        }
        List<WebElement> alldate = driver.findElements(By.xpath("//table[@class='rb-monthTable first last']//td"));
        // Date
        for (WebElement element : alldate) {
            if (element.getText().equalsIgnoreCase(date)) {
                element.click();
                break;
            }
        }
    }

    @Test
    public void testDatePicker2() {
        driver.get("https://www.dummyticket.com/dummy-ticket-for-visa-application/");
        driver.findElement(By.id("dob")).click();

        Select mont_drp = new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
        mont_drp.selectByVisibleText("Mar");

        Select year_drp = new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")));
        year_drp.selectByVisibleText("1967");

        //Date selection
        String date = "21";
        List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//a"));
        for (WebElement el : allDates) {
            if (el.getText().equals(date)) {
                el.click();
                break;
            }
        }
    }
}
