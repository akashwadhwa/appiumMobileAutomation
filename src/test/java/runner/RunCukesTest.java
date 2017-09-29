package runner;
import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import appiumService.AppiumServerService;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:target/cucumber.json", features = {
"src/test/resources/features/Demo.feature"},
glue = {"stepDefinitions","support"},
tags = {"@regression"})

public class RunCukesTest {

	@BeforeClass
	public static void startAppium() throws MalformedURLException {
		AppiumServerService.startAppium();
	}


	@AfterClass
	public static void stopAppium() {
		AppiumServerService.stopAppium();
	}

}
