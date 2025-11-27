package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class MenuScreen extends BaseScreen {
    private By loginMenuButton = AppiumBy.accessibilityId("menu item log in");

    public LoginScreen navigateToLogin() {
        waitNclick(loginMenuButton);
        return new LoginScreen();
    }
}
