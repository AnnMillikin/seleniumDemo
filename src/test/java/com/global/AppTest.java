package com.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.global.testBase.ScreenshotListener;
import com.global.testBase.TestBase;
import junit.textui.TestRunner;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AppTest extends TestBase
{
    WebDriver driver;
//
//    public AppTest() {
//        System.out.println("=== AppTest constructor ===");
//        final JUnitCore junit = new JUnitCore();
//        junit.addListener(new ScreenshotListener());
//        junit.run();
//    }

    @BeforeAll
    static void setupListener() {
//        final JUnitCore junit = new JUnitCore();  // not working
//        junit.addListener(new ScreenshotListener());
//        junit.run();
    }

    @BeforeEach
    void setupTest() {
        System.out.println("=== BeforeEach ===");
          driver = setChromeDriver();
//        JUnitCore junit = new JUnitCore();
//        junit.addListener(new ScreenshotListener((TakesScreenshot) getChromeDriver()));
    }

    @AfterEach
    void quit() {
        tearDown(driver);
    }

//    @AfterAll // TODO
//    void removeListener(){ junit.removeListener(new ScreenshotListener);}

    @Test
    public void failMe() throws Exception {
        throw new Exception("fail fucker"); // doesn't trigger screenshot
        //assertTrue(false); // doesn't trigger screenshot
        }

    /**
     * open website
     */
//    @Test TODO remove comment
    public void openUrl()
    {
        String actualTitle = driver.getTitle();
        assertEquals( actualTitle, homePage.title); //"Google"); //
    }

    /**
     * check login page title
     */
//    @Test TODO remove comment
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
//    @Test TODO remove comment
    public void searchBox()
    {
        driver = getChromeDriver();
        int size = getSearchSize("Dress");
        assertTrue(size > 0, size + " Dress(es) found, expected: > 0");
    }

    /**
     * select items, add items to cart and assert the total price is > 0
     */
//    @Test  TODO remove comment
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
        }
        // check cart after escape key
        Actions action = new Actions(driver);
        WebElement logo = driver.findElement(homePage.headerLogo);
        action.moveToElement(logo).pause(java.time.Duration.ofSeconds(1)).sendKeys(Keys.ESCAPE).pause(java.time.Duration.ofSeconds(1)).perform();
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


