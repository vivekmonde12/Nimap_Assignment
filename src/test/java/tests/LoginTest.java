package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return ExcelUtils.getData("Login");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String user, String pass, String expected) {
        LoginPage login = new LoginPage(driver);
        login.login(user, pass);

        if (expected.equalsIgnoreCase("PASS")) {
            Assert.assertTrue(new DashboardPage(driver).isLoaded(), "Dashboard not loaded for valid login");
        } else {
            Assert.assertFalse(login.getMessage().isEmpty(), "No error message for invalid login");
        }
    }
}