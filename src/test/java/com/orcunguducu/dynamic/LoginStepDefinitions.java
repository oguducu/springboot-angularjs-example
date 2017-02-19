package com.orcunguducu.dynamic;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orcunguducu.dynamic.util.TestConstants;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinitions {
	private WebDriver webDriver;
	@Given("^Navigate to login page$")
	public void navigate_to_login_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", TestConstants.WEB_DRIVER_URL);
		webDriver = new ChromeDriver();

		//webDriver.navigate().to("https://fast-dusk-85378.herokuapp.com/login");
		webDriver.navigate().to("http://localhost:8080/login");
	}

	@When("^User is not logged in$")
	public void user_is_not_logged_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^Populate login form$")
	public void populate_login_form() throws Throwable {
	    webDriver.findElement(By.name("username")).sendKeys("orcunguducu@hotmail.com");
	    webDriver.findElement(By.name("password")).sendKeys("dynamic");
	    webDriver.findElement(By.name("login")).click();
	}

	@Then("^Click login button$")
	public void click_login_button() throws Throwable {
		WebDriverWait _wait = new WebDriverWait(webDriver, 5);
		_wait.until(ExpectedConditions.elementToBeClickable(By.name("searchCurrency")));
		Assert.assertTrue("Login is successful", webDriver.getTitle().equals("DynamicUserPage"));
	}
	
	@Then("^Populate wrong login form$")
	public void populate_wrong_login_form() throws Throwable {
		webDriver.findElement(By.name("username")).sendKeys("orcunguducu@hotmail.com");
	    webDriver.findElement(By.name("password")).sendKeys("dynamicuserpageee");
	    webDriver.findElement(By.name("login")).click();
	}

	@Then("^Click login button with wrong form$")
	public void click_login_button_with_wrong_form() throws Throwable {
		Assert.assertTrue("Login is not successful", webDriver.getTitle().equals("DynamicUserPage Login Page"));
	}
}
