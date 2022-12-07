package com.qa.freecrm.pages;

import com.qa.freecrm.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
    // initialize page objects
    public LoginPage(){
         PageFactory.initElements(driver, this);
    }

    //Page Factory - OR object Repository
    @FindBy(name = "email")
    @CacheLookup
    private WebElement email;
    @FindBy (name = "password")
    @CacheLookup
    private WebElement password;
    @FindBy (xpath = "//div[text()='Login']")
    @CacheLookup
    private WebElement loginBtn;
    @FindBy (linkText = "Login")
    @CacheLookup
    private WebElement loginLink;

    @FindBy(linkText="Sign Up")
    @CacheLookup
    private WebElement signBtn;

    //Defining page action
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }
    
    public HomePage login(String useremail, String userpassword){
        loginLink.click();
        email.sendKeys(useremail);
        password.sendKeys(userpassword);
        loginBtn.click();

        return new HomePage();
    }
}
