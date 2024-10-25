package com.sagar.pageobjects;

import com.sagar.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    WebElement userEmail = driver.findElement(By.id("userEmail"));
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id ="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginBtn;

    public ProductsCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
        return new ProductsCatalogue(driver);
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
