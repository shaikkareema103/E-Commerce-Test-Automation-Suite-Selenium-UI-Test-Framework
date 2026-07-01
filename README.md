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

## Status

All 9 tests passing. Built and debugged locally — fixed two real bugs during development:
a stale element reference when adding items to cart, and missing explicit waits causing
timing issues on cart/checkout navigation.