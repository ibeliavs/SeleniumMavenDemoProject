package com.ultimate.qa.csvDataDriven;

import org.openqa.selenium.WebDriver;

public class CSVExercise {
    WebDriver driver;

//    public static String[][] readCsvFile() throws IOException {
//        CSVReader csvReader = new CSVReader(new FileReader("src/test/java/csvDataDriven/Prices.csv"), ',', '"', 1);
//        String[] line;
//        List<String[]> userList = new ArrayList<>();
//        while ((line = csvReader.readNext()) != null) {
//            userList.add(line);
//        }
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
//
//    private WebDriver getDriver() {
//        return new ChromeDriver();
//    }
//
//    @BeforeMethod
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = getDriver();
//        driver.get("https://www.saucedemo.com/");
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
//
////    @MethodSource("readCsvFile")
////    @ParameterizedTest
//    public void loginTest(String itemName, String price) {
//        // The xPath for the element is a bit complex. Basically what we are looking for is the div that contains the product price
//        // and has the same ancestor as the div that contains the itemName (so we know we are checking the price for our specific product)
//        String xPath = String.format
//                ("//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']",
//                        itemName);
//        WebElement priceElement = driver.findElement(By.xpath(xPath));
//        System.out.println(priceElement.getText());
//        Assert.assertEquals(priceElement.getText(), price);
//    }
}
