package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.DriverUtils;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import config.deviceConfig;

public class Hooks {

  private static boolean setup = false;
  private static DriverUtils driverUtils;

  public static RemoteWebDriver driver;
  
  public RemoteWebDriver getDriver() {
      return driver;
  }

  @Before(order = 1)
  public void beforeAll() throws Throwable {
    if (!setup) {
      Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
      deviceConfig config = new deviceConfig();
        try {
        	driverUtils = new DriverUtils(config.url, config.deviceMap);
        } catch (MalformedURLException e) {
            throw e;
        }

        setup = true;
    }
  }

  @Before(order = 2)
  public void beforeScenario() throws Throwable {
      driver = driverUtils.createDriver();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @After
  public void afterScenario(Scenario scenario) {

    if (driver != null && scenario.isFailed()) {
      try {
        byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");

      } catch (WebDriverException webdriverException) {
        webdriverException.printStackTrace();
      }
    }

    if (driver != null) {
      driver.quit();
    }
  }
}