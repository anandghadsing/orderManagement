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
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Company;
import com.main.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController 
{
	
	    @Autowired
	    private CompanyService companyService;

	    @PostMapping
	    public Company create(@RequestBody Company company) {
	        return companyService.create(company);
	    }

	    @PutMapping("/{id}")
	    public Company update(@PathVariable Long id, @RequestBody Company company) {
	        return companyService.update(id, company);
	    }

	    @GetMapping("/{id}")
	    public Company getById(@PathVariable Long id) {
	        return companyService.getById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        companyService.delete(id);
	    }

	    @GetMapping
	    public List<Company> getAll() {
	        return companyService.getAll();
	    }
	}
