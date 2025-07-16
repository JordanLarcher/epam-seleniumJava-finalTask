package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LoginPage {

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoginPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;


    // Locators
    private final By usernameInput = By.xpath("//input[@id='user-name']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");




    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void enterUsername(String username) {
        log.info("Entering username: {}", username);
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password){
        log.info("Entering password: {}", password);
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton(){
        log.info("Clicking login button");
        WebElement loginButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        loginButtonElement.click();
    }

    public void clearUsername(){
        log.info("Clearing username");
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        usernameElement.clear();
    }

    public void clearPassword(){
        log.info("Clearing password");
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordElement.clear();
    }

    public String getErrorMessage(){
        log.info("Retrieving error message");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorElement.getText();
    }

    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new HomePage(driver);
    }

}
