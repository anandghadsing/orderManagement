package com.main.service;

import java.util.List;

import com.main.model.Address;

public interface AddressService 
{
	    Address create(Address address);
	    Address update(Long id, Address address);
	    Address getById(Long id);
	    void delete(Long id);
	    List<Address> getAll();
}
