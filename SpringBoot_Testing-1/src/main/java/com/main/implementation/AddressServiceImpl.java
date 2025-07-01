package com.main.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.exception.ResourceNotFoundException;
import com.main.model.Address;
import com.main.repository.AddressRepository;
import com.main.service.AddressService;

@Service
public class AddressServiceImpl  implements AddressService
{
	    @Autowired
	    private AddressRepository addressRepository;

	    @Override
	    public Address create(Address address) {
	        return addressRepository.save(address);
	    }
	    
	    @Override
	    public Address update(Long id, Address address) {
	        Address existing = getById(id);
	        existing.setStreet(address.getStreet());
	        existing.setCity(address.getCity());
	        existing.setState(address.getState());
	        existing.setZipCode(address.getZipCode());
	        return addressRepository.save(existing);
	    }

	    @Override
	    public Address getById(Long id) {
	        return addressRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	    }

	    @Override
	    public void delete(Long id) {
	        addressRepository.delete(getById(id));
	    }

	    @Override
	    public List<Address> getAll() {
	        return addressRepository.findAll();
	    }
}
