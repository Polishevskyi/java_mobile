package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ProductsScreen extends BaseScreen {
    private By productsHeader = AppiumBy.xpath("//android.widget.TextView[@text=\"Products\"]");
    private By openMenuButton = AppiumBy.accessibilityId("open menu");
    private By sortButton = AppiumBy.accessibilityId("sort button");
    private By cartIcon = AppiumBy.accessibilityId("cart badge");
    private By nameAscendingOption = AppiumBy.accessibilityId("nameAsc");
    private By nameDescendingOption = AppiumBy.accessibilityId("nameDesc");
    private By priceAscendingOption = AppiumBy.accessibilityId("priceAsc");
    private By priceDescendingOption = AppiumBy.accessibilityId("priceDesc");
    private By firstProductName = AppiumBy.xpath("(//android.widget.TextView[@content-desc='store item text'])[1]");
    private By firstProduct = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='store item'])[1]");
    private By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");

    public MenuScreen openMenu() {
        waitNclick(openMenuButton);
        return new MenuScreen();
    }

    public String getProductsHeaderText() {
        return getText(productsHeader);
    }

    public ProductsScreen tapSortButton() {
        waitNclick(sortButton);
        return this;
    }

    public ProductsScreen selectNameAscending() {
        waitNclick(nameAscendingOption);
        return this;
    }

    public ProductsScreen selectNameDescending() {
        waitNclick(nameDescendingOption);
        return this;
    }

    public ProductsScreen selectPriceAscending() {
        waitNclick(priceAscendingOption);
        return this;
    }

    public ProductsScreen selectPriceDescending() {
        waitNclick(priceDescendingOption);
        return this;
    }

    public String getFirstProductName() {
        return getText(firstProductName);
    }

    public ProductsScreen tapOnFirstProduct() {
        waitNclick(firstProduct);
        return this;
    }

    public ProductsScreen tapAddToCartButton() {
        waitNclick(addToCartButton);
        return this;
    }

    public CartScreen openCart() {
        waitNclick(cartIcon);
        return new CartScreen();
    }
}
