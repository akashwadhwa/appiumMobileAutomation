package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverUtils {

	private final URL url;
	private final DesiredCapabilities desiredCapabilities;
	private final String platform;

	public DriverUtils(String url, Map<String, Object> deviceMap) throws MalformedURLException {
		this.url = new URL(url);
		this.desiredCapabilities = new DesiredCapabilities(deviceMap);
		this.platform = desiredCapabilities.getCapability("platformName").toString();
	}
	
	public RemoteWebDriver createDriver() throws MalformedURLException {

	    switch (platform.toUpperCase()) {
	      case "ANDROID":
	        return new AndroidDriver<WebElement>(url, desiredCapabilities);
	      case "IOS":
	        return new IOSDriver<WebElement>(url, desiredCapabilities);
	      case "DESKTOP":
	        return new RemoteWebDriver(url, desiredCapabilities);
	      default:
	        throw new IllegalArgumentException(
	                String.format("Driver type not implemented: %s", platform));
	    }
	  }

}
