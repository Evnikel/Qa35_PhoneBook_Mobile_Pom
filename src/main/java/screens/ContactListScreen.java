package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    @FindBy (xpath = "//*[@content-desc='More options']")
    AndroidElement moreOptions;

    @FindBy(id = "com.sheygam.contactapp:id/title")
    AndroidElement logoutButton;


    public AuthenticationScreen logout(){
        moreOptions.click();
        logoutButton.click();
        return new AuthenticationScreen(driver);

    }

    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusButton;

    @FindBy(xpath= "//*[@text='Error']")
    AndroidElement alertError;

    @FindBy (id = "android:id/button1")
    AndroidElement backButton;

    @FindBy (id = "android:id/contentPanel")
    AndroidElement errorMessage;

    public ContactListScreen assertContactListActivityPresent(){
        Assert.assertTrue(isContactListActivityPresent());
        return this;
    }


    public boolean isContactListActivityPresent(){
        should(plusButton,5);
        return isShouldHave(activityViewText, "Contact list", 5);


    }


    public AndroidElement checkTextError(String text) {
        should(alertError,5);
        Assert.assertTrue(alertError.getText().contains(text));
        backButton.click();

        return alertError;
    }
}
