package runner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:target/cucumber.json", features = {
"src/test/resources/features"},
glue = "src/test/java/stepDefinitions")
public class RunCukesTest {

	private static AppiumDriverLocalService service;

	@BeforeClass
	public static void startAppium() {
		String osName = System.getProperty("os.name").toLowerCase();

		String nodePath = null;
		String appiumPath = null;

		if (osName.contains("mac")) {
			nodePath = "/usr/local/bin/node";
			appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
		} else if (osName.contains("linux")) {
			nodePath = System.getenv("HOME") + "/.linuxbrew/bin/node";
			appiumPath = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
		}else if (osName.contains("windows")) {
			nodePath = "C:/Program Files/Appium/node.exe";
			appiumPath = "C:/Program Files/Appium/node_modules/appium/bin/appium.js";
		}
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(nodePath))
				.withAppiumJS(new File(appiumPath))
				.withIPAddress("0.0.0.0")
				.usingPort(4723));
		System.out.println("Starting Appium server"); 
		service.start();
	}

	@AfterClass
	public static void stopAppium() {
		service.stop();
	}
}
