package com.maersk.ops.location.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.OlsonTimezone;

@Repository
public interface OlsonTimeRepo extends CrudRepository<OlsonTimezone, Long> {

}
