package com.MyAutomationFramework.Test;

import com.MyAutomationFramework.Base.SetUpAndTearDown;
import com.MyAutomationFramework.Base.TermsPage;
import com.MyAutomationFramework.Pages.SigninInfo;
import com.MyAutomationFramework.Pages.SigninPage;
import com.MyAutomationFramework.Utils.ExcelHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/* Automation Framework using PageObject, DataDriven, findelement/findelements, windowhandles,
popups/alerts, dropdowns, waits, actions, loggers, exception handling, screenshots, desired capabilities,
close/quit, getText, getAttibute, getCssValue, isEnabled, isDisplayed, isSelected.

go to Facebook.com
enter info- firstname, lastname, email, password, birthday month,day,year and gender
click on terms link
hover on the element
get cssVallue, page title and assert

TermsPageTest - click on Terms link, hover on the element, get color, page title and assert
 */

public class SigninPageTest extends SetUpAndTearDown {
     private static Logger log = LogManager.getLogger(ExcelHelper.class); // initialize logger

     SigninPage spage; // initialize SigninPage

    @DataProvider(name="signinData") // xlsx data to enter info to sign in
    public Object[][] testData() {
        return ExcelHelper.importExcelData("DataForSignin.xlsx", "Sheet1");
    }
    @Test(dataProvider = "signinData") // enter sign in info
    public void SigninDataTest(String fname, String lname, String mail, String pword, int bmonth, int bday, int byear) throws InterruptedException {
        spage = new SigninPage(driver);
        SigninInfo data = spage.SigninData(fname, lname, mail, pword, bmonth, bday, byear);
    }

    @DataProvider(name="actualData") // data for cssValue(color) and page title
    public Object[][] aData() {
        return new String[][] { {"rgba(255, 255, 255, 1)", "Terms of Service"} };
    }

    @Test(dataProvider = "actualData") // assert cssValue and page title
    public void TermsPageTest(String acolor, String atitle) throws InterruptedException {
        spage = new SigninPage(driver);
        String info[] = spage.TermsPage();
        Assert.assertEquals(info[0],acolor);
        Assert.assertEquals(info[1],atitle);
        log.info("The color of the element in --"+info[1]+ "-- is "+info[0]);
    }
}