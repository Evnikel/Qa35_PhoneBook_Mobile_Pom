package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

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


    public boolean isContactListActivityPresent(){
        return isShouldHave(activityViewText, "Contact list", 5);


    }


}
