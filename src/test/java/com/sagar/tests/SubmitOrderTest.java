package com.sagar.tests;

import com.sagar.Listeners.Retry;
import com.sagar.pageobjects.*;
import com.sagar.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    @Test(groups = {"smoke"}, retryAnalyzer = Retry.class)
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

    @Test(dataProvider = "getData1", groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void multipleRuns(String email, String password, String productName){
        ProductsCatalogue productsCatalogue = landingPage.loginApplication(email, password);
        productsCatalogue.addProductToCart(productName);
    }

    @Test(dataProvider = "readJson", groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void multipleMaps(HashMap<String, String> input) throws IOException {
        ProductsCatalogue productsCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        productsCatalogue.addProductToCart(input.get("productName"));
        getScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), driver);
    }

    @DataProvider
    public Object[][] getData1(){
        return new Object[][] {{"anisha.manwani@qapitol.com", "Lakshya@123", "IPHONE 13 PRO"}, {"anisha.manwani@qapitol.com", "Lakshya@123", "IPHONE 13 PRO"}};
    }

    @DataProvider
    public Object[][] getData(){
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("email","anisha.manwani@qapitol.com");
        map1.put("password","Lakshya@123");
        map1.put("productName", "IPHONE 13 PRO");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("email","anisha.manwani@qapitol.com");
        map2.put("password","Lakshya@123");
        map2.put("productName", "IPHONE 13 PRO");
        return new Object[][] {{map1}, {map2}};
    }

    @DataProvider
    public Object[][] readJson() throws IOException {
        List<HashMap<String, String>> jsonData = readJsonData(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\sagar\\data\\jsondata.json");
        return new Object[][] {{jsonData.get(0)},{jsonData.get(1)}};
        }
}