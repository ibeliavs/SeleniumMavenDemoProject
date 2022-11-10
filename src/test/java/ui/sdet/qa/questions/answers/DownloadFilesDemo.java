package ui.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class DownloadFilesDemo {
    WebDriver driver;
    WebElement element;
    @BeforeMethod
    public void setupTest(){
        WebDriverManager.chromedriver().setup();
        String locationFiles = System.getProperty("user.dir" + "/Downloads");
        // Chrome if you want specified some not default browser location
        //  ChromeOptions, EdgeOptions,
        //  and FirefoxProfile options.setPreference("browser.helperApps.saveToDisk", "application/msword");
        //  options.setPreference("browser.helperApps.folderList", 2);
        //  options.setPreference("browser.helperApps.dir", locationFiles);

        HashMap<String, String> preferences = new HashMap<>();
        preferences.put("download.default_directory", locationFiles);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", options);

        driver = new ChromeDriver(options);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void downloadFilesDemo(){
        driver.get("https://file-examples.com/index.php/sample-documents-download/sample-doc-download/");
        element = driver.findElement(By.xpath("(//a[contains(@class,'btn btn-orange')])[1]"));
        element.click();
    }

    @Test
    public void uploadFilesDemo() throws AWTException {
        driver.get("");
        driver.findElement(By.xpath("")).click();
        // if in window you see attribute type='file'  then we can use sendKeys method
        driver.findElement(By.xpath("")).sendKeys("location of upload file and file name");

        // if not using Robot class method
        driver.findElement(By.xpath("")).click();
        // if click method not working wi using javascript executor
        WebElement button = driver.findElement(By.xpath(""));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", button);

        // Copy the pass file
        // CTRL + V
        // Enter
        Robot rb = new Robot();
        rb.delay(2000);
        // copy to buffer (Ctrl C performed)
        StringSelection ss = new StringSelection("location of upload file and file");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //Ctrl+V
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_ENTER);
        //Then Release keys
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        //ENTER
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

    }
}
