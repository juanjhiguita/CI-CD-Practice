package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    private WebDriver driver;
    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#checkout_complete_container > h2")
    private WebElement completeMessage;

    public WebElement getCompleteMessage() {
        return this.completeMessage;
    }

}
