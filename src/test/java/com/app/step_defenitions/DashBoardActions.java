package com.app.step_defenitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.app.pages.SuitCRMContacts;
import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuitCRMLoginPage;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DashBoardActions {
	private WebDriver driver = Driver.getDriver();
	SuitCRMLoginPage loginPage = new SuitCRMLoginPage();
	SuitCRMDashboard dashPage = new SuitCRMDashboard();
	SuitCRMContacts contactPage = new SuitCRMContacts();

	@When("^I post \"([^\"]*)\"$")
	public void i_post(String arg1) {
		dashPage.post(arg1);
	}

	@Then("^Post \"([^\"]*)\" should be displayed$")
	public void post_should_be_displayed(String arg1) {
		assertTrue(dashPage.verifyPostDisplayed(arg1));
	}

	@Given("^duplicated contact \"([^\"]*)\" exists$")
	public void duplicated_contact_exists(String arg1) {
		dashPage.openContacts();
		String pathOfVcardInYourID = ConfigurationReader.getProperty("contactfile");
		contactPage.importVCard(pathOfVcardInYourID);
		
	}

	@When("^remove duplicates for this contact$")
	public void remove_duplicates_for_this_contact() {
		contactPage.deleteDuplicates();
	}

	@Then("^there should be only (\\d+) \"([^\"]*)\" in the contacts page$")
	public void there_should_be_only_in_the_contacts_page(int amount, String contactName) {
		contactPage.viewContacts.click();
		assertEquals(contactPage.getNumberOfContacts(contactName),amount);
	}

}
