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

import com.main.model.Manager;
import com.main.service.ManagerService;

@RestController
@RequestMapping("api/managers")
public class ManagerController 
{
	
	@Autowired
	    private ManagerService managerService;

	    @PostMapping
	    public Manager create(@RequestBody Manager manager) {
	        return managerService.create(manager);
	    }

	    @PutMapping("/{id}")
	    public Manager update(@PathVariable Long id, @RequestBody Manager manager) {
	        return managerService.update(id, manager);
	    }

	    @GetMapping("/{id}")
	    public Manager getById(@PathVariable Long id) {
	        return managerService.getById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        managerService.delete(id);
	    }

	    @GetMapping
	    public List<Manager> getAll() {
	        return managerService.getAll();
	    }
	}