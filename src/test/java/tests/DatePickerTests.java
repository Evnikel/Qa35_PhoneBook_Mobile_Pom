package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DatePickerTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("wick@gmail.com").password("Ww12345$").build());
    }
    @Test
    public void selectDataCurrentMouth(){
        new ContactListScreen(driver)
                .openDatePickerScreen()
                .openCalendar()
                .selectData("15 December 2022");
                .isDataChanges("15 December 2022");
    }
}
