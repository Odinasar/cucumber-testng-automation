package com.app.step_defenitions;

import static org.testng.Assert.assertEquals;

import com.app.pages.CreateContactPage;
import com.app.pages.SuitCRMContacts;
import com.app.pages.SuitCRMDashboard;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateContactStepDef {
	SuitCRMDashboard dashPage = new SuitCRMDashboard();
	CreateContactPage createContactPage = new CreateContactPage();
	SuitCRMContacts contactPage = new SuitCRMContacts();
	
	@Given("^I open the create contact page$")
	public void i_open_the_create_contact_page() {
	   dashPage.creteOptions("Create Contact");
	}

	@Given("^I enter the first name \"([^\"]*)\" and the last name \"([^\"]*)\"$")
	public void i_enter_the_first_name_and_the_last_name(String firstName, String lastName) {
	   createContactPage.firstNameBox.sendKeys(firstName);
	   createContactPage.lastNameBox.sendKeys(lastName);
	}

	@Given("^I enter the phone number \"([^\"]*)\"$")
	public void i_enter_the_phone_number(String phoneNumber) {
	   createContactPage.phoneNumberBox.sendKeys(phoneNumber);
	}

	@Given("^I enter the department \"([^\"]*)\"$")
	public void i_enter_the_department(String department) {
	   createContactPage.departmentBox.sendKeys(department);
	}

	@When("^click on the save button$")
	public void click_on_the_save_button() {
	   createContactPage.saveButton.click();;
	   contactPage.saveContact.click();
	}

	@Then("^I should see contact information for \"([^\"]*)\"$")
	public void i_should_see_contact_information_for(String fullName) {
		String firstName = fullName.substring(0, fullName.indexOf(" "));
		String lastName = fullName.substring(fullName.indexOf(" ")+1);
		assertEquals(contactPage.firstName.getText(),firstName,"First names does not match");
		assertEquals(contactPage.lastName.getText(),lastName, "Last names does not match");
	}
}
