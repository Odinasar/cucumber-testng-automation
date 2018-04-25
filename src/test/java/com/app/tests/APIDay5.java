package com.app.tests;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.util.Random;

import org.testng.annotations.Test;
import com.app.tests.MyPojo.Employee;
import com.app.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay5 {
    
	
	@Test
	public void warmUp() {
		int id = 0;
		while(id < 200) {
			id = new Random().nextInt(10000);
		}
		
		String letters = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String email = "";
		for(int i = 0; i < 10 ; i++) {
			email += letters.charAt(new Random().nextInt(letters.length()))+"";
		}
		email = email + "@gmail.com";
		Employee emp = new Employee();
		emp.setFirst_name("Beknazar");
		emp.setLast_name("Suranchiyev");
		emp.setEmail(email);
		emp.setDepartment_id(60);
		emp.setJob_id("IT_PROG");
		emp.setPhone_number("774.534.6272");
		emp.setSalary(9000);
		emp.setEmployee_id(id);
		emp.setHire_date("2003-06-17T04:00:00Z");
		
		Response response = given().log().all().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.and().body(emp) // serialization 
		.when().post(ConfigurationReader.getProperty("hrapp.basedresturl")+"/employees/");
		
		assertEquals(response.statusCode(), 201);
		
		Employee empResponse = response.body().as(Employee.class);
		
		assertEquals(emp,empResponse);  // equlas method was Overrided in the Employee class !
		
//		assertEquals(emp.getFirst_name(),empResponse.getFirst_name());
//		assertEquals(emp.getLast_name(),empResponse.getLast_name());
//		assertEquals(emp.getEmployee_id(),empResponse.getEmployee_id());
//		assertEquals(emp.getDepartment_id(),empResponse.getDepartment_id());
//		assertEquals(emp.getEmail(),empResponse.getEmail());
//		assertEquals(emp.getHire_date(),empResponse.getHire_date());
//		assertEquals(emp.getPhone_number(),empResponse.getPhone_number());
		
		Response getResponse = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.when().get(ConfigurationReader.getProperty("hrapp.basedresturl")+"/employees/"+id);
		
		assertEquals(getResponse.statusCode(),200);
		
		Employee getEmp = getResponse.body().as(Employee.class);
	
//		assertEquals(emp.getFirst_name(),getEmp.getFirst_name());
//		assertEquals(emp.getLast_name(),getEmp.getLast_name());
//		assertEquals(emp.getEmployee_id(),getEmp.getEmployee_id());
//		assertEquals(emp.getDepartment_id(),getEmp.getDepartment_id());
//		assertEquals(emp.getEmail(),getEmp.getEmail());
//		assertEquals(emp.getHire_date(),getEmp.getHire_date());
//		assertEquals(emp.getPhone_number(),getEmp.getPhone_number());
		
		assertEquals(emp,getEmp);
	}
}
