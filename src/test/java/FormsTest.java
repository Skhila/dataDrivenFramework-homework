import ConfigClasses.ConfigSelenide;
import POM.Data.Dao.UserDataProvider;
import POM.Steps.FormSteps;
import org.testng.annotations.Test;


public class FormsTest extends ConfigSelenide {
    FormSteps formSteps = new FormSteps();

    @Test(dataProvider = "userDataProvider", dataProviderClass = UserDataProvider.class)
    public void formsTest(String firstName, String lastName, String phoneNumber){

        formSteps.openUrl().setFirstName(firstName).setLastName(lastName).setMobileNumber(phoneNumber).submit();
    }

}