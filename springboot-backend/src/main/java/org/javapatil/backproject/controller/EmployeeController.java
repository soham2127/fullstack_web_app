package org.javapatil.backproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javapatil.backproject.exception.ResourceNotFoundException;
import org.javapatil.backproject.model.Employee;
import org.javapatil.backproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeerepository;
	
	//get all employees
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeerepository.findAll();
	}
	
	//create employee rest ap 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeerepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	//get employee by id api
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		
		Employee employee=employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	@CrossOrigin
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeedetails){
		
		Employee employee=employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+id));
		
		employee.setFirstName(employeedetails.getFirstName());
		employee.setLastName(employeedetails.getLastName());
		employee.setEmailId(employeedetails.getEmailId());
		
	 	Employee updatedEmployee=employeerepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
		
	}
	@DeleteMapping("/employees/{id}")
	@CrossOrigin
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id){
		
		Employee employee=employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+id));
		
		employeerepository.delete(employee);
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
}
