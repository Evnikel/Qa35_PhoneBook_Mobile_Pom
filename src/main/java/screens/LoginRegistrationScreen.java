package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class LoginRegistrationScreen extends BaseScreen{

    public LoginRegistrationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy (xpath = "")
    AndroidElement editTextEmail;

}
