package com.sagar.pageobjects;

import com.sagar.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class='input txt text-validated ng-untouched ng-pristine ng-valid']")
    WebElement emailName;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countries;

    @FindBy(xpath = "//span[contains(text(),'India')]")
    WebElement country;

    @FindBy(xpath = "//a[text()='Place Order ']")
    WebElement placeOrder;

    public void enterEmail(String email){
        emailName.sendKeys(email);
    }

    public void clearexistingMail(){
        emailName.clear();
    }

    public void enterCountry(String country){
        countries.sendKeys(country);
    }

    public void selectCountry(){
        scrollBy();
        country.click();
    }

    public BillingPage clickPlaceOrder(){
        placeOrder.click();
        return new BillingPage(driver);
    }
}
