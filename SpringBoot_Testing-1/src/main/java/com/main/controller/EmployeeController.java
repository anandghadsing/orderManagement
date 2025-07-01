package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Employee;
import com.main.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController 
{

	    @Autowired
	    private EmployeeService employeeService;

	    @PostMapping
	    public Employee create(@RequestBody Employee employee) {
	        return employeeService.create(employee);
	    }

	    @PutMapping("/{id}")
	    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
	        return employeeService.update(id, employee);
	    }

	    @GetMapping("/{id}")
	    public Employee getById(@PathVariable Long id) {
	        return employeeService.getById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        employeeService.delete(id);
	    }

	    @GetMapping
	    public List<Employee> getAll(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(required = false) Long managerId,
	            @RequestParam(required = false) Long companyId
	    ) {
	        return employeeService.getAll(page, size, managerId, companyId);
	    }
	}