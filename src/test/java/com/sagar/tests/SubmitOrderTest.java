package com.sagar.tests;

import com.sagar.pageobjects.*;
import com.sagar.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
    @Test
    public void sampleTest(){
        String productName = "IPHONE 13 PRO";
        ProductsCatalogue productsCatalogue = landingPage.loginApplication("anisha.manwani@qapitol.com", "Lakshya@123");
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
    }
}
