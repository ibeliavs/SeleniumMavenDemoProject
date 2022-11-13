package com.selenium.basic.examples.sdet.qa.questions.answers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class SeleniumInterviewQuestionsAnswers {
   // WebDriver driver;
    RemoteWebDriver driver;

    @BeforeMethod
    public void setUp(){
        //Before
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void pageTest1(){
        driver.get("https://www.google.com");
        String pageSource = driver.getPageSource();

        Assert.assertTrue(pageSource.contains("<title>Google</title>"));
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.google.com/"));
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    // Check WebElement is Displayed, is Enabled and is Selected
    @Test
    public void webElementTest2(){
        driver.get("https://www.google.com");
        WebElement element = driver.findElement(By.name("q"));

        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.isEnabled());
        //isSelected() for radio, check box and drop down list
      //  Assert.assertTrue(element.isSelected());
    }

    //Navigation
    @Test
    public void navigationTest3(){
        //get accepts only one string parameter
        driver.get("https://www.google.com");
        // to accepts string and URL instance as parameter
        driver.navigate().to("https://www.cp24.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

    }

    @Test
    public void verifyLinksTest4(){
        driver.get("https://www.google.com/");
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='KxwPGc AghGtd']//a"));
        System.out.println(list.size());
        for( WebElement el : list) {
            verifyLinks(el.getAttribute("href"));
        }
    }
    @Test
    public void dropDownTest(){
        driver.get("https://www.opencart.com/index.php?route=account/register");
        WebElement selectEl = driver.findElement(By.id("input-country"));

        Select select = new Select(selectEl);
        select.selectByIndex(2);
        select.selectByValue("3");
        select.selectByVisibleText("Canada");

        String option = select.getFirstSelectedOption().getText();
        Assert.assertTrue(option.equals("Canada"));

        // Select all options
        List<WebElement> allOptions = select.getOptions();
        for (WebElement element : allOptions){
            if(element.getText().equals("Cuba")){
                element.click();
                break;
            }
        }
    }

    @Test
    public void multipleDropDown(){
        driver.get("https://www.opencart.com/index.php?route=account/register");
        WebElement element1 = driver.findElement(By.id("input-country"));
        selectOptionFromDropDown(element1,"Cuba");

        WebElement element2 = driver.findElement(By.id("input-country"));
        selectOptionFromDropDown(element1,"Canada");
    }

    public void selectOptionFromDropDown(WebElement dropDown, String value){
        Select select = new Select(dropDown);

        List<WebElement> allOptions = select.getOptions();
        for (WebElement element : allOptions){
            if(element.getText().equals(value)){
                element.click();
                break;
            }
        }
    }

    public void verifyLinks(String link){
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.connect();

            if (connection.getResponseCode() >= 400)
                System.out.println("link is broke " + connection.getResponseMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void jQueryDropDown(){
        driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");
        WebElement inputBox = driver.findElement(By.id("justAnInputBox"));
        inputBox.click();

        selectChoiceValue("choice 1");
        selectChoiceValue("choice 1", "choice 2", "choice 2 1", "choice 7");
       //TODO
        //selectChoiceValue("all");
    }

    //String ... one or many values of the same type
    public void selectChoiceValue(String... value){
        List<WebElement> choiceList= driver.findElements(By.xpath("(//div[@class='comboTreeDropDownContainer'])[1]//span"));
        if(!value[0].equalsIgnoreCase("all")) {

            for (WebElement item : choiceList) {

                String text = item.getText();

                for (String val : value) {
                    if (text.equalsIgnoreCase(val)) {
                        item.click();
                        break;
                    }
                }
            }
        } else {
            for(WebElement item:choiceList){
                item.click();
            }
        }

    }
}
