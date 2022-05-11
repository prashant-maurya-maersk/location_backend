package com.maersk.ops.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {

}
