package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.appium.driver.AppDriver;

import java.time.Duration;

public class BaseScreen {

    private static final int DEFAULT_TIMEOUT_SECONDS = 30;

    protected WebElement waitUntilElementPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(AppDriver.getCurrentDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findElement(By locator) {
        return AppDriver.getCurrentDriver().findElement(locator);
    }

    protected void enterText(By locator, String text) {
        waitUntilElementPresent(locator);
        findElement(locator).clear();
        findElement(locator).sendKeys(text);
    }

    protected void tap(By locator) {
        waitUntilElementPresent(locator).click();
    }

    protected String getText(By locator) {
        String str = "";
        if (AppDriver.getCurrentDriver() instanceof AndroidDriver) {
            str = findElement(locator).getText();
        } else if (AppDriver.getCurrentDriver() instanceof IOSDriver) {
            str = waitUntilElementPresent(locator).getAttribute("label");
        }
        return str;
    }
}
