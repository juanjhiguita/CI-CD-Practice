package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOnePage {
    private WebDriver driver;

    public CheckoutOnePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(css = "#continue")
    private WebElement continueBtn;

    public WebElement getFirstNameField() {
        return this.firstNameField;
    }

    public void setFirstNameField(WebElement firstNameField) {
        this.firstNameField = firstNameField;
    }

    public WebElement getLastNameField() {
        return this.lastNameField;
    }

    public void setLastNameField(WebElement lastNameField) {
        this.lastNameField = lastNameField;
    }

    public WebElement getPostalCodeField() {
        return this.postalCodeField;
    }

    public void setPostalCodeField(WebElement postalCodeField) {
        this.postalCodeField = postalCodeField;
    }

    public WebElement getContinueBtn() {
        return this.continueBtn;
    }

    public void setContinueBtn(WebElement continueBtn) {
        this.continueBtn = continueBtn;
    }

    public void clickContinueBtn(){
        this.continueBtn.click();
    }
}
