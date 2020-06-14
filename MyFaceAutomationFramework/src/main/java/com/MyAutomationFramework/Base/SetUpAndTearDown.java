package com.MyAutomationFramework.Base;

import com.MyAutomationFramework.Utils.ConfigUtil;
import com.MyAutomationFramework.Utils.Constants;
import com.MyAutomationFramework.Utils.ScreenShotsUtil;
import com.MyAutomationFramework.Utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class SetUpAndTearDown {
    protected WebDriver driver;
    protected static String url;
    protected static String browser;

    @BeforeSuite
    public void beforeSuite() throws IOException { // exception handling
        ConfigUtil cutil = new ConfigUtil( Constants.RESOURCES_PATH + "/Test-Suits"+"/Config.Properties");

        url = cutil.getProperty("stageUrl");
        browser = cutil.getProperty("browser");
    }

    @BeforeMethod
    public void initializedriver() {
        driver = WebDriverUtil.getDriver(browser);// getDriver() from WebdriverHelper class
        driver.get(url);
        driver.manage().deleteAllCookies();
    }

    @AfterMethod // take screen shots when test fail
    public void closeBrowser(ITestResult testresults) {
        if (ITestResult.FAILURE==testresults.getStatus()) {
            ScreenShotsUtil.screenShots(testresults.getName(),driver);
        }
        driver.quit();
    }
}
