package PageObjectClasses;

import ReusableClasses.AbstractTestngClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseClass extends AbstractTestngClass {
    //constructor method to inherit the class name and you call the driver and logger
    //to be used locally for this class
    public BaseClass(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
    }//end of constructor method

    //create static object method for page classes
    //registration class
    public static RegistrationClass registrationClass(){
        RegistrationClass registrationClass = new RegistrationClass(driver);
        return registrationClass;
    } //end of registration class object

    //investment class
    public static InvestmentClass investmentClass(){
        InvestmentClass investmentClass = new InvestmentClass(driver);
        return investmentClass;
    } //end of investment class object

}//end of java class
