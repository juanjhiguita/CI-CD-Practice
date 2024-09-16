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
        productsPage = new ProductsPage(driver);


    }

    @Test(description = "Add 3 products in the empty cart and delete all and check if the delete is successful")
    public void deleteProcessIsSuccesfull() throws InterruptedException {
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
        // Adding 3 products to the car
        WebElement addToCartBtn1 = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getAddToCartBtn1()));
        addToCartBtn1.click();
        WebElement addToCartBtn2 = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getAddToCartBtn2()));
        addToCartBtn2.click();
        WebElement addToCartBtn3 = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getAddToCartBtn3()));
        addToCartBtn3.click();

        // Open the cart when click in the shoppyCartMenu
        WebElement shoppyCartMenu = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getShoppyCartMenu()));
        shoppyCartMenu.click();

        cartPage = new CartPage(driver);
        // Verify that are 3 products in the cart
        Assert.assertEquals(3,cartPage.getCartProductsRemoveBtns().size());
        cartPage.removeAllProducts();
        // Verify that the cart is empty
        Assert.assertEquals(0,cartPage.getCartProductsRemoveBtns().size());
        log.info("Test Finish");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
        log.info("Cerro el navegador");
    }
}
