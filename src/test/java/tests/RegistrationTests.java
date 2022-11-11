package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000)+1000;
        new AuthenticationScreen(driver)
                .fillEmail("fox"+i+"@mail.com")
                .fillPassword("Ww12345!")
                .submitRegistration()
                .assertContactListActivityPresent()
                .logout();


    }
    @Test
    public void registrationNegativeWrongEmail(){

        new AuthenticationScreen(driver)
                .fillEmail("evnikmail.com")
                .fillPassword("Ww12345!")
                .submitRegistrationNegative()
                .isErorrMessageContaisText("must be a well-formed email address");

    }

    @Test
    public void registrationNegativeWrongPassword(){

        new AuthenticationScreen(driver)
                .registrationUnsuccessful(Auth.builder().email("bob@mail.com").password("Bb123").build())
                .isErorrMessageContaisTextInAlert("At least 8 character");
    }

    @Test
    public void registrationSuccessModel(){
        int i = new Random().nextInt(1000)+1000;
        new AuthenticationScreen(driver)
                .registration(Auth.builder().email("bob"+i+"@mail.com").password("Bb12345$").build())
                .assertContactListActivityPresent()
                .logout();
    }
}
//{password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter,
//        and 1 number; Can contain special characters [@$#^&*!], username=must be a well-formed email address}