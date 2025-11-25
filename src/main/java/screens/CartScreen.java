package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartScreen extends BaseScreen {
    private final By productLabelInCart = AppiumBy.accessibilityId("product label");
    private final By removeItemButton = AppiumBy.accessibilityId("remove item");

    public String getProductNameInCart() {
        return getText(productLabelInCart);
    }

    public CartScreen tapRemoveItemButton() {
        tapWhenVisible(removeItemButton);
        return this;
    }

    public void verifyProductNotInCart() {
        assertElementIsNotVisible(productLabelInCart);
    }
}
