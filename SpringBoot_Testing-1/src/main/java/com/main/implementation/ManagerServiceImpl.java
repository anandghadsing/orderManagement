package com.main.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.exception.ResourceNotFoundException;
import com.main.model.Manager;
import com.main.repository.ManagerRepository;
import com.main.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService
{
	    @Autowired 
	    private ManagerRepository managerRepository;

	    @Override
	    public Manager create(Manager manager) {
	        return managerRepository.save(manager);
	    }

	    @Override
	    public Manager update(Long id, Manager manager) {
	        Manager existing = getById(id);
	        existing.setName(manager.getName());
	        existing.setDepartment(manager.getDepartment());
	        return managerRepository.save(existing);
	    }

	    @Override
	    public Manager getById(Long id) {
	        return managerRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
	    }

	    @Override
	    public void delete(Long id) {
	        managerRepository.delete(getById(id));
	    }

	    @Override
	    public List<Manager> getAll() {
	        return managerRepository.findAll();
	    }
}
