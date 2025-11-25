package tests;

import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MenuScreen;
import screens.ProductsScreen;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
    @Test(description = "Verify that user can login with valid credentials")
    public void validLoginTest() {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();

        loginScreen.enterUsername(ConfigReader.getProperty("validUsername"))
                .enterPassword(ConfigReader.getProperty("validPassword"))
                .tapLoginButton()
                .verifyProductPageVisible();
    }
}
