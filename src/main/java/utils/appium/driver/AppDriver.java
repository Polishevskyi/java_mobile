package utils.appium.driver;

import org.openqa.selenium.WebDriver;

public class AppDriver {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getCurrentDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
}
