package tests;

import org.testng.Assert;
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
            {"", ConfigReader.getProperty("test.credentials.password"), Constants.ERROR_USERNAME_REQUIRED},
            {ConfigReader.getProperty("test.credentials.username"), "", Constants.ERROR_PASSWORD_REQUIRED},
            {"", "", Constants.ERROR_USERNAME_REQUIRED},
            {
                DataGenerator.email(),
                ConfigReader.getProperty("test.credentials.password"),
                Constants.ERROR_INVALID_CREDENTIALS
            },
            {
                ConfigReader.getProperty("test.credentials.username"),
                DataGenerator.password(),
                Constants.ERROR_INVALID_CREDENTIALS
            },
            {DataGenerator.email(), DataGenerator.password(), Constants.ERROR_INVALID_CREDENTIALS}
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Verify that user cannot login with invalid credentials")
    public void invalidLoginTest(String username, String password, String expectedError) {
        MenuScreen menuScreen = new ProductsScreen().openMenu();
        LoginScreen loginScreen = menuScreen.navigateToLogin();

        loginScreen.enterUsername(username);
        loginScreen.enterPassword(password);
        loginScreen.tapLoginButtonExpectingError();

        String actualError = "";
        if (expectedError.equals(Constants.ERROR_USERNAME_REQUIRED)) {
            actualError = loginScreen.getUserNameErrorText();
        } else if (expectedError.equals(Constants.ERROR_PASSWORD_REQUIRED)) {
            actualError = loginScreen.getPasswordErrorText();
        } else if (expectedError.equals(Constants.ERROR_INVALID_CREDENTIALS)) {
            actualError = loginScreen.getCredentialsErrorText();
        }

        Assert.assertEquals(actualError, expectedError);
    }
}
