package com.main.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.exception.ResourceNotFoundException;
import com.main.model.Address;
import com.main.model.Company;
import com.main.model.Employee;
import com.main.model.Manager;
import com.main.repository.AddressRepository;
import com.main.repository.CompanyRepository;
import com.main.repository.EmployeeRepository;
import com.main.repository.ManagerRepository;
import com.main.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	    @Autowired
	    private EmployeeRepository employeeRepository;
	    @Autowired 
	    private ManagerRepository managerRepository;
	    @Autowired 
	    private CompanyRepository companyRepository;
	    @Autowired
	    private AddressRepository addressRepository;

	    @Override
	    public Employee create(Employee employee) {
	        validateRelations(employee);
	        return employeeRepository.save(employee);
	    }

	    @Override
	    public Employee update(Long id, Employee newEmployee) {
	        Employee existing = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
	        
	        existing.setName(newEmployee.getName());
	        existing.setEmail(newEmployee.getEmail());
	        existing.setDesignation(newEmployee.getDesignation());
	        existing.setAddress(newEmployee.getAddress());

	        if (newEmployee.getManager() != null) {
	            Long managerId = newEmployee.getManager().getId();
	            Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
	            existing.setManager(manager);
	        }

	        if (newEmployee.getCompany() != null) {
	            Long companyId = newEmployee.getCompany().getId();
	            Company company = companyRepository.findById(companyId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
	            existing.setCompany(company);
	        }

	        return employeeRepository.save(existing);
	    }

	    @Override
	    public Employee getById(Long id) {
	        return employeeRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
	    }

	    @Override
	    public void delete(Long id) {
	        Employee emp = getById(id);
	        employeeRepository.delete(emp);
	    }

	    @Override
	    public List<Employee> getAll(int page, int size, Long managerId, Long companyId) {
	         if (managerId != null) {
	            return employeeRepository.findByManagerId(managerId);
	        } else if (companyId != null) {
	            return employeeRepository.findByCompanyId(companyId);
	        } else {
	            return employeeRepository.findAll();
	        }
	    }

	    private void validateRelations(Employee employee) {
	        if (employee.getManager() != null) {
	            Long managerId = employee.getManager().getId();
	            Manager manager = managerRepository.findById(managerId)
	                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
	            employee.setManager(manager);
	        }

	        if (employee.getCompany() != null) {
	            Long companyId = employee.getCompany().getId();
	            Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
	            employee.setCompany(company);
	        }
	        if (employee.getAddress() != null) {
	            Long addressId = employee.getAddress().getId();
	            Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	            employee.setAddress(address);
	        }
	    }
	}