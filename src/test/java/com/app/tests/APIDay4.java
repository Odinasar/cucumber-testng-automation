package com.app.tests;

import org.testng.annotations.Test;

import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIDay4 {
	/*
	 * Given Content type is Json And Limit is 10 When I send request to Rest API
	 * url: http://34.223.219.142:1212/ords/hr/regions Then I should see following
	 * data: 1. Europe 2. Americas 3. Asia 4. Middle East and Africa
	 */

	@Test
	public void wormUp() {
		Response response = 
				given().accept(ContentType.JSON)
				.and().params("limit", 10)
				.when().get(ConfigurationReader.getProperty("hrapp.basedresturl")+"/regions");

		JsonPath json = response.jsonPath();
		assertEquals(response.getStatusCode(),200);
		List<Object> region_name = json.getList("items.region_name");
		
        assertEquals(json.getInt("items[0].region_id"),1);
        assertEquals(json.getString("items[0].region_name"),"Europe");
        
        assertEquals(json.getInt("items[1].region_id"),2);
        assertEquals(json.getString("items[1].region_name"),"Americas");
        
        assertEquals(json.getInt("items[2].region_id"),3);
        assertEquals(json.getString("items[2].region_name"),"Asia");
        
        assertEquals(json.getInt("items[3].region_id"),4);
        assertEquals(json.getString("items[3].region_name"),"Middle East and Africa");
        
		
		assertEquals(region_name.get(0).toString(), "Europe");
		assertEquals(region_name.get(1).toString(), "Americas");
		assertEquals(region_name.get(2).toString(), "Asia");
		assertEquals(region_name.get(3).toString(), "Middle East and Africa");
	}
	
	@Test
	public void wormUp2() {
		Response response = 
				given().accept(ContentType.JSON)
				.and().params("limit", 10)
				.when().get(ConfigurationReader.getProperty("hrapp.basedresturl")+"/regions");

		JsonPath json = response.jsonPath();
		assertEquals(response.getStatusCode(),200);
		
		// deserialize json to List<Map>
		List<Map> regions = json.getList("items",Map.class);
		
		Map<Integer,String> expected = new HashMap<>();
		expected.put(1,"Europe");
		expected.put(2,"Americas");
		expected.put(3,"Asia");
		expected.put(4,"Middle East and Africa");
		
		int index = 1;
		for(int i = 0; i < regions.size(); i++) {
			if(regions.get(i).get("region_id").equals(index)) {
				assertEquals(regions.get(i).get("region_name") , expected.get(index));
			}
		}
		
		for(Integer regionId : expected.keySet()) {
			System.out.println("Looking for region: "+regionId);
			for(Map map : regions) {
				if(map.get("region_id") == regionId) {
					assertEquals(map.get("region_name"),expected.get(regionId));
				} 
			}
		}
		
	}
}