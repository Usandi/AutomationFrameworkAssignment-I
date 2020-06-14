package com.MyAutomationFramework.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverUtil {
    //Factory design pattern
    public static WebDriver getDriver(String browserName) {
        WebDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities(); // using desired capabilities
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
        System.setProperty("webdriver.chrome.driver",Constants.RESOURCES_PATH +"\\WebDrivers\\chromedriver1.exe");
        driver = new ChromeDriver(capabilities);

        return driver;
    }
}
