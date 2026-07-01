package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSortTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Sorting by price low-to-high should return prices in ascending order")
    public void sortByPriceLowToHigh() {
        inventoryPage.sortBy("Price (low to high)");

        List<Double> actual = inventoryPage.getDisplayedPrices();
        List<Double> expectedSorted = new ArrayList<>(actual);
        Collections.sort(expectedSorted);

        Assert.assertEquals(actual, expectedSorted, "Products should be sorted by ascending price");
    }

    @Test(description = "Sorting by price high-to-low should return prices in descending order")
    public void sortByPriceHighToLow() {
        inventoryPage.sortBy("Price (high to low)");

        List<Double> actual = inventoryPage.getDisplayedPrices();
        List<Double> expectedSorted = new ArrayList<>(actual);
        expectedSorted.sort(Collections.reverseOrder());

        Assert.assertEquals(actual, expectedSorted, "Products should be sorted by descending price");
    }

    @Test(description = "Sorting by name A-to-Z should return names in alphabetical order")
    public void sortByNameAToZ() {
        inventoryPage.sortBy("Name (A to Z)");

        List<String> actual = inventoryPage.getDisplayedProductNames();
        List<String> expectedSorted = new ArrayList<>(actual);
        Collections.sort(expectedSorted);

        Assert.assertEquals(actual, expectedSorted, "Products should be sorted alphabetically A-Z");
    }
}
