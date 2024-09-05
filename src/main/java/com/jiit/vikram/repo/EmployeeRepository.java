package com.jiit.vikram.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiit.vikram.dto.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,String>{

	Optional<Employee> findByEmployeeUuid(String employeeUuid);
	
	List<Employee> findByDepartmentDepartmentName(String departmentName);
}
