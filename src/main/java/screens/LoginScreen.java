package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import utils.appium.driver.AppDriver;

public class LoginScreen extends BaseScreen {
    private By usernameField = AppiumBy.accessibilityId("Username input field");
    private By passwordField = AppiumBy.accessibilityId("Password input field");
    private By loginButton = AppiumBy.accessibilityId("Login button");
    private By userNameErrorText;
    private By passwordErrorText;
    private By credentialsErrorText;

    public LoginScreen() {
        if (AppDriver.getCurrentDriver() instanceof AndroidDriver) {
            userNameErrorText = By.xpath("//android.view.ViewGroup[@content-desc='Username-error-message']/android.widget.TextView");
            passwordErrorText = By.xpath("//android.view.ViewGroup[@content-desc='Password-error-message']/android.widget.TextView");
            credentialsErrorText = By.xpath("//android.view.ViewGroup[@content-desc='generic-error-message']/android.widget.TextView");
        } else if (AppDriver.getCurrentDriver() instanceof IOSDriver) {
            userNameErrorText = By.xpath("//XCUIElementTypeOther[@name='Username-error-message']/XCUIElementTypeStaticText");
            passwordErrorText = By.xpath("//XCUIElementTypeOther[@name='Password-error-message']/XCUIElementTypeStaticText");
            credentialsErrorText = By.xpath("//XCUIElementTypeOther[@name='generic-error-message']/XCUIElementTypeStaticText");
        }
    }

    public LoginScreen enterUsername(String username) {
        waitNtype(usernameField, username);
        return this;
    }

    public LoginScreen enterPassword(String password) {
        waitNtype(passwordField, password);
        return this;
    }

    public ProductsScreen tapLoginButton() {
        waitNclick(loginButton);
        return new ProductsScreen();
    }

    public LoginScreen tapLoginButtonExpectingError() {
        waitNclick(loginButton);
        return this;
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
