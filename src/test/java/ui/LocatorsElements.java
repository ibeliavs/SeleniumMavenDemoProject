package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LocatorsElements {
    private WebDriver driver;
    private WebElement element;

    //Specify parameters in xml file     <parameter name="browser" value="chrome"></parameter>
    // Run from testngCrossBrowserParameter.xml temporary comment
//    @BeforeTest
//    @Parameters("browser")
//    public void setupTest(String browser){
    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown(){
      driver.quit();
    }

    @Test(priority = 1, description = "Test 1 description")
    public void assertTest(){
        driver.get("https://saucedemo.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("secret_sauce");

        // Using Relative Locators toLeftOf(), toRightOf(), above(), below(), near()
//        driver.findElement(RelativeLocator.with(By.tagName("input"))
//                .near(By.id("lbl-email")));
//        driver.findElement(RelativeLocator.with(By.tagName("input"))
//                .above(password)).sendKeys("standard_user");
//
//        driver.findElement(RelativeLocator.with(By.tagName("input"))
//                .below(By.id("user-name"))).sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login-button']"));
        System.out.println(loginButton.getSize());
        System.out.println(loginButton.getLocation());
        System.out.println(loginButton.getTagName());
        System.out.println(loginButton.isSelected());
        System.out.println(loginButton.getAttribute("class"));
        System.out.println(loginButton.getCssValue("color"));

        // Hard assertion
        Assert.assertTrue(loginButton.isDisplayed());
        Assert.assertTrue(loginButton.isEnabled(), "This text will be displayed in failed case.");

        Assert.assertTrue(loginButton.getAttribute("class").contains("submit-button"));
        Assert.assertEquals(loginButton.getAttribute("class"),"submit-button btn_action");

        // Soft assertion
        SoftAssert assertion = new SoftAssert();
        assertion.assertEquals(loginButton.getAttribute("class"),"submit-button btn_action");
        assertion.assertAll();

        loginButton.click();
    }

    @Test(priority = 2)
    public void testDropDown(){
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.linkText("Create new account")).click();
        driver.findElement(By.name("firstname")).sendKeys("");

        // Select Standard Element
        // 1. By visible text *** This is RECOMMEND
        WebElement month = driver.findElement(By.id("month"));
        Select monthOption = new Select(month);
        monthOption.selectByVisibleText("Mar");

        // Selected Option
        String selectedMonth = monthOption.getFirstSelectedOption().getText();
        System.out.println(selectedMonth);

        // 2. By value
        WebElement birthday_day = driver.findElement(By.id("day"));
        Select dayOption = new Select(birthday_day);
        dayOption.selectByValue("21");

        // 3. By index
        WebElement year = driver.findElement(By.id("year"));
        Select yearOption = new Select(year);
        yearOption.selectByIndex(0);
        Assert.assertEquals(yearOption.getFirstSelectedOption().getText(), "2022");

        //check some options in for each
        List<WebElement> yearList = yearOption.getOptions();
        for (WebElement el: yearList){
            if(el.getText().equals("1967")) {
                System.out.println("1967 value is available");
                break;
            }
        }
        // in for loop
        for(int i=0; i<yearList.size(); i++){
            if(yearList.get(i).getText().equals("1967")){
                System.out.println("1967 value is available");
                break;
            }
        }

        //radio button search for label because all in this set buttons case has same attribute
        driver.findElement(By.xpath("//label[text()='Male']")).click();

//        // some usage of ExpectedConditions
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("id"))).isDisplayed();
//                //.getText();
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.id("btSave"))).click();
    }

    @Test(priority = 3)
    public void linkTest(){
        driver.get("https://www.sugarcrm.com/request-demo/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println(links.size());
        for(WebElement element: links){
            System.out.println(element.getAttribute("href"));
        }
    }

    @Test(priority = 4)
    public void dropDownMultipleSelectTest(){
        driver.get("https://demoqa.com/select-menu");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        element = driver.findElement(By.id("oldSelectMenu"));

        // 1. Standard select list
        Select select = new Select(element);
        select.selectByValue("3");
        select.selectByIndex(2);
        select.selectByVisibleText("Black");

        WebElement option = select.getFirstSelectedOption();
        System.out.println(option.getText());

        // 2. Multiple select standard select list
        element = driver.findElement(By.id("cars"));

        //Example how to scroll to visible area where element located
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        select = new Select(element);
        select.isMultiple();
        select.selectByIndex(3);
        select.selectByValue("volvo");
        select.selectByValue("saab");

        // All selected options
        List<WebElement> listOfOptions = select.getAllSelectedOptions();
        for (WebElement item : listOfOptions) {
            System.out.println(item.getText());
        }
        System.out.println(listOfOptions.size());

        select.deselectByIndex(1);
        select.deselectByIndex(0);

        listOfOptions = select.getAllSelectedOptions();
        System.out.println(listOfOptions.size());
    }

    @Test(priority = 5)
    public void autoSuggestDropDownTest1(){
        driver.get("https://www.makemytrip.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//span[text()='From']")).click();

        //Select from auto-suggest drop down
        WebElement autoSuggest = driver.findElement(By.linkText("//input[@placeholder='From']"));
        autoSuggest.sendKeys("Sydney");
        autoSuggest.sendKeys(Keys.ARROW_DOWN);
        autoSuggest.sendKeys(Keys.ENTER);
    }

    @Test
    public void autoCompleteGooglePlacesDrop () throws InterruptedException {
        driver.get("https://www.twoplugs.com/newsearchserviceneed");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement searchBox = driver.findElement(By.id("autocomplete"));
        searchBox.clear();
        searchBox.sendKeys("Toronto");

        String text;
        do{
            searchBox.sendKeys(Keys.ARROW_DOWN);
            text = searchBox.getAttribute("value");
            if(text.equals("Toronto NSW, Australia")){
                searchBox.sendKeys(Keys.ENTER);
                break;
            }

        }while(!text.isEmpty());

    }


    @Test
    public void autoSuggestDropDownTest3() throws InterruptedException {
        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.name("q")).sendKeys("selenium");
        Thread.sleep(3000);
        List<WebElement> list = driver.findElements(By.xpath("//li[@class='sbct']//div[@role='option']//div[1]//span"));
        System.out.println(list.size());

        for( WebElement listitem : list){
            if(listitem.getText().equalsIgnoreCase("selenium")){
                listitem.click();
                break;
            }
        }
    }

    //Verify dropdown in Ascending and Descending order
    @Test(priority = 6)
    public void verifyDropDownOrder(){
        driver.get("http://seleniumpractise.blogspot.com/2019/01/dropdown-demo-for-selenium.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<String> actualList = new ArrayList<>();
        Select select1 = new Select(driver.findElement(By.id("tools")));
        List<WebElement> list1 = select1.getOptions();

        for(WebElement el : list1){
            actualList.add(el.getText());
        }

        List<String> temp = new ArrayList<>();
        temp.addAll(actualList);

        //Ascending order sorting
        Collections.sort(temp);
        //Descending order sorting
        //Collections.sort(temp, Collections.reverseOrder());
        assertTrue(actualList.equals(temp));
    }

    // UI-li - dropdown
    @Test(priority = 7)
    public void bootstrapDropDown(){
        driver.get("http://seleniumpractise.blogspot.com/2016/08/bootstrap-dropdown-example-for-selenium.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        assertEquals(driver.getTitle(), "Selenium Practise: Bootstrap Dropdown Example for Selenium");
        driver.findElement(By.id("menu1")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> dropDownMenu = driver.findElements(By.xpath("//*[@class='dropdown-menu']//li/a"));
        System.out.println(dropDownMenu.size());

        for(WebElement el : dropDownMenu){
            String value = el.getText();
            if(value.equalsIgnoreCase("JavaScript")){
                el.click();
                break;
            }
            System.out.println(value);
        }
    }

    @Test(priority = 8)
    public void implicitlyWaitTest(){
        driver.get("https://saucedemo.com");
        // Implicitly Wait for findElement and findElements on hall page repeats every 500 milliseconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
    }

    @Test(priority = 9)
    public void explicitWaitTest(){
        driver.get("https://letcode.in/waits");
        driver.findElement(By.id("accept")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        // or normal wai
        // driver.switchTo().alert().accept();

        // some usage of ExpectedConditions
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("id"))).click();
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.id("btSave"))).click();

    }
    @Test
    public void fluentWaitTest(){
        // Using fluent wait
        //  Declaration
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .withMessage("Any Custom message")
                .ignoring(NoSuchElementException.class);

        // Usage Fluent wait
        WebElement element1 = wait1.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.xpath("//a[text()='Apple iPhone']"));
            }
        });
        element1.click();
    }
}