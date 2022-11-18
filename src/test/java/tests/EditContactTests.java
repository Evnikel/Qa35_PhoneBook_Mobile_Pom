package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class EditContactTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("evnikel@gmail.com").password("Elena1234$@").build())
                .isContactListActivityPresent();
    }
    @Test
    public void editFirstContactSuccessByName(){
        new ContactListScreen(driver)
                .openEditForm()
                .editNameOnly("Wwwwww")
                .updateChanges()
                .isContactAddedByNameOnly("Wwwwww");

    }
    @Test
    public void editFirstContactSuccessByPhone(){
        new ContactListScreen(driver)
                .openEditForm()
                .editFieldContact("phone","09800000000")
                .updateChanges()
                .isContactAddedByPhone("09800000000");

    }
    @Test
    public void editFirstContactSuccessByLastName(){
        new ContactListScreen(driver)
                .openEditForm()
                .editFieldContact("lastname","QaTest")
                .updateChanges()
                .isContactAddedByLastNameOnly("QaTest");

    }
    @Test
    public void editFirstContactSuccessByEmail(){
        int i = new Random().nextInt(1000)+1000;
        String email = "test"+i+"@mail.com";
        new ContactListScreen(driver)
                .openEditForm()
                .editFieldContact("email",email)
                .updateChanges();
        // isContactAddedByEmail (email);

    }
    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }

}