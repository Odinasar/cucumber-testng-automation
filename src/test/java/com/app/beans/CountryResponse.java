package com.app.beans;

import java.util.List;
import java.util.Map;

public class CountryResponse {
	private String country_id;
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public List<Map<String, String>> getLinks() {
		return links;
	}
	public void setLinks(List<Map<String, String>> links) {
		this.links = links;
	}
	private String country_name;
	private String region_id;
	private List<Map<String, String>> links;

}
