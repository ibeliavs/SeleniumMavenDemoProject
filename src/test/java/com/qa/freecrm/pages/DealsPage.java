package com.qa.freecrm.pages;

import com.qa.freecrm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DealsPage extends TestBase {
    public DealsPage(){
        PageFactory.initElements(driver,this);
    }
    @FindBy (xpath = "//div[text()='Deals']")
    WebElement dealsPageTitle;
    public String verifyDealsPage(){
        return dealsPageTitle.getText();
    }
}
