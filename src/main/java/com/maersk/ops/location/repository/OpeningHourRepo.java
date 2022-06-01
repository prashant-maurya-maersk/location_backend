package com.maersk.ops.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.FacilityOpeningHours;

@Repository
public interface OpeningHourRepo extends JpaRepository<FacilityOpeningHours, Long> {
	List<FacilityOpeningHours> findByEntityIdAndEntityTypeId(String entityId, EntityType entityTypeId);
}
