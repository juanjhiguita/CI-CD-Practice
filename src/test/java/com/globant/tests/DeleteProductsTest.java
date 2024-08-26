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

public class DeleteProductsTest {
    private WebDriver driver = null;
    private CheckoutOnePage checkoutOnePage;
    private CartPage cartPage;
    private CheckoutTwoPage checkoutTwoPage;
    private CheckoutCompletePage checkoutCompletePage;
    private ProductsPage productsPage;

    private final Logger log = Logger.getLogger(String.valueOf(DeleteProductsTest.class));

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
    public void deleteProcessIsSuccesfull() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));

        //SELECCIONAR UNO DE LA CASE DEL BOTON DEL CART
        productsPage.getAddToCartBtn1().click();
        productsPage.getAddToCartBtn2().click();
        productsPage.getAddToCartBtn3().click();

        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebElement shoppyCartMenu = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        shoppyCartMenu.click();

        // Se remueven todos los productos
        cartPage = new CartPage(driver);
        cartPage.removeAllProducts();

        // Se verifica que se eliminaron
        Assert.assertEquals(0,cartPage.getCartProductsRemoveBtns().size());

    }

    @AfterTest
    public void afterTest(){
        driver.quit();
        log.info("Cerro el navegador");
    }
}
