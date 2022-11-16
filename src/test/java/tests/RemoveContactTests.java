package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactTests extends AppiumConfig {

    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("evnikel@gmail.com").password("Elena1234$@").build());
        //.isContactListActivityPresent();
        new ContactListScreen(driver)
                .providerOfContacts();
    }




    @Test
    public void removeOneContactSuccess(){
        Assert.assertEquals(new ContactListScreen(driver).checkRemoveOneContact(),1);

        // size less one
    }

    @Test
    public void removeAllContacts() {
        new ContactListScreen(driver)
                .removeAllContacts();
            Assert.assertTrue(new ContactListScreen(driver).isNoContactsHere());


    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }

}
