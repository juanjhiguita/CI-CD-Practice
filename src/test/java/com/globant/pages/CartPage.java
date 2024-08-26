package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;

    // Inicializamos el driver
    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(css = ".cart_button")
    private List<WebElement> cartProductsRemoveBtns;

    public List<WebElement> getCartProductsRemoveBtns() {
        return cartProductsRemoveBtns;
    }

    public WebElement getCheckoutBtn() {
        return this.checkoutBtn;
    }

    public void setCheckoutBtn(WebElement checkoutBtn) {
        this.checkoutBtn = checkoutBtn;
    }

    public void removeAllProducts(){
        for (WebElement button : cartProductsRemoveBtns) {
            if (button.getAttribute("id").contains("remove-sauce-labs")) {
                button.click();
            }
        }

    }

}
