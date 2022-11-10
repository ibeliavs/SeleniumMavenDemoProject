package ebanking.testcases;

import ebanking.pages.LoginPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass {

    @Test
    public void loginPageTest() throws IOException {

        log.info("TC_LoginTest_001 loginPage test start.");

        LoginPage login = new LoginPage(driver);
        login.loginToPage(username, password);
        if (driver.getTitle().equals("Swag Labs")){
            captureScree(driver, "loginPageTest");
            log.info("TC_LoginTest_001 loginPage test Pass.");
        } else {
            log.error("TC_LoginTest_001 loginPage test Failed.");
        }

//        Assert.assertTrue(driver.getTitle().equals("Swag Labs"));
    }
}
