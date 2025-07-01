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

import com.main.model.Address;
import com.main.service.AddressService;


@RestController
@RequestMapping("/api/addresses")
public class AddressController 
{
	    @Autowired
	    private AddressService addressService;

	    @PostMapping
	    public Address create(@RequestBody Address address) {
	        return addressService.create(address);
	    }

	    @PutMapping("/{id}")
	    public Address update(@PathVariable Long id, @RequestBody Address address) {
	        return addressService.update(id, address);
	    }

	    @GetMapping("/{id}")
	    public Address getById(@PathVariable Long id) {
	        return addressService.getById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        addressService.delete(id);
	    }

	    @GetMapping
	    public List<Address> getAll() {
	        return addressService.getAll();
	    }
	}