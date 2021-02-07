package ReusableClasses;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class AbstractTestngClass {
    //declare all the global variables outside of the methods
    public static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest logger;

    @BeforeSuite
    public void setPrecondition() throws IOException, InterruptedException {
        //set the report path here
        reports = new ExtentReports("src/main/java/HTML_Report/AutomationReport.html",true);
        //set the chrome driver here
        driver = Reusable_Methods_Loggers.setDriver();
    }//end of before suite


    //to be able to pick up the name of your test classes dynamically we need to use beforemethod
    @BeforeMethod
    public void captureTestName(Method methodName) throws IOException, InterruptedException {
        //start the test for each @test by capturing the method name for extent report from test class
        logger = reports.startTest(methodName.getName());
    }//end of before method

    @AfterMethod
    public void endTest(){
        //end the test for each @test method on test class
        reports.endTest(logger);
        //report.flush should go here so we can append each test on the report
        reports.flush();
    }//end of after method

    @AfterSuite
    public void endSession(){
        //driver.quit();
    }//end of afterSuite





}//end of abstract class
