package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.FacilityServiceRelation;

@Repository
public interface FacilityServiceRelationRepo extends JpaRepository<FacilityServiceRelation, Long> {
	List<FacilityServiceRelation> findByEntityIdAndEntityTypeId(String entityId, EntityType entityTypeId);
}
