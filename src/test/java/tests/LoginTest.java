package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MenuScreen;
import screens.ProductsScreen;
import utils.ConfigReader;
import utils.Constants;

public class LoginTest extends BaseTest {
    @Test(description = "Verify that user can login with valid credentials")
    public void validLoginTest() {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();

        loginScreen.enterUsername(ConfigReader.getProperty("test.credentials.username"));
        loginScreen.enterPassword(ConfigReader.getProperty("test.credentials.password"));
        loginScreen.tapLoginButton();

        ProductsScreen productsScreen = new ProductsScreen();

        Assert.assertEquals(
                productsScreen.getProductsHeaderText(),
                Constants.PRODUCT_PAGE_HEADER_TEXT,
                Constants.ASSERT_ELEMENT_VISIBLE + Constants.PRODUCT_PAGE_HEADER_NAME);
    }
}
