package com.commons.nirakar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commons.nirakar.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
