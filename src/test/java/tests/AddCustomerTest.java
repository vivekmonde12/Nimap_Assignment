package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.*;

public class AddCustomerTest extends BaseTest {

    @DataProvider(name = "customerData")
    public Object[][] customerData() {
        return ExcelUtils.getData("Customer");
    }

    @Test(dataProvider = "customerData")
    public void addCustomer(String name, String ref, String refBy, String contact,
                            String mob, String mail, String desig, String loc, String expected) {

        new LoginPage(driver).login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
        Assert.assertTrue(new DashboardPage(driver).isLoaded(), "Login failed");

        // make PASS names unique so reruns don't hit "duplicate customer"
        if (expected.equalsIgnoreCase("PASS") && !name.isEmpty()) {
            name = name + " " + System.currentTimeMillis();
        }

        String msg = new CustomerPage(driver).addCustomer(name, ref, refBy, contact, mob, mail, desig, loc);
        System.out.println("Message: " + msg);

        if (expected.equalsIgnoreCase("PASS")) {
            Assert.assertTrue(msg.toLowerCase().contains("success"), "Expected success, got: " + msg);
        } else {
            Assert.assertFalse(msg.toLowerCase().contains("success"), "Invalid data accepted: " + msg);
        }
    }
}