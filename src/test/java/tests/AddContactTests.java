package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContactTests extends AppiumConfig {

    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .login(Auth.builder().email("evnikel@gmail.com").password("Elena1234$@").build());
    }

    @Test(invocationCount = 2)
    public void addNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("Linor" + i)
                .lastname("Lin" + i)
                .email("linor" + i + "@gmail.com")
                .phone("1234567"+i)
                .address("Haifa")
                .description("The best friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastname())
                .isContactAddedByPhone(contact.getPhone());
    }
    @Test
    public void addNewContactSuccessRequiredFields() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("Viki" + i)
                .lastname("Snow" + i)
                .email("viki" + i + "@gmail.com")
                .phone("4567891"+i)
                .address("Tel Aviv")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastname())
                .isContactAddedByPhone(contact.getPhone());
    }

    @Test
    public void addNewContactNegativeEmptyName() {

        Contact contact = Contact.builder()
                .lastname("Paula")
                .email("paula@gmail.com")
                .phone("456789100012")
                .address("Ashdod")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("name=must not be blank");

    }

    @Test
    public void addNewContactNegativeEmptyPhone() {

        Contact contact = Contact.builder()
                .name("Loki")
                .lastname("Tivad")
                .email("tloki@gmail.com")
                .address("TelAviv")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("Phone number must contain only digits! And length min 10, max 15!");

    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }

}