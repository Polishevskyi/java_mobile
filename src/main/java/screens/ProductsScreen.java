package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ProductsScreen extends BaseScreen {

    private final By containerHeader = AppiumBy.accessibilityId("container header");
    private final By openMenuButton = AppiumBy.accessibilityId("open menu");
    private final By sortButton = AppiumBy.accessibilityId("sort button");
    private final By cartIcon = AppiumBy.accessibilityId("cart badge");

    private final By nameAscendingOption = AppiumBy.accessibilityId("nameAsc");
    private final By nameDescendingOption = AppiumBy.accessibilityId("nameDesc");
    private final By priceAscendingOption = AppiumBy.accessibilityId("priceAsc");
    private final By priceDescendingOption = AppiumBy.accessibilityId("priceDesc");

    private final By firstProductName = AppiumBy.xpath("(//android.widget.TextView[@content-desc='store item text'])[1]");
    private final By firstProduct = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='store item'])[1]");
    private final By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");

    public MenuScreen openMenu() {
        tapWhenVisible(openMenuButton);
        return new MenuScreen();
    }

    public ProductsScreen verifyProductPageVisible() {
        assertElementIsVisible(containerHeader);
        return this;
    }

    public ProductsScreen tapSortButton() {
        tapWhenVisible(sortButton);
        return this;
    }

    public ProductsScreen selectNameAscending() {
        tapWhenVisible(nameAscendingOption);
        return this;
    }

    public ProductsScreen selectNameDescending() {
        tapWhenVisible(nameDescendingOption);
        return this;
    }

    public ProductsScreen selectPriceAscending() {
        tapWhenVisible(priceAscendingOption);
        return this;
    }

    public ProductsScreen selectPriceDescending() {
        tapWhenVisible(priceDescendingOption);
        return this;
    }

    public String getFirstProductName() {
        return getText(firstProductName);
    }

    public ProductsScreen tapOnFirstProduct() {
        tapWhenVisible(firstProduct);
        return this;
    }

    public ProductsScreen tapAddToCartButton() {
        tapWhenVisible(addToCartButton);
        return this;
    }

    public CartScreen openCart() {
        tapWhenVisible(cartIcon);
        return new CartScreen();
    }
}
