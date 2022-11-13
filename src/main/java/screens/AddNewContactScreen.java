package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(id="com.sheygam.contactapp:id/inputName")
    AndroidElement nameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputLastName")
    AndroidElement lastNameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputEmail")
    AndroidElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPhone")
    AndroidElement phoneEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputAddress")
    AndroidElement addressEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputDesc")
    AndroidElement descriptionEditText;
    @FindBy(id="com.sheygam.contactapp:id/createBtn")
    AndroidElement createButton;

    @FindBy(id = "android:id/message")
    AndroidElement errorTextView;
    @FindBy(id = "android:id/button1")
    AndroidElement okBtn;


    public AddNewContactScreen isErorrMessageContaisText(String text){
        pause(2000);
        Assert.assertTrue(errorTextView.getText().contains(text));
        okBtn.click();
        pause(2000);
        driver.navigate().back();
        return this;
    }

    public AddNewContactScreen fillContactForm(Contact contact){
        should(nameEditText,3);
        type2(nameEditText, contact.getName());
        type2(lastNameEditText, contact.getLastname());
        type2(emailEditText, contact.getEmail());
        type2(phoneEditText, contact.getPhone());
        type2(addressEditText, contact.getAddress());
        type2(descriptionEditText, contact.getDescription());
        return  this;
    }

    public ContactListScreen  submitContactForm(){
        createButton.click();
        pause(5000);
        return new ContactListScreen(driver);
    }

    public AddNewContactScreen submitContactNegativeForm() {
        createButton.click();
        return this;
    }


}
