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
                .fillEmail("evnikel@gmail.com") //AuthenticationScreen
                .fillPassword("Elena1234$@")  //AuthenticationScreen
                .submitLogin()//ContactListScreen
                .assertContactListActivityPresent()
                .logout();

    }
    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("evnikel@gmail.com").password("Elena1234$@").build())
                .assertContactListActivityPresent()
                .logout();

    }
}
