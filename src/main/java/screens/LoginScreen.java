package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {
    private final By usernameField = AppiumBy.accessibilityId("Username input field");
    private final By passwordField = AppiumBy.accessibilityId("Password input field");
    private final By loginButton = AppiumBy.accessibilityId("Login button");
    private final By errorMessage = AppiumBy.accessibilityId("generic-error-message");

    public LoginScreen enterUsername(String username) {
        setValue(usernameField, username);
        return this;
    }

    public LoginScreen enterPassword(String password) {
        setValue(passwordField, password);
        return this;
    }

    public ProductsScreen tapLoginButton() {
        tapWhenVisible(loginButton);
        return new ProductsScreen();
    }

    public LoginScreen tapLoginButtonExpectingError() {
        tapWhenVisible(loginButton);
        return this;
    }

    public LoginScreen verifyErrorMessageDisplayed(String expectedError) {
        assertElementValueContains(errorMessage, expectedError);
        return this;
    }
}
