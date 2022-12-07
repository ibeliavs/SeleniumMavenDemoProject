package com.qa.freecrm.testcases;

import com.qa.freecrm.base.BaseClass;
import com.qa.freecrm.pages.ContactsPage;
import com.qa.freecrm.pages.HomePage;
import com.qa.freecrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsPageTest extends BaseClass {
    ContactsPage contactsPage;
    @BeforeMethod
    public void setUp(){
        initialization();
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login(config.getUserName(), config.getPassword());
        contactsPage = homePage.clickOnContactLink();
    }
    @Test
    public void verifyContactsPageTest(){
        String contactsPageTitle = contactsPage.verifyContactsPageTitle();
        Assert.assertEquals(contactsPageTitle, "Contacts");
    }
}
