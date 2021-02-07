package PageObjectClasses;

import ReusableClasses.AbstractTestngClass;
import ReusableClasses.Reusable_Methods_Loggers;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InvestmentClass extends AbstractTestngClass{
    ExtentTest logger;
    //constructor method to inherit the class name and you call the driver and logger
    //to be used locally for this class
    public InvestmentClass(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.logger = AbstractTestngClass.logger;
    }//end of constructor method

    /** defining all the xpath locator by @FindBy method **/
    @FindBy(xpath = "//div[contains(@class,'icon dialog-close-button')]")
    WebElement popupCloseIcon;
    @FindBy(xpath = "//*[@tooltip='investment directive offering']")
    WebElement directiveOfferingImageLink;
    @FindBy(xpath = "//*[@id='btn-invest-offer']")
    WebElement investNowButton;
    @FindBy(xpath = "//*[@id='amountRequested']")
    WebElement offerAmountField;
    @FindBy(xpath = "//div[@class='_check_1fb41']")
    List<WebElement> checkboxList;
    @FindBy(xpath = "//button[@id='submitOfferButton']")
    WebElement submitOfferButton;
    @FindBy(xpath = "//*[@id='offer-status' and text()='Your offer has been reviewed and approved.']")
    WebElement offerStatusMessage;

    public void investNowProcess(String enterOfferAmount) throws InterruptedException {
        //close dialog box
        Reusable_Methods_Loggers.clickByJs(driver,popupCloseIcon,logger,"Close Icon");
        Thread.sleep(2800);
        //click on Directive Investment Image link
        Reusable_Methods_Loggers.click(driver,directiveOfferingImageLink,logger,"Directive Offering Invest Image Link");
        Thread.sleep(2500);
        //click on Invest Now
        Reusable_Methods_Loggers.clickByJs(driver,investNowButton,logger,"Invest Now Button");
        //Enter Offer Amount
        Reusable_Methods_Loggers.sendKeys(driver,offerAmountField,enterOfferAmount,logger,"Offer Amount Field");
        //click on acknowledge checkbox
        Reusable_Methods_Loggers.clickByJs(driver,checkboxList.get(0),logger,"Acknowledge Checkbox");
        //click on I have Read checkbox
        Reusable_Methods_Loggers.clickByJs(driver,checkboxList.get(0),logger,"I Have Read Checkbox");
        //click on Submit Offer
        Reusable_Methods_Loggers.click(driver,submitOfferButton,logger,"Submit Offer Button");
        Thread.sleep(2500);
        WebDriverWait wait = new WebDriverWait(driver,Reusable_Methods_Loggers.timeout);
        try {
            if (wait.until(ExpectedConditions.visibilityOf(offerStatusMessage)).isDisplayed()) {
                System.out.println("PASS: Message Appears: 'Your offer has been reviewed and approved.'");
                logger.log(LogStatus.PASS,"Pop up message appears: 'Your offer has been reviewed and approved.'");
                Reusable_Methods_Loggers.getScreenShotRegular(driver,logger,"Approved Message");
            }//end of if
        } catch (Exception err) {
            System.out.println("FAIL: Unable to locate approved message:" + err);
            logger.log(LogStatus.FAIL,"Unable to locate approved message");
            Reusable_Methods_Loggers.getScreenShot(driver,logger,"Approved message Issue");
        }//end of Approved Message  exception
    }//end of investNowProcess method












}//end of java class
