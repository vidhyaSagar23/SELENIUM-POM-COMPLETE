package com.sagar.pageobjects;

import com.sagar.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkOutBtn;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    @FindBy(xpath = "//h1[text()='My Cart']")
    WebElement title;

    By byCheckoutBtn = By.xpath("//button[contains(text(),'Checkout')]");

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductDisplay(String productName){
        Boolean match = cartProducts.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage clickCheckoutBtn(){
        scrollBy();
        waitForElementToBeVisible(byCheckoutBtn);
        checkOutBtn.click();
        return new CheckOutPage(driver);
    }

    public String getTitle(){
        return title.getText();
    }

}
