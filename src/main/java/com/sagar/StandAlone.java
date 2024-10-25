package com.sagar;

import com.sagar.pageobjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandAlone {

    public static void main(String[] args) throws InterruptedException {
        String Productname = "IPHONE 13 PRO";
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        LandingPage landingpage= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("anisha.manwani@qapitol.com");
		driver.findElement(By.id("userPassword")).sendKeys("Lakshya@123");
		driver.findElement(By.id("login")).click();
//        landingpage.LoginApplication("anisha.manwani@qapitol.com", "Lakshya@123");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst()
                .orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartproducts.stream().anyMatch(cartpro -> cartpro.getText().equalsIgnoreCase(Productname));
//        Assert.assertTrue(match);
        //driver.findElement(By.cssSelector(".totalRow button")).click();
        //WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement totalRowButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));

        // Try normal click first
        try {
            totalRowButton.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Normal click failed, trying JavaScript click.");
            // If the normal click fails, use JavaScript to click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", totalRowButton);
        }


        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build()
                .perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
    }
}