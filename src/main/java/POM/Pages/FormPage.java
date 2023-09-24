package POM.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FormPage {

    public final String URL = "https://demoqa.com/automation-practice-form";
    public SelenideElement
            userForm = $(By.id("userForm")),
            firstNameField = $(By.id("firstName")),
            lastNameField = $(By.id("lastName")),
            mobileNumberField = $(By.id("userNumber"));
}
