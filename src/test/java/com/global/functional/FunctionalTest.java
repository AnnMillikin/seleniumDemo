package com.global.functional;
import com.global.testBase.TestBase;

import jdk.jfr.Label;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class FunctionalTest extends TestBase
{
    WebDriver driver;

//    @BeforeMethod
//    public void setupTest() {
//        Reporter.log("\n=== Functional: Before Test ===");
//          driver = setChromeDriver();
//    }
//
//    @AfterMethod
//    void quit() {
//        Reporter.log("\n=== Functional: After Test ===");
//        tearDown(driver);
//    }

    /**
     * check login page title
     */
    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
    public void checkLoginPage()
    {
//        driver = getChromeDriver();
//        driver.findElement(homePage.signIn).click();
//        String actualTitle = driver.getTitle();
//        Reporter.log("\nactualTitle: "+actualTitle);
//        Reporter.log("\nexpectedTitle: "+homePage.createAccountTitle);
//        assertEquals(actualTitle,homePage.createAccountTitle);
    }

    /**
     * search for items
     */
//    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
    public void searchBox()
    {
        driver = getChromeDriver();
        int size = getSearchSize("Dress");
        assertTrue(size > 0, size + " Dress(es) found, expected: > 0");
    }

    /**
     * select items, add items to cart and assert the total price is > 0
     */
    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
    public void addToShoppingCart() throws InterruptedException {
//        driver = getChromeDriver();
//        String str = "Dress";
//        int size = getSearchSize(str);
//        List<WebElement> items = driver.findElements(By.cssSelector(searchStrBeginning+ str + searchStrEnding));
//        for (int i=0; i<items.size(); i++) {
//            Actions action = new Actions(driver);
//            action.moveToElement(items.get(i)).pause(java.time.Duration.ofSeconds(1)).perform();
//            /*
//            Since there are multiple Add To Cart buttons, click only the one that is displayed.
//             */
//            List<WebElement> addButtons = driver.findElements(homePage.addToCartBtn);
//            for(WebElement button: addButtons){
//                if(button.isDisplayed()) {button.click();}
//            }
//            Thread.sleep(1000);
//            // continue shopping
////            driver.findElement(homePage.continueShoppingBtn).click();
//            List<WebElement> continueShoppingButtons = driver.findElements(homePage.continueShoppingBtn);
//            for(WebElement button: continueShoppingButtons){
//                if(button.isDisplayed()) {button.click();}
//            }
//        }
//        // check cart after escape key
//        Actions action = new Actions(driver);
//        WebElement logo = driver.findElement(homePage.headerLogo);
//        action.moveToElement(logo).pause(java.time.Duration.ofSeconds(1)).sendKeys(Keys.ESCAPE).pause(java.time.Duration.ofSeconds(1)).perform();
//        driver.findElement(homePage.openCart).click();
//        String totalPriceStr = driver.findElement(homePage.totalPrice).getText();
//        totalPriceStr= totalPriceStr.replace("$", "");
//        double totalPrice = Double.valueOf(totalPriceStr);
//        Reporter.log(totalPrice);
//        assertTrue( totalPrice>0, "Total price: " + totalPrice);
    }

//    /**
//     * make payments TODO
//     */
//    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
//    public void makePayments()
//    {
//        driver = getChromeDriver();
////        String actualTitle = driver.getTitle();
////        assertEquals( actualTitle, homePage.title);
//    }
//
//    /**
//     * click tabs TODO
//     */
//    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
//    public void clickTabs()
//    {
//        driver = getChromeDriver();
////        String actualTitle = driver.getTitle();
////        assertEquals( actualTitle, homePage.title);
//    }
}


