package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
    private final By title = By.xpath("//div[@class='app_logo']");
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public String getTitle(){
        logger.info("Retrieving home page title: {}", title);
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return titleElement.getText();
    }
}
