package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.BdaRelation;
import com.maersk.ops.location.model.EntityType;

@Repository
public interface BdaRelationRepo extends CrudRepository<BdaRelation, Long> {
	List<BdaRelation> findByEntityIdAndEntityTypeId(String entityId, EntityType entityTypeId);
}
