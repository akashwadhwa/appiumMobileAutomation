package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.RunCukesTest;

public class DemoStepDefs{

	static RunCukesTest hooks;
	
	public DemoStepDefs(RunCukesTest hooks){
		this.hooks=hooks;
	}

	@Given("^I am on \"([^\"]*)\"$")
	public static void i_am_on(String arg1) throws Throwable {
		hooks.getDriver().get("http://saucelabs.com/test/guinea-pig");
		Thread.sleep(1000);
	}

	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String arg1) throws Throwable {
		WebElement idElement = hooks.getDriver().findElement(By.id("i_am_an_id"));
		assertNotNull(idElement);
		assertEquals("I am a div", idElement.getText());
	}

	@When("^select \"([^\"]*)\" in the search results$")
	public void select_in_the_search_results(String arg1) throws Throwable {
		WebElement commentElement = hooks.getDriver().findElement(By.id("comments"));
		assertNotNull(commentElement);
		commentElement.sendKeys("This is an awesome comment");
		WebElement submitElement = hooks.getDriver().findElement(By.id("submit"));
		assertNotNull(submitElement);
		submitElement.click();
		Thread.sleep(2000);
	}

	@Then("^the User views the Appium screen \"([^\"]*)\"$")
	public void the_User_views_the_Appium_screen(String arg1) throws Throwable {
		WebElement yourCommentsElement = hooks.getDriver().findElement(By.id("your_comments"));
		assertNotNull(yourCommentsElement);
		assertTrue(hooks.getDriver().findElement(By.id("your_comments")).getText().contains("This is an awesome comment"));
		System.out.println(hooks.getDriver().getCurrentUrl());
	}


}
