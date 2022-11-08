package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class Login2Tests extends AppiumConfig {

    @Test
    public void loginSuccess()  {

        new AuthenticationScreen(driver)
                .fillEmail("noa@gmail.com") //AuthenticationScreen
                .fillPassword("Nnoa12345$")  //AuthenticationScreen
                .submitLogin()//ContactListScreen
                .assertContactListActivityPresent()
                .logout();

    }
    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build())
                .assertContactListActivityPresent()
                .logout();

    }
}
