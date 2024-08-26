package com.globant.tests;

import com.globant.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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

public class CartPageTest {
    private WebDriver driver = null;
    private CheckoutOnePage checkoutOnePage;
    private CartPage cartPage;
    private CheckoutTwoPage checkoutTwoPage;
    private CheckoutCompletePage checkoutCompletePage;
    private ProductsPage productsPage;

    private final Logger log = Logger.getLogger(String.valueOf(ProductsPageTest.class));

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/inventory.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void verifyCartMenuIsRedirectCorrectly() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebElement shoppyCartMenu = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        shoppyCartMenu.click();

        String currentUrlPage = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        log.info(expectedUrl);
        log.info("Current URL: " + currentUrlPage);
        Assert.assertEquals(currentUrlPage, expectedUrl);
    }

    @Test
    public void buyProcessIsSuccesfull() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

        //SELECCIONAR UNO DE LA CASE DEL BOTON DEL CART
        productsPage.getAddToCartBtn().click();

        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebElement shoppyCartMenu = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        shoppyCartMenu.click();

        cartPage = new CartPage(driver);
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(cartPage.getCheckoutBtn()));
        checkoutBtn.click();

        checkoutOnePage = new CheckoutOnePage(driver);
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

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutOnePage.getContinueBtn()));
        continueBtn.click();

        checkoutTwoPage = new CheckoutTwoPage(driver);
        // Espera a que el botón del menú hamburguesa sea visible y clickeable
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutTwoPage.getFinishBtn()));
        finishBtn.click();

        checkoutCompletePage = new CheckoutCompletePage(driver);

        // VERIFICAR QUE SI ESTE EN LA URL
        String currentUrlPage = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        log.info("Expected URL: " + expectedUrl);
        log.info("Current URL: " + currentUrlPage);
        Assert.assertEquals(currentUrlPage, expectedUrl);

        // VERIFICAR QUE EN LA PAGINA APAREZCA EL MENSAJE
        WebElement completeMessage = wait.until(ExpectedConditions.visibilityOf(checkoutCompletePage.getCompleteMessage()));


        String currentMessage = completeMessage.getText();
        String expectedMessage = "Thank you for your order!";
        Assert.assertEquals(currentMessage, expectedMessage);
    }

    @Test
    public void verifyBuyAProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ProductsPage productsPage = new ProductsPage(driver);

        // Espera a que el botón del menú hamburguesa sea visible y clickeable
        WebElement shoppingCartMenu = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        shoppingCartMenu.click();
        log.info("SI SE CLICKEO");
    }



    @AfterTest
    public void afterTest(){
        driver.quit();
        log.info("Cerro el navegador");
    }
}
