package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import screens.ProductsScreen;
import utils.Constants;

public class SortTest extends BaseTest {

    @DataProvider(name = "sortOptions")
    public Object[][] sortOptionsData() {
        return new Object[][] {
            {"nameAsc", Constants.PRODUCT_BACKPACK},
            {"nameDesc", Constants.PRODUCT_TSHIRT},
            {"priceAsc", Constants.PRODUCT_ONESIE},
            {"priceDesc", Constants.PRODUCT_JACKET}
        };
    }

    @Test(dataProvider = "sortOptions", description = "Verify that products are sorted correctly")
    public void sortProductsTest(String sortType, String expectedProductName) {
        ProductsScreen productsScreen = new ProductsScreen();

        productsScreen.tapSortButton();

        switch (sortType) {
            case "nameAsc":
                productsScreen.selectNameAscending();
                break;
            case "nameDesc":
                productsScreen.selectNameDescending();
                break;
            case "priceAsc":
                productsScreen.selectPriceAscending();
                break;
            case "priceDesc":
                productsScreen.selectPriceDescending();
                break;
        }

        String actualProductName = productsScreen.getFirstProductName();
        Assert.assertEquals(
                actualProductName,
                expectedProductName,
                Constants.ASSERT_FIRST_PRODUCT_AFTER_SORTING + expectedProductName);
    }
}
