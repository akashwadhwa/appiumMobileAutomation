package runner;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import appiumService.AppiumServerService;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import driver.DriverUtils;
import utility.launchEmulator;

//Strict: It will try to find the pending steps or missing steps, and fail if not found.
//feature: need to provide path of feature files
//plug-in: will contain the reporting features.
//tag: for tagged hooks, for scenarios.
//monochrome: enhance the readability.
//glue: provide your step definitions class path.
//dryRun: will check if you have pending or missing steps, but will not fail the scripts.

@CucumberOptions(strict = true,
plugin = "json:target/cucumber.json", 
features = {"src/test/resources/features/Demo.feature"},
glue = {"stepDefinitions","support"},
tags = {"@regression"},
monochrome=true,
dryRun=false)

public class RunCukesTest extends AbstractTestNGCucumberTests{

	public static RemoteWebDriver driver;

	@BeforeSuite()
	public static void startAppium() throws MalformedURLException {
		AppiumServerService.startAppium("0.0.0.0",4723);
		AppiumServerService.startAppium("127.0.0.1",4724);
	}

	@Parameters({"deviceName","platformName","platformVersion", "browserName", "automationName", "avd", "avdReadyTimeout", "URL"})
	@BeforeClass
	public static void createDriver(String deviceName, String platformName, String platformVersion,
			String browserName,@Optional("") String automationName,@Optional("") String avd,@Optional("") String avdReadyTimeout, String URL) throws MalformedURLException{
		driver=DriverUtils.createDriver(deviceName,platformName,platformVersion,browserName,automationName,avd,avdReadyTimeout,URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@AfterSuite
	public static void stopAppium() {
		launchEmulator.closeAndroidEmulator();
		AppiumServerService.stopAppium();
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

}
