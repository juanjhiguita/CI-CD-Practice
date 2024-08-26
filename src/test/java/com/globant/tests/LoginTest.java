package com.globant.tests;

import com.globant.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;

public class LoginTest {

    private WebDriver driver = null;
    private LoginPage loginPage;
    private final Logger log = Logger.getLogger(String.valueOf(LoginTest.class));

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyResourceAreLoaded() throws InterruptedException{
        // Espera a que el botón del menú del carrito sea visible y clickeable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTxt()));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordTxt()));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginBtn()));
    }

    @Test
    public void verifySuccesfullLogin(){
        log.info("Inicio del test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        // Verificar que esta en la pagina de productos, o sea que si se logeo
        // POner un wait mientas carga la pagina de productos
        log.info("Finalizo el test");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
        log.info("Cerro el navegador");
    }
}

