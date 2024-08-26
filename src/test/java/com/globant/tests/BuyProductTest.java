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

    @Test
    public void verifyProductsPageResourceAreLoaded() throws InterruptedException{
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
    }

    @Test
    public void verifyCartMenuIsRedirectCorrectly() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebElement shoppyCartMenu = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        shoppyCartMenu.click();
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        verifyCurrentUrl(expectedUrl);
        cartPage = new CartPage(driver);
    }

    @Test
    public void verifyCartPageResourceAreLoaded() throws InterruptedException{
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.getCheckoutBtn()));
    }

    @Test
    public void verifyCheckoutOnePageIsRedirectCorrectly() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(cartPage.getCheckoutBtn()));
        checkoutBtn.click();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        verifyCurrentUrl(expectedUrl);
        checkoutOnePage = new CheckoutOnePage(driver);
    }

    @Test
    public void verifyCheckoutOnePageResourcesAreLoaded() throws InterruptedException{
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getFirstNameField()));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getLastNameField()));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getPostalCodeField()));
        wait.until(ExpectedConditions.visibilityOf(checkoutOnePage.getContinueBtn()));
    }

    public void fillBasicFieldsCorrectly() throws InterruptedException{
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Llenar el formulario
        String firstName = "juan";
        String lastName = "sanchez";
        String postalCode = "050060";

        WebElement fistNameField = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getFirstNameField()));
        fistNameField.sendKeys(firstName);
        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getLastNameField()));
        lastNameField.sendKeys(lastName);
        WebElement postalCodeField = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getPostalCodeField()));
        postalCodeField.sendKeys(postalCode);
    }

    @Test
    public void verifyCheckoutTwoPageIsRedirectCorrectly() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        fillBasicFieldsCorrectly();
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getContinueBtn()));
        continueBtn.click();

        String currentUrlPage = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        verifyCurrentUrl(expectedUrl);
        checkoutTwoPage = new CheckoutTwoPage(driver);
    }

    @Test
    public void verifyLoadedAndCompleteFinishBuy() throws InterruptedException{
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutTwoPage.getFinishBtn()));
        finishBtn.click();
        checkoutCompletePage = new CheckoutCompletePage(driver);

        // VERIFICAR QUE SI ESTE EN LA URL
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        verifyCurrentUrl(expectedUrl);

        WebElement currentMessageWE = wait.until(ExpectedConditions.elementToBeClickable(checkoutCompletePage.getCompleteMessage()));
        String expectedMessage = "Thank you for your order!";
        Assert.assertEquals(currentMessageWE.getText(), expectedMessage);
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
