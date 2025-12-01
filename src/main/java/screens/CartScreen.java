package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import utils.appium.driver.AppDriver;

public class CartScreen extends BaseScreen {
    private final By productLabelInCart = AppiumBy.accessibilityId("product label");
    private final By removeItemBtn = AppiumBy.accessibilityId("remove item");

    private By goShoppingBtn;

    public CartScreen() {
        if (AppDriver.getCurrentDriver() instanceof AndroidDriver) {
            goShoppingBtn = By.xpath("//android.widget.TextView[@text='Go Shopping']");
        } else if (AppDriver.getCurrentDriver() instanceof IOSDriver) {
            goShoppingBtn = By.xpath("//XCUIElementTypeOther[@name='Go Shopping button']");
        }
    }

    public void tapRemoveItemButton() {
        tap(removeItemBtn);
    }

    public String getProductNameInCart() {
        return getText(productLabelInCart);
    }

    public String getGoShoppingButtonText() {
        return getText(goShoppingBtn);
    }
}
