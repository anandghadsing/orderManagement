package com.main.service;

import java.util.List;

import com.main.model.Company;

public interface CompanyService 
{
	    Company create(Company company);
	    Company update(Long id, Company company);
	    Company getById(Long id);
	    void delete(Long id);
	    List<Company> getAll();
}
