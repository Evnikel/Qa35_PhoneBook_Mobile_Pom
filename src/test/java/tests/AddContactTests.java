package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

    @Test
    public void addNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("Paula" + i)
                .lastname("Pop" + i)
                .email("paula" + i + "@gmail.com")
                .phone("123456789123")
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
    public void addNewContactSuccessModel() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("Paula" + i)
                .lastname("Pop" + i)
                .email("paula" + i + "@gmail.com")
                .phone("123456789123")
                .address("Haifa")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastname())
                .isContactAddedByPhone(contact.getPhone());
    }

    @Test
    public void addNewContactSNegativeWrongEmail() {
        Contact contact = Contact.builder()
                .name("Paula")
                .lastname("Pop")
                .email("paulagmail.com")
                .phone("123456789123")
                .address("Haifa")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactNegativeForm()
                .isErorrMessageContaisText("must be a well-formed email address");
    }

    @Test
    public void addNewContactSNegativeWrongPhone() {
        Contact contact = Contact.builder()
                .name("Paula")
                .lastname("Pop")
                .email("paula@gmail.com")
                .phone("123456789")
                .address("Haifa")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactNegativeForm()
                .isErorrMessageContaisText("Phone number must contain only digits!");
    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }

}



//{phone=Phone number must contain only digits! And length min 10, max 15!}