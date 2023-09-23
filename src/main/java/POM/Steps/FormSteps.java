package POM.Steps;

import POM.Pages.FormPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;


public class FormSteps {
    FormPage formPage = new FormPage();
    @Step("Open Forms Page")
    public FormSteps openUrl(){
        open(formPage.URL);
        return this;
    }
    @Step("Fill firstName field")
    public FormSteps setFirstName(String firstName){
        formPage.firstNameField.setValue(firstName);
        return this;
    }
    @Step("Fill lastName field")
    public FormSteps setLastName(String lastName)  {
        formPage.lastNameField.setValue(lastName);
        return this;

    }
    @Step("Fill mobile number field")
    public FormSteps setMobileNumber(String mobileNumber) {
        formPage.mobileNumberField.setValue(mobileNumber);
        return this;
    }
    @Step("Submit form")
    public FormSteps submit(){
        formPage.userForm.submit();
        return this;
    }
}
