package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    // Inicializamos el driver
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#user-name")
    private WebElement usernameTxt;


    @FindBy(id = "password")
    private WebElement passwordTxt;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    private WebElement loginBtn;

    public void setUsername(String username){
        this.usernameTxt.sendKeys(username);
    }

    public void setPassword(String password){
        this.passwordTxt.sendKeys(password);
    }

    public void clickLogin(){
        this.loginBtn.click();
    }

    public void login(String username, String password){
        this.usernameTxt.sendKeys(username);
        this.passwordTxt.sendKeys(password);
        this.loginBtn.click();
    }





}
