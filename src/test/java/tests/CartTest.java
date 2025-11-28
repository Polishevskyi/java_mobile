package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.CartScreen;
import screens.ProductsScreen;
import utils.Constants;

public class CartTest extends BaseTest {
    @Test(description = "Verify that user can add and remove product from cart")
    public void addAndRemoveProductFromCartTest() {
        ProductsScreen productsScreen = new ProductsScreen();

        String productName = productsScreen.getFirstProductName();
        productsScreen.tapOnFirstProduct();
        productsScreen.tapAddToCartButton();

        CartScreen cartScreen = productsScreen.openCart();

        String productNameInCart = cartScreen.getProductNameInCart();
        Assert.assertEquals(productNameInCart, productName,
                Constants.ASSERT_PRODUCT_IN_CART + productName);

        cartScreen.tapRemoveItemButton();
        Assert.assertEquals(cartScreen.getGoShoppingButtonText(), Constants.BUTTON_GO_SHOPPING);
    }
}

