package com.global.smoke;

import com.global.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SmokeTest extends TestBase {
    WebDriver driver;

    @BeforeTest
    public void setupTest() {
        System.out.println("=== Before Test ===");
        driver = setChromeDriver();
    }

    @AfterTest
    void quit() {
        System.out.println("=== After Test ===");
        tearDown(driver);
    }

    /**
     * open website and confirm title
     */
    @Test
    public void openUrl()
    {
        String actualTitle = driver.getTitle();
        assertEquals( actualTitle, homePage.title); //"Google"); //
    }

}