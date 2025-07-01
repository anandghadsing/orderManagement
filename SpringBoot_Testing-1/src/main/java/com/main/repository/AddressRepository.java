package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>
{

}
