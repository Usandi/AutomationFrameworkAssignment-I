package com.MyAutomationFramework.Base;

import com.MyAutomationFramework.Utils.WaitsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class TermsPage {  //page object
    protected WebDriver driver;

    //factory annotations
    @FindBy(how = How.ID,using = "terms-link")
    WebElement termslink;
    @FindBy(how = How.XPATH,using = "//div[@id='u_0_c']/div/div[1]/div[@class='_b_1']/div[@role='presentation']")
    WebElement firstElement;

    public TermsPage(WebDriver driver) { //constructor
        this.driver = driver; //initialize the driver object
    }

    public String[] TermsPage() throws InterruptedException { //returns CssValue to TermsPageTest in SigninPageTest

        String css = null;
        String ptitle = null;
        termslink.click(); //using factory annotation
        String parentwindow = driver.getWindowHandle(); // using window handles
        Set<String> handles = driver.getWindowHandles();
        for (String windowhandle : handles) {
            if (!windowhandle.equals(parentwindow)) { // if not parent window
                driver.switchTo().window(windowhandle); // switch to other window
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,800)"); // scroll down
                Actions hoverover = new Actions(driver); // using actions
                hoverover.moveToElement(firstElement).build().perform();
                css = firstElement.getCssValue("color"); // get cssValue - color
                WaitsUtil.exwait(driver,20,firstElement);
                ptitle = driver.getTitle();
                driver.close();
                driver.switchTo().window(parentwindow);
            }
        }
        return new String[] {css,ptitle};
    }
}
