package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // Inicializamos el driver
    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuBtn;


    @FindBy(css = "[data-test='logout-sidebar-link']")
    private WebElement logoutSidebarLink;

    @FindBy(css = "#shopping_cart_container > a")
    private WebElement shoppyCartMenu;

    public WebElement getBurgerMenuBtn() {
        return this.burgerMenuBtn;
    }

    public void setBurgerMenuBtn(WebElement burgerMenuBtn) {
        this.burgerMenuBtn = burgerMenuBtn;
    }

    public WebElement getShoppyCartMenu() {
        return this.shoppyCartMenu;
    }


    public WebElement getLogoutSidebarLink() {
        return this.logoutSidebarLink;
    }

    public void setLogoutSidebarLink(WebElement logoutSidebarLink) {
        this.logoutSidebarLink = logoutSidebarLink;
    }

    public void clickBurguerMenu(){
        this.burgerMenuBtn.click();
    }


    public void clickLogout(){
        this.logoutSidebarLink.click();
    }

    public void clickShoppyCartMenu(){
        this.shoppyCartMenu.click();
    }
}
