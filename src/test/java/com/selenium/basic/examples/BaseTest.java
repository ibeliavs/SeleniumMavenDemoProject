package com.selenium.basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties locators =  new Properties();
    public static FileReader configReader;
    public static FileReader locatorsReader;

    //usually BeforeSuit
    @BeforeMethod
    public void setUp() throws IOException {
        if (driver == null) {
            //fileReader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
            configReader = new FileReader("src/test/resources/configfiles/config.properties");
            config.load(configReader);

            locatorsReader = new FileReader("src/test/resources/configfiles/locators.properties");
            locators.load(locatorsReader);
        }


        if(config.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(config.getProperty("url"));
        }
        else if (config.getProperty("firefox").equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(config.getProperty("url"));
        }
        else if (config.getProperty("browser").equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get(config.getProperty("url"));
        }
        else if (config.getProperty("browser").equalsIgnoreCase("safari")){
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static class ReadPropertyFile {
        public static void main(String[] args) {
            FileReader fr;
            try {
                fr = new FileReader("src/test/resources/configfiles/config.properties");
                Properties properties = new Properties();
                properties.load(fr);
                System.out.println(properties.getProperty("browser"));
                System.out.println(properties.getProperty("url"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class ReadXLSData {
        @DataProvider(name = "xlsdata")
        public Object[][] getData(Method method) throws IOException {
            //Using test name same as Excel sheet name
            String excelSheetName = method.getName();

            File file = new File("src/test/resources/testdata/testdata.xlsx");
            // for windows use \\ as separator \\src\\test\\resources\\testdata\\testdata.xlsx
            //File file1 = new File(System.getProperty("user.dir") + "/src/test/resources/testdata/testdata.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(fileInputStream);

            Sheet sheetName = wb.getSheet(excelSheetName);
            int totalRows = sheetName.getLastRowNum();
            //System.out.println(totalRows);

            Row rowCells = sheetName.getRow(0);
            int totalCols = rowCells.getLastCellNum();
           // System.out.println(totalCols);

            DataFormatter format = new DataFormatter();

            Object[][] testData = new Object[totalRows][totalCols];
            //start from 1 if we have header
            for (int i = 1; i <= totalRows; i++) {
                for (int j = 0; j < totalCols; j++) {
                    testData[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
                    //System.out.println(testData[i - 1][j]);
                }
            }

            return testData;
        }

    }
}
