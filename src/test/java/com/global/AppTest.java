package com.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.global.testBase.WebDriverManager;
import com.global.testBase.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class AppTest extends WebDriverManager
{
    WebDriver driver;
    HomePage homePage = new HomePage();

    @BeforeAll
    static void setupClass() {

    }

    @BeforeEach
    void setupTest() {
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
        WebElement search = driver.findElement(homePage.searchBox);
        search.sendKeys("dress");
        search.sendKeys(Keys.ENTER);
        int size = driver.findElements(By.partialLinkText("Dress")).size();
        assertTrue(size > 0, size + "dresses found");
    }
//
//    /**
//     * select items, add/delete from cart and view cart TODO
//     */
//    @Test
//    public void shoppingCart()
//    {
//        driver = getChromeDriver();
////        String actualTitle = driver.getTitle();
////        assertEquals( actualTitle, homePage.title);
//    }
//
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


