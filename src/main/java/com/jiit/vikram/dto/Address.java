package com.jiit.vikram.dto;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Address {
	@Id
    @UuidGenerator
	private String addressUuid;
	private String street;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	
	public String getAddressUuid() {
		return addressUuid;
	}
	public void setAddressUuid(String addressUuid) {
		this.addressUuid = addressUuid;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
		
}

