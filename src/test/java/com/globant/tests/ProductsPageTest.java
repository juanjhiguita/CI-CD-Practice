package com.globant.tests;

import com.globant.pages.LoginPage;
import com.globant.pages.ProductsPage;
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

public class ProductsPageTest {

    WebDriver driver = null;
    ProductsPage productsPage;
    LoginPage loginPage;
    Logger log = Logger.getLogger(String.valueOf(ProductsPageTest.class));

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/inventory.html");
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyLogoutRedirectLogin() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        productsPage = new ProductsPage(driver);
        productsPage.clickBurguerMenu();

        // Espera a que el botón del menú hamburguesa sea visible y clickeable
        WebElement burgerMenuBtn = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getBurgerMenuBtn()));

        // Espera a que el enlace de logout sea visible y clickeable
        WebElement logoutSidebarLink = wait.until(ExpectedConditions.elementToBeClickable(productsPage.getLogoutSidebarLink()));
        logoutSidebarLink.click();

        String currentUrlPage = driver.getCurrentUrl();
        log.info("Expected URL: " + "https://www.saucedemo.com/");
        log.info("Current URL: " + currentUrlPage);
        Assert.assertEquals(currentUrlPage, "https://www.saucedemo.com/");


    }



    @AfterTest
    public void afterTest(){
        driver.quit();
        log.info("Cerro el navegador");
    }
}
