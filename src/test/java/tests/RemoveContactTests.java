package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactTests extends AppiumConfig {

    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("evnikel@gmail.com").password("Elena1234$@").build())
                .isContactListActivityPresent();
    }
    @BeforeMethod
    public void providerContacts(){
        new ContactListScreen(driver)
                .providerContacts();
    }

    @Test
    public void removeOneContactSuccess(){
        new ContactListScreen(driver)
                .removeOneContact()
                .isListSizeOneLess();

    }
    @Test
    public void removeAllContactSuccess(){

        new ContactListScreen(driver)
                .removeAllContacts()
                .isNoContactHere();
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();
    }
}