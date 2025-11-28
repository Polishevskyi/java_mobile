package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class MenuScreen extends BaseScreen {
    private final By loginMenuBtn = AppiumBy.accessibilityId("menu item log in");

    public LoginScreen navigateToLogin() {
        tap(loginMenuBtn);
        return new LoginScreen();
    }
}
