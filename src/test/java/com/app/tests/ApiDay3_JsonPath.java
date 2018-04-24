package com.app.tests;

import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class ApiDay3_JsonPath {

	/*
	 * Given Accept type is JSON When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/regions Then status code is 200 And
	 * Response content should be json And 4 regions should be returned And Americas
	 * is one of the region names
	 */

	private String endpoint = ConfigurationReader.getProperty("hrapp.basedresturl");

	@Test
	public void testItemsFromResposeBody() {
		given().accept(ContentType.JSON).when().get(endpoint + "/regions").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().assertThat().body("items.region_id", hasSize(4)).and().assertThat()
				.body("items.region_name", hasItem("Americas")).and().assertThat()
				.body("items.region_name", hasItems("Americas", "Asia"));
	}

	/*
	 * Given Accept type is JSON And Params are limit 100 
	 * When I send get request to
	 * http://34.223.219.142:1212/ords/hr/employees 
	 * Then status code is 200 And
	 * Response content should be JSON 
	 * And 100 employees data should be in json
	 * response body
	 */

	@Test
	public void testWithQueryParameter() {
		given().accept(ContentType.JSON)
		.and().param("limit", 100)
		.when().get(endpoint + "/employees")
		.then().assertThat().statusCode(200)
		.and().assertThat().contentType(ContentType.JSON)
		.and().assertThat().body("items.employee_id", hasSize(100));
	}
	
	/*
	 * Given Accept type is JSON 
	 * And Params are limit 100 
	 * And path param is 110
	 * When I send get request to
	 * http://34.223.219.142:1212/ords/hr/employees 
	 * Then status code is 200 
	 * And Response content should be JSON 
	 * And following data should be returned:
	 * "employee_id" : 100
	 */
	

	@Test
	public void testWithPathParameter() {
       given().accept(ContentType.JSON)
       .and().params("limit", 100)
       .and().pathParams(":id", 110)
       .when().get(endpoint+"/employees/{:id}")
       .then().statusCode(200)
       .and().contentType(ContentType.JSON)
       .and().assertThat().body("employee_id",equalTo(110),
                              "first_name", equalTo("John"),
                               "last_name", equalTo("Chen"),
                               "email", equalTo("JCHEN"));
       
	}
	
	
	
	/*
	 * Given Accept type is JSON 
	 * And Params are limit 100 
	 * When I send get request to
	 * http://34.223.219.142:1212/ords/hr/employees 
	 * Then status code is 200 
	 * And Response content should be JSON 
	 * all employee ids should be returned
	 */
	
	 @Test
	 public void testWithJsonPath() {
		 Map<String,Integer> rParamMap = new HashMap<>();
		 rParamMap.put("limit", 100);
		 
	      Response response = 
	                  given().accept(ContentType.JSON) // header
		              .and().params(rParamMap)         // query param / request param
	                  .and().pathParams(":id", 177)    // path param
	                  .when().get(endpoint+"/employees/{:id}");
	      
	      JsonPath json = response.jsonPath(); // get json body and assign to json path object
	      System.out.println(json.getInt("employee_id"));
	      System.out.println(json.getString("last_name"));
	      System.out.println(json.getString("job_id"));
	      System.out.println(json.getInt("salary"));
	      System.out.println(json.getString("links[0].href")); // get specific element from Array
	      
	      List<String> href = json.getList("links.href");
	      System.out.println(href);
	 }
	 
	 /*
	  *   Given Accept type is Json
	  *   And Params are limited=100
	  *   When I send get request to
	  *   http://34.223.219.142:1212/ords/hr/employees 
   	  *   Then status code is 200 
	  *   And Response content should be JSON 
	  *   all employee ids should be returned
	  */
	
	@Test
	public void testWithJsonPathCollection() {
		 Map<String,Integer> rParamMap = new HashMap<>();
		 rParamMap.put("limit", 100);
		 
	      Response response = 
	                  given().accept(ContentType.JSON) // header
		              .and().params(rParamMap)         // query param / request param
	                  .when().get(endpoint+"/employees");
	   
	      assertEquals(response.statusCode(),200);
	          
	      // JsonPath json = new JsonPath(new FilePath.json);
	      // JsonPath json = new JsonPath(response.asString());
	      
	      JsonPath json = response.jsonPath(); 
	      
	      // get all employee ids into an arraylist
	      List<Integer> ids = json.getList("items.employee_id");
	      
	      assertEquals(ids.size(),100);
	      
	      // get all emails assign to arrayList
	      
	      List<String> emails = json.getList("items.email");
	      System.out.println(emails);
	      assertEquals(emails.size(),100);
	      
	      // get all employee ids that are greater than 150
	      List<Integer> moreThan150 = new ArrayList<>();
	      for(Integer id : ids) {
	    	  if(id > 150) {
	    		  moreThan150.add(id);
	    	  }
	      }
	      System.out.println("More then: "+moreThan150);
	      
	      //store.book.findAll { book -> book.price >= 5 && book.price <= 15 }
	      List<Integer> idList = json.getList("items.findAll{it.employee_id > 150}.employee_id");
	      System.out.println(idList);
	      
	      //get all employee lastnames, whose salary more than 7000
	      List<String> lastnameListSalary = json.getList("items.findAll{it.salary > 7000}.last_name");
	      System.out.println(lastnameListSalary);
	      
	      JsonPath jsonPathFromFile = new JsonPath(new File("C:\\Users\\Beknazar\\Desktop\\employees.json"));
	      List<String> empFromFile = jsonPathFromFile.getList("items.findAll{it.salary > 8000}.first_name");
	      System.out.println(empFromFile);
	      
	}
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
