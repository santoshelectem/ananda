/**
 * 
 */
package com.example.postgresdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.repository.EmployeeRepository;
import com.example.postgresdemo.service.EmployeService;

/**
 * @author Cybertech1
 *
 */
@RestController
public class EmployeeController {
	/**
	 * Logger
	 */
	private Logger log = LoggerFactory.getLogger(EmployeeController.class);
	/**
	 * employeeRepository
	 */
	@Autowired
	private EmployeeRepository employRepostry;

	/**
	 * EmployeeController
	 */
	@Autowired
	private EmployeService employeService;

	/**
	 * @param employee
	 * @return
	 */
	@PostMapping("/employee")
	public Employee saveEmployee(final @Valid @RequestBody Employee employee) {
		log.info("Start of EmployeeController :: saveEmployee ");
		Employee employe = null;
		try {
			if (employee != null) {
				employe = employRepostry.save(employee);
			}

		} catch (Exception e) {
			log.error("EmployeeController :: saveEmployee" + e.getMessage());
			// TODO: handle exception
		}
		log.info("end of EmployeeController :: saveEmployee ");
		return employe;

	}

	/**
	 * fetch recod based on metric name and panelId
	 */
	@GetMapping("/employee")
	public List<Employee> fetchEmployee() {
		log.info("Start of EmployeeController :: fetchEmployee ");
		List<Employee> employee = null;
		try {

			employee = employeService.fetchAllEmployees();

		} catch (ResourceNotFoundException e) {
			log.error("EmployeeController :: fetchEmployee" + e.getMessage());
		}
		log.info("end of EmployeeController :: fetchEmployee ");
		return employee;
	}

	/**
	 * @param employeeRepository
	 * @param employeService
	 */
	public EmployeeController(final EmployeeRepository employRepstry, final EmployeService employeService) {
		super();
		this.employRepostry = employRepstry;
		this.employeService = employeService;
	}

	/**
	 * @param employeeId
	 * @return
	 */
	// fetch the data from database by employee Id
	@GetMapping("/employee/{employeeId}")
	public Employee fetchEmployeeId(final @PathVariable Long employeeId) {
		log.info("Start of EmployeeController :: fetchEmployeeId ");
		Employee employee = null;
		try {
			employee = employeService.fetchEmployeesById(employeeId);
		} catch (ResourceNotFoundException e) {
			log.error("EmployeeController :: fetchEmployeeId" + e.getMessage());
		}
		log.info("end of EmployeeController :: fetchEmployeeId ");
		return employee;
	}

	/**
	 * @param employeeId
	 * @return
	 */
	// delete data by id
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<HttpStatus> deleteEmployee(final @PathVariable long employeeId) {
		log.info("Start of EmployeeController :: deleteEmployee ");
		final Employee employee = employRepostry.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + employeeId));

		employRepostry.delete(employee);
		log.info("end of EmployeeController :: deleteEmployee ");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// build update employee REST API
	/**
	 * @param employeeId
	 * @param employeeDetails
	 * @return
	 */
	@PutMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(final @PathVariable long employeeId,
			final @RequestBody Employee employeeDetails) {
		log.info("Start of EmployeeController :: updateEmployee ");
		final Employee updateEmployee = employRepostry.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + employeeId));
		updateEmployee.setFirstName(employeeDetails.getFirstName());
		updateEmployee.setLastName(employeeDetails.getLastName());
		updateEmployee.setEmailId(employeeDetails.getEmailId());

		employRepostry.save(updateEmployee);
		log.info("end of EmployeeController :: updateEmployee ");
		return ResponseEntity.ok(updateEmployee);
	}

}
