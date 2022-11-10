package ebanking.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

//To Do
public class Utility {

    //TODO alets, frames, windows, Sync issue, javascript executor
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String screenshotPath = System.getProperty("user.dir") + "/src/test/resources/screenshots/" + screenshotName + "_" + getCurrentDateTime() + ".png";
        try {
            //Interface casting because we cant create object from interface
            //TakesScreenshot ts = (TakesScreenshot) driver;
            //Takes screenshot and keeps it in buffer in file format
            //File srcFile = ts.getScreenshotAs(OutputType.FILE);
            // copy file from one location to other
            //FileHandler.copy(srcFile, new File("src/test/resources/screenshots/" + screenshotName + screenshotDate + ".png"));

            //short form
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }

        return screenshotPath;
    }
//TODO
//    // We use throws IOException because this is the exception we might expect if the file is not found.
//    // Alternatively, we can wrap all our code in a try-catch block, and cath the IOException.
//    public static String[][] readCsvFile() throws IOException {
//        // Here we actually read the CSV file.
//        // We need to provide the file location, the separator used, the quotes character and the starting line.
//        // The file is read as 0-based, and since we have a header line, we wil start at the second line, which means we pass 1 as parameter
//        CSVReader csvReader = new CSVReader(new FileReader("src/test/java/csvDataDriven/Users.csv"), ',', '"', 1);
//        String[] line;
//        // Here we create a string list, which one element for each line of the CSV
//        List<String[]> userList = new ArrayList<>();
//        while ((line = csvReader.readNext()) != null) {
//            userList.add(line);
//        }
//        // After we get the number of rows and columns, we create a new array which has 2 string values,
//        // which will be the string data (username and password) from our file:
//        int rows = userList.size();
//        int cols = userList.get(0).length;
//        String[][] dataFromCSV = new String[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            String[] eachRow = userList.get(i);
//            for (int j = 0; j < cols; j++) {
//                dataFromCSV[i][j] = eachRow[j];
//            }
//        }
//        return dataFromCSV;
//    }

    public static String getCurrentDateTime() {
        DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return customFormat.format(currentDate);
    }
    public static boolean waitUntilDisplayed(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }

    public static WebElement waitForElementPresent(WebDriver driver, By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitForElementWithFluentWait(WebDriver driver, final By locator){
        // Declaring
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .withMessage("Any Custom message")
                .ignoring(NoSuchElementException.class);

        // Usage Fluent wait
        WebElement element = wait1.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(locator);
            }
        });
        return element;
    }

    public static void handelFrames() {

    }

    public static void handelAlerts() {

    }
}
