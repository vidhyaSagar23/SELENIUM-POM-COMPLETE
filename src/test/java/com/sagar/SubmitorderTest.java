package com.sagar;

import com.sagar.pageobjects.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SubmitorderTest {



    @Test
    public void sampleTest(){
        String productName = "IPHONE 13 PRO";
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        LandingPage landingpage= new LandingPage(driver);
        landingpage.goTo();
        ProductsCatalogue productsCatalogue = landingpage.loginApplication("anisha.manwani@qapitol.com", "Lakshya@123");
        productsCatalogue.addProductToCart(productName);
        CartPage cartPage = productsCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        System.out.println(cartPage.getTitle());
        CheckOutPage checkOutPage = cartPage.clickCheckoutBtn();
        checkOutPage.enterCountry("ind");
        checkOutPage.selectCountry();
        BillingPage  billingPage = checkOutPage.clickPlaceOrder();
        System.out.println(billingPage.checkOrderId());
        driver.close();
    }
}
