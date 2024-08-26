package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    WebDriver driver;

    // Inicializamos el driver
    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuBtn;


    @FindBy(css = "[data-test='logout-sidebar-link']")
    private WebElement logoutSidebarLink;

    public WebElement getBurgerMenuBtn() {
        return burgerMenuBtn;
    }

    public void setBurgerMenuBtn(WebElement burgerMenuBtn) {
        this.burgerMenuBtn = burgerMenuBtn;
    }

    public WebElement getLogoutSidebarLink() {
        return logoutSidebarLink;
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

    // Verificar que se haya devuelto al login
    public void logout(){
        clickBurguerMenu();
    }
}
