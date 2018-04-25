package com.app.tests.selfPractice;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.app.tests.MyPojo.Employee;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API01 {
   
	/*
	 *    Given accept type JSON and limit set to 100
	 *    When I send request to:
	 *    http://34.223.219.142:1212/ords/hr/employees/
	 *    Then response type should be JSON
	 *    And status code should be 200
	 *    And I should get 100 employees
	 */
	
	//@Test
	public void practice01() {
		Response responce = given().accept(ContentType.JSON)
		.param("limit",100)
		.when().get("http://34.223.219.142:1212/ords/hr/employees/");
		
		assertEquals(responce.statusCode(),200);
		assertEquals(responce.contentType(),"application/json");
		
		JsonPath path = responce.jsonPath();
		List<String> emp = path.getList("items.employee_id");
		
		assertEquals(emp.size(),100);
		
		System.out.println(emp);	
	}
	
	/*
	 *  Given I accept xml format 
	 *  Whent I send request to: 
	 *  http://34.223.219.142:1212/ords/hr/employees/102
	 *  Then I should status code should be 200
	 *  And response type xml 
	 *  And 
	 *        first_name = "Lex"
	 *        last_name = "De Haan"
	 */
	
	// This test fail because API does not support XML format. Even when I request 
	// XML it returns me default one which is JSON
//	@Test
	public void practice02() {
		given().accept(ContentType.XML)
		.when().get("http://34.223.219.142:1212/ords/hr/employees/102")
		.then().statusCode(200);
		//.and().contentType(ContentType.XML);
	}
	
	/*
	 *   Given I accept json format 
	 *   And limit is seted to 50
	 *   When I send request to:
	 *   http://34.223.219.142:1212/ords/hr/employees/107
	 *   Then Status code should be 200
	 *   And Content type should be json 
	 *   And 
	 *       "first_name" : "Diana"
	 *       "last_name": "Lorentz",
	 */
	
	//@Test
	public void practice03() {
		Response responce =
		 given().accept(ContentType.JSON)
		.and().param("limit",50)
		.when().get("http://34.223.219.142:1212/ords/hr/employees/107");
		
	   assertEquals(responce.statusCode(), 200);
	   assertEquals(responce.contentType(),"application/json");
       
	   // de-serializing.   JSON -> JAVA 
	   Employee emp = responce.body().as(Employee.class);
	   // It prints all the data couse toString method was overrided in the POJO class. 
	   System.out.println(emp.toString());
	   assertEquals(emp.getFirst_name(),"Diana");
	   assertEquals(emp.getLast_name(),"Lorentz");
	}
	
	/*
	 *  Given I content type is json
	 *  And I accept response in json format
	 *  When I send request post: "http://34.223.219.142:1212/ords/hr/employees/"
	 *       "first_name" : "Beknazar"
	 *       "last_name" : "Suranchiyev"
	 *       "employee_id" : 108
	 *  Then staus code should be 201
	 *  And response should be in json format         
	 */
	
	@Test 
	public void practice04() {
		Map<String,String> post = new HashMap<>();
		post.put("first_name", "Beknazar");
		post.put("last_name", "Suranchiyev");
		post.put("employee_id","108");
		
		Response response = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.body(post)
		.when().post("http://34.223.219.142:1212/ords/hr/employees/");
		
		System.out.println(response.statusCode());
	}
}
