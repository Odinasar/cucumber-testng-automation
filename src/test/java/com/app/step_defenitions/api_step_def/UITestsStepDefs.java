package com.app.step_defenitions.api_step_def;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.app.pages.SuitCRMContacts;
import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuitCRMLoginPage;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class UITestsStepDefs {
	private WebDriver driver = Driver.getDriver();
	SuitCRMLoginPage loginPage = new SuitCRMLoginPage();
	SuitCRMDashboard dashPage = new SuitCRMDashboard();
	SuitCRMContacts contactsPage = new SuitCRMContacts();
	
	@Given("^I logged in into suiteCRM$")
	public void i_logged_in_into_suiteCRM() {
	  System.out.println("Gogging to CRM");
	  driver.get(ConfigurationReader.getProperty("url"));
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  loginPage.login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
	}

	@Then("^CRM name should be SuiteCRM$")
	public void crm_name_should_be_SuiteCRM() {
	  assertTrue(driver.getTitle().endsWith("SuiteCRM"));
	}

	@Then("^Moduls should be displayed$")
	public void moduls_should_be_displayed() {
	  assertTrue(dashPage.verifyElementsDispayed(dashPage.moduls));
	}
	@Then("^I logout from app$")
	public void i_logout_from_app() {
	    dashPage.logout();
	}
	@Then("^close browser$")
	public void close_browser() {
	  Driver.closeDriver();
	}
	@Given("^I open contact \"([^\"]*)\"$")
	public void i_open_contact(String contactName) {
	    dashPage.openContacts();
	    dashPage.openContact(contactName);
	    
	}

	@Then("^contact name should be \"([^\"]*)\"$")
	public void contact_name_should_be(String contactName) {
	    assertEquals(contactsPage.getFullName(),contactName);
	}

	@Then("^contact email should be \"([^\"]*)\"$")
	public void contact_email_should_be(String email) {
	    assertEquals(contactsPage.email.getText(),email);
	}
}
