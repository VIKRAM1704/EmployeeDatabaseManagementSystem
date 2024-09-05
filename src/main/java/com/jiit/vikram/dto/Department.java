package com.jiit.vikram.dto;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	@Id
	@UuidGenerator
	private String departmentUuid;
	private String departmentName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Company company;

//	@OneToMany
//	@JoinColumn(name = "departmentUuid")
//	private List<Employee> employee;
//	
//	public List<Employee> getEmployee() {
//		return employee;
//	}
//	public void setEmployee(List<Employee> employee) {
//		this.employee = employee;
//	}
	
	public String getDepartmentUuid() {
		return departmentUuid;
	}
	public void setDepartmentUuid(String departmentUuid) {
		this.departmentUuid = departmentUuid;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
}

