package com.qa.freecrm.pages;

import com.qa.freecrm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Ilia Test']")
    WebElement userName;
    @FindBy(xpath = "//span[contains(text(), 'Contacts')]")
    WebElement contactsLink;
    @FindBy(xpath = "//span[contains(text(), 'Deals')]")
    WebElement dealsLink;

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

    public boolean verifyCorrectUserName(){
        return userName.isDisplayed();
    }

    public ContactsPage clickOnContactLink(){
        contactsLink.click();
        return new ContactsPage();
    }

    public DealsPage clickOnDealsLink(){
        dealsLink.click();
        return new DealsPage();
    }
}
