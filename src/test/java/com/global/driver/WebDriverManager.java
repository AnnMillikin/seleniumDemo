package com.global.driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WebDriverManager {// TODO } extends TestCommon {

    public static String url = "http://automationpractice.com/index.php";
//    public static String url = "https://www.google.com/";

    public static WebDriver getChromeDriver() {
        return driver;
    }

    public static WebDriver driver;

    @BeforeMethod
    public void setupTest() {
        Reporter.log("\n=== WDM: BeforeMethod  ===");
        driver = setChromeDriver();
    }

    @AfterMethod
    public void quit() {
        Reporter.log("\n=== WDM: AfterMethod  ===");
        tearDown(driver);
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
