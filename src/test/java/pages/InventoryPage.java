package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object for the product listing (inventory) page.
 */
public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By sortDropdown = By.className("product_sort_container");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By addToCartButtons = By.cssSelector("button[id^='add-to-cart']");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle))
                .getText().equalsIgnoreCase("Products");
    }

    public void sortBy(String visibleOptionText) {
        WebElement dropdownEl = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        new Select(dropdownEl).selectByVisibleText(visibleOptionText);
    }

    public List<Double> getDisplayedPrices() {
        return driver.findElements(productPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public List<String> getDisplayedProductNames() {
        return driver.findElements(productNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void addFirstNProductsToCart(int n) {
    for (int i = 0; i < n; i++) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }
}

    public int getCartCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) return 0;
        return Integer.parseInt(badges.get(0).getText());
    }

    public void goToCart() {
    wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
}
}
