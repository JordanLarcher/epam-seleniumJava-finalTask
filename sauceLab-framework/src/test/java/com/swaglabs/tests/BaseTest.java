package com.swaglabs.tests;


import com.swaglabs.utils.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Execution(ExecutionMode.CONCURRENT)
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeEach
    void setup() {

    }

    protected void setupTest(String browserName){
        logger.info("Setup Test Browser: " + browserName);
        WebDriverFactory.createInstanceDriver(browserName);
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    void tearDown() {
        logger.info("Tear down Test");
        WebDriverFactory.quitDriver();
    }

}
