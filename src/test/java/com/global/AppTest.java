package com.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.global.testBase.ScreenshotListener;
import com.global.testBase.TestBase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase
{
    WebDriver driver;

    @BeforeAll
    static void setupClass() {

    }

    @BeforeEach
    void setupTest() {
        System.out.println("=== BeforeEach ===");
          driver = setChromeDriver();
    }

    @AfterEach
    void quit() {
        tearDown(driver);
    }
    /**
     * open website
     */
    @Test
    public void openUrl()
    {
        String actualTitle = driver.getTitle();
        assertEquals( actualTitle, homePage.title); //"Google"); //
    }

    /**
     * check login page title
     */
    @Test
    public void checkLoginPage()
    {
        driver = getChromeDriver();
        driver.findElement(homePage.signIn).click();
        String actualTitle = driver.getTitle();
        System.out.println("actualTitle: "+actualTitle);
        System.out.println("expectedTitle: "+homePage.createAccountTitle);
        assertEquals(actualTitle,homePage.createAccountTitle);
    }

    /**
     * search for items
     */
    @Test
    public void searchBox()
    {
        driver = getChromeDriver();
        int size = getSearchSize("dress");
        assertTrue(size > 0, size + "dress(es) found");
    }

    /**
     * select items, add items to cart and assert the total price is > 0
     */
    @Test
    public void addToShoppingCart() throws InterruptedException {
        driver = getChromeDriver();
        String str = "Dress";
        int size = getSearchSize(str);
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
            // continue shopping
            driver.findElement(homePage.continueShoppingBtn).click();
//                items = driver.findElements(By.cssSelector(searchStrBeginning+ str + searchStrEnding));
        }
        // check cart
        driver.findElement(homePage.openCart).click();
        String totalPriceStr = driver.findElement(homePage.totalPrice).getText();
        totalPriceStr= totalPriceStr.replace("$", "");
        double totalPrice = Double.valueOf(totalPriceStr);
        System.out.println(totalPrice);
        assertTrue( totalPrice>0, "Total price: " + totalPrice);
    }

//    /**
//     * make payments TODO
//     */
//    @Test
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
//    @Test
//    public void clickTabs()
//    {
//        driver = getChromeDriver();
////        String actualTitle = driver.getTitle();
////        assertEquals( actualTitle, homePage.title);
//    }
}


