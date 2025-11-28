package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartScreen extends BaseScreen {
    private final By productLabelInCart = AppiumBy.accessibilityId("product label");
    private final By removeItemBtn = AppiumBy.accessibilityId("remove item");
    private final By goShoppingBtn = AppiumBy.xpath("//android.widget.TextView[@text='Go Shopping']");

    public String getProductNameInCart() {
        return getText(productLabelInCart);
    }

    public void tapRemoveItemButton() {
        tap(removeItemBtn);
    }

    public String getGoShoppingButtonText() {
        return getText(goShoppingBtn);
    }
}
