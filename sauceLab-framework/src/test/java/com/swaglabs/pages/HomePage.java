package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement title;
    @FindBy(xpath ="//button[text()='Add to cart']" )
    private List<WebElement> addToCartButtons;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement cart;

    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }


    public String getTitle(){
        logger.info("Retrieving home page title: {}", title);
        wait.until(ExpectedConditions.visibilityOf(title));
        return title.getText();
    }


    /**
     * Add a specific number of products to the cart
     * @param numberOfProducts - This is the number of products that you want to add to the cart
     */

    public void addProductsToCart(int numberOfProducts){
        logger.info("Adding {} products to cart", numberOfProducts);

        //Then wait till all "Add to cart" buttons are visible
        wait.until(ExpectedConditions.visibilityOfAllElements(addToCartButtons));
        if(addToCartButtons.size() < numberOfProducts){
            throw new IllegalArgumentException("Not enough products to be added to cart.");
        }

        for(int i = 0; i < numberOfProducts; i++){
            addToCartButtons.get(i).click();
            logger.info("Adding {} products to cart", numberOfProducts);
        }
    }

    /**
     * Get the number of items in the cart
     * @return Returns the number of items in the cart
     */
    public String getCartItemCount(){
        // Wait for the counter to appear
        wait.until(ExpectedConditions.visibilityOf(cart));
        String count = cart.getText();
        logger.info("Number of items in the cart: {}", count);
        return count;
    }

}
