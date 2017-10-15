package driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import utility.launchEmulator;

public class DriverUtils {
	
	public static RemoteWebDriver driver;

	public static RemoteWebDriver createDriver(String deviceName, String platformName, String platformVersion,
			String browserName, String automationName, String avd, String avdReadyTimeout, String URL) throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
	    switch (platformName.toUpperCase()) {
	      case "ANDROID":
	    	    capabilities.setCapability("deviceName", deviceName);
				capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
				capabilities.setCapability("platformVersion", platformVersion);
				capabilities.setCapability("platformName", platformName);
				capabilities.setCapability("avd", avd);
				capabilities.setCapability("avdReadyTimeout", avdReadyTimeout);
				launchEmulator.launchAndroidEmulator(avd);
	        return new AndroidDriver<WebElement>(new URL("http://"+URL), capabilities);
	      case "IOS":
	    	  capabilities.setCapability("deviceName", deviceName);
				capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
				capabilities.setCapability("platformVersion", platformVersion);
				capabilities.setCapability("platformName", platformName);
				capabilities.setCapability("automationName", automationName);
				capabilities.setCapability("avdReadyTimeout", avdReadyTimeout);
	        return new IOSDriver<WebElement>(new URL("http://"+URL), capabilities);
	      case "DESKTOP":
	        return new RemoteWebDriver(new URL("http://"+URL), capabilities);
	      default:
	        throw new IllegalArgumentException(
	                String.format("Driver type not implemented: %s", platformName));
	    }
	  }
	

}
