package com.global.functional;
import com.global.testBase.TestBase;

import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

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

    /**
     * check for broken links - this test took 16 min to run checking 149
     */
    @Test(groups = {"long"})
    public void checkForBrokenLinks() throws Exception {
        String homePage = getUrl();
        String url;
        HttpURLConnection huc = null;
        int respCode = 200;
        int responseGreaterThan2seconds=0;
        int numberBrokenLinks=0;
        List<WebElement> links = driver.findElements(By.tagName("a"));
        Reporter.log("\nabout to check the response code of "+ links.size()+ " links", true);

        Iterator<WebElement> it = links.iterator();

        // write response times to an excel sheet
        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet
                = workbook.createSheet(" Response Times ");

        // creating a row object
        XSSFRow row;

        // This data needs to be written (Object[])
        Map<String, Object[]> responseTimes
                = new TreeMap<String, Object[]>();
        // header row
        responseTimes.put(
                "1",
                new Object[] { "Link",  "Response Time (milliseconds)", "Response Time (seconds)" });
        int rowCount=2;
        Set<String> keyid = responseTimes.keySet();
        int rowid = 0;

        while(it.hasNext()){
            url = it.next().getAttribute("href");
            Reporter.log("\nchecking url: " + url, true);

            if(url == null || url.isEmpty()){
                Reporter.log("\nURL is either not configured for anchor tag or it is empty", true);
                continue;
            }

            if(!url.startsWith(homePage)){
                Reporter.log("\nURL belongs to another domain, skipping it.", true);
                continue;
            }


                huc = (HttpURLConnection)(new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                long startTime = System.nanoTime();
                huc.connect();
                respCode = huc.getResponseCode();
                long elapsedTime = System.nanoTime() - startTime;
                Reporter.log(url + " response time: " +elapsedTime/1000000 +" milliseconds, or " + elapsedTime/1000000000 + " seconds", true);
                responseTimes.put(Integer.toString(rowCount), new Object[] { url, Long.toString(elapsedTime/1000000),
                        Long.toString(elapsedTime/1000000000) });
                rowCount++;
                if(elapsedTime/1000000000>2) {responseGreaterThan2seconds++;}
                if(respCode >= 400){
                    Reporter.log(url+" is a broken link", true);
                    numberBrokenLinks++;
                }
                else{
                    Reporter.log(url+" is a valid link", true);
                }
        }
        Reporter.log("\n\nNumber of broken links: "+numberBrokenLinks, true); // TODO write to spreadsheet
        Reporter.log("\n\nNumber of links with greater than 2 second response time: "+responseGreaterThan2seconds, true);

        // writing the data into the sheets...
        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = responseTimes.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        FileOutputStream out = null;
        out = new FileOutputStream(
                "src/test/screenshots/link_response_times.xlsx");

        workbook.write(out);
        out.close();
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


