package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoginPage.class);
    private final WebDriver driver;


    // Locators
    private final By usernameInput = By.xpath("//input[@id='user-name']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");



    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        log.info("Entering username: {}", username);
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password){
        log.info("Entering password: {}", password);
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton(){
        log.info("Clicking login button");
        driver.findElement(loginButton).click();
    }

    public void clearUsername(){
        log.info("Clearing username");
        driver.findElement(usernameInput).clear();
    }

    public void clearPassword(){
        log.info("Clearing password");
        driver.findElement(passwordInput).clear();
    }

    public String getErrorMessage(){
        log.info("Retrieving error message");
        return driver.findElement(errorMessage).getText();
    }

    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new HomePage(driver);
    }

}
