package com.app.tests;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import com.app.beans.Country;
import com.app.beans.CountryResponse;
import com.app.beans.Region;
import com.app.beans.RegionResponse;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay4PostRequest {
//	  Given content type is Json
//	  And Accept type is Json
//	  When I send POST request to
//	  http://34.223.219.142:1212/ords/hr/regions/
//	  with request body:
//	  {
//	    "region_id" : 17,
//		"region_name" : "Beknazar's region"
//	  }
//	  Then status code should be 200
//	  And response body should match request body 
	
	//@Test
	public void postRegionsToDataBase() {
		String url = "http://34.223.219.142:1212/ords/hr/regions/";
		//String requestJson = "  {\"region_id\" : 17,\"region_name\" : \"Beknazar's region\"}";
		
		Map requestMap = new HashMap<>();
		requestMap.put("region_id", 7777);
		requestMap.put("region_name", "My Region");
		
		Response response = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.and().body(requestMap)
		.when().post(url);
		
		System.out.println(response.statusLine());
		response.prettyPrint();
		
		assertEquals(response.statusCode(),201);
		
		// de-serilization. JSON -> JAVA 
		Map responseMap =response.body().as(Map.class);
		
		assertEquals(requestMap.get("region_id"),responseMap.get("region_id"));
		assertEquals(requestMap.get("region_name"),responseMap.get("region_name"));
	}
	
//	@Test
	public void postRegionsWhithCustomPOJO(){
		String url = "http://34.223.219.142:1212/ords/hr/regions/";
		
		Region region = new Region();
		region.setRegion_id(new Random().nextInt(9999));
		region.setRegion_name("My Region");
		
		Response response = 
				given().accept(ContentType.JSON)
				.and().contentType(ContentType.JSON)
				.and().body(region)
				.when().post(url);
		assertEquals(response.statusCode(),201);
		RegionResponse responseRegion = response.body().as(RegionResponse.class);
		
		assertEquals(responseRegion.getRegion_id(),region.getRegion_id());
		assertEquals(responseRegion.getRegion_name(),region.getRegion_name());
		
	}
	
	@Test
	public void postCountryWithPOJO() {
		String url = "http://34.223.219.142:1212/ords/hr/countries/";
		
		Country country = new Country();
		country.setCountry_id("XXXX");
		country.setCountry_name("KazakhstaN");
		country.setRegion_id("12312");
		
		Response response = 
				given().log().all().accept(ContentType.JSON)
				.and().contentType(ContentType.JSON)
				.and().body(country)
				.when().post(url);
		
		assertEquals(response.statusCode(),201);
		
		CountryResponse responseCountry = response.body().as(CountryResponse.class);
		
		assertEquals(responseCountry.getCountry_id(),country.getCountry_id());
		assertEquals(responseCountry.getCountry_name(),country.getCountry_name());
		assertEquals(responseCountry.getRegion_id(),country.getRegion_id());
	}
	
	
	
	
}
