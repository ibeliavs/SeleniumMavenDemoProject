package com.company.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "email")
    @CacheLookup
    private WebElement user_email;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement user_password;

    @FindBy(xpath = "//div[text()='Login']")
    @CacheLookup
    private WebElement login_button;

//    @FindBy(how = How.TAG_NAME, using = "a")
//    List<WebElement> links;
    public void loginToPage(String email, String password){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(user_email)).sendKeys(email);

        //user_email.sendKeys(email);
        user_password.sendKeys(password);
        login_button.click();
    }
}
