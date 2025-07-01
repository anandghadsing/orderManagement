package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>
{

}
