package com.app.step_defenitions;

import java.util.Map;

import com.app.pages.CreateContactPage;
import com.app.pages.SuitCRMContacts;
import com.app.pages.SuitCRMDashboard;

import cucumber.api.java.en.When;

public class CreateContactdMapsStepsDef {
	SuitCRMDashboard dashPage = new SuitCRMDashboard();
	CreateContactPage createContactPage = new CreateContactPage();
	SuitCRMContacts contactPage = new SuitCRMContacts();
	
	@When("^I create a new contact:$")
	public void i_create_a_new_contact(Map<String,String> contact) {
		dashPage.creteOptions("Create Contact");
		if(contact.get("first_name") != null) {
		createContactPage.firstNameBox.sendKeys(contact.get("first_name"));
		}
		if(contact.get("last_name") != null) {
		createContactPage.lastNameBox.sendKeys(contact.get("last_name"));
		}
		if(contact.get("cell_phone") != null) {
		createContactPage.phoneNumberBox.sendKeys(contact.get("cell_phone"));
		}
		if(contact.get("office_phone") != null) {
		createContactPage.officePhoneBox.sendKeys(contact.get("office_phone"));
		}
		createContactPage.saveIt();
		
	    
	}
}
