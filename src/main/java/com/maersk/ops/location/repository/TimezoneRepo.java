package com.maersk.ops.location.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.Timezone;

@Repository
public interface TimezoneRepo extends CrudRepository<Timezone, Long> {

}
