package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AuthenticationScreen extends BaseScreen{

    public AuthenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    //@FindBy (xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputEmail']")
    //AndroidElement editTextEmail;
    @FindBy (id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement editTextEmail;

    //@FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputPassword']")
    //AndroidElement editTextPassword;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement editTextPassword;


    //@FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/loginBtn']")
    //AndroidElement loginButton;

    //@FindBy (xpath= "//*[@text='LOGIN']")
    //AndroidElement loginButton;

    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    AndroidElement loginButton;

    //@FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/regBtn']")
    //AndroidElement registrationButton;
    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    AndroidElement registrationButton;




    public ContactListScreen login(Auth auth){
        should(editTextEmail,5);
        type(editTextEmail,auth.getEmail());
        type(editTextPassword,auth.getPassword());
        loginButton.click();

        return new ContactListScreen(driver);
    }

    public AuthenticationScreen fillEmail(String email){
        should(editTextEmail,7);
        type(editTextEmail,email);
        return this;

    }

    public AuthenticationScreen fillPassword(String password){
        type(editTextPassword,password);
        //return new AuthenticationScreen(driver);
        return this;

    }

    public ContactListScreen submitLogin(){
        loginButton.click();
        //pause(2000);
        return new ContactListScreen(driver) ;
    }


    public ContactListScreen submitRegistration() {
        registrationButton.click();
        return new ContactListScreen(driver);
    }

    public ContactListScreen registration(Auth auth) {
        should(editTextEmail,5);
        type(editTextEmail,auth.getEmail());
        type(editTextPassword,auth.getPassword());
        registrationButton.click();

        return new ContactListScreen(driver);
    }




}


