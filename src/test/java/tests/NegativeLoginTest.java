package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MenuScreen;
import screens.ProductsScreen;
import utils.ConfigReader;
import utils.Constants;
import utils.DataGenerator;

public class NegativeLoginTest extends BaseTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"", ConfigReader.getProperty("validPassword"), Constants.ERROR_USERNAME_REQUIRED},
            {ConfigReader.getProperty("validUsername"), "", Constants.ERROR_PASSWORD_REQUIRED},
            {"", "", Constants.ERROR_USERNAME_REQUIRED},
            {DataGenerator.email(), ConfigReader.getProperty("validPassword"), Constants.ERROR_INVALID_CREDENTIALS},
            {ConfigReader.getProperty("validUsername"), DataGenerator.password(), Constants.ERROR_INVALID_CREDENTIALS},
            {DataGenerator.email(), DataGenerator.password(), Constants.ERROR_INVALID_CREDENTIALS}
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Verify that user cannot login with invalid credentials")
    public void invalidLoginTest(String username, String password, String expectedError) {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();

        loginScreen.enterUsername(username)
                .enterPassword(password)
                .tapLoginButtonExpectingError()
                .verifyErrorMessageDisplayed(expectedError);
    }
}

