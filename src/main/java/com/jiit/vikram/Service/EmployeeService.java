package com.jiit.vikram.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jiit.vikram.Dao.EmployeeDao;
import com.jiit.vikram.dto.Company;
import com.jiit.vikram.dto.Department;
import com.jiit.vikram.dto.Employee;
import com.jiit.vikram.repo.CompanyRepository;
import com.jiit.vikram.repo.DepartmentRepository;
import com.jiit.vikram.repo.EmployeeRepository;
import com.jiit.vikram.util.ResponseStructure;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	EmployeeRepository employeeRepository;

//    @Autowired
//    DepartmentRepository departmentRepository;
//
//    @Autowired
//    CompanyRepository companyRepository;
	
	
	public List<ResponseStructure<LinkedList<Employee>>> employeeServiceDataSave(Employee employee)
	{
		List<ResponseStructure<LinkedList<Employee>>> responseStrucure = new LinkedList<>();
		
	        ResponseStructure<LinkedList<Employee>> emailResponse = new ResponseStructure<>();
	        LinkedList<Employee> emailData = new LinkedList<>();
	        emailData.add(employee);
	        
		        if (validEmail(employee.getEmail())) 
		        {
		            emailResponse.setStatus(HttpStatus.CREATED.value());
		            emailResponse.setMessage("Email saved successfully");
		            emailResponse.setData(emailData);
		        } 
		        else 
		        {
		            emailResponse.setMessage("Invalid email address");
		            emailResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
		
		responseStrucure.add(emailResponse);
	
	        ResponseStructure<LinkedList<Employee>> nameResponse = new ResponseStructure<>();
	        LinkedList<Employee> nameData = new LinkedList<>();
	        nameData.add(employee);
	        
		        if (validName(employee.getEmployeeName())) 
		        {
		            employee.setEmployeeName(employee.getEmployeeName());
		            nameResponse.setStatus(HttpStatus.CREATED.value());
		            nameResponse.setMessage("Name saved successfully");
		            nameResponse.setData(nameData);
		        } 
		        else 
		        {
		            nameResponse.setMessage("Invalid name: Special characters or null values not accepted");
		            nameResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
		
		responseStrucure.add(nameResponse);
		
	        ResponseStructure<LinkedList<Employee>> addressResponse = new ResponseStructure<>();
	        LinkedList<Employee> addressData = new LinkedList<>();
	        addressData.add(employee);
	        
		        if (employee.getEmployeeAddress() != null &&
		        	    employee.getEmployeeAddress().getCity() != null &&
		        	    !employee.getEmployeeAddress().getCity().trim().isEmpty()) 
		        {
		            addressResponse.setStatus(HttpStatus.CREATED.value());
		            addressResponse.setMessage("Address saved successfully");
		            addressResponse.setData(addressData);
		        }
		        else
		        {
		            addressResponse.setMessage("City is mandatory");
		            addressResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
		
		responseStrucure.add(addressResponse);

	        ResponseStructure<LinkedList<Employee>> employeePhoneNumberResponse = new ResponseStructure<>();
	        LinkedList<Employee> employeePhoneNumberData = new LinkedList<>();
	        employeePhoneNumberData.add(employee);
	        
		        if (validPhoneNumber(employee.getPhoneNumber())) 
		        {
		        	 employeePhoneNumberResponse.setStatus(HttpStatus.CREATED.value());
		        	 employeePhoneNumberResponse.setMessage("Phone Number Saved Successfully");
		        	 employeePhoneNumberResponse.setData(employeePhoneNumberData);
		        } 
		        else 
		        {
		        	 employeePhoneNumberResponse.setMessage("Invalid employee Phone Number");
		        	 employeePhoneNumberResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
		        
        responseStrucure.add(employeePhoneNumberResponse);
       
        
	        ResponseStructure<LinkedList<Employee>> companyPhoneNumberResponse = new ResponseStructure<>();
	        LinkedList<Employee> companyPhoneNumberData = new LinkedList<>();
	        companyPhoneNumberData.add(employee);
		        if (validPhoneNumber( employee.getDepartment().getCompany().getPhoneNumber())) 
		        {
		        	companyPhoneNumberResponse.setStatus(HttpStatus.CREATED.value());
		        	companyPhoneNumberResponse.setMessage("Company Phone Number Saved Successfully");
		        	companyPhoneNumberResponse.setData(companyPhoneNumberData);
		        }
		        else 
		        {
		        	companyPhoneNumberResponse.setMessage("Invalid Company Phone Number");
		        	companyPhoneNumberResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
		        
        responseStrucure.add(companyPhoneNumberResponse);
        
	        ResponseStructure<LinkedList<Employee>> dobResponse = new ResponseStructure<>();
	        LinkedList<Employee> dobData = new LinkedList<>();
	        dobData.add(employee);
		        if ( isValidDateOfBirth(employee.getDob())) 
		        {
		        	dobResponse.setStatus(HttpStatus.CREATED.value());
		        	dobResponse.setMessage("DateOfBirth Saved Successfully");
		        	dobResponse.setData(dobData);
		        	employee.setAge(ageAutoUpdate(employee.getDob()));
		        }
		        else 
		        {
		        	dobResponse.setMessage("Invalid Age and DateOfBirth yyyy-mm-dd");
		        	dobResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
        responseStrucure.add(dobResponse); 
        
        if (emailResponse.getStatus() == HttpStatus.CREATED.value() &&
                nameResponse.getStatus() == HttpStatus.CREATED.value() &&
                addressResponse.getStatus() == HttpStatus.CREATED.value() && 
                dobResponse.getStatus() == HttpStatus.CREATED.value() &&
                employeePhoneNumberResponse.getStatus() == HttpStatus.CREATED.value() && 
                companyPhoneNumberResponse.getStatus()==HttpStatus.CREATED.value())
            {
            	employeeDao.employeeDaoDataSave(employee);
            }
        return responseStrucure;
	}
	
	public ResponseStructure<Employee> employeeServiceFetchById(String employeeUuid)
	{	
		Optional<Employee> optional=employeeRepository.findByEmployeeUuid(employeeUuid);
			if(optional.isPresent())
			{
				
				ResponseStructure<Employee> responsestructure=new ResponseStructure<>();
				responsestructure.setStatus(HttpStatus.FOUND.value());
				responsestructure.setMessage("Data Fetched Sucessfully");
				responsestructure.setData(optional.get());
				return responsestructure;
			}
			else
			{
				ResponseStructure<Employee> responsestructure=new ResponseStructure<>();
				responsestructure.setStatus(HttpStatus.NOT_FOUND.value());
				responsestructure.setMessage("Data not present");
				return responsestructure;
			}
	}
	
	
	public ResponseStructure<List<Employee>> employeeServiceFetchAll()
	{
		ResponseStructure<List<Employee>> responsestructure=new ResponseStructure<List<Employee>>();
		responsestructure.setStatus(HttpStatus.FOUND.value());
		responsestructure.setMessage("Data FetchedAll Sucessfully");
		responsestructure.setData(employeeDao.employeeDaoDataFetchAll());
		return responsestructure;
	}
	
	public ResponseStructure<Employee> employeeServiceDataDeleteById(Employee employee,String employeeUuid)
	{
	Optional<Employee> optional=employeeRepository.findByEmployeeUuid(employeeUuid);
	ResponseStructure<Employee> responseStructure=new ResponseStructure<>();
		if(optional.isPresent())
		{
			
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Data Deleted Sucessfully");
			responseStructure.setData(optional.get());
			employeeDao.employeeDaoDataDeleteById(employeeUuid);
			return responseStructure;
		}
		else 
		{
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Data is Not Present");
			return responseStructure;
		}
	}
	
	public List<ResponseStructure<LinkedList<Employee>>> employeeServiceDataupdate(Employee employee,String employeeUuid)
	{
	Optional<Employee> optional=employeeRepository.findByEmployeeUuid(employeeUuid);
	List<ResponseStructure<LinkedList<Employee>>> responseStructure = new LinkedList<>();

		if(optional.isPresent())
		{
			employee.setEmployeeUuid(employeeUuid);
	        LinkedList<Employee> emailData = new LinkedList<>();
	        emailData.add(employee);
	        
	        ResponseStructure<LinkedList<Employee>> emailResponse = new ResponseStructure<>();
	        
		        if (validEmail(employee.getEmail())) 
		        {
		            emailResponse.setStatus(HttpStatus.CREATED.value());
		            emailResponse.setMessage("Email updated successfully");
		            emailResponse.setData(emailData);
		        }
		        else 
		        {
		            emailResponse.setMessage("Invalid email address");
		            emailResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
		
	responseStructure.add(emailResponse);
	
	        ResponseStructure<LinkedList<Employee>> nameResponse = new ResponseStructure<>();
	        LinkedList<Employee> nameData = new LinkedList<>();
	        nameData.add(employee);
	        
		        if (validName(employee.getEmployeeName())) 
		        {
		            employee.setEmployeeName(employee.getEmployeeName());
		            nameResponse.setStatus(HttpStatus.CREATED.value());
		            nameResponse.setMessage("Name updated successfully");
		            nameResponse.setData(nameData);
		        } 
		        else 
		        {
		            nameResponse.setMessage("Invalid name: Special characters or null values not accepted");
		            nameResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
	
	responseStructure.add(nameResponse);
	
	        ResponseStructure<LinkedList<Employee>> addressResponse = new ResponseStructure<>();
	        LinkedList<Employee> addressData = new LinkedList<>();
	        addressData.add(employee);
	        
		        if (!employee.getEmployeeAddress().getCity().isEmpty()) 
		        {
		            addressResponse.setStatus(HttpStatus.CREATED.value());
		            addressResponse.setMessage("Address updated successfully");
		            addressResponse.setData(addressData);
		        } 
		        else
		        {
		            addressResponse.setMessage("City is mandatory");
		            addressResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
	
	responseStructure.add(addressResponse);
	        
	        ResponseStructure<LinkedList<Employee>> employeePhoneNumberResponse = new ResponseStructure<>();
	        LinkedList<Employee> employeePhoneNumberData = new LinkedList<>();
	        employeePhoneNumberData.add(employee);
		        if (validPhoneNumber(employee.getPhoneNumber())) 
		        {
		        	employeePhoneNumberResponse.setStatus(HttpStatus.CREATED.value());
		        	employeePhoneNumberResponse.setMessage("Employee Phone Number Updated Successfully");
		        	employeePhoneNumberResponse.setData(employeePhoneNumberData);
		        } 
		        else 
		        {
		        	employeePhoneNumberResponse.setMessage("Invalid Employee Phone Number");
		        	employeePhoneNumberResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
		        
	responseStructure.add(employeePhoneNumberResponse);
	
	        
	        ResponseStructure<LinkedList<Employee>> companyPhoneNumberResponse = new ResponseStructure<>();
	        LinkedList<Employee> cmployeePhoneNumberData = new LinkedList<>();
	        employeePhoneNumberData.add(employee);
		        if (validPhoneNumber( employee.getDepartment().getCompany().getPhoneNumber())) 
		        {
		        	companyPhoneNumberResponse.setStatus(HttpStatus.CREATED.value());
		        	companyPhoneNumberResponse.setMessage("Company Phone Number Updated Successfully");
		        	companyPhoneNumberResponse.setData(employeePhoneNumberData);
		        } 
		        else 
		        {
		        	companyPhoneNumberResponse.setMessage("Invalid Company Phone Number");
		        	companyPhoneNumberResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
	
	responseStructure.add(companyPhoneNumberResponse);
	     
	        ResponseStructure<LinkedList<Employee>> dobResponse = new ResponseStructure<>();
	        LinkedList<Employee> dobData = new LinkedList<>();
	        dobData.add(employee);
		        if (isValidDateOfBirth(employee.getDob())) 
		        {
		        	dobResponse.setStatus(HttpStatus.CREATED.value());
		        	dobResponse.setMessage("DateOfBirth updated Successfully");
		        	dobResponse.setData(dobData);
		        	employee.setAge(ageAutoUpdate(employee.getDob()));
		        }
		        else 
		        {
		        	dobResponse.setMessage("Invalid Age & dateOfBirth yyyy-mm-dd");
		        	dobResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		        }
	
	responseStructure.add(dobResponse);   
		if (emailResponse.getStatus() == HttpStatus.CREATED.value() &&
	            nameResponse.getStatus() == HttpStatus.CREATED.value() &&
	            addressResponse.getStatus() == HttpStatus.CREATED.value() && 
	            dobResponse.getStatus() == HttpStatus.CREATED.value() &&
	            employeePhoneNumberResponse.getStatus() == HttpStatus.CREATED.value() &&
	            companyPhoneNumberResponse.getStatus()==HttpStatus.CREATED.value())
	        {
	        	employeeDao.employeeDaoDataSave(employee);
	        }

		return responseStructure;
	}
		
	else 
		{
			ResponseStructure<LinkedList<Employee>> DataNotFound = new ResponseStructure<>();
			DataNotFound.setStatus(HttpStatus.NOT_FOUND.value());
			DataNotFound.setMessage("Data is Not Present");
			responseStructure.add(DataNotFound);
			return responseStructure;
		}
	}
	
//	   public List<Employee> getEmployeesByDepartmentName(String departmentName)
//	   {
//	        return employeeRepository.findByDepartmentDepartmentName(departmentName);
//	   }
//
//	    public List<Employee> getEmployeesByCompanyName(String companyName) {
//	        Company company = companyRepository.findByCompanyName(companyName);
//	        if (company != null) {
//	            List<Department> departments = company.getDepartments();
//	            List<Employee> employees = departments.stream()
//	                                                  .flatMap(department -> department.getEmployee().stream())
//	                                                  .toList();
//	            return employees;
//	        }
//	        return List.of();
//	    }
	
	public boolean validEmail(String email) 
	{
	    if (email == null || email.isEmpty()) 
	    {
	        return false;
	    }
	    
	    String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(emailPattern);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}

	public boolean validName(String name)
	{
	    if (name != null && !name.trim().isEmpty()) 
	    {
	        String nameRegex = "^[A-Za-z\\d\\s]+$";
	        Pattern pattern = Pattern.compile(nameRegex);
	        Matcher matcher = pattern.matcher(name);
	        return matcher.matches();
	    } 
	    else 
	    {
	        return false;
	    }
	}

	public boolean validPhoneNumber(String phoneNumber) 
	{
	    if (phoneNumber != null && !phoneNumber.trim().isEmpty() && phoneNumber.length() == 10) 
	    {
	    	String PHONE_NUMBER_PATTERN = "^[0-9]{10}$";
	        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
	        Matcher matcher = pattern.matcher(phoneNumber);
	        return matcher.matches();
	    }
	    return false;
	}

    
	public boolean isValidDateOfBirth(LocalDate dob) 
	{
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
	    if (dob != null) 
	    {
	        try 
	        {
	        	String dateString = dob.format(formatter);
		        LocalDate parsedDate = LocalDate.parse(dateString, formatter);
		        return true;
	        } 
	        catch (DateTimeParseException e) 
	        {
	            return false;
	        }
	    }
	    return false;
	}
    
	public int ageAutoUpdate(LocalDate dob) 
	{
	    if (dob == null) 
	    {
	        throw new IllegalArgumentException("Date of birth cannot be null");
	    }
	    
	    LocalDate today = LocalDate.now();
	    return Period.between(dob, today).getYears();
	}
    
}
