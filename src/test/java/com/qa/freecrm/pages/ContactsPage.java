package com.qa.freecrm.pages;

import com.qa.freecrm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage extends TestBase {
    public ContactsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='Contacts']")
    WebElement pageTitle;

    public String verifyContactsPageTitle(){
        return pageTitle.getText();
    }


}
