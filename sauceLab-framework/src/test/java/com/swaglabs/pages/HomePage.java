package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

     private final WebDriver driver;

     private final By title = By.xpath("//div[@class='app_logo']");

     public HomePage(WebDriver driver) {
         this.driver = driver;
     }


     public String getTitle(){
         return driver.findElement(title).getText();
     }


}
