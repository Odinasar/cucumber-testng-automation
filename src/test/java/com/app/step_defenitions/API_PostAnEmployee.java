package com.app.step_defenitions;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.util.Random;

import com.app.tests.MyPojo.Employee;
import com.app.utilities.ConfigurationReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_PostAnEmployee {
	RequestSpecification request;
	Employee emp;
	Response response;
	Response getResponse;
	int id;
	String url = ConfigurationReader.getProperty("hrapp.basedresturl")+"/employees/";
	
	@Given("^Content type and Accept type are Json$")
	public void content_type_and_Accept_type_are_Json() {
	    request = 
	     given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON);
	}

	@When("^I post a new Employee with \"([^\"]*)\" id$")
	public void i_post_a_new_Employee_with_id(String id) {
		
		if(id.equals("random")) {
		int idR = 0;
		while(idR < 200) {
			idR = new Random().nextInt(10000);
		}
		this.id = idR;
		}else {
			this.id = Integer.parseInt(id);
		}
		
		String letters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String email = "";
		for(int i = 0; i < 10 ; i++) {
			email += letters.charAt(new Random().nextInt(letters.length()))+"";
		}
		email = email + "@gmail.com";
		emp = new Employee();
		emp.setFirst_name("Beknazar");
		emp.setLast_name("Suranchiyev");
		emp.setEmail(email);
		emp.setDepartment_id(60);
		emp.setJob_id("IT_PROG");
		emp.setPhone_number("774.534.6272");
		emp.setSalary(9000);
		emp.setEmployee_id(this.id);
		emp.setHire_date("2003-06-17T04:00:00Z");
		
		response = request.body(emp) // serialization 
		.when().post(url);
	}

	@Then("^Status code is (\\d+)$")
	public void status_code_is(int statusCode) {
	   assertEquals(response.statusCode(),statusCode);
	}

	@Then("^Response Json should contain Employee info$")
	public void response_Json_should_contain_Employee_info() {
		Employee empResponse = response.body().as(Employee.class);
		assertEquals(emp,empResponse);  // equlas method was Overrided in the Employee class !
	}

	@When("^I send a GET request with smae id$")
	public void i_send_a_GET_request_with_smae_id(){
		 getResponse = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.when().get(url+this.id);
	}

	@Then("^status code (\\d+)$")
	public void status_code(int statusCode) {
        assertEquals(getResponse.statusCode(),200);
	}

	@Then("^employee JSON Response Data should match the posted JSON data$")
	public void employee_JSON_Response_Data_should_match_the_posted_JSON_data() {
		Employee getEmp = getResponse.body().as(Employee.class);
		assertEquals(emp,getEmp);
	}
}
