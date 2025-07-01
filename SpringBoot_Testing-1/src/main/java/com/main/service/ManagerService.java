package com.main.service;

import java.util.List;

import com.main.model.Manager;

public interface ManagerService 
{
	    Manager create(Manager manager);
	    Manager update(Long id, Manager manager);
	    Manager getById(Long id);
	    void delete(Long id);
	    List<Manager> getAll();
}
