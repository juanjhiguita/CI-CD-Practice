package com.globant.tests;

import com.globant.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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

    @Test(description = "Verify that login page resources loaded correctly")
    public void verifyResourceAreLoaded() throws InterruptedException{
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTxt()));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordTxt()));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginBtn()));
        log.info("Test Finish");
    }

    @Test(description = "Verify that the login is successful")
    @Parameters({"username", "password"})
    public void verifySuccesfulLogin(String username, String password){
        log.info("Test Start");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage.setUsername(username);
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        log.info("Test Finish");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
        log.info("Cerro el navegador");
    }
}

