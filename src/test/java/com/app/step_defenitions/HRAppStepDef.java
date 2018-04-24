package com.app.step_defenitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.app.pages.HRAppDepEmpPage;
import com.app.utilities.BrowserUtils;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.DBType;
import com.app.utilities.DBUtility;
import com.app.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HRAppStepDef {
	private WebDriver driver = Driver.getDriver();
	private HRAppDepEmpPage deptEmpPage = new HRAppDepEmpPage();
    private	Map<String,String> depUI;
	private Map<String,Object> depDB;
	
	@Given("^I am on DeptEmpPage$")
	public void i_am_on_DeptEmpPage() {
	    driver.get(ConfigurationReader.getProperty("hrapp.url"));
	}

	@When("^I search for department id (\\d+)$")
	public void i_search_for_department_id(int depID) throws InterruptedException {
		depUI = new HashMap<>();
		deptEmpPage.searchForDepartment(depID);
	  
	   depUI.put("DEPARTMENT_NAME", deptEmpPage.departmentName.getText());
	   depUI.put("MANAGER_ID", deptEmpPage.managerID.getText());
	   depUI.put("LOCATION_ID", deptEmpPage.locationID.getText());
	}

	@When("^I query database with sql:\"([^\"]*)\"$")
	public void i_query_database_with_sql(String query) throws SQLException {
		DBUtility.establishConnection(DBType.ORACLE);
		depDB = DBUtility.runSQLQuery(query).get(0);
		DBUtility.closeConnections();
	}
	
	@When("^I search for email \"([^\"]*)\" to see firstname and lastname$")
	public void i_search_for_email_to_see_firstname_and_lastname(String email) {
		depUI = new HashMap<>();
		
		deptEmpPage.email.clear();
		deptEmpPage.email.sendKeys(email);
		deptEmpPage.findDetails.click();
		BrowserUtils.waitForVisibility(deptEmpPage.firstName, 10);
		
		depUI.put("FIRST_NAME", deptEmpPage.firstName.getText());
		depUI.put("LAST_NAME", deptEmpPage.lastName.getText());
	}

	@Then("^UI data Database data must match$")
	public void ui_data_Database_data_must_match() {
	   for(String key: depDB.keySet()) {
		   assertEquals(depDB.get(key).toString(),depUI.get(key));
		   
	   }
		
	   //assertEquals(depDB,depUI);
//		assertEquals(depDB.get("DEPARTMENT_NAME"),depUI.get("DEPARTMENT_NAME"));
//		assertEquals(depDB.get("MANAGER_ID").toString(),depUI.get("MANAGER_ID"));
//		assertEquals(depDB.get("LOCATION_ID").toString(),depUI.get("LOCATION_ID"));
	}
	
	@When("^I search for department id (\\d+) and get number of employees$")
	public void i_search_for_department_id_and_get_number_of_employees(int depID) {
		depUI = new HashMap<>();
		deptEmpPage.searchForDepartment(depID);
		deptEmpPage.detach.click();
		BrowserUtils.scrollDown(deptEmpPage.departments.get(0));
		depUI.put("EMLOYEES_COUNT", String.valueOf(deptEmpPage.departments.size()));
	}
	
	
	
	
	
	
	
	
	
}
