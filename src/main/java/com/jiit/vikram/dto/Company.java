package com.jiit.vikram.dto;

import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Company {
	@Id
	@UuidGenerator
	private String companyUuid;
	private String companyName;
	private LocalDate startDate;
	private int gst;
	private String phoneNumber;
	private String companyShortCode;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
//	@OneToMany(mappedBy = "company")
//	private List<Department> departments;
//	
//	public List<Department> getDepartments() {
//		return departments;
//	}
//	public void setDepartments(List<Department> departments) {
//		this.departments = departments;
//	}
	
	public String getCompanyUuid() {
		return companyUuid;
	}
	public void setCompanyUuid(String companyUuid) {
		this.companyUuid = companyUuid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public int getGst() {
		return gst;
	}
	public void setGst(int gst) {
		this.gst = gst;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompanyShortCode() {
		return companyShortCode;
	}
	public void setCompanyShortCode(String companyShortCode) {
		this.companyShortCode = companyShortCode;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}

