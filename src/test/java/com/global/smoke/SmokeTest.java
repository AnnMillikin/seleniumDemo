package com.global.smoke;

import com.global.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.assertEquals;

public class SmokeTest extends TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setupTest() {
        System.out.println("=== Smoke: Before Test ===");
        driver = setChromeDriver();
    }

    @AfterMethod
    void quit() {
        System.out.println("=== Smoke: After Test ===");
        tearDown(driver);
    }

    /**
     * open website and confirm title
     */
    @Test(groups={"smoke"})
    public void openUrl()
    {
        String actualTitle = driver.getTitle();
        assertEquals( actualTitle, homePage.title); //"Google"); //
    }

}