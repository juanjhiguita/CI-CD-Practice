package com.globant.tests;

import com.globant.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;

public class BuyProductTest {
    private WebDriver driver = null;
    private CheckoutOnePage checkoutOnePage;
    private CartPage cartPage;
    private CheckoutTwoPage checkoutTwoPage;
    private ProductsPage productsPage;
    private CheckoutCompletePage checkoutCompletePage;

    private final Logger log = Logger.getLogger(String.valueOf(LogoutTest.class));

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/inventory.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    @Test(description = "Verify that products page resources loaded correctly")
    public void verifyProductsPageResourceAreLoaded() throws InterruptedException{
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        log.info("Test Finish");
    }

    @Test(description = "Verify that the shoppiCartMenu is clickable and click to check if redirect correctly")
    public void verifyCartMenuIsRedirectCorrectly() throws InterruptedException {
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement shoppyCartMenu = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        shoppyCartMenu.click();

        String expectedUrl = "https://www.saucedemo.com/cart.html";
        verifyCurrentUrl(expectedUrl);
        cartPage = new CartPage(driver);
        log.info("Test Finish");
    }

    @Test(description = "Verify that cartPage resources loaded correctly")
    public void verifyCartPageResourceAreLoaded() throws InterruptedException{
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.getCheckoutBtn()));
        log.info("Test Finish");
    }

    @Test(description = "Verify that the checkoutBtn is clickable and click to check if redirect correctly")
    public void verifyCheckoutOnePageIsRedirectCorrectly() throws InterruptedException {
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(cartPage.getCheckoutBtn()));
        checkoutBtn.click();

        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        verifyCurrentUrl(expectedUrl);
        checkoutOnePage = new CheckoutOnePage(driver);
        log.info("Test Finish");
    }

    @Test(description = "Verify that checkoutOnePage resources loaded correctly")
    public void verifyCheckoutOnePageResourcesAreLoaded() throws InterruptedException{
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getFirstNameField()));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getLastNameField()));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getPostalCodeField()));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getContinueBtn()));
        log.info("Test Finish");
    }

    @Test(description = "Verify that necessary fields are displayed and type the parameters")
    @Parameters({"firstName", "lastName", "postalCode"})
    public void verifyFillBasicFields(String firstName, String lastName, String postalCode) throws InterruptedException {
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Fill the formulary
        WebElement fistNameField = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getFirstNameField()));
        fistNameField.sendKeys(firstName);
        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getLastNameField()));
        lastNameField.sendKeys(lastName);
        WebElement postalCodeField = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getPostalCodeField()));
        postalCodeField.sendKeys(postalCode);

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getContinueBtn()));
        continueBtn.click();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        verifyCurrentUrl(expectedUrl);
        checkoutTwoPage = new CheckoutTwoPage(driver);
        log.info("Test Finish");
    }

    @Test(description = "Verify that the checkoutBtn is clickable and click to check if redirect to the correctly url and page")
    public void verifyLoadedAndCompleteFinishBuy() throws InterruptedException{
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutTwoPage.getFinishBtn()));
        finishBtn.click();
        checkoutCompletePage = new CheckoutCompletePage(driver);

        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        verifyCurrentUrl(expectedUrl);
        WebElement currentMessageWE = wait.until(ExpectedConditions.elementToBeClickable(checkoutCompletePage.getCompleteMessage()));
        String expectedMessage = "Thank you for your order!";
        Assert.assertEquals(currentMessageWE.getText(), expectedMessage);
        log.info("Test Finish");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
        log.info("Cerro el navegador");
    }

    private void verifyCurrentUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        log.info("Expected URL: " + expectedUrl);
        log.info("Current URL: " + currentUrl);
        Assert.assertEquals(currentUrl, expectedUrl);
    }
}
