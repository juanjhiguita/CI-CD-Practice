package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    WebDriver driver;
    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getBackHomeBtn() {
        return backHomeBtn;
    }

    public void setBackHomeBtn(WebElement backHomeBtn) {
        this.backHomeBtn = backHomeBtn;
    }

    @FindBy(id = "back-to-products")
    private WebElement backHomeBtn;

    @FindBy(className = "complete-header")
    private WebElement completeMessage;

    public WebElement getCompleteMessage() {
        return completeMessage;
    }

    public void setCompleteMessage(WebElement completeMessage) {
        this.completeMessage = completeMessage;
    }

    public void clickBackHomeBtn(){
        this.backHomeBtn.click();
    }
}
