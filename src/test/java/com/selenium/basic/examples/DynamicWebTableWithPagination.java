package com.selenium.basic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicWebTableWithPagination {

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        BaseTest.driver = new ChromeDriver();
        BaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        BaseTest.driver.quit();
    }
    @Test
    public void test1(){
        BaseTest.driver.get("https://mdbootstrap.com/docs/b4/jquery/tables/pagination/");

        //Find total number of pages
        String text = BaseTest.driver.findElement(By.id("dtBasicExample_info")).getText();
        text = "1 to 10 of 57 (6 Pages)"; //hardcoded for testing
        System.out.println(text);
        // if Showing 1 to 10 of 57 (6 Pages)
        int totalPages = Integer.parseInt(text.substring(text.indexOf("(")+1,text.indexOf("Pages")-1).trim());

        //Find how many rows exist in each page
        for(int p= 1; p<= totalPages; p++){
            String pageNum = Integer.toString(p);
            WebElement activePage = BaseTest.driver.findElement(
                    By.xpath("//ul[@class='pagination']//li//a[text()='" + pageNum +"']"));
            System.out.println("Active page " + activePage.getText());
            activePage.click();

            int rows = BaseTest.driver.findElements(By.xpath("//table[@id='dtBasicExample']/tbody[1]/tr")).size();
            System.out.println("Number of Rows " + rows);

            // Read all the rows from each page first three column data
            for(int r = 1; r<=rows; r++){
                String name = BaseTest.driver.findElement(
                        By.xpath("//table[@id='dtBasicExample']//tbody//tr["+r+"]//td[1]")).getText();
                String position = BaseTest.driver.findElement(
                        By.xpath("//table[@id='dtBasicExample']//tbody//tr["+r+"]//td[2]")).getText();
                String office = BaseTest.driver.findElement(
                        By.xpath("//table[@id='dtBasicExample']//tbody//tr["+r+"]//td[3]")).getText();

                // Print data just for London office
                if(office.equalsIgnoreCase("London")){
                    System.out.println(name + "  " + position + "  " + office);
                }
            }
            System.out.println();
        }

    }
}
