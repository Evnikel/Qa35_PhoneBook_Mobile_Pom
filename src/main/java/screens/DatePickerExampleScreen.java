package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerExampleScreen extends BaseScreen{
    public DatePickerExampleScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    @FindBy (id="com.sheygam.contactapp:id/dateTxt")
    AndroidElement dateTextView;
    @FindBy(id="com.sheygam.contactapp:id/dateBtn")
    AndroidElement changeDateButton;
    @FindBy (id = "android:id/date_picker_header_date")
    AndroidElement headerDateView; // Sat, Dec 17
    @FindBy(id = "android:id/button1")
    AndroidElement okButton;
    @FindBy (xpath = "//*[@content-desc = '15 December 2022']") // //*[@content-desc='15 December 2022']
    AndroidElement currentDate;   // 15 December 2022

    public DatePickerExampleScreen openCalendar(){
        shouldHave(activityViewText,"Date picker example",5);
        changeDateButton.click();
        return this;
    }

    public DatePickerExampleScreen selectData(String date){
        String locator = String.format("//*[@content-desc='%s']",date);
        should(currentDate,5);
        driver.findElement(By.xpath(locator)).click();
        okButton.click();
        return this;
    }

    public void isDataChanges(String date){ /// "15 December 2022"
        String currentDate = dateTextView.getText(); // " 15/12/2022"
        LocalDate dateTest = LocalDate.parse(currentDate,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(date);

        LocalDate dateElement = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMMM yyyy"));

        Assert.assertEquals(dateTest,dateElement);
    }

}
