package com.main.service;

import java.util.List;

import com.main.model.Employee;

public interface EmployeeService 
{
	    Employee create(Employee employee);
	    Employee update(Long id, Employee employee);
	    Employee getById(Long id);
	    void delete(Long id);
	    List<Employee> getAll(int page, int size, Long managerId, Long companyId);
}

