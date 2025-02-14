package com.namankhurana.springboot.traini8.dao;

import com.namankhurana.springboot.traini8.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
