import PageObjectClasses.BaseClass;
import ReusableClasses.AbstractTestngClass;
import ReusableClasses.Reusable_Methods_Loggers;
import org.testng.annotations.Test;

public class CrowdStreet_CreateAnAccount_And_InvestNow extends AbstractTestngClass {

    /** Test Data **/
    String email = "crowdstreettester"+Reusable_Methods_Loggers.generateRandomDigits(4)+"@zmail.com";
    String firstName = "Crowd " + Reusable_Methods_Loggers.generateRandomDigits(3);
    String lastName = "Street " + Reusable_Methods_Loggers.generateRandomDigits(3);
    String password = "abc123Pas$";
    String phoneNumber = "212"+Reusable_Methods_Loggers.generateRandomDigits(3)+"4444";
    String streetAddress = "411 John Street";
    String city = "Brooklyn";
    String state = "New York";
    String zipcode = "11218";
    String country = "United States";
    @Test
    public void CreateAnAccountProcess() throws InterruptedException {
       BaseClass.registrationClass().createNewAccount(email,firstName,lastName,password,phoneNumber,streetAddress,
       city,state,zipcode,country);
    }//end of test 1

    @Test(dependsOnMethods = "CreateAnAccountProcess" )
    public void DirectiveOfferingInvestNowProcess() throws InterruptedException {
        BaseClass.investmentClass().investNowProcess("4000");
    }//end of test 2

}//end of java class
