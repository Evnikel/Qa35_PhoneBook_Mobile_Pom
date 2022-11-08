package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginSuccess(){
        //boolean res = new SplashScreen(driver)
                //.checkVersion("1.0.0")
        boolean res = new AuthenticationScreen(driver)
                .fillEmail("evnikel@gmail.com")
                .fillPassword("Elena1234$@")
                .submitLogin()
                .isContactListActivityPresent();
        Assert.assertTrue(res);

    }

    @Test
    public void loginSuccessModel(){
        //boolean res =new SplashScreen(driver)
                //.checkVersion("1.0.0")
        boolean res = new AuthenticationScreen(driver)
                .login(Auth.builder().email("evnikel@gmail.com").password("Elena1234$@").build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);


    }

    @AfterMethod
    public void logoutFromSys(){
        new ContactListScreen(driver)
                .logout();
    }
}
