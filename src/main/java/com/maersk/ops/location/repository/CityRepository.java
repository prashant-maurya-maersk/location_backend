package com.maersk.ops.location.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

	
}
