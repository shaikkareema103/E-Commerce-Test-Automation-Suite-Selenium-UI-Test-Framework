package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartAndCheckoutTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Adding products to cart should update the cart badge count")
    public void addingProductsUpdatesCartBadge() {
        inventoryPage.addFirstNProductsToCart(3);
        Assert.assertEquals(inventoryPage.getCartCount(), 3, "Cart badge should reflect 3 added items");
    }

    @Test(description = "Cart page should list exactly the items that were added on the products page")
    public void cartPageReflectsAddedItems() {
        inventoryPage.addFirstNProductsToCart(2);
        int badgeCount = inventoryPage.getCartCount();

        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.getItemCount(), badgeCount,
                "Number of items on cart page should match the cart badge count");
        Assert.assertFalse(cartPage.getItemNames().isEmpty(), "Cart should display item names");
    }

    @Test(description = "Checkout button should be present and clickable from a non-empty cart")
    public void checkoutButtonIsReachable() {
        inventoryPage.addFirstNProductsToCart(1);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

       
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
        .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("checkout-step-one"));

Assert.assertTrue(
        driver.getCurrentUrl().contains("checkout-step-one"),
        "Clicking checkout should navigate to the checkout information step"
);
    } 
}
