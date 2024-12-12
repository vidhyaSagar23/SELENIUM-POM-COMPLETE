package com.sagar.testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagar.pageobjects.LandingPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver intializeDriver() {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\sagar\\resources\\GlobalData.properties");
            properties.load(file);
            String browserName = System.getProperty("browser") != null ?System.getProperty("browser"):properties.getProperty("browser");
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public List<HashMap<String, String>> readJsonData(String filePath) throws IOException {
        String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() {
        driver = intializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public String getScreenShot(String fileName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+ "\\reports\\" +fileName +".png");
        FileUtils.copyFile(source, destination);
        return System.getProperty("user.dir")+ "\\reports\\" +fileName +".png";
    }
}