package com.MyAutomationFramework.Pages;

import com.MyAutomationFramework.Base.TermsPage;
import com.MyAutomationFramework.Utils.ExcelHelper;
import com.MyAutomationFramework.Utils.WaitsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class SigninPage extends TermsPage {
    private static Logger log = LogManager.getLogger(ExcelHelper.class); // using loggers

    @FindBy(how = How.NAME,using = "firstname")
    WebElement fName;
    @FindBy(how = How.NAME,using = "lastname")
    WebElement lName;
    @FindBy(how = How.NAME,using = "reg_email__")
    WebElement email;
    @FindBy(how = How.NAME,using = "reg_passwd__")
    WebElement pword;
    @FindBy(how = How.NAME,using = "birthday_month")
    WebElement bdayMonth;
    @FindBy(how = How.NAME,using = "birthday_day")
    WebElement bdayDay;
    @FindBy(how = How.NAME,using = "birthday_year")
    WebElement bdayYear;
    @FindBy(how = How.ID,using = "birthday-help")
    WebElement bdayHelp;
    @FindBy(how = How.XPATH,using = "//*[@id=\"globalContainer\"]/div[3]/div/div/div/div[1]/div[1]/div")
    WebElement bdayMessage;
    @FindBy(how = How.CSS,using = "._572u._5lnf > a[role='button']")
    WebElement bdayHelpClose;
    @FindBy(how = How.ID,using = "u_0_6")
    WebElement femaleRButton;
    @FindBy(how = How.ID, using = "u_0_17")
    WebElement signupbutton;

    //auto create matching constructor for extending the BasePage class, extending the initialization-dependency injection
    public SigninPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this); // to initialize all the web elements using page factory
    }

    public SigninInfo SigninData(String fn, String ln, String em, String pw, int bmonth, int bday, int byear) {
        fName.sendKeys(fn);
        lName.sendKeys(ln);
        email.sendKeys(em);
        pword.sendKeys(pw);

        Select bdayM = new Select(bdayMonth); // select dropdown
        bdayM.selectByIndex(bmonth);

        Select bdayD = new Select(bdayDay); // select dropdown
        bdayM.selectByIndex(bday);

        Select bdayY = new Select(bdayYear); // select dropdown
        bdayM.selectByIndex(byear);

        bdayHelp.click(); // to use popup/alert
        boolean text = bdayMessage.isDisplayed(); // using isDisplayed
        if (text == true) {
            String text1 = bdayMessage.getText();
            WaitsUtil.imwait(driver, 30, TimeUnit.SECONDS); // using waits
            log.info("Birthday message is : "+text1);
        } else {
            log.info("Text not visible");
        }
        bdayHelpClose.click();
        WaitsUtil.exwait(driver,20,femaleRButton);
        if (femaleRButton.isEnabled() && !femaleRButton.isSelected()) { // using isSelected, isEnabled
            femaleRButton.click();
        } else {
            log.info("No such element");
        }

        return new SigninInfo(driver);
    }
}
