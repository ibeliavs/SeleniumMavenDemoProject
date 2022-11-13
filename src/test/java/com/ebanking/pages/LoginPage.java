package com.ebanking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "user-name")
    @CacheLookup
    private WebElement user_name;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement user_password;

    @FindBy(xpath = "//input[@type='submit']")
    @CacheLookup
    private WebElement login_button;

//    @FindBy(tagName = "a")
//    List<WebElement> links;
    public void loginToPage(String name, String password){
        user_name.sendKeys(name);
        user_password.sendKeys(password);
        login_button.click();
    }
}
