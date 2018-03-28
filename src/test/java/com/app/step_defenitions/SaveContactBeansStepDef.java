package com.app.step_defenitions;

import java.util.List;

import com.app.beans.ContactBean;
import com.app.pages.CreateContactPage;
import com.app.pages.SuitCRMContacts;
import com.app.pages.SuitCRMDashboard;

import cucumber.api.java.en.When;

public class SaveContactBeansStepDef {
	SuitCRMDashboard dashPage = new SuitCRMDashboard();
	CreateContactPage createContactPage = new CreateContactPage();
	SuitCRMContacts contactPage = new SuitCRMContacts();
	
	@When("^I save a new contact:$")
	public void i_save_a_new_contact(List<ContactBean> contacts) {
		dashPage.creteOptions("Create Contact");
		System.out.println(contacts.size());
		ContactBean contactBean = contacts.get(0);

		createContactPage.firstNameBox.sendKeys(contactBean.getFirstName());
		createContactPage.lastNameBox.sendKeys(contactBean.getLastName());
		createContactPage.phoneNumberBox.sendKeys(contactBean.getCellPhone());
		createContactPage.departmentBox.sendKeys(contactBean.getDepartment());
		
		createContactPage.saveIt();
		
	    
	}
	
}
