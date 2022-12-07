package com.selenium.basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableTest {
    private WebDriver driver;

    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
    //Mark Raj as present
    @Test
    public void selectCheckBoxInTableTest(){
        driver.get("https://letcode.in/table");
        WebElement table = driver.findElement(By.cssSelector("table#simpletable>tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(int i=0; i < rows.size(); i++){
            List<WebElement> cols  = rows.get(i).findElements(By.tagName("td"));
            if(cols.get(1).getText().equals("Raj")){
                cols.get(3).findElement(By.tagName("input")).click();
                break;
            }
        }

        //another way
        driver.findElement(By.xpath("//td[contains(text(), 'Man')]//following-sibling::td[2]//input")).click();
    }

    //Add all the prices and check if the total is correct
    @Test
    public void checkTotalInTable(){
        driver.get("https://letcode.in/table");
        WebElement table = driver.findElement(By.xpath("//table[@id='shopping']//child::tbody"));
        List<WebElement> tr = table.findElements(By.tagName("tr"));
        int sum = 0;
        for(WebElement el: tr){
            sum+= Integer.parseInt(el.findElements(By.tagName("td")).get(1).getText());
        }

        String total = table.findElement(By.xpath("//tfoot//b")).getText();
        Assert.assertEquals(sum,Integer.parseInt(total));
    }

    @Test
    public void testDynamicTable(){
        driver.get("https://letcode.in/table");
        WebElement table = driver.findElement(By.xpath("//table[contains(@class,'mat-sort table')]"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(int i = 0; i < rows.size(); i++){
            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            String results = " ";
            for(int j= 0; j < cols.size(); j++){
                //System.out.println(cols.get(j).getText());
                results += cols.get(j).getText() + " ";
            }
            System.out.println(results);
        }
    }

    @Test
    public void navigationTest(){
        driver.navigate().to("https://letcode.in/buttons");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        
        driver.findElement(By.id("home")).click();
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }

    @Test
    public void staticWebTableBase(){
        driver.get("https://www.selenium.dev/ecosystem/");
        // How many rows and columns
        int rows = driver.findElements(By.xpath("(//table[@class='table'])[1]/tbody/tr")).size();
        int cols= driver.findElements(By.xpath("(//table[@class='table'])[1]/thead/tr/th")).size();

        //Retrieve the specific row/column data
        String value = driver.findElement(By.xpath("(//table[@class='table'])[1]/tbody/tr[1]/td[1]")).getText();

        //Retrieve all the data from the table parametrizing xpath
        for(int r= 1; r<= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                String data = driver.findElement(By.xpath("(//table[@class='table'])[1]/tbody/tr[" + r + "]/td[" + c + "]")).getText();
                System.out.println(data);
            }
        }
    }


    @Test
    public void sortingAdvancedWebTable(){
        driver.get("https://letcode.in/table");

        WebElement table = driver.findElement(By.xpath("//table[contains(@class,'mat-sort table')]"));

        List<TableData> list = new ArrayList<>();

        for (int i = 1; i < 6; i++){
            WebElement fat = driver.findElement(By.xpath("//table[contains(@class,'mat-sort table')]/tr["+ i +"]/td[3]"));
            WebElement desert = driver.findElement(By.xpath("//table[contains(@class,'mat-sort table')]/tr[" + i +"]/td[1]"));

            list.add(new TableData(Integer.parseInt(fat.getText()), desert.getText()));
        }
        System.out.println("Before sorting" + list);
        Collections.sort(list);
        System.out.println("After sorting" + list);

        list.forEach(i -> {
            System.out.println("Fat: " + i.fat + " Desert: " + i.dessert);
        });

    }

    class TableData implements Comparable<TableData> {
        Integer fat;
        String dessert;

        public TableData(Integer fat, String dessert) {
            this.fat = fat;
            this.dessert = dessert;
        }

        @Override
        public String toString() {
            return "TableData{" +
                    "fat=" + fat +
                    ", dessert='" + dessert + '\'' +
                    '}';
        }

        @Override
        public int compareTo(TableData data) {
            if(this.fat < data.fat){
                return -1; //Check for Ascending order
            } else if (this.fat > data.fat) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}















