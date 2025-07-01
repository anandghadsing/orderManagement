package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>
{
	
}
