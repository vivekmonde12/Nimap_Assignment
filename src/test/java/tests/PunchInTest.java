package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;

public class PunchInTest extends BaseTest {

    @Test
    public void verifyPunchInToast() {
        new LoginPage(driver).login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
        DashboardPage dash = new DashboardPage(driver);
        Assert.assertTrue(dash.isLoaded());

        String msg = dash.punchInAndGetToast();
        Assert.assertTrue(msg.toLowerCase().contains("punch"), "Unexpected toast: " + msg); // use real text
    }
}