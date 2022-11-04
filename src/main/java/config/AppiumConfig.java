package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {

    protected static AppiumDriver<AndroidElement> driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Qa");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("appPackage","com.sheygam.contactapp");
        capabilities.setCapability("appActivity",".SplashActivity");
        capabilities.setCapability("automationName","Appium");
        //capabilities.setCapability("automationname", "uiautomator2");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app","C:\\Users\\evnik\\Downloads\\contacts-android.apk");
        driver=new AppiumDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

    }

    @AfterSuite
    public void teardown(){
        driver.quit();
    }
}