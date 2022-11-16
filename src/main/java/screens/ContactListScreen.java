package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

import static java.awt.SystemColor.text;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement moreOptions;
    @FindBy(id = "com.sheygam.contactapp:id/title")
    AndroidElement logoutButton;
    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusButton;
    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    List<AndroidElement> contactNamesList;
    @FindBy(id = "com.sheygam.contactapp:id/rowPhone")
    List<AndroidElement> contactPhonesList;
    @FindBy(id = "com.sheygam.contactapp:id/rowContainer")
    List<AndroidElement> contacts;
    @FindBy(id = "android:id/button1")
    AndroidElement yesButton;

    @FindBy(id = "android:id/button2")
    AndroidElement cancelButton;

    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement emptyText;


    public ContactListScreen isContactAddedByName(String name, String lastName) {
        checkContainsText(contactNamesList, name + " " + lastName);
        return this;
    }

    public ContactListScreen isContactAddedByPhone(String phone) {
        checkContainsText(contactPhonesList, phone);

        return this;
    }

    private void checkContainsText(List<AndroidElement> list, String text) {
        boolean isPresent = false;
        for (AndroidElement el : list) {

            if (el.getText().contains(text)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
    }

    public AddNewContactScreen openContactForm() {

        if (isDisplayedWithExp(plusButton)) {
            plusButton.click();
        }
        return new AddNewContactScreen(driver);
    }


    public AuthenticationScreen logout() {
        if (driver.findElements(By.xpath("//*[@content-desc='More options']")).size() > 0) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout2() {
        // ,moreOptions.isEnabled(),moreOptions.isSelected()
        if (moreOptions.isDisplayed()) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout3() {

        if (isDisplayedWithExp(moreOptions)) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout4() {

        if (activityViewText.getText().equals("Contact list")) {
            moreOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }


    public ContactListScreen assertContactListActivityPresent() {
        Assert.assertTrue(isContactListActivityPresent());
        return this;
    }

    public boolean isContactListActivityPresent() {
        should(plusButton, 5);
        return isShouldHave(activityViewText, "Contact list", 5);
    }

    public ContactListScreen removeOneContact() {


        //shouldHave(activityViewText,"Contact list",5);

        AndroidElement contact = contacts.get(0);
        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension.getHeight());
        System.out.println(dimension.getWidth());

        Rectangle rect = contact.getRect();

        int xA = rect.getX() + rect.getWidth() / 8;
        int xB = rect.getX() + (rect.getWidth() / 8) * 7;
        //int xB = rect.getX()+ rect.getWidth()*0.8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xA, y))
                .moveTo(PointOption.point(xB, y))
                .release().perform();
        should(yesButton, 6);
        yesButton.click();

        pause(7000);

        return this;
    }

    public int checkRemoveOneContact() {

        int before = contacts.size();

        if (!contacts.isEmpty()) {
            removeOneContact();
        }

        int after = contacts.size();
        return before - after;

    }



    public void removeAllContacts() {
        while (contacts.size() != 0) {
            removeOneContact();
        }

    }

    public boolean isNoContactsHere() {
        should(plusButton, 5);
        return isShouldHave(emptyText, "No Contacts. Add One more!", 5);
    }

    public void providerOfContacts() {
        Random random = new Random();
        if (contacts.size()<4) {
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(100) + 100;
                Contact contact = Contact.builder()
                        .name("Linor" + index)
                        .lastname("Lin" + index)
                        .email("linor" + index + "@gmail.com")
                        .phone("1234567" + index)
                        .address("Haifa")
                        .build();

                new ContactListScreen(driver)
                        .openContactForm()
                        .fillContactForm(contact)
                        .submitContactForm();
            }
        }
    }
}