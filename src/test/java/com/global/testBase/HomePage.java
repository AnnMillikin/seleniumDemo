package com.global.testBase;
import org.openqa.selenium.By;

public class HomePage {
    /**
     * url: http://automationpractice.com/index.php
     *
     * Web element locators for the home page
     */
    public String title = "My Store";

    public By searchBox = By.id("search_query_top");
    public By cart = By.cssSelector("a[title='View my shopping cart']");

    public By signIn = By.cssSelector(".login");

    // create account
    public String createAccountTitle = "Login - My Store";
    public By emailInput = By.cssSelector("input[name='email_create']");
    public By createAccountBtn = By.id("SubmitCreate");

}
