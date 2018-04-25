package com.app.tests.MyPojo;

import java.util.List;

//"location_id": 1700,
//"street_address": "2004 Charade Rd",
//"postal_code": "98199",
//"city": "Seattle",
//"state_province": "Washington",
//"country_id": "US",
//"links":
public class Location {
	private int location_id;
	private String street_address;
	private int postal_code;
	private String city;
	private String state_province;
	private String country_id;
	private List links;

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public int getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState_province() {
		return state_province;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public List getLinks() {
		return links;
	}

	public void setLinks(List links) {
		this.links = links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country_id == null) ? 0 : country_id.hashCode());
		result = prime * result + location_id;
		result = prime * result + postal_code;
		result = prime * result + ((state_province == null) ? 0 : state_province.hashCode());
		result = prime * result + ((street_address == null) ? 0 : street_address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country_id == null) {
			if (other.country_id != null)
				return false;
		} else if (!country_id.equals(other.country_id))
			return false;
		if (location_id != other.location_id)
			return false;
		if (postal_code != other.postal_code)
			return false;
		if (state_province == null) {
			if (other.state_province != null)
				return false;
		} else if (!state_province.equals(other.state_province))
			return false;
		if (street_address == null) {
			if (other.street_address != null)
				return false;
		} else if (!street_address.equals(other.street_address))
			return false;
		return true;
	}
	
	

}
