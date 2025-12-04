package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import utils.appium.driver.AppDriver;

public class LoginScreen extends BaseScreen {
    private final By usernameField = AppiumBy.accessibilityId("Username input field");
    private final By passwordField = AppiumBy.accessibilityId("Password input field");
    private final By loginBtn = AppiumBy.accessibilityId("Login button");

    private By userNameErrorText;
    private By passwordErrorText;
    private By credentialsErrorText;

    public LoginScreen() {
        if (AppDriver.getCurrentDriver() instanceof AndroidDriver) {
            userNameErrorText = By.xpath(
                    "//android.view.ViewGroup[@content-desc='Username-error-message']/android.widget.TextView");
            passwordErrorText = By.xpath(
                    "//android.view.ViewGroup[@content-desc='Password-error-message']/android.widget.TextView");
            credentialsErrorText =
                    By.xpath("//android.view.ViewGroup[@content-desc='generic-error-message']/android.widget.TextView");
        } else if (AppDriver.getCurrentDriver() instanceof IOSDriver) {
            userNameErrorText =
                    By.xpath("//XCUIElementTypeOther[@name='Username-error-message']/XCUIElementTypeStaticText");
            passwordErrorText =
                    By.xpath("//XCUIElementTypeOther[@name='Password-error-message']/XCUIElementTypeStaticText");
            credentialsErrorText =
                    By.xpath("//XCUIElementTypeOther[@name='generic-error-message']/XCUIElementTypeStaticText");
        }
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void tapLoginButton() {
        tap(loginBtn);
    }

    public void tapLoginButtonExpectingError() {
        tap(loginBtn);
    }

    public String getUserNameErrorText() {
        return getText(userNameErrorText);
    }

    public String getPasswordErrorText() {
        return getText(passwordErrorText);
    }

    public String getCredentialsErrorText() {
        return getText(credentialsErrorText);
    }
}
