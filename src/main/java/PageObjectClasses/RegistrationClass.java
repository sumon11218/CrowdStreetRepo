package PageObjectClasses;

import ReusableClasses.AbstractTestngClass;
import ReusableClasses.Reusable_Methods_Loggers;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegistrationClass extends AbstractTestngClass {

    ExtentTest logger;
    //constructor method to inherit the class name and you call the driver and logger
    //to be used locally for this class
    public RegistrationClass(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.logger = AbstractTestngClass.logger;
    }//end of constructor method

    /** defining all the xpath locator by @FindBy method **/
    @FindBy(xpath = "//*[@id='create_account_email']")
    WebElement emailField;
    @FindBy(xpath = "//*[text()='First Name']/following::input")
    WebElement firstNameField;
    @FindBy(xpath = "//*[text()='Last Name']/following::input")
    WebElement lastNameField;
    @FindBy(xpath = "//*[text()='Create a Password']/following::input")
    WebElement createPasswordField;
    @FindBy(xpath = "//*[text()='Confirm Password']/following::input")
    WebElement confirmPasswordField;
    @FindBy(xpath = "//*[text()='Phone Number']/following::input")
    WebElement phoneNumField;
    @FindBy(xpath = "//*[text()='Street Address']/following::input")
    WebElement streetAddressField;
    @FindBy(xpath = "//*[text()='City']/following::input")
    WebElement cityField;
    @FindBy(xpath = "//*[text()='State']/following::input")
    WebElement stateDropdown;
    @FindBy(xpath = "//*[text()='Zip or Postal Code']/following::input")
    WebElement zipField;
    @FindBy(xpath = "//*[text()='Country']/following::div")
    WebElement countryDropdown;
    @FindBy(xpath = "//div[contains(@class,'check_1fb41')]")
    List<WebElement> checkboxList;
    @FindBy(xpath = "//div[contains(@class,'radio_e1a')]")
    List<WebElement> radioList;
    @FindBy(xpath = "//iframe[contains(@name,'a-')]")
    WebElement switchToFrame;
    @FindBy(css = "div.recaptcha-checkbox-border")
    WebElement recaptchaCheckbox;
    @FindBy(xpath = "//button[text()='Sign Up']")
    WebElement signupButton;
    @FindBy(xpath = "//h2[contains(@class,'title margin-top-') and contains(text(),'Congrats,')]")
    WebElement popUpMessage;

    public void createNewAccount(String enterEmail, String enterFirstName, String enterLastName, String createPassword, String enterPhone, String enterAddress,
        String enterCity, String selectState, String enterZip, String selectCountry) throws InterruptedException {
        System.out.println("Navigating to create-account page");
        logger.log(LogStatus.INFO,"Navigating to create-account page");
        driver.navigate().to("https://test.crowdstreet.com/invexp/accounts/create-account/");
        Thread.sleep(2500);
        Reusable_Methods_Loggers.sendKeys(driver,emailField,enterEmail,logger,"Email Field");
        Reusable_Methods_Loggers.sendKeys(driver,firstNameField,enterFirstName,logger,"First Name Field");
        Reusable_Methods_Loggers.sendKeys(driver,lastNameField,enterLastName,logger,"Last Name Field");
        Reusable_Methods_Loggers.sendKeys(driver,createPasswordField,createPassword,logger,"Create Password Field");
        Reusable_Methods_Loggers.sendKeys(driver,confirmPasswordField,createPassword,logger,"Confirm Password Field");
        Reusable_Methods_Loggers.sendKeys(driver,phoneNumField,enterPhone,logger,"Phone Number Field");
        Reusable_Methods_Loggers.sendKeys(driver,streetAddressField,enterAddress,logger,"Street Address Field");
        Reusable_Methods_Loggers.sendKeys(driver,cityField,enterCity,logger,"City Field");
        Reusable_Methods_Loggers.click(driver,stateDropdown,logger,"State Dropdown");
        Reusable_Methods_Loggers.click(driver,driver.findElement(By.xpath("//*[@class='text' and contains(text(),'"+selectState+"')]")),logger,"State Text Value");
        Reusable_Methods_Loggers.sendKeys(driver,zipField,enterZip,logger,"Zip Code Field");
        Reusable_Methods_Loggers.click(driver,countryDropdown,logger,"Country Dropdown");
        Reusable_Methods_Loggers.click(driver,driver.findElement(By.xpath("//*[@class='text' and contains(text(),'"+selectCountry+"')]")),logger,"Country Text Value");
        Reusable_Methods_Loggers.clickByJs(driver,radioList.get(1),logger,"No Radio");
        Reusable_Methods_Loggers.clickByJs(driver,checkboxList.get(0),logger,"Terms Of Service Checkbox");
        Reusable_Methods_Loggers.clickByJs(driver,checkboxList.get(1),logger,"Investment Opportunity Checkbox");
        Thread.sleep(1000);
        System.out.println("Switching to Recaptcha Iframe");
        logger.log(LogStatus.INFO,"Switching to Recaptcha Iframe");
        driver.switchTo().frame(switchToFrame);
        Reusable_Methods_Loggers.click(driver,recaptchaCheckbox,logger,"Recaptcha Checkbox");
        driver.switchTo().defaultContent();
        Reusable_Methods_Loggers.click(driver,signupButton,logger,"Sign Up Button");
        Thread.sleep(2500);
        WebDriverWait wait = new WebDriverWait(driver,Reusable_Methods_Loggers.timeout);
        try {
            if (wait.until(ExpectedConditions.visibilityOf(popUpMessage)).isDisplayed()) {
                System.out.println("PASS: Pop up message appears stating Congrats, " + enterFirstName + "!");
                logger.log(LogStatus.PASS,"Pop up message appears stating Congrats, " + enterFirstName + "!");
                Reusable_Methods_Loggers.getScreenShotRegular(driver,logger,"Popup Message");
            }//end of if
        } catch (Exception err) {
                System.out.println("FAIL: Unable to locate pop up message for congrats: " + err);
                logger.log(LogStatus.FAIL,"Unable to locate pop up message for congrats: " + err);
                Reusable_Methods_Loggers.getScreenShot(driver,logger,"Popup Message Issue");
        }//end of pop up exception
    }//end of createNewAccount



}//end of java class
