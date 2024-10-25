package com.sagar.pageobjects;

import com.sagar.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BillingPage extends AbstractComponent {
    WebDriver driver;
    public BillingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//td[@class='em-spacer-1']/label)[2]")
    WebElement orderId;

    public String checkOrderId(){
        return orderId.getText();
    }
}
