package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    // Inicializamos el driver
    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;


    public WebElement getCheckoutBtn() {
        return this.checkoutBtn;
    }

    public void setCheckoutBtn(WebElement checkoutBtn) {
        this.checkoutBtn = checkoutBtn;
    }

    public void clickCheckoutBtn(){
        this.checkoutBtn.click();
    }

}
