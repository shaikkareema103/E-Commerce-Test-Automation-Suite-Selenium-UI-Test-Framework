# E-Commerce Test Automation Suite

Automated UI test suite built with **Selenium WebDriver**, **Java**, and **TestNG**,
targeting the public Selenium practice site [saucedemo.com](https://www.saucedemo.com/).

Covers:
- **Login flows** — valid login, locked-out user, invalid credentials
- **Product sorting** — price low→high, price high→low, name A→Z (verifies actual displayed order against expected sorted order)
- **Cart & checkout** — add-to-cart badge count, cart contents match what was added, checkout navigation

## Tech stack
- Java 17
- Selenium WebDriver 4.21
- TestNG 7.10 (test runner + assertions)
- WebDriverManager (auto-manages the ChromeDriver binary — no manual driver downloads)
- Maven

## Project structure
```
src/test/java/
  pages/    -> Page Object classes (LoginPage, InventoryPage, CartPage)
  tests/    -> Test classes (LoginTest, ProductSortTest, CartAndCheckoutTest)
testng.xml  -> Test suite configuration
pom.xml     -> Maven dependencies & build config
```

Uses the **Page Object Model (POM)** pattern: locators and page actions live in
`pages/`, so if the UI changes, only the page object needs updating — not every test.

## How to run

1. Install Java 17+ and Maven.
2. Install Google Chrome (WebDriverManager auto-downloads the matching driver).
3. From the project root:
   ```bash
   mvn test
   ```
4. To watch the browser instead of running headless, open `tests/BaseTest.java`
   and comment out this line:
   ```java
   options.addArguments("--headless=new");
   ```

## Before you add this to your resume

Run it yourself first (`mvn test`) and read through the test output. Be ready to
explain the Page Object Model, why WebDriverManager is used, and what each
assertion checks — that's what an interviewer will actually probe.
