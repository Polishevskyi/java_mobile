package screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ProductsScreen extends BaseScreen {
    private final By productsHeader = AppiumBy.xpath("//android.widget.TextView[@text=\"Products\"]");
    private final By openMenuBtn = AppiumBy.accessibilityId("open menu");
    private final By sortBtn = AppiumBy.accessibilityId("sort button");
    private final By cartIcon = AppiumBy.accessibilityId("cart badge");
    private final By nameAscendingOpt = AppiumBy.accessibilityId("nameAsc");
    private final By nameDescendingOpt = AppiumBy.accessibilityId("nameDesc");
    private final By priceAscendingOpt = AppiumBy.accessibilityId("priceAsc");
    private final By priceDescendingOpt = AppiumBy.accessibilityId("priceDesc");
    private final By firstProductName = AppiumBy.xpath("(//android.widget.TextView[@content-desc='store item text'])[1]");
    private final By firstProduct = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='store item'])[1]");
    private final By addToCartBtn = AppiumBy.accessibilityId("Add To Cart button");

    public MenuScreen openMenu() {
        tap(openMenuBtn);
        return new MenuScreen();
    }

    public String getProductsHeaderText() {
        return getText(productsHeader);
    }

    public void tapSortButton() {
        tap(sortBtn);
    }

    public void selectNameAscending() {
        tap(nameAscendingOpt);
    }

    public void selectNameDescending() {
        tap(nameDescendingOpt);
    }

    public void selectPriceAscending() {
        tap(priceAscendingOpt);
    }

    public void selectPriceDescending() {
        tap(priceDescendingOpt);
    }

    public String getFirstProductName() {
        return getText(firstProductName);
    }

    public void tapOnFirstProduct() {
        tap(firstProduct);
    }

    public void tapAddToCartButton() {
        tap(addToCartBtn);
    }

    public CartScreen openCart() {
        tap(cartIcon);
        return new CartScreen();
    }
}
