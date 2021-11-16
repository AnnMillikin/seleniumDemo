package com.global;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TestBase extends WebDriverManager {

    public HomePage homePage = new HomePage();
    public String searchStrBeginning = ".product_img_link [title*='";
    public String searchStrEnding = "']";

    /**
     * This method places the searchStr parameter in the search box and hit enter
     * then returns the number of items found.
     *
     * Note:  the searchStr is case-sensitive for the AUT
     * @param searchStr
     * @return number of items found
     */
    public int getSearchSize(String searchStr) {
        WebElement search = driver.findElement(homePage.searchBox);
        search.sendKeys(searchStr);
        search.sendKeys(Keys.ENTER);
        String str =   searchStrBeginning+ searchStr +searchStrEnding;
        int size = driver.findElements(By.cssSelector(str)).size();
        return size;
    }

}
