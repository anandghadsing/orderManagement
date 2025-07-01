package com.main.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.exception.ResourceNotFoundException;
import com.main.model.Company;
import com.main.repository.CompanyRepository;
import com.main.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	    @Autowired
	    private CompanyRepository companyRepository;

	    @Override
	    public Company create(Company company) {
	        return companyRepository.save(company);
	    }

	    @Override
	    public Company update(Long id, Company company) {
	        Company existing = getById(id);
	        existing.setName(company.getName());
	        existing.setIndustry(company.getIndustry());
	        return companyRepository.save(existing);
	    }

	    @Override
	    public Company getById(Long id) {
	        return companyRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
	    }

	    @Override
	    public void delete(Long id) {
	        companyRepository.delete(getById(id));
	    }

	    @Override
	    public List<Company> getAll() {
	        return companyRepository.findAll();
	    }
	}
