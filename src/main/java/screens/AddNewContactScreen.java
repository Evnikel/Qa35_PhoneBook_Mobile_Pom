package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        //pause(5000);
        return new ContactListScreen(driver);
    }
    public AddNewContactScreen  submitContactFormNegative(){
        createButton.click();
        pause(5000);
        return this;
    }
    public AddNewContactScreen isErrorContainsText(String text){
        Alert alert = new WebDriverWait(driver,5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();

        return this;


    }

}