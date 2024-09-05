package com.jiit.vikram.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiit.vikram.dto.Employee;
import com.jiit.vikram.repo.EmployeeRepository;
@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee employeeDaoDataSave(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	public Optional<Employee> employeeDaoDataFetchEmployeeId(String employeeUuid)
	{
			return employeeRepository.findByEmployeeUuid(employeeUuid);
		
	}
	
	public List<Employee> employeeDaoDataFetchAll()
	{
		return employeeRepository.findAll();
	}
	
	public String employeeDaoDataDeleteById(String employeeUuid)
	{
		employeeRepository.deleteById(employeeUuid);
		return "Data Deleted Sucessfully";
	}
	
//	public Employee employeeDaoDataupdate(Employee employee,String employeeUuid)
//	{
//		employee.setEmployeeUuid(employeeUuid);
//		return employeeRepository.save(employee);
//	}

}
