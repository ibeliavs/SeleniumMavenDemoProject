package com.qa.freecrm.testcases;

import com.qa.freecrm.base.BaseClass;
import com.qa.freecrm.pages.ContactsPage;
import com.qa.freecrm.pages.DealsPage;
import com.qa.freecrm.pages.HomePage;
import com.qa.freecrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseClass {
    LoginPage loginPage;
    HomePage homePage;
    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(config.getUserName(), config.getPassword());
    }

    @Test(priority=1)
    public void verifyHomePageTitleTest(){
        String homePageTitle = homePage.verifyHomePageTitle();
        Assert.assertEquals(homePageTitle, "Cogmento CRM");
    }

    @Test(priority=2)
    public void verifyUserNameTest(){
        Assert.assertTrue(homePage.verifyCorrectUserName());
    }

    @Test(priority=3)
    public void verifyContactsLinkTest(){
        ContactsPage contactsPage = homePage.clickOnContactLink();
        String contactsPageTitle = contactsPage.verifyContactsPageTitle();
        Assert.assertEquals(contactsPageTitle, "Contacts");
    }

    @Test(priority=4)
    public void verifyDealsLinkTest(){
        DealsPage dealsPage = homePage.clickOnDealsLink();
        String dealsPageTitle = dealsPage.verifyDealsPage();
        Assert.assertEquals(dealsPageTitle, "Deals");
    }
}
