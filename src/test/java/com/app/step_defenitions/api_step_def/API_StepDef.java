package com.app.step_defenitions.api_step_def;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.app.tests.MyPojo.Employee;
import com.app.tests.MyPojo.Location;
import com.app.utilities.ConfigurationReader;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_StepDef {
	RequestSpecification request;
	Response response;
	Response getResponse;
	String url = ConfigurationReader.getProperty("hrapp.basedresturl");
	int id;
	Employee emp;
	Map location = new HashMap();
	


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
		.when().post(url + "/employees/");
	}

	@Then("^Status code is (\\d+)$")
	public void status_code_is(int statusCode) {
	   assertEquals(response.statusCode(),statusCode);
	}

	@Then("^Response Json should contain Employee info$")
	public void response_Json_should_contain_Employee_info() {
		Employee empResponse = response.body().as(Employee.class);
		assertEquals(emp,empResponse);  // equlas method was Overrided in the Employee class !
		System.out.println(emp +" <> "+empResponse);
	}

	@When("^I send a GET request with smae id$")
	public void i_send_a_GET_request_with_smae_id(){
		 getResponse = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.when().get(url + "/employees/"+this.id);
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
	

	
	@Given("^Content type and Accept type are Json$")
	public void content_type_and_Accept_type_are_Json() {
	    request = 
	     given().log().all().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON);
	}
	@When("^I post new location with (\\d+) location_id$")
	public void i_post_new_location_with_location_id(int location_id) {
		location.put("location_id", location_id);
		location.put("street_address", "136 Locust st");
		location.put("postal_code", "02601");
		location.put("city", "Hyannis");
		location.put("state_province","MA");
		location.put( "country_id", "US");
		
	   response = request.body(location).
	    when().post(url+"/locations/");
	}

	@Then("^Response json should contain this location info$")
	public void response_json_should_contain_this_location_info() {
	   Map resLoc = response.body().as(Map.class);
	   
	   for(Object key : location.keySet()) {
		   assertEquals(location.get(key),resLoc.get(key));
		   System.out.println(location.get(key)+ " <> " +resLoc.get(key));
	   }
	}
	
	@When("^I send get request with (\\d+)$")
	public void i_send_get_request_with(int id) {
	   response = request.accept(ContentType.JSON)
			   .and().contentType(ContentType.JSON)
			   .when().get(url+"/locations/"+id);
	}

	@Then("^response json should match with:$")
	public void response_json_should_match_with(List<Location> list) {
	    Location response = this.response.body().as(Location.class);
	    
	    assertEquals(response.getLocation_id(),list.get(0).getLocation_id());
	    System.out.println(response.getLocation_id()+" <> "+list.get(0).getLocation_id());
	    
	    assertEquals(response.getCity(),list.get(0).getCity());
	    System.out.println(response.getCity()+" <> "+list.get(0).getCity());
	    
	    assertEquals(response.getCountry_id(),list.get(0).getCountry_id());
	    System.out.println(response.getCountry_id()+" <> "+list.get(0).getCountry_id());
	    
	    assertEquals(response.getPostal_code(),list.get(0).getPostal_code());
	    System.out.println(response.getPostal_code()+" <> "+list.get(0).getPostal_code());
	    
	    assertEquals(response.getState_province(),list.get(0).getState_province());
	    System.out.println(response.getState_province()+" <> "+list.get(0).getState_province());
	    
	    assertEquals(response.getStreet_address(),list.get(0).getStreet_address());
	    System.out.println(response.getStreet_address()+" <> "+list.get(0).getStreet_address());
	    
	    
	}
}
