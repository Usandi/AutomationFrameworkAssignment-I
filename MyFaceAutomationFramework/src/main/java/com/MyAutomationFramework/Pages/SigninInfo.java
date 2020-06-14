package com.MyAutomationFramework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SigninInfo extends SigninPage {

        public SigninInfo(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }
        public String getPgTitle() throws InterruptedException { // exception handling
            return driver.getTitle();
        }
}
