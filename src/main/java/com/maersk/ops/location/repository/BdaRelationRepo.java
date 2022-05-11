package com.maersk.ops.location.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.BdaRelation;

@Repository
public interface BdaRelationRepo extends CrudRepository<BdaRelation, Long> {

}
