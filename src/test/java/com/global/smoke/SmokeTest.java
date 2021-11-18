package com.global.smoke;

import com.global.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SmokeTest extends TestBase {

    /**
     * open website and confirm title
     */
    @Test(groups={"smoke"})
    public void openUrl()
    {
        String actualTitle = driver.getTitle();
        assertTrue(false);
        assertEquals( actualTitle, homePage.title); //"Google"); //
    }

}