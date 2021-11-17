package com.global.driver;
import com.global.testBase.ScreenshotListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverManager  extends ScreenshotListener {
    public static WebDriver driver;

    public static String url = "http://automationpractice.com/index.php";
//    public static String url = "https://www.google.com/";

    public static WebDriver getChromeDriver() {
        return driver;
    }


    public static WebDriver setChromeDriver() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");

        // Instantiate a ChromeDriver class.
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.navigate().to(url);
//        Assert.assertFalse(); TODO look for the 508
        driver.manage().window().maximize();
        // TODO deprecated driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static void tearDown(WebDriver driver){
        if (driver != null) {
            driver.quit();
        }
    }
}
