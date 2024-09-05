package com.jiit.vikram.dto;
import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
public class Employee {
    @Id
    @UuidGenerator
    private String employeeUuid;     
    private String employeeName;
    private LocalDate dob;
    private int age;
    private String phoneNumber;
    private String email;
    private String employeeSystem;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Address employeeAddress;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

	public String getEmployeeUuid() {
		return employeeUuid;
	}

	public void setEmployeeUuid(String employeeUuid) {
		this.employeeUuid = employeeUuid;
	}

	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob)  {	
	   this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age){
	  this.age=age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeSystem() {
		return employeeSystem;
	}

	public void setEmployeeSystem(String employeeSystem) {
		this.employeeSystem = employeeSystem;
	}

	public Address getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(Address employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}





