package ReusableClasses;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Reusable_Methods_Loggers {
    //static explicit wait timeout
    public static int timeout = 15;

    public static WebDriver setDriver() throws IOException, InterruptedException {
        //kill all chrome driver instance before opening a new one
        Thread.sleep(1000);
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver88.exe /T");
        Thread.sleep(1000);
        //set the chrome driver location
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver88.exe");
        //setting the chrome options before defining the driver
        ChromeOptions options = new ChromeOptions();
        //set the driver to be maximized
        options.addArguments("start-maximized");
        //set the driver to be incognito mode(private)
        options.addArguments("incognito");
        //run the driver in headless mode
        //options.addArguments("headless");
        //defining the web driver that you will be using
        WebDriver driver = new ChromeDriver(options); //options variable should be passed here
        return driver;
    }//end of get driver

    //reusable method to click on any element on any websites
    public static void click(WebDriver driver,WebElement locator,ExtentTest logger,String elementName){
        //define explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Clicking on " + elementName);
            /** below is locating element explicitly with locator which will be defined by xpath
             in page object classes **/
            wait.until(ExpectedConditions.visibilityOf(locator)).click();
            logger.log(LogStatus.PASS,"Successfully clicked on " + elementName);
        }catch (Exception err){
            System.out.println("Unable to click on " + elementName + ": " + err);
            logger.log(LogStatus.FAIL,"Unable to click on " + elementName);
            getScreenShot(driver,logger,elementName);
        }//end of exception
    }//end of click method

    //reusable method to click by javascriptexecutor on any element on any websites
    public static void clickByJs(WebDriver driver,WebElement locator,ExtentTest logger,String elementName){
        //define explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Clicking on " + elementName);
            /** below is locating element explicitly with locator which will be defined by xpath
             in page object classes **/
            WebElement element= wait.until(ExpectedConditions.visibilityOf(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.log(LogStatus.PASS,"Successfully clicked by Javascript Executor on " + elementName);
        }catch (Exception err){
            System.out.println("Unable to click on " + elementName + ": " + err);
            logger.log(LogStatus.FAIL,"Unable to click by Javascript Executor on " + elementName);
            getScreenShot(driver,logger,elementName);
        }//end of exception
    }//end of clickByJs method

    //reusable method to click on any element on any websites
    public static void sendKeys(WebDriver driver,WebElement locator,String userInput,ExtentTest logger,String elementName){
        //define explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            System.out.println("Entering a test data " + userInput + " on " + elementName);
            /** below is locating element explicitly with locator which will be defined by xpath
            in page object classes **/
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            element.sendKeys(userInput);
            logger.log(LogStatus.PASS,"Successfully entered test data " + userInput + "  on " + elementName);
        }catch (Exception err){
            System.out.println("Unable to enter test data on " + elementName + ": " + err);
            logger.log(LogStatus.FAIL,"Unable to enter test data  on " + elementName);
            getScreenShot(driver,logger,elementName);
        }//end of exception
    }//end of sendKeys method

    //reusable method to click on any element on any websites
    public static void mouseHitEnter(WebDriver driver,WebElement locator,ExtentTest logger,String elementName){
        //define explicit wait
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        Actions actions = new Actions(driver);
        try{
            System.out.println("Hitting enter a on " + elementName);
            /** below is locating element explicitly with locator which will be defined by xpath
             in page object classes **/
            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
            actions.moveToElement(element).sendKeys(Keys.ENTER).perform();
            logger.log(LogStatus.PASS,"Successfully hit enter on " + elementName);
        }catch (Exception err){
            System.out.println("Unable to hit enter on " + elementName + ": " + err);
            logger.log(LogStatus.FAIL,"Unable to hit enter on " + elementName);
            getScreenShot(driver,logger,elementName);
        }//end of exception
    }//end of mouseHitEnter method

    //method to capture screenshot when logger fails
    public static void getScreenShot(WebDriver wDriver,ExtentTest logger,String imageName) {
        try {
            String fileName = imageName + ".png";
            String directory = "src/main/java/HTML_Report/Screenshots/";
            File sourceFile = ((TakesScreenshot) wDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture("Screenshots/" + fileName);
            logger.log(LogStatus.FAIL, "", image);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Error Occured while taking SCREENSHOT!!!");
            e.printStackTrace();
        }
    }//end of getScreenShot method

    //method to capture general screenshot
    public static void getScreenShotRegular(WebDriver wDriver,ExtentTest logger,String imageName) {
        try {
            String fileName = imageName + ".png";
            String directory = "src/main/java/HTML_Report/Screenshots/";
            File sourceFile = ((TakesScreenshot) wDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture("Screenshots/" + fileName);
            logger.log(LogStatus.INFO, "", image);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Error Occured while taking SCREENSHOT!!!");
            e.printStackTrace();
        }
    }//end of getScreenShotRegular method

    // Generates a random int with n digits
    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }//end of generateRandomDigits method

}//end of java class
