package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartScreen extends BaseScreen {
    private By productLabelInCart = AppiumBy.accessibilityId("product label");
    private By removeItemButton = AppiumBy.accessibilityId("remove item");
    private By goShoppingButton = AppiumBy.xpath("//android.widget.TextView[@text='Go Shopping']");

    public String getProductNameInCart() {
        return getText(productLabelInCart);
    }

    public CartScreen tapRemoveItemButton() {
        waitNclick(removeItemButton);
        return this;
    }

    public String getGoShoppingButtonText() {
        return getText(goShoppingButton);
    }
}
