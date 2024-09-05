package com.jiit.vikram.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiit.vikram.Service.EmployeeService;
import com.jiit.vikram.dto.Employee;
import com.jiit.vikram.util.ResponseStructure;
 

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
    
	@PostMapping("/saveEmployee")
	
	public List<ResponseStructure<LinkedList<Employee>>> employeeDataSave(@RequestBody Employee employee)
	{
		return  employeeService.employeeServiceDataSave(employee);
	}
	
	@GetMapping("/getEmployee/{employeeUuid}")
	public ResponseStructure<Employee> employeeDataFetchById(@PathVariable String employeeUuid)
	{
		return employeeService.employeeServiceFetchById(employeeUuid);
	}
	
	@GetMapping("/getAllEmployee")
	public ResponseStructure<List<Employee>> employeeDataFetchAll()
	{
		return employeeService.employeeServiceFetchAll();
	}
	
	@DeleteMapping("/deleteEmployee{employeeUuid}")
	public ResponseStructure<Employee> employeeDataDeleteById(@RequestBody Employee employee,@PathVariable String employeeUuid)
	{
		return employeeService.employeeServiceDataDeleteById(employee, employeeUuid);
	}
	
	@PutMapping("/updateEmployee{employeeUuid}")
	public List<ResponseStructure<LinkedList<Employee>>> employeeDataupdate(@RequestBody Employee employee,@PathVariable String employeeUuid)
	{
		return employeeService.employeeServiceDataupdate(employee,employeeUuid);
	}
	
//	@GetMapping("/employees/department/{departmentName}")
//    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@PathVariable String departmentName) {
//        List<Employee> employees = employeeService.getEmployeesByDepartmentName(departmentName);
//        return ResponseEntity.ok(employees);
//    }
//
//    @GetMapping("/employees/company/{companyName}")
//    public ResponseEntity<List<Employee>> getEmployeesByCompanyName(@PathVariable String companyName) {
//        List<Employee> employees = employeeService.getEmployeesByCompanyName(companyName);
//        return ResponseEntity.ok(employees);
//    }
	
}
