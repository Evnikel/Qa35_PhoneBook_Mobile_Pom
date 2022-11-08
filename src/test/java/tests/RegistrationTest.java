package tests;

import config.AppiumConfig;
import io.appium.java_client.android.AndroidElement;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RegistrationTest extends AppiumConfig {

    @Test
    public void registrationSuccess(){

        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        boolean res = new AuthenticationScreen(driver)
                .fillEmail("evnikel" + i + "@gmail.com")
                .fillPassword("Elena1234$@")
                .submitRegistration()
                .isContactListActivityPresent();
        Assert.assertTrue(res);
        new ContactListScreen(driver)
                .logout();
    }

    @Test
    public void registrationSuccessModel(){

        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        boolean res = new AuthenticationScreen(driver)
                .registration(Auth.builder().email("evnikel" + i + "@gmail.com").password("Elena1234$@").build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);
        new ContactListScreen(driver)
                .logout();
    }

    @Test
    public void registrationWrongEmail(){
        AndroidElement res = new AuthenticationScreen(driver)
                .registration(Auth.builder().email("evnikelgmail.com").password("Elena1234$@").build())
                .checkTextError("Error");


    }

}
