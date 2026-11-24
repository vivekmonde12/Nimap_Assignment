package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Confirmed from your screenshot
    private final By todaysStats = By.xpath("//*[normalize-space()=\"Today's Stats\"]");
    private final By punchedInCard = By.xpath("//*[normalize-space()='Punched In']");

    // TODO: update after you find where Punch In lives
    private final By punchInBtn = By.xpath("//button[contains(.,'Punch In')]");
    private final By toast = By.xpath("//*[@role='alert' or contains(@class,'toast') or contains(@class,'snackbar') or contains(@class,'Toastify')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(todaysStats)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String punchInAndGetToast() {
        wait.until(ExpectedConditions.elementToBeClickable(punchInBtn)).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).getText();
    }
}