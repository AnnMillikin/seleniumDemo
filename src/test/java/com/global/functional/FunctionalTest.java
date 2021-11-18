package com.global.functional;
import com.global.testBase.TestBase;

import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class FunctionalTest extends TestBase
{
    /**
     * check login page title
     */
    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
    public void checkLoginPage()
    {
        driver.findElement(homePage.signIn).click();
        String actualTitle = driver.getTitle();
        Reporter.log("\nactualTitle: "+actualTitle);
        Reporter.log("\nexpectedTitle: "+homePage.createAccountTitle);
        assertEquals(actualTitle,homePage.createAccountTitle);
    }

    /**
     * search for items
     */
    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
    public void searchBox()
    {
        int size = getSearchSize("Dress");
        assertTrue(size > 0, size + " Dress(es) found, expected: > 0");
    }

    /**
     * select items, add items to cart and assert the total price is > 0
     */
    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
    public void addToShoppingCart() throws InterruptedException {
        String str = "Dress";
        int size = getSearchSize(str);
        assertTrue(size > 0, "No items found searching for: "+ str);
        List<WebElement> items = driver.findElements(By.cssSelector(searchStrBeginning+ str + searchStrEnding));
        for (int i=0; i<items.size(); i++) {
            Actions action = new Actions(driver);
            action.moveToElement(items.get(i)).pause(java.time.Duration.ofSeconds(1)).perform();
            /*
            Since there are multiple Add To Cart buttons, click only the one that is displayed.
             */
            List<WebElement> addButtons = driver.findElements(homePage.addToCartBtn);
            for(WebElement button: addButtons){
                if(button.isDisplayed()) {button.click();}
            }
            Thread.sleep(1000);
            // continue shopping
            List<WebElement> continueShoppingButtons = driver.findElements(homePage.continueShoppingBtn);
            for(WebElement button: continueShoppingButtons){
                if(button.isDisplayed()) {button.click();}
            }
        }
        // check cart
        driver.findElement(homePage.headerLogo).click();
        driver.findElement(homePage.openCart).click();
        String totalPriceStr = driver.findElement(homePage.totalPrice).getText();
        totalPriceStr= totalPriceStr.replace("$", "");
        double totalPrice = Double.valueOf(totalPriceStr);
        Reporter.log("totalPrice: "+totalPriceStr, true);
        assertTrue( totalPrice>0, "Total price: " + totalPrice);
    }

//    /**
//     * make payments TODO
//     */
//    @Test(groups = {"functional"}, dependsOnGroups = {"smoke"})
//    public void makePayments()
//    {
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
////        String actualTitle = driver.getTitle();
////        assertEquals( actualTitle, homePage.title);
//    }
}


