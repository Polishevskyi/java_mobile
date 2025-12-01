package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import utils.appium.driver.AppDriver;

public class ProductsScreen extends BaseScreen {
    private final By sortBtn = AppiumBy.accessibilityId("sort button");
    private final By nameAscendingOpt = AppiumBy.accessibilityId("nameAsc");
    private final By nameDescendingOpt = AppiumBy.accessibilityId("nameDesc");
    private final By priceAscendingOpt = AppiumBy.accessibilityId("priceAsc");
    private final By priceDescendingOpt = AppiumBy.accessibilityId("priceDesc");
    private final By addToCartBtn = AppiumBy.accessibilityId("Add To Cart button");

    private By openMenuBtn;
    private By cartIcon;
    private By productsHeader;
    private By firstProductName;
    private By firstProduct;

    public ProductsScreen() {
        if (AppDriver.getCurrentDriver() instanceof AndroidDriver) {
            openMenuBtn = AppiumBy.accessibilityId("open menu");
            cartIcon = AppiumBy.accessibilityId("cart badge");
            productsHeader = By.xpath("//android.widget.TextView[@text='Products']");
            firstProductName = By.xpath("(//android.widget.TextView[@content-desc='store item text'])[1]");
            firstProduct = By.xpath("(//android.view.ViewGroup[@content-desc='store item'])[1]");
        } else if (AppDriver.getCurrentDriver() instanceof IOSDriver) {
            openMenuBtn = AppiumBy.accessibilityId("tab bar option menu");
            cartIcon = AppiumBy.accessibilityId("tab bar option cart");
            productsHeader = By.xpath("//XCUIElementTypeStaticText[@name='Products']");
            firstProductName = By.xpath("(//XCUIElementTypeStaticText[@name='store item text'])[1]");
            firstProduct = AppiumBy.accessibilityId("store item");
        }
    }

    public MenuScreen openMenu() {
        tap(openMenuBtn);
        return new MenuScreen();
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

    public String getProductsHeaderText() {
        return getText(productsHeader);
    }

    public String getFirstProductName() {
        return getText(firstProductName);
    }
}
