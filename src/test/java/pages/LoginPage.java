package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By username = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@name='password']");
    private final By signInBtn = By.xpath("//button[@type='submit']");
    private final By message  = By.xpath("//p[text()='Dashboard']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }
}