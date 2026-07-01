package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Standard user should log in successfully and land on the products page")
    public void validLoginSucceeds() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isLoaded(), "Expected to land on the Products page after login");
    }

    @Test(description = "Locked-out user should be blocked with a clear error message")
    public void lockedOutUserIsBlocked() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error message for a locked-out user");
        Assert.assertTrue(
                loginPage.getErrorText().contains("locked out"),
                "Error message should mention the account is locked out"
        );
    }

    @Test(description = "Invalid credentials should be rejected")
    public void invalidCredentialsAreRejected() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("invalid_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error message for invalid credentials");
    }
}
