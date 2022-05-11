package com.maersk.ops.location.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.BDAType;

@Repository
public interface BdaTypeRepo extends CrudRepository<BDAType, Long> {

}
