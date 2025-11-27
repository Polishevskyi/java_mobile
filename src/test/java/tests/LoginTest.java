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

        ProductsScreen productsScreen = loginScreen.enterUsername(ConfigReader.getProperty("validUsername"))
                .enterPassword(ConfigReader.getProperty("validPassword"))
                .tapLoginButton();

        Assert.assertEquals(productsScreen.getProductsHeaderText(), Constants.PRODUCT_PAGE_HEADER_TEXT,
                Constants.ASSERT_ELEMENT_VISIBLE + Constants.PRODUCT_PAGE_HEADER_NAME);
    }
}
