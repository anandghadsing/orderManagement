package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	 	List<Employee> findByManagerId(Long managerId);
	    List<Employee> findByCompanyId(Long companyId);
	    boolean existsByEmail(String email);
}
