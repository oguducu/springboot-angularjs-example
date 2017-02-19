package com.orcunguducu.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunguducu.dynamic.domain.Address;

/**
 * Spring Data JPA repository for the Address entity.
 */
public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
