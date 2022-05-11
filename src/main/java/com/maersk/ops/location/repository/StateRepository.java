package com.maersk.ops.location.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.State;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {

}
