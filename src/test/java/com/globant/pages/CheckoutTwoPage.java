package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutTwoPage {
    private WebDriver driver;

    public CheckoutTwoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getFinishBtn() {
        return finishBtn;
    }


    @FindBy(id = "finish")
    private WebElement finishBtn;

}
