package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DatePickerTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("evnikel@gmail.com").password("Elena1234$@").build());
    }
    @Test
    public void selectDataCurrentMouth(){
        new ContactListScreen(driver)
                .openDatePickerScreen()
                .openCalendar()
                .selectData("29 December 2022")
                .isDataChanges("29 December 2022");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout3();
    }
}
