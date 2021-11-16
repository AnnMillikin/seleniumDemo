package com.global;
import org.openqa.selenium.By;

public class HomePage {
    /**
     * url: http://automationpractice.com/index.php
     *
     * Web element locators for the home page
     */
    public String title = "My Store";
    public By headerLogo = By.id("header_logo");

    public By searchBox = By.id("search_query_top");

    public By openCart = By.cssSelector("a[title='View my shopping cart']");
    public By addToCartBtn = By.cssSelector("a[title='Add to cart']");
    public By continueShoppingBtn = By.cssSelector(".btn[title = 'Continue shopping']");
    public By totalPrice = By.cssSelector("#total_price");

    public By signIn = By.cssSelector(".login");

    // create account
    public String createAccountTitle = "Login - My Store";
    public By emailInput = By.cssSelector("input[name='email_create']");
    public By createAccountBtn = By.id("SubmitCreate");

}
