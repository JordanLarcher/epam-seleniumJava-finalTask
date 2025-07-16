package com.swaglabs.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void createInstanceDriver(String browserName){
        WebDriver webDriver;

        if(browserName.equalsIgnoreCase("firefox")){
            webDriver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("edge")){
            webDriver = new EdgeDriver();
        } else if(browserName.equalsIgnoreCase("chrome")){
            webDriver = new ChromeDriver();
        } else {
            logger.warn("Browser '{}' is not supported. Defaulting to Firefox instance.", browserName);
            webDriver = new FirefoxDriver();
        }

        driver.set(webDriver);
        logger.info("WebDriver instance created for browser {}", browserName);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver instance has not been created for this thread. Please call createInstance first");
        }
        return driver.get();
    }

    public static void quitDriver(){
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            logger.info("WebDriver instance has been quit and removed for this record");
        }
    }



}
