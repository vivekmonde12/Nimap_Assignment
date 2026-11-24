package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CustomerPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Navigation
    private final By myCustomersMenu   = By.xpath("//*[normalize-space()='My Customers']");
    private final By myCustomerSubMenu = By.xpath("//*[normalize-space()='My Customer']");
    private final By manageBtn   = By.xpath("//button[normalize-space()='Manage']");
    private final By newCustomer = By.xpath("//*[normalize-space()='New Customer']");

    // Form fields (from your screenshots)
    private final By leadName    = By.xpath("//input[@placeholder='Enter Lead/Customer Name']");
    private final By refNo       = By.xpath("//input[@placeholder='Enter Ref No.']");
    private final By referredBy  = By.xpath("//input[@placeholder='Enter Referred By']");
    private final By contactName = By.xpath("//input[@placeholder='Enter Contact Person Name']");
    private final By mobile      = By.xpath("//input[@placeholder='Enter Mobile No']");
    private final By email       = By.xpath("//input[@placeholder='Enter Email']");
    private final By designation = By.xpath("//input[starts-with(@placeholder,'Enter Contact Person Designatio')]");
    private final By location    = By.xpath("//input[@placeholder='Enter Contact Person Location']");
    private final By saveBtn     = By.xpath("//button[normalize-space()='Save']");

    // TODO: confirm via Inspect
    private final By toast = By.xpath("//*[@role='alert' or contains(@class,'toast') or contains(@class,'Toastify') or contains(@class,'snackbar')]");
    private final By modalTitle = By.xpath("//*[normalize-space()='New Customer' and not(self::button)]");

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openNewCustomerForm() {
        wait.until(ExpectedConditions.elementToBeClickable(myCustomersMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(myCustomerSubMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(manageBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(newCustomer)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(leadName));
    }

    private void type(By locator, String value) {
        if (value == null || value.isEmpty()) return;
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(value);
    }

    public String addCustomer(String name, String ref, String refBy, String contact,
                              String mob, String mail, String desig, String loc) {
        openNewCustomerForm();
        type(leadName, name);
        type(refNo, ref);
        type(referredBy, refBy);
        type(contactName, contact);
        type(mobile, mob);
        type(email, mail);
        type(designation, desig);
        type(location, loc);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).getText();
    }
}